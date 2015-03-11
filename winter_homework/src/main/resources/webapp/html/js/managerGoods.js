var select;
var commodities;
var flag=1;
$(document).ready(function(){
    var admin=getCookie("admin");
    if(admin==null){
        $(".body").empty();
        alert("您无法访问此页面");
        document.location.href="index.html";
    }
    var id=getCookie("userId");
    getGoods(id);
    getUserName();
});

function getUserName(){
    $(".name").html(getCookie("name"));
    $("#index_name").click(
    function(){
        $("#logout").show();
    });
 }

function getGoods(){
    $.ajax({
            type:"POST",
            url:"/commodity/getCommodities",
            contentType:"application/json",
            success:function(result){
            commodities=result;
            select=new Array(_.size(result));
            dataToView(result);
            }
    });
}

function dataToView(data){
    $("#all_unit").empty();
    $("#name span").html(_.size(data));
    _.chain(data).map(function(goods,ind) {
        return diskDiv(goods,ind);
    }).each(function(div) {
    $("#all_unit").append(div);
    });
}

function diskDiv(goods,ind) {
    return $("<div>")
    .attr("class", "unit")
    .append(selectBox(goods.id,ind))
    .append(goodsImg(goods.imgUrl))
    .append(goodsDesc(goods.name,goods.comDesc))
    .append(goodsPrice(goods.newPrice,goods.oldPrice))
    .append(goodsStock(goods.stock))
    .append(goodsSalesVolume(goods.salesVolume))
    .append(goodsPublishDate(goods.publishDate))
    .append(ModifyGoods(ind,goods));
}

function selectBox(id,ind){
    return $("<input>").attr("type","checkbox").attr("class","singleSelect")
    .click(function(){
        if(this.checked){
            select[ind]=id;
            }
        else{
            select[ind]=0;}
    });
}

function goodsImg(imgUrl){
    return $("<img>").attr("src",imgUrl);
}

function goodsPrice(newPrice,oldPrice){
    return $("<div>").attr("class","ManagerPrice").html(newPrice+"  "+oldPrice);
}
function goodsPriceModify(newPrice,oldPrice){
  return $("<div>").attr("class","ManagerPrice")
    .append($("<input>")
        .attr("id","modifyOldPrice")
        .attr("placeholder",oldPrice))
    .append($("<input>")
        .attr("id","modifyNewPrice")
        .attr("placeholder",newPrice));
}

function goodsDesc(name,comDesc){
    return $("<div>").attr("class","ManagerDesc").html(name+comDesc);
}
function goodsDescModify(name,comDesc){
    return $("<div>").attr("class","ManagerDesc")
    .append($("<textarea>")
        .attr("id","modifyDesc")
        .attr("placeholder",name+comDesc));
}

function goodsStock(stock){
    return $("<div>").attr("class","ManagerStock").html(stock);
}

function goodsStockModify(stock){
    return $("<div>").attr("class","ManagerStock")
    .append($("<input>")
        .attr("id","modifyStock")
        .attr("placeholder",stock));
}
function goodsSalesVolume(salesVolume){
    return $("<div>").attr("class","ManagerSaleVolume").html(salesVolume);
}

function goodsPublishDate(publishDate){
    var curDate=new Date(publishDate);
    var date=new Date(curDate.getTime()-24*60*60*1000);
    var m=date.getMonth()+1;
    var d=date.getDate();
    var min=date.getMinutes();
    if(min<10)
    min="0"+min;
    return $("<div>").attr("class","ManagerDate")
    .append($("<div>").attr("class","yearMonthDay").html(date.getFullYear()+"-"+m+"-"+d))
    .append($("<div>").attr("class","HourMin").html(date.getHours()+":"+min));
}

function ModifyGoods(ind,goods){
    return $("<div>").attr("class","ManagerModify").html("编辑宝贝")
    .click(function(){
    doModify(ind,goods);
    });
}
function doModify(ind,goods){
$(".unit").eq(ind).empty();
$(".unit").eq(ind)
 .append(selectBox(goods.id,ind))
    .append(goodsImg(goods.imgUrl))
    .append(goodsDescModify(goods.name,goods.comDesc))
    .append(goodsPriceModify(goods.newPrice,goods.oldPrice))
    .append(goodsStockModify(goods.stock))
    .append(goodsSalesVolume(goods.salesVolume))
    .append(goodsPublishDate(goods.publishDate))
    .append(choiceButton(goods,ind));
}
function choiceButton(goods,ind){
    return $("<div>").attr("class","ManagerModify")
    .append($("<input>").attr("type","button").attr("value","×").attr("class","choice_button").click(function(){
     $(".unit").eq(ind).replaceWith(diskDiv(goods,ind));
    }))
    .append($("<input>").attr("type","button").attr("value","√").attr("class","choice_button").click(function(){
    ajaxModify(goods,ind);
    }));
}
function ajaxModify(goods,ind){
var g={
      id:goods.id,
      comDesc:$("#modifyDesc").val(),
      oldPrice:$("#modifyOldPrice").val(),
      newPrice:$("#modifyNewPrice").val(),
      stock:$("#modifyStock").val(),
      }
 $.ajax({
             type:"POST",
             url:"/commodity/modify",
             data:JSON.stringify(g),
             contentType:"application/json",
              success:function(result){
               $(".unit").eq(ind).replaceWith(diskDiv(result,ind));
              }
             });

}
function orderByPrice(){
    flag=-flag;
    if(flag>0)
        $(".a_2").attr("id","up");
    else
        $(".a_2").attr("id","down");
    dataToView(_.sortBy(commodities,function(c){return flag*c.newPrice;}));
}

function orderByStock(){
    flag=-flag;
    if(flag>0)
        $(".a_3").attr("id","up");
    else
        $(".a_3").attr("id","down");
    dataToView(_.sortBy(commodities,function(c){return flag*c.stock;}));
}

function orderBySalesVolume(){
    flag=-flag;
    if(flag>0)
        $(".a_4").attr("id","up");
    else
        $(".a_4").attr("id","down");
    dataToView(_.sortBy(commodities,function(c){return flag*c.salesVolume;}));
}

function orderByDate(){
    flag=-flag;
    if(flag>0)
        $(".a_5").attr("id","up");
    else
        $(".a_5").attr("id","down");

    dataToView(_.sortBy(commodities,function(c){
    return flag*new Date(c.publishDate).getTime();
    }));
}

function search(){
    var keyWord=$("#serach_goods").val();
    dataToView(_.filter(commodities,function(c){
    return (c.name+c.comDesc).indexOf(keyWord)>=0;
    }));
}
function selectAll(){
    $(".singleSelect").click();
}

function deleteGoods(){
  select= _.filter(select,function(id){
  return id>0
  });
    $.ajax({
             type:"POST",
             url:"/commodity/delMutliCommodity",
             data:JSON.stringify(select),
             contentType:"application/json",
              success:function(result){
              dataToView(result);
              }
             });
}
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
    $(".user_info").html(getCookie("name"));
    $(".name").html(getCookie("name"));
    $("#index_name").click(
    function(){
        $(".logout").show();
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
    .append(ModifyGoods(goods.id));
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

function goodsDesc(name,comDesc){
    return $("<div>").attr("class","ManagerDesc").html(name+comDesc);
}

function goodsStock(stock){
    return $("<div>").attr("class","ManagerStock").html(stock);
}

function goodsSalesVolume(salesVolume){
    return $("<div>").attr("class","ManagerSaleVolume").html(salesVolume);
}

function goodsPublishDate(publishDate){
    return $("<div>").attr("class","ManagerDate").html(publishDate);
}

function ModifyGoods(id){
    return $("<div>").attr("class","ManagerModify").html("编辑宝贝");
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
    dataToView(_.sortBy(commodities,function(c){return flag*c.publishDate;}));
}

function search(){
    var keyWord=$("#serach_goods").val();
    dataToView(_.filter(commodities,function(c){
    return c.name.indexOf(keyWord)>=0;
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
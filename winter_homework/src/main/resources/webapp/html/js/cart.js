$(document).ready(function() {
    getCart();
});

function getCart(){
     $.ajax({
     type:"POST",
     url:"/cart",
     data:JSON.stringify(getCookie("userId")),
     contentType:"application/json",
     success:function(result){
        dataToView(result);
     }
     });
}

function dataToView(data){
$("#table_content").empty();
  _.chain(data).map(function(s,ind){
  var cid=s.commodityId;
    $.ajax({
       type:"POST",
       url:"/commodity/getCommodityById",
       data:JSON.stringify(cid),
       contentType:"application/json",
       success:function(result){
        var div=diskDiv(result,ind,s);
        $("#table_content").append(div);
       }
       });
  });
}
function diskDiv(goods,ind,s){
return  $("<div>")
    .attr("class","info_detail")
    .append(selectBox(s.id))
    .append(goodsInfo(goods.imgUrl,goods.name))
    .append(goodsPrice(goods.newPrice,goods.oldPrice))
    .append(goodsCount(s.num))
    .append(unitSum(goods.newPrice*s.num))
    .append(option(ind,s.id));
}

function selectBox(id){
return $("<div>").attr("class","select")
.append($("<input>").attr("type","checkbox"));
}

function goodsInfo(url,name){
return $("<div>")
.attr("class","goods_info")
.append(descLeft(url))
.append(descRight(name));
}

function descLeft(url){
return $("<div>")
.attr("class","desc_left")
.append($("<img>").attr("src", url).attr("class","cartGoodsImg"));
}
function descRight(name){
return $("<div>")
.attr("class","desc_right")
.append($("<div>").attr("class", "desc_text").html(name))
.append(serviceLogo());
}
function serviceLogo(){
return $("<div>")
.attr("class","service_logo")
.append($("<img>").attr("src", "imgs/login/1.png"))
.append($("<img>").attr("src", "imgs/login/2.png"));
}

function goodsPrice(newPrice,oldPrice){
return $("<div>").attr("class","unit_price")
.append($("<div>").attr("class","old_price").html(oldPrice))
.append($("<div>").attr("class","new_price").html(newPrice));
}
function goodsCount(count){
return $("<div>").attr("class","count").html(count);
}

function unitSum(sum){
return $("<div>").attr("class","unit_sum").html(sum);
}
function option(ind,id){
return $("<div>").attr("class","operation").html("删除");
}
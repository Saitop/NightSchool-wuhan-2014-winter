$(document).ready(function() {
getGoods();
cookieLogin();
});
function cookieLogin(){
var name=getCookie("name");
var password=getCookie("password");
var admin=getCookie("admin");
var u={
    userName:name,
    password:password
    };
  if(name!=null){
      if(admin!=null){
        doCookieAdminLogin();
      }
      else{
        doCookieLogin();
        }
    }
  else{
    visterView();
  }
}
function doCookieLogin(){
  webUserView();
}
function doCookieAdminLogin(){
  adminView();
}
function adminView(){
  $("#index_name").html("Admin");
}
function webUserView(){
  $("#index_name").html("webUser");
  $("#index_manager").hide();
}
function visterView(){
  $("#index_name").html("please login");
  $("#index_cart").hide();
  $("#index_manager").hide();
}

function getGoods(){
  $.post("/commodity/getCommodities",function(result){
   console.log(result);
   dataToView(result);
    });
}
function dataToView(data){
$("#allgoods").empty();
  _.chain(data).map(function(goods,ind) {
    return diskDiv(goods,ind);
  }).each(function(div) {
   $("#allgoods").append(div);
});
}

function diskDiv(goods,ind) {
  return $("<div>")
    .attr("class", "goods")
    .append(goodsImg(goods.imgUrl))
    .append(goodsPrice(goods.newPrice))
    .append(goodsTip(goods.comDesc))
    .append(goodsName(goods.name))
    .append(addToCartButton(ind,goods.id));
}
function goodsImg(url) {
  return $("<img>")
 .attr("class","indexGoodsImg")
  .attr("src", url);
}

function goodsPrice(price){
return $("<div>").attr("class","new_price small_block theme_color").html(price);
}
function goodsTip(desc){
return $("<div>").attr("class","tip small_block theme_color_bg").html("保");
}
function goodsName(name){
return $("<div>").attr("class","name").html(name);
}
function addToCartButton(ind,id){
return $("<button>").html("加入购物车").attr("class","theme_button add_button")
    .click(function(){
      add(ind,id);
    });
}
function add(ind,id){
console.log("id"+id);
 var newP= {
   buyerId: getCookie("userId"),
   commodityId:id,
   num: 1,
   date:new Date(),
   status:"inCart"
  };

  $.ajax({
              type:"POST",
              url:"/cart/isInCart",
              data:JSON.stringify(newP),
              contentType:"application/json",
              success:function(result){
             if(result==0){
              doAdd(newP);
             }
             else{
              alert("已经存在于购物车，修改数量请到购物车中进行");
             }
            }
          });
}
function doAdd(newP){
$.ajax({
            type:"POST",
            url:"/cart/addToCart",
            data:JSON.stringify(newP),
            contentType:"application/json",
            success:function(result){
            console.log(result);
            }
        });
}
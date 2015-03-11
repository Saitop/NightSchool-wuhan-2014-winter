var cartSize=0;
$(document).ready(function() {
getGoods();
cookieLogin();
});
function showCartSize(){
 $.ajax({
       type:"GET",
       url:"/cart/num/"+getCookie("userId"),
       success:function(num){
         cartSize=num;
          $(".cart span").html(cartSize);
       }
       });
}
function cookieLogin(){
var name=getCookie("name");
var password=getCookie("password");
var admin=getCookie("admin");
var u={
    userName:name,
    password:password
    };
  if(name!=null){
    $(".name").html(getCookie("name"));
    $("#index_name").click(
    function(){
    $(".logout").show();
    });
      if(admin==null){
        $("#index_manager").hide();
        }
        showCartSize();
    }
  else{
    visitorView();
  }
}
function visitorView(){
    $(".option_button").empty();
    $(".option_button")
    .append($("<a>").attr("href","login.html").html("亲,请登录 "))
    .append($("<a>").attr("href","register.html").html(" 免费注册"));
}

function getGoods(){
    $.post("/commodity/getCommodities",function(result){
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
    .append(goodsFreeShipping(goods.freeShipping))
    .append(goodsName(goods.name,goods.comDesc))
    .append(addToCartButton(ind,goods.id));
}
function goodsImg(url) {
    return $("<img>")
    .attr("class","indexGoodsImg")
    .attr("src", url);
}

function goodsPrice(price){
    return $("<div>").attr("class","new_price  theme_color").html("￥"+price);
}
function goodsFreeShipping(freeShipping){
if(freeShipping==true)
    return $("<div>").attr("class","free_shipping theme_color_bg").html("包邮");
}

function goodsName(name,desc){
    return $("<div>").attr("class","goodsDesc").html(name+" "+desc);
}
function addToCartButton(ind,id){
    return $("<button>").html("加入购物车").attr("class","theme_button add_button")
        .click(function(){
        if(getCookie("name")!=null){
            add(ind,id);
            }
        else{
       document.location.href="login.html";
        }
         });
}
function add(ind,id){
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
     showCartSize();
     }
     });
}

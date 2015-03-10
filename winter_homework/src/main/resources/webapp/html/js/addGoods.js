$(document).ready(function() {
    var admin=getCookie("admin");
    console.log(admin);
    if(admin==null){
        $(".body").empty();
        alert("您无法访问此页面");
        document.location.href="index.html";
        }
});

function addImg(){
$("#add_img").click();

}
function uploadImg(){
$("#submit_img").click();
};

function addGoods(){
  $.ajax({
     type:"GET",
     url:"/commodity/upload/fileName",
     contentType:"application/json",
     dataType: "text",
     beforeSend:uploadImg,
     success:function(url){
     if(url){
     $("#imghead").attr("src",url);
     add(getGoods(url));
     }
     else{
     addGoods();
    }}
  })
}
function getGoods(url){
 var g={
      name:$("#input_title").val(),
      comDesc:$("#input_desc_goods").val(),
      imgUrl:url,
      oldPrice:$("#input_old_price").val(),
      newPrice:$("#input_new_price").val(),
      stock:$("#input_stock").val(),
      salesVolume:0,
      creatorId:getCookie("userId"),
      freeShipping:$("#input_free_shipping").is(':checked'),
      sevenDaysReturn:$("#input_seven_days_return").is(':checked')
      }
      console.log("c1"+$("#input_free_shipping").is(':checked'));
      return g;
}
function add(goods){
$.ajax({
          type:"POST",
          url:"/commodity/addCommodity",
          data:JSON.stringify(goods),
          contentType:"application/json",
          beforeSend:getGoods,
          success:function(result){
          alert("发布新的商品成功");
          location.ht
         }
     });
}
function addImg(){
$("#add_img").click();
}
function uploadImg(){
$("#submit_img").click();
};

function addGoods(){
  $.ajax({
     type:"GET",
     url:"/file/upload/fileName",
     contentType:"application/json",
     dataType: "text",
     beforeSend:uploadImg,
     success:function(url){
     if(url){
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
      stock:1,
      salesVolume:1,
      ownerId:1
      }
      return g;
}
function add(goods){
$.ajax({
          type:"POST",
          url:"/file/addCommodity",
          data:JSON.stringify(goods),
          contentType:"application/json",
          beforeSend:getGoods,
          success:function(result){
          alert("发布新的商品成功");
         }
     });
}
var select;
var selectCount=0;
var sid;
$(document).ready(function() {
    getUserName();
    getCart();
});

function getUserName(){
 $(".name").html(getCookie("name")).click(
  function(){
  $("#logout").show();
  });
  if(getCookie("admin")==null){
  $(".goods_manager").hide();
}
}

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
select=new Array(_.size(data));
var cids=_.map(data, function(s){ return s.commodityId; });
 $.ajax({
       type:"POST",
       url:"/commodity/getMutliCommodity",
       data:JSON.stringify(cids),
       contentType:"application/json",
       success:function(result){
       if(result==null){
       alert("购物车为空 请选择商品后再查看");
        document.location.href="index.html";
       }else{
       dataToViewSecond(result,data);
       }
       }
       });
  }
function dataToViewSecond(commodities,data){
$("#cart_all_goods_num").html(_.size(data));
    for(i=0;i<_.size(data);i++){
     var div=diskDiv(commodities[i],i,data[i]);
     $("#table_content").append(div);
    }
}
function diskDiv(goods,ind,s){
return  $("<div>")
    .attr("class","info_detail")
    .append(selectBox(s.id,ind))
    .append(goodsInfo(goods.imgUrl,goods.name))
    .append(goodsPrice(goods.newPrice,goods.oldPrice))
    .append(goodsCount(s.num,ind,s.id,goods.stock))
    .append(unitSum(goods.newPrice*s.num))
    .append(option(ind,s.id));
}

function selectBox(id,ind){
return $("<div>").attr("class","select")
.append($("<input>").attr("type","checkbox").attr("class","checkboxclasss")
.click(function(){
    if(this.checked){
    select[ind]=id;
    ++selectCount;
    }
    else{
    select[ind]=0;
    --selectCount;
    }
      $("#cart_selected_goods_num").html(selectCount);
       updateSelectedPrice();
    })
);
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
.append($("<img>").attr("src", "imgs/main/1.png"))
.append($("<img>").attr("src", "imgs/main/2.png"));
}

function goodsPrice(newPrice,oldPrice){
return $("<div>").attr("class","unit_price")
.append($("<div>").attr("class","old_price").html(oldPrice.toFixed(2)))
.append($("<div>").attr("class","new_price").html(newPrice.toFixed(2)));
}
function goodsCount(count,ind,id,stock){
    var meg;
    if(stock>0&&stock<count){
        meg="剩下的商品不多,客官少买一点还是可以的";
    }
    else if(stock<=0){
        meg="惊呆了 居然已经卖光了";
    }
    else{
        meg="客官 没事你还可以继续增加";
    }
    return $("<div>").attr("class","count")
     .append($("<span>").html(meg))
    .append($("<input>").attr("class","count_num").val(count)
    .change(function(){
    if($(this).val()<=stock){
     $(".count span").eq(ind).html("客官 没事你还可以继续增加");
       modifyNumInCart(id,ind,$(this).val());
       }
       else
       $(".count span").eq(ind).html("客官 商品木有那么多额");
    }));
}

function unitSum(sum){
return $("<div>").attr("class","unit_sum").html(sum);
}
function option(ind,id){
    return $("<div>").attr("class","operation").html("删除")
    .click(function(){
        doDelete(id);
    });
}
function doDelete(id){
 $.ajax({
              type:"POST",
              url:"/cart/delete/"+id,
              contentType:"application/json",
              success:function(result){
             getCart();
              }
        });
}
function doMutilDelete(){
    select= _.filter(select,function(id){return id>0});
    if(select.length==0)
        alert("客官，您未选择任何商品删除！");
    else{
     $.ajax({
           type:"POST",
           url:"/cart/deleteMore/"+getCookie("userId"),
           data:JSON.stringify(select),
           contentType:"application/json",
           success:function(result){
               getCart();
           }
           });
       }
}

function  modifyNumInCart(id,ind,count){
    if(count<=0){
        alert("您输入的数值小于等于零");
         $(".count input").eq(ind).val(1);
         count=1;
        }
        $(".unit_sum").eq(ind+1).html(count* $(".new_price").eq(ind).html());
        doModifyNumInCart(id,count);
}
function updateSelectedPrice(){
    var selectPrice= 0;
    _.each(select,function(id,ind){
    if(id>0){
    selectPrice+=parseFloat($(".unit_sum").eq(ind+1).html());
    }
    });
    $(".cart_selected_goods_price").html("￥"+selectPrice.toFixed(2));
}

function doModifyNumInCart(id,count){
 updateSelectedPrice()
 $.ajax({
              type:"POST",
              url:"/cart/modify/"+id,
              data:JSON.stringify(count),
              contentType:"application/json",
              success:function(result){
              }
        });
}

function checkOut(){
    select= _.filter(select,function(id){return id>0});
    if(select.length==0)
        alert("客官，您未选择任何商品结算！");
    else{
     $.ajax({
           type:"POST",
           url:"/cart/changeStatus/"+getCookie("userId"),
           data:JSON.stringify(select),
           contentType:"application/json",
           success:function(result){
               getCart();
           }
           });
       }
}
function selectAll(){
$(".checkboxclasss").click();
}
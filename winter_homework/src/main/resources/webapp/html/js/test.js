$(document).ready(function() {
 $.ajax({
     type:"GET",
     url:"/file/upload/fileName",
     dataType: "text",
     contentType:"application/json",
     success:function(result){
    console.log(result);
     },
     error:function(e){
     console.log(e+"error");
     }
  })
});
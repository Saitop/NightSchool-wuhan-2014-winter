$(document).ready(function() {
cookieInput();
});

function cookieInput(){
if(getCookie("name")!=null){
  $("#login_name").val(getCookie("name")+"");
 $("#login_password").val(getCookie("password")+"");
 }
}

function getName(){
var name = $("#login_name").val();
  $.ajax({
          type:"POST",
          url:"/users/isUserExist",
          data:JSON.stringify(name),
          contentType:"application/json",
          success:function(exist){
            if(!exist){
            $("#login_message").html("用户名"+ $("#login_name").val()+"不存在");
            }
            else{
            $("#login_message").html("");
            }
          }
      });
}
function login(){
var u={
userName:$("#login_name").val(),
password:$("#login_password").val()};
doLogin(u);
}

function doLogin(u){
 $.ajax({
          type:"POST",
          url:"/users/login",
          data:JSON.stringify(u),
          contentType:"application/json",
          success:function(match){
           if(!match){
            $("#login_message").html("用户名与密码不匹配");
            }
           else{
           setCookie("name",$("#login_name").val());
           setCookie("password",$("#login_password").val());
           document.location.href="index.html";
           }
         }
      });
}
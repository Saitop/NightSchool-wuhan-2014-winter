function comparePassword(){
var p1 = $("#register_password").val();
var p2 = $("#register_confirm_password").val();
if(p1==p2){
 $("#register_message").html("两次输入的密码相同");
 return true;
}
else{
 $("#register_message").html("两次输入的密码不相同");
  return false;
}
}
function getName(){
var name =$("#register_name").val();
  $.ajax({
          type:"POST",
          url:"/users/isUserExist",
          data:JSON.stringify(name),
          contentType:"application/json",
          success:function(exist){
          if(!exist)
          $("#register_message").html("用户名不存在");
          else
            $("#register_message").html("用户名已存在");
         }
      });
}
function register(){
var u={
userName:$("#register_name").val(),
password:$("#register_password").val()};
if(comparePassword())
doRegister(u);
}
function  doRegister(u){
    $.ajax({
    type:"POST",
    url:"/users/register",
    data:JSON.stringify(u),
    contentType:"application/json",
    success:function(match){
    if(match){
            $("#login_message").html("注册成功");
             setCookie("name",$("#register_name").val());
             setCookie("password",$("#register_password").val());
             document.location.href="index.html";
     }
     }});
    }
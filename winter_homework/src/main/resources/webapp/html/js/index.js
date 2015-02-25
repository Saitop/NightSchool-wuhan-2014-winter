$(document).ready(function() {
cookieLogin();
});
function cookieLogin(){
var name=getCookie("name");
var password=getCookie("password");
var u={
    userName:name,
    password:password
    };
    if(name!=null){
    $.ajax({
              type:"POST",
              url:"/users/login",
              data:JSON.stringify(u),
              contentType:"application/json",
              success:function(match){
               if(!match){
               alert("您的信息已更新 请重新登陆");
                }
               else{
               alert("自动登录成功");
               }
             }
          });
    }
}
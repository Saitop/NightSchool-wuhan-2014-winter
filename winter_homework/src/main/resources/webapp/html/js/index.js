$(document).ready(function() {
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

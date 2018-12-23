//var phoneWidth = parseInt(window.screen.width);
//var phoneScale = phoneWidth/640;
var ua = navigator.userAgent;
if (/Android (\d+\.\d+)/.test(ua)){
    var version = parseFloat(RegExp.$1);
    if(version>2.3){
        document.write('<meta name="viewport" content="width=640, minimum-scale = ‘+phoneScale+‘, maximum-scale = ‘+phoneScale+‘, target-densitydpi=device-dpi">');
    }else{
        document.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
    }
} else {
    document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
}
$(document).ready(function(){
	$('#hidden_frame').load(function(){
	    var text=$(this).contents().find("body").text();
	       // 根据后台返回值处理结果
	    var j=$.parseJSON(text);
	    if(j!=0) {
	        alert(j.msg);
	    } else {
	        alert($("#email").val());
	        location.href="./registerSuccess.html?email="+ $("#email").val()
	    }
	});
	//密码回车事件
	$("#password").bind('keypress',function(event){
	    if(event.keyCode == 13)
	    {
	       normal_login();
	    }
	});
	//验证码框回车事件
	$("#code").bind('keypress',function(event){

	    if(event.keyCode == 13)
	    { 
	       normal_login();
	    }
	});
});
function normal_login() {
	if(checkUserName() && checkPassword() && checkCode()) {
        $("#normal_form").submit();
	}
}
function checkUserName() {
	var userName = $('#username').val();
	if(userName == "" || userName == null ){
//	    alert("请输入用户名");
		$("#back_data").css("display","block");
		return false;
	}
	return true;
}
function checkPassword() {
	var password = $('#password').val();
	if(password == "" || password == null || password.length < 6){
//	    alert("请输入密码");
		$("#back_data").css("display","block");
		return false;
	}
	return true;
}
function changeCaptcha() {
	//增加当前时间参数，此参数没有实际意义，只是为了更新链接，再次访问
	$("#captchaImg").attr('src', '/dreamland/register/codeCaptcha?' + (new Date().getTime()));
}
var flag_c = false;
function checkCode() {
    var code = $("#code").val();
    code = code.replace(/^\s+|\s+$/g,"");
    if(code == ""){
        $("#code_span").text("请输入验证码！").css("color","red");
        flag_c = false;
    }else{
        $.ajax({
            type: 'post',
            url: '/dreamland/register/checkCode',
            data: {"code": code},
            dataType: 'json',
            success: function (data) {
                var val = data['message'];
                if (val == "success") {
                    $("#code_span").text("√").css("color","green");
                    $("#reg_span").text("");
                    flag_c = true;
                }else {
                    $("#code_span").text("验证码错误！").css("color","red");
                    flag_c = false;
                }
            }
        });

    }
    return flag_c;
}
 //根据内容增加而增加高度
function increaseHeight() {

       var hgt = $("#regist-left").height();
        $("#regist-left").height(hgt+30);
        $("#regist-right").height(hgt+30);

}
//根据内容减少而减少高度
function reduceHeight() {
    var hgt = $("#regist-left").height();
    $("#regist-left").height(hgt-30);
    $("#regist-right").height(hgt-30);
}

//密码框离焦事件
//var cp = 0;
//var flag= true;
//function checkPassword() {
//    var password = $("#password").val();
//    password = password.replace(/^\s+|\s+$/g,"");
//    if(password == ""){
//        $("#password_span").text("请输入密码！").css("color","red");
//        if(cp==0){
//            increaseHeight();
//            cp++;
//        }
//        return false;
//    }
//    if(password.length < 6){
//        $("#password_span").text("密码长度少于6位，请重新输入！").css("color","red");
//        if(cp==0){
//            cp++;
//            increaseHeight();
//        }
//        return false;
//    }
//
//
//    if(flag){
//        if(cp==1){
//            reduceHeight();
//            cp=0;
//        }
//        return true;
//    }
//}
//昵称校验
function checkNickName() {
    var nickName = $("#nickName").val();
    nickName = nickName.replace(/^\s+|\s+$/g,"");
    if(nickName == ""){
        $("#nickName_span").text("请输入昵称！");
        return false;
    }else{
        $("#nickName_span").text("");
        $("#reg_span").text("");
        return true;
    }
};


// 	$.ajax({
//      type:"GET",
//      url:"http://localhost:8080/dreamland/register/codeCaptcha",
//      success:function(data){
//          
//      },
//      error:function(jqXHR){
//          ("Error: "+jqXHR.status);
//      }
//  });
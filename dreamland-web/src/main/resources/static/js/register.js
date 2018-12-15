$(document).ready(function(){
	 var phoneWidth = parseInt(window.screen.width);
	    var phoneScale = phoneWidth/640;
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
});
//     changeCaptcha();
function changeCaptcha() {
	//增加当前时间参数，此参数没有实际意义，只是为了更新链接，再次访问
	$("#captchaImg").attr('src', '/dreamland/register/codeCaptcha?' + (new Date().getTime()));
}
var v = 0;
var flag2 = false;
function checkPhone(){
    var phone = $("#phone").val();
    phone = phone.replace(/^\s+|\s+$/g,"");
    if(phone == ""){
        $("#phone_span").text("请输入手机号码！");
        $("#phone_ok").text("");
        var hgt = $("#regist-left").height();

        if(v==0){
            $("#regist-left").height(hgt+30);
            $("#regist-right").height(hgt+30);
            v++;
        }

        flag2 =  false;
    }
    if(!(/^1[3|4|5|8|7][0-9]\d{8}$/.test(phone))){
        $("#phone_span").text("手机号码非法，请重新输入！");
        $("#phone_ok").text("");
        var hgt = $("#regist-left").height();
        if(v==0){
            $("#regist-left").height(hgt+30);
            $("#regist-right").height(hgt+30);
            v++;
        }
        flag2 = false;
    }else{
        $.ajax({
            type:'post',
            url:'/checkPhone',
            data: {"phone":phone},
            dataType:'json',
            success:function(data){
                var val = data['message'];
                if(val=="success"){
                    $("#phone_span").text("");
                    $("#reg_span").text("");
                    $("#phone_ok").text("√").css("color","green");

                    var content = $("#phone_ok").text();
                    if(content=="√" ){
                        var hgt = $("#regist-left").height();
                        if(v==1){
                            $("#regist-left").height(hgt-30);
                            $("#regist-right").height(hgt-30);
                        }
                        v=0;
                    }
                    flag2 =  true;

                }else{

                    $("#phone_span").text("该手机号已经注册！");
                    $("#phone_ok").text("");
                    var hgt = $("#regist-left").height();
                    if(v==0){
                        $("#regist-left").height(hgt+30);
                        $("#regist-right").height(hgt+30);
                        v++;
                    }
                    flag2 =  false;
                }
            }
        });

    }

    return flag2;
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
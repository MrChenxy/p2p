//错误提示
function showError(id,msg) {
	$("#"+id+"Ok").hide();
	$("#"+id+"Err").html("<i></i><p>"+msg+"</p>");
	$("#"+id+"Err").show();
	$("#"+id).addClass("input-red");
}
//错误隐藏
function hideError(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id).removeClass("input-red");
}
//显示成功
function showSuccess(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id+"Ok").show();
	$("#"+id).removeClass("input-red");
}


//打开注册协议弹层
function alertBox(maskid,bosid){
	$("#"+maskid).show();
	$("#"+bosid).show();
}
//关闭注册协议弹层
function closeBox(maskid,bosid){
	$("#"+maskid).hide();
	$("#"+bosid).hide();
}

//注册协议确认
$(function() {
	$("#agree").click(function(){
		var ischeck = document.getElementById("agree").checked;
		if (ischeck) {
			$("#btnRegist").attr("disabled", false);
			$("#btnRegist").removeClass("fail");
		} else {
			$("#btnRegist").attr("disabled","disabled");
			$("#btnRegist").addClass("fail");
		}
	});

	//注册功能
	//手机号的验证
	$("#phone").on("blur",function(){
		var phone  = $.trim( $("#phone").val() );
		if("" == phone){
			showError("phone","手机号不能为空");
		} else if( !/^1[1-9]\d{9}$/.test(phone)){
			showError("phone","手机号不正确");
		} else {
			//发送ajax ，检查手机号是否已经注册过。
			$.ajax({
				url: "/web/loan/checkPhone",
				data:"phone="+phone,
				dataType:"json",
				success:function(data){
					if(data.code == 200){
						showSuccess("phone",data.message);
					} else {
						showError("phone",data.message);
					}
				},
				error:function(){
					showError("phone","请求错误，稍后重试");
				}
			})
		}
	});//手机号验证完成

	//密码的blur事件
	$("#loginPassword").on("blur",function(){
		var pwd = $.trim( $("#loginPassword").val()  );
		if( pwd == "") {
			showError("loginPassword","必须输入密码")
		} else if( pwd.length < 6 || pwd.length > 20){
			showError("loginPassword","密码长度是6-20位")
		}else if( !/^[0-9a-zA-Z]+$/.test(pwd)){
			showError("loginPassword","密码字符只可使用数字和大小写英文字母");
		} else if( !/^(([a-zA-Z]+[0-9]+)|([0-9]+[a-zA-Z]+))[a-zA-Z0-9]*/.test(pwd)){
			showError("loginPassword","密码应同时包含英文和数字")
		} else {
			showSuccess("loginPassword")
		}
	});//密码的blur事件完成

	//验证码文本框
	$("#messageCode").on("blur",function(){
		var code = $.trim($("#messageCode").val());
		if( ""==code){
			showError("messageCode","请输入验证码");
		} else if( code.length != 6){
			showError("messageCode","请输入正确的验证码，有效时间60秒")
		} else {
			showSuccess("messageCode");
		}

	});//验证码文本框完成

	//到计时按钮单击事件
	$("#messageCodeBtn").on("click",function(){
		//调用手机号组件的 blur
		$("#phone").blur();
		//获取错误的文本
		var text = $.trim($("#phoneErr").text());
		//手机号的值是正确的
		if( "" == text){
			var btnObj = $("#messageCodeBtn");
			if( !btnObj.hasClass("on") ){
				//没有on样式，
				btnObj.addClass("on");
				$.leftTime(60,function(d){
					var  second = parseInt(d.s);
					btnObj.text( (second == 0 ? "60": second) + "秒后获取");
					if( second == 0 ){
						btnObj.removeClass("on");
					}
				});

				//发送短信验证码。
				$.post(contextPath+"/loan/sendCode",
					   "phone="+ $.trim( $("#phone").val() ),
					    "json"
				       )
			}
		}
	});
	//单击完成


	//注册按钮的单击事件
	$("#btnRegist").on("click",function(){
		//验证数据， phone， loginPassword, messageCode
		$("#phone").blur();
		$("#loginPassword").blur();
		$("#messageCode").blur();

		//id的属性值以Err结尾的dom对象
		var errorText = $("div[id$='Err']").text();
		if( "" == errorText){
			//可以注册
			var phone = $.trim(  $("#phone").val() );
			var pwd = $.trim( $("#loginPassword").val() );
			var code = $.trim( $("#messageCode").val() );

			$.ajax({
				url: contextPath + "/loan/register",
				data:{ phone:phone, pwd: $.md5(pwd), code:code },
				type:"post",
				dataType:"json",
				success:function(resp){
					// 实名认证
					if( resp.code == 200){
						window.location.href= contextPath + "/loan/page/realName";
					} else {
						alert(resp.message);
					}
				},
				error:function(){
					alert("请稍后重试！！！");
				}
			})
		}
	})

});

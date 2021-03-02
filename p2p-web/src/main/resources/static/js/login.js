$(function(){
	//手机号验证
	$("#phone").on("blur",function(){
		var phone = $.trim(  $("#phone").val() );
		if( "" == phone){
			$("#showId").html("手机号不能为空");
		} else if( !/^1[1-9]\d{9}$/.test(phone)){
			$("#showId").html("手机号不正确");
		} else {
			$("#showId").html("");
		}
	});

	// 密码
	$("#loginPassword").on("blur",function(){
		var  pwd = $.trim( $("#loginPassword").val() );
		if( "" == pwd){
			$("#showId").html("密码不能为空");
		} else if( pwd.length < 6 || pwd.length > 20){
			$("#shouId").html("密码的长度是6-20");
		} else if( !/^[0-9a-zA-Z]+$/.test(pwd)){
			$("#shouId").html("密码字符只可使用数字和大小写英文字母");
		} else if( !/^(([a-zA-Z]+[0-9]+)|([0-9]+[a-zA-Z]+))[a-zA-Z0-9]*/.test(pwd)){
			$("#shouId").html("密码应同时包含英文和数字");
		} else {
			$("#showId").html("");
		}
	});

	//登录按钮
	$("#btnLogin").on("click",function(){
		$("#phone").blur();
		$("#loginPassword").blur();

		var text = $("#showId").html();
		if( "" == text ){
			var  phone = $.trim( $("#phone").val() );
			var  pwd  =  $.trim( $("#loginPassword").val() );
			//ajax请求登录
			$.ajax({
				url: contextPath + "/loan/login",
				data:{ phone: phone, pwd : $.md5(pwd) },
				dataType:'json',
				type:"post",
				success:function(resp){
					if( resp.code  == 200 && resp.error == 1000){
						//跳转到returnUrl
						window.location.href= $("#returnUrlHidden").val();
					} else {
						$("#showId").html(resp.message);
					}
				},
				error:function(){
					$("#showId").html("登录失败，稍后重试");
				}
			})
		}
	})
})
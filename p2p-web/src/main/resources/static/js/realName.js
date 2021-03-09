
//同意实名认证协议
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

	//数据验证, realName姓名
	$("#realName").on("blur",function(){
		var  name = $.trim( $("#realName").val() );
		if(""==name){
			showError("realName","姓名不能为空")
		} else if( !/^[\u4e00-\u9fa5]{0,}$/.test(name)){
			showError("realName","姓名必须是中文")
		} else if( name.length > 16){
			showError("realName","姓名最多16个字符");
		} else {
			showSuccess("realName");
		}

	});
	//姓名完成

	//身份证号
	$("#idCard").on("blur",function(){
		var card= $.trim( $("#idCard").val() );
		if( ""==card){
			showError("idCard","必须输入身份证号");
		} else if( !(card.length == 15 || card.length == 18) ){
			showError("idCard","身份证号必须是15或18位");
		} else if( !/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(card)){
			showError("idCard","请检查身份证号码格式")
		} else {
			showSuccess("idCard");
		}
	});

	//身份证号

	//获取短信验证码
	$("#messageCodeBtn").on("click",function(){
		//调用姓名，身份证号的blur
		$("#idCard").blur();
		$("#realName").blur();

		//判断有无错误文本
		var nameErr = $.trim($("#realName").text());
		var idCardErr = $.trim($("#idCard").text());
		if( "" == nameErr && idCardErr == "" ){
			if( !$("#messageCodeBtn").hasClass("on") ){
				//可以去发送短信
				$("#messageCodeBtn").addClass("on");
				$.leftTime(60,function(d){
					var second = parseInt(d.s);
					$("#messageCodeBtn").text( (second == 0 ? 60: second) +"秒,获取" );
					if(second == 0 ){
						$("#messageCodeBtn").removeClass("on");
					}
				});

				//发送ajax请求
				//发送短信验证码。
				$.post(contextPath+"/loan/sendCode",
					"phone="+ $.trim( $("#phone").val() ),
					"json"
				)
			}
		}

	});

	// 验证码文本
	$("#messageCode").on("blur",function(){
		var code = $.trim($("#messageCode").val());
		if( "" == code ){
			showError("messageCode","验证码不能空");
		} else if( code.length != 6){
			showError("messageCode","验证码必须是6位的字符")
		} else{
			showSuccess("messageCode")
		}
	})
	//  验证码文本

	//  认证按钮
	$("#btnRegist").on("click",function(){
		$("#idCard").blur();
		$("#realName").blur();
		$("#messageCode").blur();

		var errorText = $("div[id $='Err']").text();
		if( "" == errorText){
			$.ajax({
				url:contextPath+"/loan/realName",
				data:{
					phone:$.trim($("#phone").val()),
					idCard: $.trim( $("#idCard").val() ),
					realName: $.trim( $("#realName").val() ),
					code: $.trim( $("#messageCode").val() )
				},
				type:"post",
				dataType:"json",
				success:function(resp){
					if(resp.code == 200){
						// 跳转到用户中心
						window.location.href = contextPath + "/loan/myCenter";
					} else {
						alert(resp.message);
					}
			    },
				error:function(){
					alert("请稍后重试")
				}
			})
		}
	})


});
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
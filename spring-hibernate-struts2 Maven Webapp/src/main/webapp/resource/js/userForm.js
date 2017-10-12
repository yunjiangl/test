$(document).ready(function() {
	
	/**
	* ajax异步请求方法
	* @param type 请求的类型
	* @param url 请求的地址
	* @param data 请求发送的数据
	* @param seccessMethod 请求成功之后的执行的方法
	*
	* @author 芸江
	* @since 2017年9月13日14:34:57
	*
	**/
	function ajaxRequest(type, url, data, seccessMethod) {
		$.ajax({
			type : type,
			url : url,
			data : data,
			success : seccessMethod,
			error : function() {
				alert("出错咯");
			}
		})
	}
	
	// 表单验证
	var input_username = false;
	var input_password1 = false;
	var input_password2 = false;
	var input_email = false;

	/**
	 * 编辑页面加载信息
	 * */
	ajaxRequest("post", "getUser", null, function(data) {

		if (data != "no") {
			var res = JSON.parse(data);
			$("input[name='username']").val(res.username);
			$("input[name='email']").val(res.email);
			$("input:checked").removeAttr("checked");
			$("input[name='sex']").each(function() {
				if ($(this).val() == res.sex) {
					$(this).attr("checked", "checked");
				}
			});

			input_username = true;
			input_email = true;
		}
	})

	// 账号的判断
	$("input[name='username']").blur(function() {
		if ($(this).val() == "") {
			$(this)
				.parent()
				.next()
				.find("span")
				.css("color", "red");
			$(this)
				.parent()
				.next()
				.find("span")
				.html("账号不能为空");
			input_username = false;
		}
	});

	$("input[name='username']").on("change keyup", function() {

		if ($(this).val().length < 6) {
			$(this)
				.parent()
				.next()
				.find("span")
				.css("color", "red");
			$(this)
				.parent()
				.next()
				.find("span")
				.html("账号长度不能小于6");
			input_username = false;
		} else {

			ajaxRequest("post", "username", "username=" + $(this).val(), function(data) {

				if (data == "true") {
					$("input[name='username']")
						.parent()
						.next()
						.find("span")
						.css("color", "green");
					$("input[name='username']")
						.parent()
						.next()
						.find("span")
						.html("账号可用");
					input_username = true;
				}

				if (data == "false") {
					$("input[name='username']")
						.parent()
						.next()
						.find("span")
						.css("color", "red");
					$("input[name='username']")
						.parent()
						.next()
						.find("span")
						.html("账号已被注册");
					input_username = false;
				}
			})


		}
	});

	// 密码判断
	$("input[name='password1']").blur(function() {
		if ($(this).val() == "") {
			$(this)
				.parent()
				.next()
				.find("span")
				.css("color", "red");
			$(this)
				.parent()
				.next()
				.find("span")
				.html("密码不能为空");
			input_password1 = false;
		}
	});

	$("input[name='password1']").keyup(function() {
		if ($(this).val().length < 6) {
			$(this)
				.parent()
				.next()
				.find("span")
				.css("color", "red");
			$(this)
				.parent()
				.next()
				.find("span")
				.html("密码长度不能小于6");
			input_password1 = false;
		} else {
			$(this)
				.parent()
				.next()
				.find("span")
				.css("color", "green");
			input_password1 = true;
		}
	});

	$("input[name='password2']").blur(function() {
		var msg = $(this).parent().next().find("span");
		if ($(this).val() == "") {
			msg.css("color", "red");
			msg.html("密码不能为空");
			input_password2 = false;
		}
	});

	$("input[name='password2']").keyup(function() {
		var msg = $(this).parent().next().find("span");
		if ($(this).val().length < 6) {
			msg.css("color", "red");
			msg.html("密码长度不能小于6");
			input_password2 = false;
		} else {
			if ($(this).val() == $("input[name='password1']").val()) {
				msg.css("color", "green");
				msg.html("两次密码一致");
				input_password2 = true;
			} else {
				msg.css("color", "red");
				msg.html("两次密码不一致");
			}

		}
	});

	// 判断邮箱
	$("input[name='email']").blur(function() {
		var msg = $(this).parent().next().find("span");
		if ($(this).val() == "") {
			msg.css("color", "red");
			msg.html("邮箱不能为空");
			input_email = false;
		}
	});



	$("input[name='email']").on("keyup change", function() {
		var msg = $(this).parent().next().find("span");
		var email = $(this).val();
		var atpos = email.indexOf("@");
		var dotpos = email.lastIndexOf(".");
		if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length) {
			msg.css("color", "red");
			msg.html("不是一个有效的email地址");
			input_email = false;
		} else {
			msg.css("color", "green");
			msg.html("有效的email地址");
			input_email = true;
		}
	});





	/**
	 * 表单提交事件
	 * */
	$("form").submit(function() {
		var data = "account.username=" +
		$("input[name='username']").val() +
		"&account.password=" +
		$("input[name='password1']").val() +
		"&account.email=" +
		$("input[name='email']").val() +
		"&account.sex=" +
		$("input:checked").val();
		console.log(data);
		if (input_email && input_username && input_password1 && input_password2) {
			ajaxRequest("post", "updateOrAddUser", data, function(info) {
				alert(info);
				$(location).prop("href", "userList.html");
			})
		} else {
			alert("操作失败！请检查相关信息是否填写");
		}
		return false;
	})
})
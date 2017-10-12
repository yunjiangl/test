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


	$("#left,#right").css("height", $(window).height() - 80 + "px");
	$("#left_bottom").css("height", $(window).height() - 80 - 200 + "px");
	$(window).resize(function() {
		$("#left,#right").css("height", $(window).height() - 80 + "px");
		$("#left_bottom").css("height", $(window).height() - 80 - 200 + "px");
	});

	$("#select").click(function() {
		$(location).prop("href", "userList.html");
	});

	$("input[type='button']").click(function() {
		$(location).prop("href", "userList.html");
	});

	$("#add").click(function() {
		ajaxRequest("post", "clean", null, function() {
			$(location).prop("href", "userForm.html");
		})
	});
	
	$("#analysis").click(function(){
		ajaxRequest("post", "clean", null, function() {
			$(location).prop("href", "userAnalysis.html");
		})
	})

	$("#back").click(function() {
		ajaxRequest("post", "clean", null, null)
	});


})
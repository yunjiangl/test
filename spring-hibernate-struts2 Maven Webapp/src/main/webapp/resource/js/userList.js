$(document).ready(function() {

	var flag = true; //页面加载完成时加载数据
	var page; // 当前页码
	var pages; // 总页数

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

	/**
	 * 在表格中显示数据
	 * 
	 * @param data 将要显示的json格式数据
	 * 
	 * @author 芸江
	 * @since 2017年9月28日17:36:24
	 * 
	 */
	function showInfoOnTable(data) {
		$.each(data, function(i) {
			$("tbody").append("<tr><td><span>" +
				data[i].username +
				"</span></td><td>" +
				data[i].email +
				"</td><td>" +
				data[i].sex +
				"</td><td>" +
				data[i].caertedate +
				"</td><td>" +
				data[i].modifydate +
				"</td><td><span id='userId' hidden='hidden'>" +
				data[i].id +
				"</span><span class='edit'>编辑</span>|<span class='delete'>删除</span></td></tr>");
		});
	}

	/**
	 * 删除或编辑用户信息点击事件绑定
	 * 
	 * @author 芸江
	 * @since 2017年9月28日17:31:36
	 */
	function editOrDelete() {
		/**
		 * 编辑用户信息
		 * */
		$(".edit").click(function() {
			ajaxRequest('post', 'userId', "id=" + $("#userId").text(), function() {
				$(location).prop("href", "userForm.html");
			});
		});

		/**
		 * 删除用户信息
		 * */
		$(".delete").click(function() {
			if (confirm("你确信要删除用户名为" + $(this)
						.parent()
						.parent()
						.find("td:eq(0)")
						.html() + "的用户？")) {

				ajaxRequest('post', 'deleteUser', "id=" + $("#userId").text(), function() {
					alert("删除成功");
					$(location).prop("href", "userList.html");
				});
			}
		});
	}

	/**
	 * 模糊查询用户信息
	 * 
	 * @param page 所要查询的页面
	 * @author 芸江
	 * @since 2017年9月28日20:00:02
	 */
	function inquireUserInfo() {
		page = 1;
		ajaxRequest("post", "Inquire", "username=" + $("input[name='username']").val() + "&page=1", function(data) {

			$("tbody tr").remove();

			if (data != "") {
				var res = JSON.parse(data);
				
				countForPages("userInquirePages");

				showInfoOnTable(res);

				editOrDelete();

			} else {
				$("tbody").append("<tr><td colspan='6'>没有可以显示的用户</td></tr>")
			}
		})
		
		return false;
	}

	/**
	 * 获取结果页面总数
	 * 
	 * @param url 请求的地址
	 * 
	 * @author 芸江
	 * @since 2017年9月28日17:27:02
	 * 
	 * */
	function countForPages(url) {

		/**
		 * 获取页面总数
		 * */
		ajaxRequest('post', url, "username=" + $("input[name='username']").val(), function(data) {
			pages = data;

			$(".pages").remove();
			
			for (i = 0; i < pages; i++) {
				$("#next").before("<button class='pages'>" + (i + 1) + "</button>");
			}

			$(".pages").css({
				"margin-left" : "3px",
				"background-color" : "#F0F8FF",
				"color" : "black"
			});

			$(".pages").first().css({
				"background-color" : "rgb(77,152,193)",
				"color" : "#FFF"
			});

			$(".pages").last().css("margin-right", "3px");

			$(".pages").click(function() {
				var nextPage = $(this).html();
				var nextPageUrl;
				var username;

				if ($("input[name='username']").val() != "") {
					nextPageUrl = "Inquire";
					username = $("input[name='username']").val();
				} else {
					nextPageUrl = "userList";
				}

				ajaxRequest('post', nextPageUrl, "page=" + nextPage + "&username=" + username, function(data) {

					page = nextPage;
					var res = JSON.parse(data);

					$("tbody tr").remove();

					showInfoOnTable(res);

					editOrDelete();

				});

				$(".pages").css({
					"background-color" : "#F0F8FF",
					"color" : "black"
				});

				$(this).css({
					"background-color" : "rgb(77,152,193)",
					"color" : "#FFF"
				});

				if ($(this).html() == pages) {
					$("#next").attr("disabled", "disabled");
				} else {
					$("#next").removeAttr("disabled");
				}

				if ($(this).html() == 1) {
					$("#previous").attr("disabled", "disabled");
				} else {
					$("#previous").removeAttr("disabled");
				}
			});
		});
	}

	/**
	 * 模糊查询用户信息
	 * 
	 * */
	$("form").submit(function() {
		if ($("input[name='username']").val() == "") {
			alert("请输入查询关键字！")
		} else {
			inquireUserInfo();
		}
	})


	/**
	 *加载后台数据，第一页数据,以及获取数据页数
	 * 
	 */

	if (flag) {
		flag = false;
		ajaxRequest('post', 'userList', "page=1", function(data) {
			var res = JSON.parse(data);
			page = 1;

			countForPages("userPages");

			showInfoOnTable(res);

			editOrDelete();

			$("#previous").attr("disabled", "disabled");

		});

	}


	/**
	 * 下一页数据 
	 **/
	$("#next").click(function() {

		$(".pages").eq(page).trigger("click");
	});

	/**
	 * 上一页数据 
	 **/
	$("#previous").click(function() {
		$(".pages").eq(page - 2).trigger("click");
	});


});
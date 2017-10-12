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

	// 用户性别统计
	ajaxRequest("post", "statisticsGender", null, function(data) {
		var res = JSON.parse(data);

		var genderCtx = $("#gender").get(0).getContext("2d");

		var genderData = {
			datasets : [ {
				data : [ res[1], res[0] ],
				backgroundColor : [ "#0072E3", "#FF5151" ]
			} ],
			labels : [
				'男',
				'女'
			]
		};

		var genderPieChart = new Chart(genderCtx, {
			type : 'pie',
			data : genderData,
			options : {
				title : {
					display : true,
					text : '用户性别分析'
				}
			}
		});
	});

	// 用户创建时间统计
	ajaxRequest("post", "statisticsCreateDataCount", null, function(data) {
		
		var createDateCount = JSON.parse(data); // 纵坐标数组
		
		ajaxRequest("post", "statisticsCreateData", null, function(data) {

			var res = JSON.parse(data); // 横坐标数组

			var createCtx = $("#create").get(0).getContext("2d");

			var createData = {
				labels : res,
				datasets : [ {
					label : "创建人数",
					data : createDateCount
				} ]
			}

			var myLineChart = new Chart(createCtx, {
				type : 'line',
				data : createData,
				options : {
					scales : {
						yAxes : [ {
							stacked : true
						} ]
					},
					title : {
						display : true,
						text : '用户创建分析'
					}
				}
			});
		})
		
	});

	
})
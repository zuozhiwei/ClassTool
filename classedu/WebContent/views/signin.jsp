<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource/css/jquery.dataTables.min.css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
<title>课堂互动</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="../index.jsp">课堂互动</a>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="active"><a href="#"><%=(String) session.getAttribute("courseid")%>
									<span class="sr-only">(current)</span></a></li>
							<li class="active"><a href="#"><%=(String) session.getAttribute("classid")%>
									<span class="sr-only">(current)</span></a></li>
							<!-- 							<li><a href="#">Link</a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Dropdown <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="#">Action</a></li>
									<li><a href="#">Another action</a></li>
									<li><a href="#">Something else here</a></li>
									<li role="separator" class="divider"></li>
									<li><a href="#">Separated link</a></li>
									<li role="separator" class="divider"></li>
									<li><a href="#">One more separated link</a></li>
								</ul></li> -->
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="javascript:alert('请联系管理员：******')">帮助</a></li>
						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</div>

		<div class="row"></div>

		<div class="row">
			<div class="col-md-2">
				<ul class="nav nav-pills nav-stacked">
					<li role="presentation" class="active"><a href="signin.jsp">签到</a></li>
					<li role="presentation"><a href="question.jsp">题目</a></li>
					<li role="presentation"><a href="message.jsp">留言</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">签到</h3>
					</div>
					<div class="row">
						<div class="col-md-11"></div>
						<div class="col-md-1">
							<button class="btn btn-primary" onclick="getSignin()">刷新</button>
						</div>
					</div>
					<div class="panel-body">
						<table id="table_signin" class="display">
							<thead>
								<tr>
									<th>学号</th>
									<th>姓名</th>
									<th>专业</th>
									<th>年纪</th>
									<th>班级</th>
									<th>时间</th>
								</tr>
							</thead>

							<tfoot>
								<tr>
									<th>学号</th>
									<th>姓名</th>
									<th>专业</th>
									<th>年纪</th>
									<th>班级</th>
									<th>时间</th>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
/* 			var thisURL = decodeURI(window.location.search);
			var getval = thisURL.split('?')[1];
			var showval = getval.split("=")[1]; */
			$.ajax({
				type : "POST",
				url : "/classedu/getSignin",
				dataType : "json",
				data : {
					/* "targetClass" : showval */
				},
				success : function(data) {
					$('#table_signin').DataTable({
						data : data,
						destroy : true,
						"columns" : [ {
							data : "userid"
						}, {
							data : "name"
						}, {
							data : "major"
						}, {
							data : "grade"
						}, {
							data : "userclass"
						}, {
							data : "time"
						}, ],
						language : {
							"processing" : "处理中...",
							"lengthMenu" : "显示 _MENU_ 项结果",
							"zeroRecords" : "没有匹配结果",
							"info" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
							"infoEmpty" : "显示第 0 至 0 项结果，共 0 项",
							"infoFiltered" : "(由 _MAX_ 项结果过滤)",
							"infoPostFix" : "",
							"search" : "搜索:",
							"url" : "",
							"emptyTable" : "没有数据",
							"loadingRecords" : "载入中...",
							"infoThousands" : "20",
							"paginate" : {
								"first" : "首页",
								"previous" : "上页",
								"next" : "下页",
								"last" : "末页"
							}
						}
					});
				}
			});
		})
		function getSignin() {
			window.location.reload();
		}
		$(document).ready(function() {
			$('#table_signin').DataTable({
				language : {
					"processing" : "处理中...",
					"lengthMenu" : "显示 _MENU_ 项结果",
					"zeroRecords" : "没有匹配结果",
					"info" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
					"infoEmpty" : "显示第 0 至 0 项结果，共 0 项",
					"infoFiltered" : "(由 _MAX_ 项结果过滤)",
					"infoPostFix" : "",
					"search" : "搜索:",
					"url" : "",
					"emptyTable" : "没有数据",
					"loadingRecords" : "载入中...",
					"infoThousands" : "20",
					"paginate" : {
						"first" : "首页",
						"previous" : "上页",
						"next" : "下页",
						"last" : "末页"
					}
				}
			});
		});
	</script>

</body>
</html>
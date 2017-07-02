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
							<!-- 							<li><a href="#">Link</a></li> -->
							<!-- 							<li class="dropdown"><a href="#" class="dropdown-toggle"
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
					<li role="presentation"><a href="signin.jsp">签到</a></li>
					<li role="presentation" class="active"><a href="question.jsp">题目</a></li>
					<li role="presentation"><a href="message.jsp">留言</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">题目</h3>
					</div>
					<div class="container-fluid">


						<div class="row">
							<div class="well well-sm"></div>
							<div class="col-md-2">
								<div class="well well-sm">选择推送的题目</div>
							</div>
							<div class="col-md-4">
								<div class="input-group">
									<input id="selectQuestion" type="text" class="form-control"
										placeholder="输入题目编号"> <span class="input-group-btn">
										<button class="btn btn-default" type="button"
											onclick="selectQuestion()">
											<b>发题！</b>
										</button>
									</span>
								</div>
								<!-- /input-group -->
							</div>
							<div class="col-md-3">
							<p id="currentquestion"></p>
							</div>
							<div class="col-md-2"></div>
							<div class="col-md-1">
								<button class="btn btn-primary" onclick="getQuestion()">刷新</button>
							</div>

						</div>
					</div>
					<div class="panel-body">
						<table id="table_question" class="display">
							<thead>
								<tr>
									<th>题号</th>
									<th>题目</th>
									<th>选项A</th>
									<th>选项B</th>
									<th>选项C</th>
									<th>选项D</th>
									<th>答案</th>
								</tr>
							</thead>

							<tfoot>
								<tr>
									<th>题号</th>
									<th>题目</th>
									<th>选项A</th>
									<th>选项B</th>
									<th>选项C</th>
									<th>选项D</th>
									<th>答案</th>
								</tr>
							</tfoot>
						</table>
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="form-horizontal">
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">输入题目</label>
										<div class="col-sm-10">
											<input type=text name="question" class="form-control"
												id="inputquestion" placeholder="题目">
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">输入A选项</label>
										<div class="col-sm-10">
											<input type="text" name="answera" class="form-control"
												id="inputanswera" placeholder="A选项">
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">输入B选项</label>
										<div class="col-sm-10">
											<input type=text name="answerb" class="form-control"
												id="inputanswerb" placeholder="B选项">
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">输入C选项</label>
										<div class="col-sm-10">
											<input type="text" name="answerc" class="form-control"
												id="inputanswerc" placeholder="C选项">
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">输入D选项</label>
										<div class="col-sm-10">
											<input type=text name="answerd" class="form-control"
												id="inputanswerd" placeholder="D选项">
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">输入正确答案</label>
										<div class="col-sm-10">
											<input type="text" name="answer" class="form-control"
												id="inputanswer" placeholder="正确答案：请输入A、B、C、D">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button class="btn btn-default" onclick="addQuestion()">添加题目</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$.ajax({
				type : "POST",
				url : "/classedu/GetQuestionList",
				dataType : "json",
				data : {},
				success : function(data) {
					$('#table_question').DataTable({
						data : data,
						destroy : true,
						"columns" : [ {
							data : "id"
						}, {
							data : "question"
						}, {
							data : "answera"
						}, {
							data : "answerb"
						}, {
							data : "answerc"
						}, {
							data : "answerd"
						}, {
							data : "answer"
						} ],
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
		function addQuestion() {
			var question = $("input#inputquestion").val();
			var answera = $("input#inputanswera").val();
			var answerb = $("input#inputanswerb").val();
			var answerc = $("input#inputanswerc").val();
			var answerd = $("input#inputanswerd").val();
			var inputanswer = $("input#inputanswer").val();
			var answer = "";
			switch (inputanswer) {
			case "a":
			case "A":
				answer = answera;
				break;
			case "b":
			case "B":
				answer = answerb;
				break;
			case "c":
			case "C":
				answer = answerc;
				break;
			case "d":
			case "D":
				answer = answerd;
				break;
			default:
				answer = answera;
				break;
			}
			$.ajax({
				type : "POST",
				url : "/classedu/addQuestion",
				dataType : "json",
				data : {
					"question" : question,
					"answera" : answera,
					"answerb" : answerb,
					"answerc" : answerc,
					"answerd" : answerd,
					"answer" : answer
				},
				success : function(data) {
					alert("添加成功");
					getQuestion();
				},
				error : function(data) {
					alert("网络错误！");
				}
			});
		}

		function selectQuestion() {
			var questionNo = $("input#selectQuestion").val();
			$.ajax({
				type : "POST",
				url : "/classedu/setQuestionNo",
				dataType : "json",
				data : {
					"questionNo" : questionNo
				},
				success : function(data) {
					$("#currentquestion").html("<h4><b>"+"当前题目："+questionNo+"</b></h4>");
					alert("设置成功");
				},
				error : function(data) {
					alert("网络错误！");
				}
			});
		}

		function clearinput() {
			$("input#inputquestion").val("");
			$("input#inputanswera").val("");
			$("input#inputanswerb").val("");
			$("input#inputanswerc").val("");
			$("input#inputanswerd").val("");
			$("input#inputanswer").val("");
		}

		function getQuestion() {
			window.location.reload();
		}
		$(document).ready(function() {
			$('#table_question').DataTable({
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
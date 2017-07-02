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
						<a class="navbar-brand" href="index.jsp">课堂互动</a>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="active"><a href="#">课程 <span class="sr-only">(current)</span></a></li>
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
			<div class="col-md-4"></div>

			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">选择课程</h3>
					</div>
					<div class="panel-body">
						<!-- 						<button type="button" class="btn btn-default"
							aria-label="Left Align">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						</button> -->
						<form role="form" action="/classedu/selectCourse" method="post">
							<div class="form-group">
								<label for="name">选择课程</label> <select class="form-control" name="selectCourse">
									<option value="1">高等数学[课程号：10005]</option>
									<option value="2">大学物理[课程号：10006]</option>
								</select>
							</div>
							<div class="form-group">
								<label for="name">选择课程</label> <select class="form-control" name="selectClass">
									<option value="1">课序号：20001</option>
									<option value="2">课序号：20002</option>
								</select>
							</div>
							<button class="btn btn-primary" type="submit" >上课</button>
						</form>

					</div>
				</div>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
	<script type="text/javascript">
		
	</script>

</body>
</html>
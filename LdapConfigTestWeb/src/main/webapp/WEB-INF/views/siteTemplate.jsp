<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="For LDAP Config Automation Test">
	<meta name="author" content="Joy Chiu">
	<link rel="shortcut icon"
		href="/LdapConfigTestWeb/resources/img/favicon.ico" />
	<title>Automation Test Web</title>
	<test:css src="/resources/css/bootstrap.min.css" />
	<style>
	body {
		padding-top: 70px;
		padding-bottom: 20px;
	}
	</style>
	<test:css src="/resources/css/bootstrap-theme.min.css" />
	<test:css src="/resources/css/main.css" />
	<test:js src="/resources/js/vendor/jquery-1.10.2.min.js" />
	<test:js src="/resources/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js" />
	<test:js src="/resources/js/vendor/bootstrap.min.js" />
	<test:js src="/resources/js/main.js" />
	<t:insertAttribute name="head" defaultValue="" />
</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href='<test:url src="/"/>'>Automation
					Test</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href='<test:url src="/"/>'>Home</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Setting <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">Selenium Config</a></li>
							<li class="divider"></li>
							<li><a href="#">Test Link Config</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#about">About</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<t:insertAttribute name="body" />
	</div>
	<!-- /container -->
</body>
</html>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="For Nasdaq OMXAutomation Test">
	<meta name="author" content="Joy Chiu">
	<link rel="shortcut icon"
		href="/AutomationTestWeb/resources/img/favicon.ico" />
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
	<script>
		test.ctx = '<test:url src=""/>';
	</script>
	<t:insertAttribute name="head" defaultValue="" />
</head>

<body>
	<div class="container">
		<t:insertAttribute name="body" />
	</div>
	<!-- /container -->
</body>
</html>
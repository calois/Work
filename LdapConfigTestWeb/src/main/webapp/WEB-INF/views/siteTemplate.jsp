<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <test:css src="/resources/css/bootstrap.min.css"/>
    <style>
        body {
            padding-top: 50px;
            padding-bottom: 20px;
        }
    </style>
    <test:css src="/resources/css/bootstrap-theme.min.css"/>
    <test:css src="/resources/css/main.css"/>
    <test:js src="/resources/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"/>
	<t:insertAttribute name="head" defaultValue="" />
</head>
<body>
	<h2><a href='<test:url src="/"/>'>Back to Home</a></h2>
	<t:insertAttribute name="body" />
	<test:js src="/resources/js/vendor/jquery-1.10.1.min.js"/>
	<test:js src="/resources/js/vendor/bootstrap.min.js"/>
	<test:js src="/resources/js/main.js"/>
</body>
</html>
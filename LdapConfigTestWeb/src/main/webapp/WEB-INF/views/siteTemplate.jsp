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
	<div id="Page">
		<div id="Header">
			<img id="ProductLogo"
				src="/AutomationTestWeb/resources/img/AutomationLogo.png"
				width="430" height="38" alt="NASDAQ OMX | Automation Test">
		</div>
		<div id="Wrapper">
			<t:insertAttribute name="body" />
		</div>
	</div>
	<div id="Footer">
		<div id="ProductInfo">
			<ul>
				<li><span>Email:</span> <a href="mailto:Joy.Chiu@nasdaqomx.com">
						Joy.Chiu@nasdaqomx.com </a></li>
				<li><span>|</span></li>
				<li><span>Phone:</span> +61 2 8076 2972</li>
			</ul>
		</div>
		<div id="FooterCopyright">
			&#169; 2013, The <a href="http://www.nasdaqomx.com" target="_blank">NASDAQ
				OMX</a> Group, Inc. All Rights Reserved.
		</div>
	</div>
</body>
</html>
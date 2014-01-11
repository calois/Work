<h1>
	<ol class="breadcrumb">
		<li class="active"><a href='<test:url src="/"/>'>Home</a></li>
	</ol>
</h1>
<div class="container">
	<h2>Test Config</h2>
	<form id="testLinkConfigForm" role="form"
		action='<test:url src="/testProjects"/>' method="GET">
		<div class="form-group">
			<label for="url">Test Link URL:</label> <input required type="text"
				class="form-control" id="baseUrl" name="baseUrl"
				placeholder="Enter Test Link URL" value="${baseUrl}">
		</div>
		<div class="form-group">
			<label for="projectName">Personal API access key:</label> <input
				required type="text" class="form-control" id="devKey" name="devKey"
				value="${devKey}">
		</div>
		<span class="help-block">Login TestLink and get the key in My
			Settings page.</span>
		<button type="submit" class="btn btn-default">Access to
			TestLink</button>
	</form>
	<form id="testConfigForm" role="form" method="POST" hidden=true>
		<div class="form-group">
			<label for="browser">Test Browser:</label> <select required
				class="form-control" id="browser" name="browserType">
				<option value="CHROME">Chrome</option>
			</select>
		</div>
		<div class="form-group">
			<label for="url">Implicit Wait (second):</label> <input required
				type="number" class="form-control" id="implicitWait"
				name="implicitWait" value="${implicitWait}"
				placeholder="Default waiting time for locating elements">
		</div>
		<div class="form-group">
			<label for="url">Explicit Wait (second):</label> <input required
				type="number" class="form-control" id="explicitWait"
				name="explicitWait" value="${explicitWait}"
				placeholder="Extra waiting time for locating elements">
		</div>
		<div class="form-group">
			<label for="url">Test URL:</label> <input required type="text"
				class="form-control" id="url" name="url"
				placeholder="Enter LDAP URL">
		</div>
		<div class="form-group">
			<label for="projectName">Test Project:</label> <select required
				class="form-control" id="testProject" name="projectId">
				<option></option>
				<c:forEach var="testProject" items="${testProjects}">
					<option value=${testProject.id}>${testProject.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="planName">Test Plan:</label> <select required
				class="form-control" id="testPlan" name="planId">
				<option></option>
				<c:forEach var="testPlan" items="${testPlans}">
					<option value=${testPlan.id}>${testPlan.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="build">Build / Release:</label> <input required disabled
				class="form-control" id="build" name="build" list="buildsList"
				placeholder="Enter or double-click to select Build/Release">
			<datalist id="buildsList">
			</datalist>
			<span class="help-block">Build/Release will be created
				automatically in TestLink if not existing.</span>
		</div>
		<button type="submit" formaction='<test:url src="/viewTestCases"/>'
			class="btn btn-default">View Test Cases</button>
		<button type="submit" formaction='<test:url src="/runTestCases"/>'
			class="btn btn-default">Run Test Cases</button>
	</form>
</div>
<script>
	var defaultProject = "LDAP Config";
	var testLinkConfigForm = $('#testLinkConfigForm');
	var testConfigForm = $('#testConfigForm');
	var testProject = $('#testProject');
	var testPlan = $('#testPlan');
	var buildList = $('#buildsList');
	var build = $('#build');
	testLinkConfigForm.submit(function() {
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");
		$.ajax({
			url : formURL,
			type : "GET",
			data : postData,
			success : function(projects) {
				testConfigForm.removeAttr("hidden");
				testProject.empty();
				for ( var i in projects) {
					testProject.append($("<option></option>").attr("value",
							projects[i].id).text(projects[i].name));
				}
				$('#testProject option:contains(' + defaultProject + ')').prop(
						{
							selected : true
						});
			}
		});
		return false;
	});
	testProject.change(function() {
		$.ajax({
			type : "get",
			url : '<test:url src="/' + $(this).val() + '/testPlan"/>'
		}).done(
				function(plans) {
					testPlan.empty();
					for ( var i in plans) {
						testPlan.append($("<option></option>").attr("value",
								plans[i].id).text(plans[i].name));
					}
				});
	});
	testPlan.change(function() {
		$.ajax({
			type : "get",
			url : '<test:url src="/' + $(this).val() + '/build"/>'
		}).done(function(builds) {
			buildList.empty();
			build.removeAttr("disabled");
			for ( var i in builds) {
				buildList.append($("<option></option>").text(builds[i].name));
			}
		});
	});
</script>
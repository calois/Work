<div class="container">
	<h2>Test Config</h2>
	<br>
	<form role="form" style="width: 600px;">
		<div class="form-group">
			<label for="browser">Test Browser:</label> <select required
				class="form-control" id="browser" name="browserType">
				<option value="CHROME">Chrome</option>
			</select>
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
		<button type="submit" formaction='<test:url src="/viewTestCases"/>' class="btn btn-default">View Test Cases</button>
		<button type="submit" formaction='<test:url src="/runTestCases"/>' class="btn btn-default">Run Test Cases</button>
	</form>
</div>
<script>
	var testPlan = $('#testPlan');
	var buildList = $('#buildsList');
	var build = $('#build');
	$('#testProject').change(
			function() {
				$.ajax({
					type : "get",
					url : '<test:url src="/' + $(this).val() + '/testPlan"/>'
				}).done(
						function(plans) {
							testPlan.empty();
							for ( var i in plans) {
								testPlan.append($("<option></option>").attr(
										"value", plans[i].id).text(
										plans[i].name));
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
	var defaultProject = "LDAP Config";
	$('#testProject option:contains(' + defaultProject + ')').prop({
		selected : true
	});
</script>
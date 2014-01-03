<div class="container">
	<form role="form" style="width: 600px;"
		action='<test:url src="/testCases"/>'>
		<div class="form-group">
			<label for="url">LDAP Config URL:</label> <input type="text"
				class="form-control" id="url" name="url"
				placeholder="Enter LDAP URL">
		</div>
		<div class="form-group">
			<label for="projectName">Test Project:</label> <select required
				class="form-control" id="projectName" name="projectName">
				<c:forEach var="testProject" items="${testProjects}">
					<option value=${testProject.id}>${testProject.name}</option>
				</c:forEach>
			</select>

		</div>
		<div class="form-group">
			<label for="planName">Test Plan:</label> <select required
				class="form-control" id="planName" name="planName">
				<c:forEach var="testPlan" items="${testPlans}">
					<option>${testPlan.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="build">Build / Release:</label> <select required
				class="form-control" id="build" name="build">
				<c:forEach var="build" items="${builds}">
					<option>${build.name}</option>
				</c:forEach>
			</select>
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
</div>
<script>
	var defaultProject = "LDAP Config";
	$('#projectName option:contains(' + defaultProject + ')').prop({
		selected : true
	});
	var planName = $('#planName');
	var build = $('#build');
	$('#projectName').change(function() {
		$.ajax({
			  type: "get",
			  url: '<test:url src="/'+$(this).val()+'/testPlan"/>'
			})
			  .done(function( msg ) {
				  console.log( planName.text() );
			  });
		console.log($(this).val());
	});
</script>
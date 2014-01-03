<h1>Home</h1>
<form role="form" style="width: 600px;"
	action='<test:url src="/testCases"/>'>
	<div class="container">
		<div class="form-group">
			<label for="url">LDAP Config URL:</label> <input type="text"
				class="form-control" id="url" name="url"
				placeholder="Enter LDAP URL">
		</div>
		<div class="form-group">
			<label for="projectName">Project Name:</label> <select required
				class="form-control" id="projectName" name="projectName">
				<c:forEach var="testProject" items="${testProjects}">
					<option>${testProject.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="planName">Plan Name:</label> <input type="text"
				class="form-control" id="planName" name="planName"
				placeholder="Enter Plan Name">
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</div>
</form>
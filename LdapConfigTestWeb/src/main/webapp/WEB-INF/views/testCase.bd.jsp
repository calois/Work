<h1>Test Result List</h1>
<table class="table table-striped">
	<thead>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Automation Key</th>
			<th>Input Data</th>
			<th>Output Data</th>
			<th>Test Result</th>
			<th>Result Note</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="testCase" items="${testCases}">
			<tr>
				<td>${testCase.id}</td>
				<td>${testCase.name}</td>
				<td>${testCase.automationKey}</td>
				<td>${testCase.inputData}</td>
				<td>${testCase.outputData}</td>
				<td>${testCase.testResult.status}</td>
				<td>${testCase.testResult.message}</td>
				<td><a href='<test:url src="/${testProject.id}/testCase/${testCase.id}"/>' class="btn btn-primary" role="button">Run</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
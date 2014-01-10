<h1>
	<ol class="breadcrumb">
		<li><a href='<test:url src="/"/>'>Home</a></li>
		<li class="active">Test List</li>
	</ol>
</h1>
<div class="container">
	<a href='<test:url src="/runAll"/>' class="btn btn-primary btn-lg"
		role="button">Run All Test Cases</a>
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
					<td id="${testCase.id}_status">${testCase.testResult.status}</td>
					<td id="${testCase.id}_message">${testCase.testResult.message}</td>
					<td><button url='<test:url src="/run/${testCase.id}"/>'
							class="btn btn-primary" role="button">Run</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
	$('button[url]').click(
			function() {
				$.ajax({
					type : "post",
					url : $(this).attr("url")
				}).done(
						function(testCase) {
							$('#' + testCase.id + '_status').text(
									testCase.testResult.status);
							if (null != testCase.testResult.message) {
								$('#' + testCase.id + '_message').text(
										testCase.testResult.message);
							}
						});
			});
</script>
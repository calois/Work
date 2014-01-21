<h2 class="Header">Test List</h2>
<div class="listingOptions">
	<a id="reset" href='<test:url src="/"/>' class="btn btn-default btn-sm"
		role="button">Reset Test Config</a> <a id="runAll"
		class="btn btn-default btn-sm" role="button">Run All Test Cases</a>
</div>
<table class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<th>Id</th>
			<th>Test Case Summary</th>
			<th>Automation Key</th>
			<th>Input Data</th>
			<th>Output Data</th>
			<th>Test Result</th>
			<th>Result Note</th>
			<th>Execution Status</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="testCase" items="${testCases}">
			<tr>
				<td>${testCase.externalId}</td>
				<td>${testCase.summary}</td>
				<td>${testCase.automationKey}</td>
				<td>${testCase.inputData}</td>
				<td>${testCase.outputData}</td>
				<td id="${testCase.id}_status"></td>
				<td id="${testCase.id}_message"></td>
				<td id="${testCase.id}_jobStatus"></td>
				<td><button url='<test:url src="/run/${testCase.id}"/>'
						class="btn btn-default btn-sm" role="button">Run</button></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<test:js src="/resources/js/testList.js" />
<script>
	if ('${runAll}' == 'true') {
		testList.runAll();
	}
</script>
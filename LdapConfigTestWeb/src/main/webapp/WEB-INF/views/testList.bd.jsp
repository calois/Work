<h2 class="Header">Test List</h2>
<div class="listingOptions">
	<a id="reset" href='<test:url src="/"/>' class="btn btn-default btn-sm"
		role="button">Reset Test Config</a> <a id="runAll"
		class="btn btn-default btn-sm" role="button">Run All Test Cases</a>
</div>
<table class="table table-striped table-bordered table-condensed"
	id="testCaseTable">
	<thead>
		<tr>
			<th width="5%">Id</th>
			<th width="10%">Test Case Summary</th>
			<th width="9%">Automation Key</th>
			<th width="20%">Input Data</th>
			<th width="20%">Output Data</th>
			<th width="6%">Test Result</th>
			<th width="20%">Result Note</th>
			<th width="5%">Execution Status</th>
			<th width="5%">Action</th>
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
	testList.idSuffix = '${idSuffix}';
	if ('${runAll}' == 'true') {
		testList.runAll();
	}
</script>
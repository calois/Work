<h2 class="Header">Test List</h2>
<div class="listingOptions">
	<a href='<test:url src="/"/>' class="btn btn-default btn-sm"
		role="button">Reset Test Config</a> <a
		href='<test:url src="/runAll"/>' class="btn btn-default btn-sm"
		role="button">Run All Test Cases</a>
</div>
<table
	class="table table-striped table-bordered table-condensed table-responsive Listing">
	<thead>
		<tr>
			<th>Id</th>
			<th>Test Case Summary</th>
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
				<td>${testCase.externalId}</td>
				<td>${testCase.summary}</td>
				<td>${testCase.automationKey}</td>
				<td>${testCase.inputData}</td>
				<td>${testCase.outputData}</td>
				<td id="${testCase.id}_status">${testCase.testResult.status}</td>
				<td id="${testCase.id}_message">${testCase.testResult.message}</td>
				<td><button url='<test:url src="/run/${testCase.id}"/>'
						class="btn btn-default btn-sm" role="button">Run</button></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script>
	'use strict';
	(function($) {
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
	})(jQuery);
</script>
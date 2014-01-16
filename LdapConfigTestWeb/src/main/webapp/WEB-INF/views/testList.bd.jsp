<h2 class="Header">Test List</h2>
<div class="listingOptions">
	<a href='<test:url src="/"/>' class="btn btn-default btn-sm"
		role="button">Reset Test Config</a> <a id="runAll"
		href='javascript:void(0);' class="btn btn-default btn-sm"
		role="button">Run All Test Cases</a>
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
			<th>Status</th>
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
				<td id="${testCase.id}_jobStatus">${testCase.status}</td>
				<td><button url='<test:url src="/run/${testCase.id}"/>'
						class="btn btn-default btn-sm" role="button">Run</button></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script>
	'use strict';
	(function($) {
		var running = 0;
		$('#runAll').click(function() {
			$('button[class="btn btn-default btn-sm"]').each(function() {
				running++;
				$(this).trigger('click');
			});
		});
		$('button[url]').click(function() {
			var button = $(this);
			button.attr("disabled", true);
			$('#runAll').attr("disabled", true);
			$.ajax({
				type : "get",
				url : $(this).attr("url")
			}).done(function(testCase) {
				var id = testCase.id;
				function checkResult() {
					$.ajax({
						type : "get",
						url : test.getUrl('/testJob/' + id)
					}).done(function(data) {
						$('#' + id + '_jobStatus').text(data.status);
						if (data.result) {
							$('#' + id + '_status').text(data.result.status);
							if (null != data.result.message) {
								$('#' + id + '_message').text(data.result.message);
							}
							button.attr("disabled", false);
							running--;
							if (running == 0) {
								$('#runAll').attr("disabled", false);
							}
						} else {
							setTimeout(checkResult, 1000);
						}
					});
				}
				checkResult();
			});
		});
	})(jQuery);
</script>
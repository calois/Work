'use strict';
(function($) {
	var running = 0;
	var runAllBtn=$('#runAll');
	runAllBtn.click(function() {
		$('button[url]').each(function() {
			running++;
			$(this).trigger('click');
		});
	});
	$('button[url]').click(function() {
		var button = $(this);
		button.attr("disabled", true);
		runAllBtn.attr("disabled", true);
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
							runAllBtn.attr("disabled", false);
						}
					} else {
						setTimeout(checkResult, 1000);
					}
				});
			}
			checkResult();
		});
	});
	if ('${runAll}' == 'true') {
		$(function() {
			runAllBtn.trigger('click');
		});
	}
})(jQuery);
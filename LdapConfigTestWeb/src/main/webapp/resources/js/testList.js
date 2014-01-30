'use strict';
var testList = {};
(function($) {
	var running = 0;
	var runAllBtn = $('#runAll');
	runAllBtn.click(function() {
		runAllBtn.attr("disabled", true);
		$('button[url]').each(function() {
			$(this).trigger('click');
		});
	});
	$('button[url]').click(function() {
		running++;
		var button = $(this);
		button.attr("disabled", true);
		$.ajax({
			type : "get",
			url : $(this).attr("url")
		}).done(function(testCase) {
			var id = testCase.id;
			function checkResult() {
				$.ajax({
					type : "get",
					url : test.getUrl('testJob/' + id + testList.idSuffix)
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
	testList.runAll = function() {
		$(function() {
			runAllBtn.trigger('click');
		});
	};
})(jQuery);
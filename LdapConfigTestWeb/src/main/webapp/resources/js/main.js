'use strict';
var test = {
	getUrl : function(url) {
		return this.ctx + url;
	}
};

(function($) {
	$(function() {
		function loadRunner() {
			$('#runner').load(test.getUrl('/runner/list'));
		}
		setInterval(loadRunner, 1000);
		function loadJobWaiting() {
			$('#waitingJob').load(test.getUrl('/job/list/waiting'));
		}
		setInterval(loadJobWaiting, 1000);
	});
})(jQuery);

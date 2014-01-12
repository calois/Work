'use strict';
(function($){
	//Code here
})(jQuery);

var defaultProject = "LDAP Config";
var testLinkConfigForm = $('#testLinkConfigForm');
var testProject = $('#testProject');
var testPlan = $('#testPlan');
var buildList = $('#buildsList');
var build = $('#build');
testLinkConfigForm.submit(function() {
	var postData = $(this).serializeArray();
	var formURL = $(this).attr("action");
	$.ajax({
		url : formURL,
		type : "GET",
		data : postData,
		success : function(projects) {
			$('#testConfigCollapse').prev().click();
			testProject.empty();
			for ( var i in projects) {
				testProject.append($("<option></option>").attr("value",
						projects[i].id).text(projects[i].name));
			}
			$('#testProject option:contains(' + defaultProject + ')').prop({
				selected : true
			});
			testProject.trigger("change");
		}
	});
	return false;
});
testProject.change(function() {
	$.ajax({
		type : "get",
		url : test.getUrl('/' + $(this).val() + '/testPlan')
	}).done(
			function(plans) {
				testPlan.empty();
				for ( var i in plans) {
					testPlan.append($("<option></option>").attr("value",
							plans[i].id).text(plans[i].name));
				}
			});
});
testPlan.change(function() {
	$.ajax({
		type : "get",
		url : test.getUrl('/' + $(this).val() + '/build')
	}).done(function(builds) {
		buildList.empty();
		build.removeAttr("disabled");
		for ( var i in builds) {
			buildList.append($("<option></option>").text(builds[i].name));
		}
	});
});
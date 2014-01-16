<h2>Test Config</h2>
<div class="panel-group accordion" id="accordion">
	<div class="panel panel-default">
		<div class="panel-heading" data-toggle="collapse"
			data-parent="#accordion" href="#testLinkCollapse">
			<h5 class="panel-title">Access to TestLink</h5>
		</div>
		<div id="testLinkCollapse" class="panel-collapse collapse in">
			<div class="panel-body">
				<form id="testLinkConfigForm" role="form"
					action='<test:url src="/testProjects"/>' method="GET">
					<div class="form-group">
						<label for="url">Test Link URL:</label> <input required
							type="text" class="form-control" id="baseUrl" name="baseUrl"
							placeholder="Enter Test Link URL" value="${baseUrl}">
					</div>
					<div class="form-group">
						<label for="devKey">Personal API Access Key:</label> <input
							required type="text" class="form-control" id="devKey"
							name="devKey" placeholder="Enter your personal API Access Key">
					</div>
					<span class="help-block">To get the access key, please Login
						the TestLink and go to My Settings page.</span>
					<button type="submit" class="btn btn-default">Access to
						TestLink</button>
				</form>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading" data-toggle="collapse"
			data-parent="#accordion" href="#testConfigCollapse">
			<h5 class="panel-title">Configuration</h5>
		</div>
		<div id="testConfigCollapse" class="panel-collapse collapse">
			<div class="panel-body">
				<form id="testConfigForm" role="form" method="GET">
					<div class="form-group">
						<label for="browser">Test Browser:</label> <select required
							class="form-control" id="browser" name="browserType">
							<option value="CHROME">Chrome</option>
						</select>
					</div>
					<div class="form-group">
						<label for="url">Implicit Wait (second):</label> <input required
							type="number" class="form-control" id="implicitWait"
							name="implicitWait" value="${implicitWait}"
							placeholder="Default waiting time for locating elements">
					</div>
					<div class="form-group">
						<label for="url">Explicit Wait (second):</label> <input required
							type="number" class="form-control" id="explicitWait"
							name="explicitWait" value="${explicitWait}"
							placeholder="Extra waiting time for locating elements">
					</div>
					<div class="form-group">
						<label for="projectName">Test Project:</label> <select required
							class="form-control" id="testProject" name="projectId">
						</select>
					</div>
					<div class="form-group">
						<label for="url">Project URL:</label> <input required type="text"
							class="form-control" id="url" name="url"
							placeholder="Enter Project URL">
					</div>
					<div class="form-group">
						<label for="planName">Test Plan:</label> <select required
							class="form-control" id="testPlan" name="planId">
						</select>
					</div>
					<div class="form-group">
						<label for="build">Build / Release:</label> <input required
							disabled class="form-control" id="build" name="build"
							list="buildsList"
							placeholder="Enter or double-click to select Build/Release">
						<datalist id="buildsList">
						</datalist>
						<span class="help-block">Build/Release will be created
							automatically in TestLink if not existing.</span>
					</div>
					<button id="viewTestCases" type="submit" form="testConfigForm"
						formaction='<test:url src="/viewTestCases"/>'
						class="btn btn-default">View Test Cases</button>
					<button id="runTestCases" type="submit" form="testConfigForm"
						formaction='<test:url src="/runTestCases"/>'
						class="btn btn-default">Run Test Cases</button>
				</form>
			</div>
		</div>
	</div>
</div>
<test:js src="/resources/js/testConfig.js" />
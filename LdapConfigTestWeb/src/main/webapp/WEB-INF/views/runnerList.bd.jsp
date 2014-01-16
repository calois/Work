<h3>Runner List</h3>
<table class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Job Id</th>
			<th>Job Status</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="runner" items="${runnerList}">
			<tr>
				<td>${runner.id}</td>
				<td>${runner.name}</td>
				<td>${runner.testJob.id}</td>
				<td>${runner.testJob.status}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<h3>Waiting Job List</h3>
<table class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<th>Id</th>
			<th>Status</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="job" items="${jobList}">
			<tr>
				<td>${job.id}</td>
				<td>${job.status}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
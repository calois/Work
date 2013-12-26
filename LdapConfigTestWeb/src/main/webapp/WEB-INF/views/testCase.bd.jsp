<h1>Test Case</h1>
<table class="table table-striped">
	<thead>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Test Key</th>
			<th>Data</th>
			<th>Expect</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="testCase" items="${testCases}">
			<tr>
				<td>${testCase.id}</td>
				<td>${testCase.name}</td>
				<td>${testCase.testKey}</td>
				<td>${testCase.data}</td>
				<td>${testCase.expect}</td>
				<td><a href='<test:url src="/testCase/${testCase.id}"/>' class="btn btn-primary btn-lg" role="button">Run</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose the type of report and the format</title>

<style type="text/css">
	body{
		line-height: 1.5em;
		font-family: Arial, Helvetica, "Helvetica Neue", Geneva, sans-serif;
		margin: 0;
		padding: 10px;
	}
	table, th, td {
			border: 1px solid black;
			line-height: 1.5em;
			font-family: Arial, Helvetica, "Helvetica Neue", Geneva, sans-serif;
			margin: 0;
			padding: 5px;
	}
	th	{
			background-color:red;
			color:black;
	}
	.padded {
			padding: 15x;
	}

</style>
</head>
<body>
	<h2 style="color:red;"> Simple Jasper Reports web app </h2>
	<br><br>
	<form name="reportForm" action="/reporting/reportService" method="post">
		<table border=1 width="90%">
			<tr>
				<th> Operation </th>
				<th> Choose </th>
			</tr>

			<tr>
				<td>
					Please select the type of report.
					<br>
				</td>
				<td align="center">
					<select class="padded" name="selectReport" id="selectReport">
						<option value="OrderSimpleExample">Order Simple Example</option>
					</select>
				</td>
			</tr>

			</tr>
			<tr>
				<td>
					Please select the output format.
				</td>
				<td>
					<input type="radio" name="format" value="viewer" checked="checked">Print Preview To Screen</input><br>
					<input type="radio" name="format" value="pdf">PDF File(Acrobat)</input><br>
					<input type="radio" name="format" value="csv">CSV File</input><br>
				</td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<input type=submit value="Submit"/>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
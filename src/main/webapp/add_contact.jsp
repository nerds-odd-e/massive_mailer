<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<%@ page import="com.odde.massivemailer.model.ContactPerson" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<title>Add Contact</title>

<!-- Bootstrap Core CSS -->
<link href="resources/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/lib/bootstrap/css/sb-admin.css" rel="stylesheet">

<link href="resources/lib/bootstrap/css/plugins/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link href="resources/lib/bootstrap/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
</head>
<body class="original-body">
<form name="addContact" id="addContact" method="post" action="addContact">
			<div id="page-wrapper">

				<div class="container-fluid">

					<!-- Page Heading -->
					<div class="row">
						<div class="col-lg-12">
							<h1 class="page-header">Add Contact</h1>
						</div>
					</div>

					<div class="row">
						<div class="col-lg-12">
							
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Contact Information</h3>
								</div>
								<div class="panel-body">
			
									<div class="row">
										<div class="col-lg-1">Email:</div>
										<div class="col-lg-11">
											<input type="text" class="form-control" name="email"
												id="email">
										</div>
									</div>
									<br>
									<br>
									<div class="row">
										<div class="col-lg-12">
											<button type="button" class="btn btn-default"
												id="add_button" >Add</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	
	</form>	
</body>
<!-- jQuery -->
<script type="text/javascript" src="resources/lib/bootstrap/js/jquery.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="resources/lib/bootstrap/js/bootstrap.min.js"></script>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.odde.massivemailer.model.Question" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact List</title>
<!-- Bootstrap Core CSS -->
<link href="resources/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/lib/bootstrap/css/sb-admin.css" rel="stylesheet">

<link href="resources/lib/bootstrap/css/plugins/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="resources/lib/bootstrap/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
</head>
<body>
    <form action="submit" method="POST">
        <input id="input_question_body" type="text" name="question_body">
        <input type="text" name="answer_1">
        <input type="text" name="answer_2">
        <input id="input_question_advice" type="text" name="question_advice">

        <input id="save_button" type="button">
    </form>
   <%
       for (Question question : (List<Question>)request.getAttribute("questions")) {
   %>
       <div class="question">
       <p class="question_body"><%= question.get("body") %></p>
       <p><%= question.get("advice") %></p>
       </div>
   <%
       }
   %>
</body>
<!-- jQuery -->
<script type="text/javascript"
	src="resources/lib/bootstrap/js/jquery.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="resources/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="resources/js/addQuestion.js"></script>
</html>
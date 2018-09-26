<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import = "java.util.Optional"
	import = "java.util.List"
	import = "com.odde.massivemailer.model.OnlineTest"
	import = "com.odde.massivemailer.model.Question"
	import = "com.odde.massivemailer.model.Option"
	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question</title>
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
<div id="page-wrapper">
    <form name="question" id="questionForm" method="post"
        action="question">
    <div class="container-fluid">
        <h1>Question</h1>

        <%
            OnlineTest onlineTest = (OnlineTest) request.getSession().getAttribute("onlineTest");
            Optional<Question> question = onlineTest.getCurrentQuestion();
        %>
        <h2 id="description">
            <% if (question.isPresent()) { %>
                <%= question.get().getDescription() %>
            <% } %>
        </h2>
        <ul>
            <% if (question.isPresent()) { %>
                <% List<Option> options = question.get().getOptions(); %>
                <li>
                    <label id="option1" ><input for="option1" type="radio" name="optionId" value="1" checked/><%= options.get(0).getValue() %></label>
                </li>
                <li>
                    <label id="option2" ><input for="option2" type="radio" name="optionId" value="2"/><%= options.get(1).getValue() %></label>
                </li>
            <% } %>
        </ul>
        <div class="col-lg-12">
            <input type="submit" id="answer" value="Answer">
        </div>
        <input id="questionId" type="hidden" name="questionId" value="1">
        <input id="from" type="hidden" name="from" value="question">
    </div>
    </form>
</div>
</body>
<!-- jQuery -->
<script type="text/javascript"
	src="resources/lib/bootstrap/js/jquery.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="resources/lib/bootstrap/js/bootstrap.min.js"></script>
</html>
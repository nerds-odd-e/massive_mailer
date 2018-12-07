<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="com.odde.massivemailer.model.onlinetest.OnlineTest" %>
<%@ page import="com.odde.massivemailer.model.onlinetest.Question" %>
<%@ page import="java.util.ArrayList" %>

<%
    OnlineTest onlineTest = (OnlineTest) request.getSession().getAttribute("onlineTest");
    Question question = onlineTest.getPreviousQuestion();
	ArrayList<Long> correctOption = question.getCorrectOption();
	Long selectedOption = Long.parseLong((String)request.getAttribute("selectedOption"));
	final String correctClass = "correct";
	final String incorrectClass = "selected incorrect";
	pageContext.setAttribute("question", question);
	pageContext.setAttribute("selectedOption", selectedOption);
	pageContext.setAttribute("correctOption", correctOption);
%>

<t:basic title="Advice">
    <jsp:attribute name="extra_head">
        <link href="/resources/question.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:body>

        <div id="page-wrapper">
            <div class="container-fluid">
                <h1>Advice</h1>
                <h2 id="questionText">${question.getDescription()}</h2>
                <ul>

               <c:forEach items="${question.getOptions()}" var="option">
                    <li>
                         <c:set var="optionId">${option.getLongId()}</c:set>

                            <c:if test="${selectedOption == optionId}">
                               <input type="radio" name="optionId" value="${option.getLongId()}" checked disabled/><label class="selected incorrect"/>${option.getDescription()}</label>
                            </c:if>
                            <c:forEach items="${correctOption}" var="correctoption">
                                <c:if test="${correctoption == optionId}">
                                    <input type="radio" name="optionId" value="${option.getLongId()}" disabled/><label class="correct"/>${option.getDescription()}</label>
                                </c:if>
                            </c:forEach>
                             <c:if test="${selectedOption != optionId}">
                                <input type="radio" name="optionId" value="${option.getLongId()}" disabled/><label> ${option.getDescription()}</label>
                             </c:if>

                    </li>
               </c:forEach>

                </ul>
                <div id="advice" class="jumbotron">${question.getAdvice()}</div>
                <form action="/onlinetest/advice" method="post">
                    <input type="submit" id="next" value="Next" class="btn-primary btn"/>
                </form>
                <div style="float:right;" id="progress">
                    <span id="numberOfAnsweredQuestions">${onlineTest.getNumberOfAnsweredQuestions()}</span>/${onlineTest.getNumberOfQuestions()}
                </div>
            </div>
        </div>
    </jsp:body>
</t:basic>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="com.odde.massivemailer.model.onlinetest.OnlineTest" %>
<%@ page import="com.odde.massivemailer.model.onlinetest.Question" %>
<%@ page import="java.util.*" %>

<t:basic title="Tokkun List">
    <jsp:body>
        ktanaka
        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tokkun List</h1>
                </div>
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12" id="">
                    <form name="tokkun" id="tokkunForm" method="get" action="/tokkun/tokkun.jsp">
                        <button type="button" class="btn btn-default" id="start_button" value="Save" onclick="location.href='/tokkun/tokkun_question.jsp'">Tokkun</button>
                    </form>
                </div>
            </div>
            <div class="row">

                <input type="hidden" id="msg_sent_cnt" value="" />
                <div class="col-lg-12" id="questionTables">
                    <c:forEach items="${questions}" var="question" varStatus="questionStatus">
                        <div class="panel panel-default">
                            <table class="table table-responsive table-bordered">
                                <thead>
                                    <th>Tokkun</th>
                                </thead>
                                <tbody id="tokkunTable">
                                    <tr>
                                        <th style="width:20%">Description</th>
                                        <td id="description">${question.getDescription()}</td>
                                    </tr>
                                    <tr>
                                        <th style="width:20%">Category</th>
                                        <td id="category">${question.getCategoryName()}</td>
                                    </tr>
                                    <tr>
                                        <th style="width:20%">Advice</th>
                                        <td id="advice" style="border:1px solid #ddd">
                                            <!-- TODO -->
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </c:forEach>
                </div>
            </div>

			<div class="modal fade" id="editContactModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Edit Contact</h4>
						</div>
					</div>
				</div>
			</div>
		</div>
    </jsp:body>
</t:basic>

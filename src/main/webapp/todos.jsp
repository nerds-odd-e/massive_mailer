<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%
        String data = "{}";//request.getAttribute("json");
        pageContext.setAttribute("data", data);
%>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
hello

<div id="app">
  {{ message }}
</div>

<script>
var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!',
    data: ${data}
  }
})

</script>
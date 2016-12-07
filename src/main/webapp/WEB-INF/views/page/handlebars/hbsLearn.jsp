<%--
  Created by IntelliJ IDEA.
  User: zhangmengwen
  Date: 2016/10/25
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>handlebars学习</title>
</head>
<body>
<div class="content">
    <button id="change">变变变</button>
    <div class="panel">
        <table border="1">
            <tr>
                <td>张三</td>
            </tr>
        </table>
    </div>
</div>

<script id="template" type="text/x-handlebars-template">
    {{#if list}}
    <tr>
        {{#each list}}
        <td>
            {{this}}
        </td>
        {{/each}}
    </tr>
    {{else}}
    <tr>
        <td>
            {{error}}
        </td>
    </tr>
    {{/if}}
</script>
<script src="<c:url value='/js/lib/require.js'/>"></script>
<script src="<c:url value='/js/config.js'/>"></script>
<script src="<c:url value='/js/handlebars/controller/hbsController.js'/>"></script>
</body>
</html>

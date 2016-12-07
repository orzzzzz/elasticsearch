<%--
  Created by IntelliJ IDEA.
  User: zhangmengwen
  Date: 2016/10/27
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>分页 jquery pagination</title>
</head>
<body>
<div id="list">
    <ul>
        <li>1</li>
    </ul>
</div>
<div id="js-pagination" class="notice-pagination-box flickr"></div>

<script id="template" type="text/x-handlebars-template">
    {{#each list}}
    <li>
        {{this}}
    </li>
    {{/each}}
</script>
<script src="<c:url value='/js/lib/require.js'/>"></script>
<script src="<c:url value='/js/config.js'/>"></script>
<script src="<c:url value='/js/paginations/controller/listController.js'/>"></script>

</body>
</html>

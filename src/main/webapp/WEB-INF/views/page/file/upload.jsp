<%--
  Created by IntelliJ IDEA.
  User: zhangmengwen
  Date: 2016/10/19
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>试一试springmvc文件上传</title>
    <link rel="stylesheet" type="text/css" href="/css/file/upload.css">
</head>
<body>
<div class="content">
    <form id="file-form" action="/file/upload" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <table border="1">
                <tr>
                    <td>
                        <label for="fileName">请选择待上传的文件：</label>
                        <span class="form-group">
                            <input type="file" id="fileName" name="fileName"/>
                        </span>
                    </td>
                </tr>
            </table>
        </div>
        <div class="form-group">
            <input type="submit" id="submit" value="提交"/>
            <input type="reset" id="reset" value="重置"/>
        </div>
    </form>
</div>
</body>
</html>

<%-- 
    Document   : student
    Created on : Mar 9, 2017, 10:28:33 AM
    Author     : Iris Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="${pageContext.servletContext.contextPath}/">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Quản lý sinh viên</h1>
   
    <form:form action="student.htm" modelAttribute="student">
        <div>Mã SV</div>
        <form:input path="masv" readonly="true"/>
        <div>Họ và tên</div>
        <form:input path="name"/>
        <div>Điểm</div>
        <form:input path="mark"/>
        <div>Chuyên ngành</div>
        <form:select path="major">
                <form:option value="APP">Ứng dụng phần mềm</form:option>
                <form:option value="WEB">Thiết kế trang web</form:option>
        </form:select>

        <div>
            <button name="btnInsert">Thêm</button>
            <button name="btnUpdate">Cập nhật</button>         
        </div>
    </form:form>
    <br>            
    
        
    <table border="1">
        <tr>
            <td>Masv</td>
            <td>Name</td>
            <td>Mark</td>
            <td>Major</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <c:forEach var="rows" items="${listStudent}">
            <form action="student/delete.htm">
            <tr>
                <td>${rows.masv}</td>
                <td>${rows.name}</td>
                <td>${rows.mark}</td>
                <td>${rows.major}</td>
                <c:url var="edit" value="student/edit.htm">
                    <c:param name="txtMasv" value="${rows.masv}"/>
                    <c:param name="txtName" value="${rows.name}"/>
                    <c:param name="txtMark" value="${rows.mark}"/>
                    <c:param name="txtMajor" value="${rows.major}"/>
                </c:url>
                <td><a href="${edit}">Edit</a></td>
                <td>
                    <input type="hidden" name="txtMasv" value="${rows.masv}"/>
                    <input type="submit" name="action" value="Delete"/>
                </td>
            </tr>
            </form>
        </c:forEach>    
    </table>
    </body>
</html>

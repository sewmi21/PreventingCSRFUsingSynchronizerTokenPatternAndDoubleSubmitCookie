<%--    IT15086730
        M.M.S.U.Mahagedara.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    
</head>
<body>
<h1>Welcome!</h1>
You are logged in as <%=session.getAttribute("username")%><br/>
<br/>
<form action="validate" method="POST">
  
    <label>User ID :</label>
    <input type="text" name="id" placeholder="UserId"><br/>

    <label>Password</label>
    <input type="password" name="key" placeholder="Password"><br/>

    <input type="hidden" name="tokentxt" id="tokentxt" value="<%=session.getAttribute("csrfToken")%>"/><br/>

    <input type="submit" value="Submit"/>

</form>

</body>
</html>

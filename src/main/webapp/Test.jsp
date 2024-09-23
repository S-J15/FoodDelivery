<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <%
    HttpSession session1 = request.getSession(false);
    String userName = (String) session1.getAttribute("user");
    if (userName != null) {
%>
    <h2>Hello, <%= userName %></h2>
    <a href="logout">Logout</a>
<%
    } else {
        response.sendRedirect("login.jsp");
        return; // Stop further processing
    }
%>
</body>
</html>
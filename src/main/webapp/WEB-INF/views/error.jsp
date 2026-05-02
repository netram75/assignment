<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error - Library Management</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #f0f4f8; color: #333; display: flex; flex-direction: column; min-height: 100vh; }
        header { background: linear-gradient(135deg, #1a237e, #283593); color: white; padding: 16px 32px; display: flex; align-items: center; justify-content: space-between; }
        header h1 { font-size: 1.6rem; }
        nav a { color: #fff; text-decoration: none; margin-left: 20px; padding: 6px 14px; border-radius: 20px; }
        nav a:hover { background: rgba(255,255,255,0.2); }
        .container { max-width: 600px; margin: 80px auto; padding: 0 20px; text-align: center; }
        .error-card { background: white; border-radius: 12px; padding: 48px 32px; box-shadow: 0 4px 16px rgba(0,0,0,0.1); border-top: 4px solid #c62828; }
        .error-icon { font-size: 3rem; margin-bottom: 16px; }
        h2 { font-size: 1.4rem; color: #c62828; margin-bottom: 12px; }
        p { color: #666; margin-bottom: 24px; }
        .btn { display: inline-block; padding: 10px 24px; background: #1a237e; color: white; border-radius: 8px; text-decoration: none; font-weight: 600; }
        .btn:hover { background: #283593; }
    </style>
</head>
<body>
<header>
    <h1>&#128218; Library Management</h1>
    <nav>
        <a href="/books">Books</a>
        <a href="/authors">Authors</a>
    </nav>
</header>
<div class="container">
    <div class="error-card">
        <div class="error-icon">&#9888;</div>
        <h2>Something went wrong</h2>
        <p><c:out value="${errorMessage}" default="An unexpected error occurred."/></p>
        <a href="/books" class="btn">Back to Books</a>
    </div>
</div>
</body>
</html>

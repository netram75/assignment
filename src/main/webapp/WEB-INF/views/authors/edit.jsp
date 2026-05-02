<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Author - Library Management</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #f0f4f8; color: #333; }
        header { background: linear-gradient(135deg, #1a237e, #283593); color: white; padding: 16px 32px; display: flex; align-items: center; justify-content: space-between; box-shadow: 0 2px 8px rgba(0,0,0,0.2); }
        header h1 { font-size: 1.6rem; }
        nav a { color: #fff; text-decoration: none; margin-left: 20px; padding: 6px 14px; border-radius: 20px; transition: background 0.2s; }
        nav a:hover { background: rgba(255,255,255,0.2); }
        .container { max-width: 580px; margin: 40px auto; padding: 0 20px; }
        .card { background: white; border-radius: 12px; padding: 32px; box-shadow: 0 4px 16px rgba(0,0,0,0.1); }
        .card h2 { font-size: 1.3rem; color: #1a237e; margin-bottom: 24px; padding-bottom: 12px; border-bottom: 2px solid #e8eaf6; }
        .form-group { margin-bottom: 18px; }
        label { display: block; font-size: 0.88rem; font-weight: 600; color: #555; margin-bottom: 6px; text-transform: uppercase; letter-spacing: 0.4px; }
        input[type="text"], input[type="number"] {
            width: 100%; padding: 10px 14px; border: 2px solid #e0e0e0; border-radius: 8px;
            font-size: 0.95rem; transition: border-color 0.2s; outline: none;
        }
        input:focus { border-color: #3949ab; }
        .error { color: #c62828; font-size: 0.82rem; margin-top: 4px; }
        .btn-row { display: flex; gap: 12px; margin-top: 24px; }
        .btn { padding: 10px 24px; border-radius: 8px; font-size: 0.95rem; font-weight: 600; cursor: pointer; border: none; text-decoration: none; display: inline-block; transition: all 0.2s; }
        .btn-primary { background: #f57c00; color: white; }
        .btn-primary:hover { background: #e65100; }
        .btn-secondary { background: #e8eaf6; color: #3949ab; }
        .btn-secondary:hover { background: #c5cae9; }
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
    <div class="card">
        <h2>Edit Author</h2>
        <form:form method="post" action="/authors/edit/${author.id}" modelAttribute="author">
            <div class="form-group">
                <label for="name">Full Name</label>
                <form:input path="name" id="name"/>
                <form:errors path="name" cssClass="error"/>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <form:input path="email" id="email"/>
                <form:errors path="email" cssClass="error"/>
            </div>
            <div class="form-group">
                <label for="nationality">Nationality</label>
                <form:input path="nationality" id="nationality"/>
                <form:errors path="nationality" cssClass="error"/>
            </div>
            <div class="form-group">
                <label for="birthYear">Birth Year</label>
                <form:input path="birthYear" id="birthYear" type="number"/>
                <form:errors path="birthYear" cssClass="error"/>
            </div>
            <div class="btn-row">
                <button type="submit" class="btn btn-primary">Update Author</button>
                <a href="/authors" class="btn btn-secondary">Cancel</a>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>

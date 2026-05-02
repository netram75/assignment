<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Authors - Library Management</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #f0f4f8; color: #333; }
        header { background: linear-gradient(135deg, #1a237e, #283593); color: white; padding: 16px 32px; display: flex; align-items: center; justify-content: space-between; box-shadow: 0 2px 8px rgba(0,0,0,0.2); }
        header h1 { font-size: 1.6rem; }
        nav a { color: #fff; text-decoration: none; margin-left: 20px; padding: 6px 14px; border-radius: 20px; transition: background 0.2s; }
        nav a:hover, nav a.active { background: rgba(255,255,255,0.2); }
        .container { max-width: 1100px; margin: 30px auto; padding: 0 20px; }
        .page-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .page-header h2 { font-size: 1.4rem; color: #1a237e; }
        .btn { display: inline-block; padding: 8px 20px; border-radius: 6px; font-size: 0.9rem; font-weight: 600; text-decoration: none; cursor: pointer; border: none; transition: all 0.2s; }
        .btn-primary { background: #1a237e; color: white; }
        .btn-primary:hover { background: #283593; }
        .btn-warning { background: #f57c00; color: white; font-size: 0.8rem; padding: 5px 12px; }
        .btn-warning:hover { background: #e65100; }
        .btn-danger { background: #c62828; color: white; font-size: 0.8rem; padding: 5px 12px; }
        .btn-danger:hover { background: #b71c1c; }
        .alert { padding: 12px 18px; border-radius: 6px; margin-bottom: 18px; font-weight: 500; }
        .alert-success { background: #e8f5e9; color: #2e7d32; border-left: 4px solid #43a047; }
        .alert-danger { background: #ffebee; color: #c62828; border-left: 4px solid #e53935; }
        table { width: 100%; border-collapse: collapse; background: white; border-radius: 10px; overflow: hidden; box-shadow: 0 2px 10px rgba(0,0,0,0.08); }
        thead { background: #1a237e; color: white; }
        th { padding: 14px 16px; text-align: left; font-size: 0.88rem; letter-spacing: 0.5px; text-transform: uppercase; }
        td { padding: 12px 16px; border-bottom: 1px solid #e8eaf6; font-size: 0.92rem; }
        tr:last-child td { border-bottom: none; }
        tr:hover td { background: #f5f5ff; }
        .badge { display: inline-block; padding: 3px 10px; border-radius: 12px; font-size: 0.78rem; font-weight: 600; background: #e8eaf6; color: #3949ab; }
        .actions { display: flex; gap: 6px; }
    </style>
</head>
<body>
<header>
    <h1>&#128218; Library Management</h1>
    <nav>
        <a href="/books">Books</a>
        <a href="/authors" class="active">Authors</a>
    </nav>
</header>

<div class="container">
    <div class="page-header">
        <h2>All Authors</h2>
        <a href="/authors/add" class="btn btn-primary">+ Add New Author</a>
    </div>

    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <c:choose>
        <c:when test="${empty authors}">
            <div style="text-align:center; padding:60px; color:#9e9e9e;">
                <p style="font-size:1.1rem; margin-bottom:16px;">No authors found.</p>
                <a href="/authors/add" class="btn btn-primary">Add First Author</a>
            </div>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Nationality</th>
                        <th>Birth Year</th>
                        <th>Books</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="a" items="${authors}" varStatus="s">
                        <tr>
                            <td>${s.count}</td>
                            <td><strong>${a.name}</strong></td>
                            <td>${a.email}</td>
                            <td><span class="badge">${a.nationality}</span></td>
                            <td>${a.birthYear}</td>
                            <td>${a.books.size()}</td>
                            <td>
                                <div class="actions">
                                    <a href="/authors/edit/${a.id}" class="btn btn-warning">Edit</a>
                                    <a href="/authors/delete/${a.id}"
                                       class="btn btn-danger"
                                       onclick="return confirm('Delete this author and all their books?')">Delete</a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>

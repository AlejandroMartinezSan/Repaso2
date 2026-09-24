<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Resultado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Resultado</h1>

    <% Integer filas = (Integer) request.getAttribute("filas"); %>
    <% if (filas != null && filas > 0) { %>
        <div class="alert alert-success">Datos guardados correctamente.</div>
    <% } %>

    <table class="table table-bordered w-50">
        <tr><th>Correo</th><td>${correo}</td></tr>
        <tr><th>Nombre</th><td>${nombre}</td></tr>
        <tr><th>Género</th><td>${sexo}</td></tr>
    </table>

    <a href="index.html" class="btn btn-secondary">Volver</a>
</div>
</body>
</html>
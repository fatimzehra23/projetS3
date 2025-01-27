<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Notes</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Gestion des Notes</h1>

        <!-- Bouton pour ajouter une nouvelle note -->
        <div class="d-flex justify-content-end mb-3">
            <a href="notes?action=new" class="btn btn-success">Ajouter une Note</a>
        </div>

        <!-- Tableau des notes -->
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Titre</th>
                    <th>Contenu</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="note" items="${notes}">
                    <tr>
                        <td>${note.id}</td>
                        <td>${note.title}</td>
                        <td>${note.content}</td>
                        <td>
                            <!-- Lien pour modifier une note -->
                            <a href="notes?action=edit&id=${note.id}" class="btn btn-primary btn-sm">Modifier</a>
                            <!-- Lien pour supprimer une note -->
                            <a href="notes?action=delete&id=${note.id}" class="btn btn-danger btn-sm"
                               onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette note ?');">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

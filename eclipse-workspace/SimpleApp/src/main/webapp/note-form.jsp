<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${note == null ? "Ajouter une Note" : "Modifier une Note"}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">${note == null ? "Ajouter une Note" : "Modifier une Note"}</h1>

        <!-- Formulaire pour ajouter ou modifier une note -->
        <form action="notes" method="post" class="mt-4">
            <!-- Champ caché pour envoyer l'ID (utilisé pour la modification) -->
            <c:if test="${note != null}">
                <input type="hidden" name="id" value="${note.id}">
            </c:if>

            <!-- Champ pour le titre -->
            <div class="mb-3">
                <label for="title" class="form-label">Titre</label>
                <input type="text" class="form-control" id="title" name="title"
                       value="${note != null ? note.title : ''}" required>
            </div>

            <!-- Champ pour le contenu -->
            <div class="mb-3">
                <label for="content" class="form-label">Contenu</label>
                <textarea class="form-control" id="content" name="content" rows="5" required>${note != null ? note.content : ''}</textarea>
            </div>

            <!-- Boutons -->
            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">${note == null ? "Ajouter" : "Modifier"}</button>
                <a href="notes" class="btn btn-secondary">Annuler</a>
            </div>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

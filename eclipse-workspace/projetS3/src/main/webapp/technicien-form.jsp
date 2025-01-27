<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>
        <c:choose>
            <c:when test="${not empty technicien}">Modifier Technicien</c:when>
            <c:otherwise>Ajouter Technicien</c:otherwise>
        </c:choose>
    </title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('${pageContext.request.contextPath}/resources/backGrunnnd.png');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-position: center;
            margin: 0;
            padding: 0;
        }

        header {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 200px;
        }

        header img {
            width: 100%;
            height: 200px;
        }

        nav {
            background-color: #21949e;
            padding: 10px 0;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        nav ul {
            list-style: none;
            display: flex;
            justify-content: center;
            margin: 0;
            padding: 0;
        }

        nav ul li {
            margin: 0 15px;
        }

        nav ul li a {
            text-decoration: none;
            color: white;
            font-weight: bold;
        }

        nav ul li a:hover {
            text-decoration: underline;
        }

        .form-container {
            background-color: #eff9f9;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: 50px auto;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input, select, button {
            width: 95%;
            padding: 10px;
            margin-top: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            background-color: #21949e;
            color: white;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            border: none;
            margin-top: 20px;
        }

        button:hover {
            background-color: darkblue;
        }
    </style>
</head>

<body>
    <header>
        <img src="${pageContext.request.contextPath}/resources/Header wooow wow.png" alt="Gestion des Techniciens">
    </header>

    <nav>
        <ul>
            <li><a href="adminDashboard.jsp">Accueil</a></li>
            <li><a href="TechnicienServlet?action=list">Liste des Techniciens</a></li>
            <li><a href="reclamationsRespo?action=list">Consulter Réclamations</a></li>
            <li><a href="interventions?action=list">Consulter Interventions</a></li>
        </ul>
    </nav>

    <div class="form-container">
        <h2>
            <c:choose>
                <c:when test="${not empty technicien}">Modifier Technicien</c:when>
                <c:otherwise>Ajouter Technicien</c:otherwise>
            </c:choose>
        </h2>
        <form action="TechnicienServlet" method="post">
            <input type="hidden" name="action" value="${not empty technicien ? 'edit' : 'add'}">

            <label for="nomTech">Nom:</label>
            <input type="text" id="nomTech" name="nomTech" value="${technicien.nomTech}" required>

            <label for="specialite">Spécialité:</label>
            <input type="text" id="specialite" name="specialite" value="${technicien.specialite}" required>

            <label for="disponibilite">Disponibilité:</label>
            <input type="text" id="disponibilite" name="disponibilite" value="${technicien.disponibilite}" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${technicien.email}" required>

            <label for="telephone">Téléphone:</label>
            <input type="tel" id="telephone" name="telephone" value="${technicien.telephone}" required>

            <button type="submit">
                <c:choose>
                    <c:when test="${not empty technicien}">Modifier</c:when>
                    <c:otherwise>Ajouter</c:otherwise>
                </c:choose>
            </button>
        </form>
    </div>
</body>

</html>

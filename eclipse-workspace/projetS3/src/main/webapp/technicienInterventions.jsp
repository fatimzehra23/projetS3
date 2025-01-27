<%@ page import="interventions.InterventionBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Gestion des Interventions</title>

    <!-- Inclusion de Font Awesome pour les icônes -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

    <style>
        /* Styles globaux */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-image: url('${pageContext.request.contextPath}/resources/backGrunnnd.png');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-position: center;
        }

        /* Header */
        header {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 150px;
        }

        header img {
            width: 93%;
            height: 150px;
        }

        /* Barre de navigation */
        nav {
            background-color: #21949e;
            padding: 10px 0;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            width: 100%;
        }

        .nav-container {
            width: 100%;
            margin: 0 auto;
        }

        nav ul {
            list-style: none;
            display: flex;
            justify-content: center;
        }

        nav ul li {
            margin: 0 15px;
        }

        nav ul li a {
            text-decoration: none;
            color: white;
            font-weight: bold;
            display: flex;
            align-items: center;
        }

        nav ul li a:hover {
            text-decoration: underline;
        }

        /* Contenu principal */
        main {
            padding: 20px;
        }

        h1 {
            text-align: center;
            margin-bottom: 40px;
        }

        /* Container pour le tableau */
        .table-container {
            background-color: #eff9f9;
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 80%;
            margin: auto;
        }

        /* Tableau */
        table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 15px;
            overflow: hidden;
        }

        th, td {
            padding: 10px;
            text-align: center;
            border: 2px solid #ddd;
        }

        th {
            background-color: #21949e;
            color: white;
        }

        /* Icônes de priorité */
        .priority-urgent {
            color: red;
        }

        .priority-normal {
            color: orange;
        }

        .priority-low {
            color: yellow;
        }

        /* Boutons */
        button {
            padding: 10px 20px;
            font-size: 14px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            background-color: #f5bc24;
            color: white;
        }

        /* Dropdown et input styles */
        .form-group {
            margin: 0;
        }
                i {
        margin-right: 8px; /* Espace entre l'icône et le texte */
    }
    </style>
</head>

<body>
    <!-- Header -->
    <header>
        <img src="${pageContext.request.contextPath}/resources/Header wooow wow.png" alt="Espace Numérique de l'Étudiant">
    </header>

    <!-- Barre de navigation -->
    <nav>
        <div class="nav-container">
            <ul>
                <li><a href="technicianDashboard.jsp"> <i class="fas fa-home"></i></a></li>
                <li><a href="technicienInterventions?action=list"> <i class="fas fa-tasks"></i> Consulter Interventions</a></li>
            </ul>
        </div>
    </nav>

    <!-- Contenu principal -->
    <main>
        <h1>Liste des Interventions Affectées</h1>

        <c:choose>
            <c:when test="${empty interventions}">
                <p>Aucune intervention affectée n'a été trouvée pour le technicien.</p>
            </c:when>
            <c:otherwise>
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>Priorité</th>
                                <th>ID</th>
                                <th>Description</th>
                                <th>Date de Création</th>
                                <th>Date de Terminaison</th>
                                <th>Statut</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="intervention" items="${interventions}">
                                <tr>
                                    <td>
                                        <i class="fas ${intervention.priority == 'Urgent' ? 'fa-exclamation-circle priority-urgent' : 
                                                    intervention.priority == 'Normal' ? 'fa-exclamation-triangle priority-normal' : 
                                                    'fa-info-circle priority-low'}"></i>
                                    </td>

                                    <td>${intervention.idInterv}</td>
                                    <td>${intervention.description}</td>
                                    <td>${fn:substring(intervention.dateCreation, 0, 10)}</td>
                                    <td>
                                        <form method="post" action="technicienInterventions">
                                            <input type="hidden" name="idInterv" value="${intervention.idInterv}" />
                                            <input type="date" name="dateTerminaison" value="${fn:substring(intervention.dateTerminaison, 0, 10)}" required />
                                    </td>
                                    <td>
                                        <select name="statut" required>
                                            <option value="en cours" ${intervention.statut == 'en cours' ? 'selected' : ''}>En cours</option>
                                            <option value="terminée" ${intervention.statut == 'terminée' ? 'selected' : ''}>Terminée</option>
                                            <option value="en attente" ${intervention.statut == 'en attente' ? 'selected' : ''}>En attente</option>
                                        </select>
                                    </td>
                                    <td>
                                        <button type="submit">Mettre à jour</button>
                                    </td>
                                        </form>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </main>
</body>

</html>

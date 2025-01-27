<%@ page import="reclamations.ReclamationBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Gestion des Réclamations</title>
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
            width: 95%;
            height: 150px;
        }

        /* Barre de navigation */
        nav {
            background-color: #21949e; /* Couleur turquoise */
            padding: 10px 0;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        .nav-container {
            width: 95%; /* Aligné avec la largeur de l'image */
            margin: 0 auto; /* Centrer la barre */
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

        th {
            background-color: #21949e; /* Couleur de fond */
            color: white; /* Texte blanc */
        }

        h2 {
            margin-bottom: 40px;
            margin-left: 150px;
            font-size: 30px;
            color: #333;
        }

        /* Table des réclamations */
        .table-container {
            background-color: #eff9f9;
            border-radius: 15px;
            padding: 20px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            margin: 20px auto;
            width: 90%;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 15px;
        }

        th,
        td {
            padding: 10px;
            text-align: center;
            border: 2px solid #ddd;
        }

        /* Boutons */
        .btn {
            padding: 10px 20px;
            font-size: 14px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            display: inline-block;
        }

        .btn-add {
            background-color: #21949e;
            color: white;
            margin-left: 150px;
        }

        /* Icônes d'actions */
        i.fa {
            font-size: 18px;
            margin: 0 5px;
            cursor: pointer;
        }

        i.fa:hover {
            opacity: 0.8;
        }

        /* Message de confirmation */
        .message {
            color: green;
            font-weight: bold;
        }

        th:last-child,
        td:last-child {
            width: 150px;
        }

        i.fa {
            font-size: 18px;
            margin: 0 5px;
            cursor: pointer;
        }

        i.fa:hover {
            opacity: 0.8;
        }

        .message {
            color: green;
            font-weight: bold;
        }

        .urgent {
            color: red;
        }

        .normal {
            color: orange;
        }

        .faible {
            color: yellow;
        }

        th:last-child,
        td:last-child {
            width: 150px;
        }

        .urgent,
        .normal,
        .faible {
            font-size: 30px;
        }

        /* Icon styles for status */
        .status-completed {
            color: green;
        }

        .status-in-progress {
            color: orange;
        }

        .status-pending {
            color: blue;
        }

        /* Priority color styles */
        .priority-urgent {
            color: red;
        }

        .priority-normal {
            color: orange;
        }

        .priority-low {
            color: yellow;
        }
                i {
        margin-right: 8px; /* Espace entre l'icône et le texte */
        }
                .col-3 {
            width: 300px;
            word-wrap: break-word;
            }

    </style>
</head>

<body>
    <!-- Header avec une image pleine largeur -->
    <header>
        <img src="${pageContext.request.contextPath}/resources/Header wooow wow.png" alt="Espace Numérique de l'Étudiant">
    </header>

    <!-- Barre de navigation -->
    <nav>
        <div class="nav-container">
        <ul>
            <li><a href="adminDashboard.jsp"> <i class="fas fa-home"></i></a></li>        
            <li><a href="reclamationsRespo?action=list"><i class="fas fa-file-alt"></i> Consulter Réclamations</a></li>
            <li><a href="intervention-form.jsp"><i class="fas fa-plus-circle"></i> Ajouter intervention</a></li>
            <li><a href="interventions?action=list"><i class="fas fa-tasks"></i> Consulter Interventions</a></li>
            <li><a href="interventions?statut=terminee"><i class="fas fa-star"></i> Consulter évaluations</a></li>
            <li><a href="TechnicienServlet?action=list"><i class="fas fa-users"></i> Gérer techniciens</a></li>
        </ul>
        </div>
    </nav>

    <h2>Liste des Interventions Terminées</h2>

    <!-- Table des interventions avec un conteneur arrondi -->
    <div class="table-container">
        <table border="1">
            <thead>
                <tr>
                    <th>Priorité</th>
                    <th>ID</th>
                    <th>Description</th>
                    <th>Date de création</th>
                    <th>Date de terminaison</th>
                    <th>Nom du technicien</th>
                    <th>Evaluation</th>
                    <th style="width: 400px;">Commentaire</th>

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
                        <td>${intervention.dateCreation}</td>
                        <td>${intervention.dateTerminaison}</td>
                        <td>
                            <c:choose>
                                <c:when test="${empty intervention.nomTech}">
                                    <form action="choisirTechnicien" method="get">
                                        <input type="hidden" name="idInterv" value="${intervention.idInterv}" />
                                        <a href="choisirTechnicien?idInterv=${intervention.idInterv}">Choisir</a>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    ${intervention.nomTech}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${intervention.evaluation}</td>
                        <td>${intervention.commentaire}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Lien d'ajout sous forme de bouton -->
    <div style="margin-top: 20px;">
        <a href="intervention-form.jsp" class="btn btn-add">Ajouter une nouvelle intervention</a>
    </div>

    <!-- Message de confirmation -->
    <c:if test="${not empty sessionScope.message}">
        <div class="message">
            ${sessionScope.message}
        </div>
        <c:remove var="message" scope="session" />
    </c:if>
</body>
</html>

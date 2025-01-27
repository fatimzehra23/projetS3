<%@ page import="reclamations.ReclamationBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Gestion des R�clamations</title>
    <!-- Inclusion de Font Awesome pour les ic�nes -->
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
            height: 200px;
        }

        header img {
            width: 100%;
            height: 200px;
        }

        /* Barre de navigation */
        nav {
            background-color: #21949e; /* Couleur turquoise */
            padding: 10px 0;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        .nav-container {
            width: 100%; /* Align� avec la largeur de l'image */
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

        /* Table des r�clamations */
        table {
            width: 80%;
            border-collapse: collapse;
            margin: auto;
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

        /* Ic�nes d'actions */
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
        th:last-child, td:last-child {
            width: 150px; /* Ajustez la valeur selon vos besoins */
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

        th:last-child, td:last-child {
            width: 150px;
        }

        .urgent, .normal, .faible {
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

    </style>
</head>

<body>
    <!-- Header avec une image pleine largeur -->
    <header>
        <img src="${pageContext.request.contextPath}/resources/Header wooow wow.png" alt="Espace Num�rique de l'�tudiant">
    </header>

    <!-- Barre de navigation -->
    <nav>
        <div class="nav-container">
            <ul>
                <li><a href="adminDashboard.jsp">Accueil</a></li>
                <li><a href="reclamationsRespo?action=list">Consulter R�clamations</a></li>
                <li><a href="intervention-form.jsp">Ajouter une Intervention</a></li>
                <li><a href="interventions?action=list">Consulter Interventions</a></li>
            <li><a href="interventions?statut=terminee">Consulter les �valuations</a></li>           
                
                <li><a href="TechnicienServlet?action=list">G�rer les techniciens</a></li>
                
            </ul>
        </div>
    </nav>

    <h2>Liste des Interventions</h2>

    <!-- Table des interventions -->
<table border="1">
    <thead>
        <tr>
            <th>Priorit�</th>  
            <th>ID</th>
            <th>Description</th>
            <th>Date de cr�ation</th>
            <th>Date de terminaison</th>
            <th>Nom du technicien</th>
            <th>Evaluation</th>
            <th>Commentaire</th>
            
            
            
        </tr>
    </thead>
    <tbody>
        <c:forEach var="intervention" items="${interventions}">
    <c:if test="${intervention.statut == 'termin�e'}">
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
            <td>${intervention.nomTech}</td>
            <td>${intervention.evaluation}</td>
            <td>${intervention.commentaire}</td>

        </tr>
    </c:if>
</c:forEach>

    </tbody>
</table>
    

    <!-- Lien d'ajout sous forme de bouton -->
    <div style="margin-top: 20px;">
        <a href="intervention-form.jsp" class="btn btn-add">Ajouter une nouvelle intervention</a>
    </div>

    <!-- Message de confirmation -->
    <c:if test="${not empty sessionScope.message}">
        <div class="message">
            ${sessionScope.message}
        </div>
        <c:remove var="message" scope="session"/>
    </c:if>
</body>
</html>

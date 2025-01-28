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
            height: 180px;
        }

        header img {
            width: 100%;
            height: 170px;
        }

        /* Barre de navigation */
        nav {
            background-color: #21949e; /* Couleur turquoise */
            padding: 13px 0;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        .nav-container {
            width: 100%; /* Aligné avec la largeur de l'image */
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
            margin-bottom: 20px;
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
            width: 80%;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 15px;
            overflow: hidden;
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
            background-color: #f5bc24;
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

        .urgent {
            color: red;
        }

        .normal {
            color: orange;
        }

        .faible {
            color: yellow;
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

    <div class="container mt-5">
        <h2 class="text-center">Gestion des Techniciens</h2>

        <!-- Table des techniciens dans un conteneur avec coins arrondis et couleur #eff9f9 -->
        <div class="table-container">
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Spécialité</th>
                        <th>Disponibilité</th>
                        <th>Email</th>
                        <th>Téléphone</th>
                        <th style="width: 100px;">Nom du Technicien</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="technicien" items="${techniciens}">
                        <tr>
                            <td>${technicien.idTech}</td>
                            <td>${technicien.nomTech}</td>
                            <td>${technicien.specialite}</td>
                            <td>${technicien.disponibilite}</td>
                            <td>${technicien.email}</td>
                            <td>${technicien.telephone}</td>
                            <td>
                                <a href="TechnicienServlet?action=edit&idTech=${technicien.idTech}" title="Modifier">
                                    <i class="fa fa-edit" style="color: #4CAF50;"></i>
                                </a>
                                
                                <a href="TechnicienServlet?action=delete&idTech=${technicien.idTech}" title="Supprimer"
                                    onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette interventions?');">
                                    <i class="fa fa-trash" style="color: #E74C3C;"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Bouton pour ajouter un technicien -->
        <div class="mt-3 text-center">
            <a href="technicien-form.jsp" class="btn btn-add">Ajouter un Technicien</a>
        </div>
    </div>
</body>
</html>

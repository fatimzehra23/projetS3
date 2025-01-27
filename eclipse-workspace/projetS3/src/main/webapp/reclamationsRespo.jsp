<%@ page import="java.util.List" %>
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
            width: 100%;
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
            margin-bottom: 30px;
            margin-left: 150px;
            font-size: 30px;
            color: #333;
        }

        /* Container pour le tableau */
        .table-container {
            background-color: #eff9f9; /* Couleur de fond du container */
            padding: 20px;
            border-radius: 15px; /* Coins arrondis du container */
            width: 80%;
            margin: auto;
        }

        /* Table des réclamations */
        table {
            width: 100%;
            border-collapse: collapse;
            margin: auto;
            border-radius: 15px; /* Coins arrondis du tableau */
            overflow: hidden; /* Pour que les coins arrondis soient visibles */
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

        th:last-child, td:last-child {
            width: 150px; /* Ajustez la valeur selon vos besoins */
        }
        
                a i {
            margin-right: 10px; /* Espace entre l'icône et le texte */
        }
        .col-2 {
            width: 400px;
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

    <!-- Main content with reclamations table -->
    <main>
        <h2>Liste des Réclamations</h2>
        <div class="table-container">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Sujet</th>
                    <th class="col-2">Description</th>
                    <th>Date de Création</th>
                    <th>Bâtiment</th>
                    <th>Étage</th>
                    <th>Numéro de Chambre</th>
                    <th>Email</th> <!-- Nouvelle colonne pour l'email -->
                    <th>Action</th>
                </tr>
                <c:forEach var="reclamation" items="${listReclamation}">
                    <tr>
                        <td>${reclamation.idRecl}</td>
                        <td>${reclamation.sujet}</td>
                        <td>${reclamation.description}</td>
                        <td><fmt:formatDate value="${reclamation.dateCreation}" pattern="dd/MM/yyyy"/></td>
                        <td>${reclamation.batiment}</td>
                        <td>${reclamation.etage}</td>
                        <td>${reclamation.numChambre}</td>
                        <td>${reclamation.email}</td> <!-- Affichage de l'email -->
                        <td>
                            <!-- Lien pour supprimer la réclamation avec une icône de poubelle en rouge -->
                            <a href="reclamationsRespo?action=delete&idRecl=${reclamation.idRecl}">
                                <i class="fa fa-trash" style="color: red;" title="Supprimer"></i>
                            </a>
                            <!-- Lien pour créer une intervention avec une icône de plus en vert -->
                            <a href="intervention-form.jsp?idRecl=${reclamation.idRecl}&sujet=${reclamation.sujet}&description=${reclamation.description}&batiment=${reclamation.batiment}&etage=${reclamation.etage}&numChambre=${reclamation.numChambre}">
                                <i class="fa fa-plus" style="color: green;" title="Créer Intervention"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <!-- Display success message if set in the session -->
        <c:if test="${not empty message}">
            <p>${message}</p>
        </c:if>
    </main>
</body>
</html>

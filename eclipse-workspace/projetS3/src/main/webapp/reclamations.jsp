<%@ page import="java.util.List" %>
<%@ page import="reclamations.ReclamationBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Gestion des Réclamations</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('${pageContext.request.contextPath}/resources/backGrunnnd.png');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-position: center;
        }

        header img {
            width: 100%;
            height: 160px;
        }

        nav {
            background-color: #21949e;
            padding: 1px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
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
        }

        nav ul li a:hover {
            text-decoration: underline;
        }

        h2 {
            margin-bottom: 40px;
            text-align: center;
            font-size: 30px;
            color: #333;
        }

        .container {
            background-color: #eff9f9;
            border-radius: 15px;
            padding: 20px;
            margin: 20px auto;
            width: 90%;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 15px;
            overflow: hidden; /* Pour arrondir les bords */
            background-color: white;
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

        .btn {
            padding: 10px 20px;
            font-size: 14px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            background-color: #21949e;
            color: white;
            display: inline-block;
            margin: 20px 0;
            text-align: center;
        }

        .btn:hover {
            background-color: #1b7b87;
        }
        
        .col-1 {
            width: 150px;
            }
                .col-2 {
            width: 300px;
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
            text-align: center;
            margin-top: 20px;
        }
        i {
        margin-right: 8px; /* Espace entre l'icône et le texte */
    }
    </style>
</head>

<body>
    <header>
        <img src="${pageContext.request.contextPath}/resources/Header wooow wow.png" alt="Espace Numérique de l'Étudiant">
    </header>

    <nav>
        <ul>
            <li><a href="home.jsp"> <i class="fas fa-home"></i>  Accueil</a></li>
            <li><a href="reclamations?action=list"> <i class="fa fa-list"></i> Consulter Réclamations</a></li>
            <li><a href="reclamation-form.jsp"> <i class="fa fa-pencil-alt"></i> Effectuer Réclamation</a></li>
            <li><a href="interventionStdnt?action=list"> <i class="fa fa-tools"></i> Consulter Interventions</a></li>
        </ul>
    </nav>

    <h2>Liste des Réclamations</h2>

    <div class="container">
        <table>
            <thead>
                <tr>
                    <th class="col-1">Sujet</th>
                    <th class="col-2">Description</th>
                    <th>Date de Création</th>
                    <th>Bâtiment</th>
                    <th>Étage</th>
                    <th>Num Chambre</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="reclamation" items="${listReclamation}">
                    <tr>
                        <td>${reclamation.sujet}</td>
                        <td>${reclamation.description}</td>
                        <td><fmt:formatDate value="${reclamation.dateCreation}" pattern="dd-MM-yyyy" /></td>
                        <td>${reclamation.batiment}</td>
                        <td>${reclamation.etage}</td>
                        <td>${reclamation.numChambre}</td>
                        <td>
                            <a href="reclamations?action=edit&idRecl=${reclamation.idRecl}" title="Modifier">
                                <i class="fa fa-edit" style="color: #4CAF50;"></i>
                            </a>
                            |
                            <a href="reclamations?action=delete&idRecl=${reclamation.idRecl}" title="Supprimer"
                               onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette réclamation?');">
                                <i class="fa fa-trash" style="color: #E74C3C;"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="reclamations?action=new" class="btn">Ajouter une nouvelle réclamation</a>
    </div>

    <c:if test="${not empty sessionScope.message}">
        <div class="message">
            ${sessionScope.message}
        </div>
        <c:remove var="message" scope="session" />
    </c:if>
</body>

</html>

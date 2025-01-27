<%@ page import="java.util.List" %>
<%@ page import="reclamations.ReclamationBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Gestion des Réclamations</title>
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

        main {
            padding: 20px;
        }

        th {
            background-color: #21949e;
            color: white;
        }

        h2 {
            margin-bottom: 40px;
            margin-left: 150px;
            font-size: 30px;
            color: #333;
        }

        /* Container avec coin arrondi et couleur de fond #eff9f9 */
        .container {
            background-color: #eff9f9;
            border-radius: 15px;
            padding: 20px;
            margin: 20px auto;
            width: 80%;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: auto;
            border-radius: 15px; /* Coins arrondis du tableau */
        }

        th, td {
            padding: 10px;
            text-align: center;
            border: 2px solid #ddd;
        }

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
        .btn-primary {
             background-color: #f5bc24 ;
             }
        .col-1 {
            width: 350px; /* Réduit la taille de cette colonne */
        }    
        .col-2 {
            width: 200px; /* Réduit la taille de cette colonne */
        }
    </style>
</head>

<body>
    <header>
        <img src="${pageContext.request.contextPath}/resources/Header wooow wow.png" alt="Espace Numérique de l'Étudiant">
    </header>

    <nav>
        <div class="nav-container">
        <ul>
            <li><a href="home.jsp"> <i class="fas fa-home"></i>  Accueil</a></li>
            <li><a href="reclamations?action=list"> <i class="fa fa-list"></i> Consulter Réclamations</a></li>
            <li><a href="reclamation-form.jsp"> <i class="fa fa-pencil-alt"></i> Effectuer Réclamation</a></li>
            <li><a href="interventionStdnt?action=list"> <i class="fa fa-tools"></i> Consulter Interventions</a></li>
        </ul>
        </div>
    </nav>
        <h2>Liste des Interventions</h2>

    <div class="container">

        <table>
            <thead>
                <tr>
                    <th>Priorité</th>
                    <th class="col-1">Description</th>
                    <th>Téchnicien</th>
                    <th class="col-2">Date de Création</th>
                    <th>Date de Términaison</th>
                    <th>Statut</th>
                    <th>Evaluation</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="intervention" items="${interventionStdnt}">
                    <tr>
                        <td>
                            <i class="fas ${intervention.priority == 'Urgent' ? 'fa-exclamation-circle priority-urgent' : 
                                       intervention.priority == 'Normal' ? 'fa-exclamation-triangle priority-normal' : 
                                       'fa-info-circle priority-low'}"></i>
                        </td>
                        <td>${intervention.description}</td>
                        <td>${intervention.nomTech}</td>
                        <td>${intervention.dateCreation}</td>
                        <td>${intervention.dateTerminaison}</td>
                        <td>
                            <i class="fas ${intervention.statut == 'terminée' ? 'status-completed fa-check-circle' : 
                                       intervention.statut == 'en cours' ? 'status-in-progress fa-spinner fa-pulse' : 
                                       'status-pending fa-clock'}"></i>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${intervention.statut == 'terminée'}">
                                    <form action="EvaluationServlet" method="GET" style="display: inline;">
                                        <!-- Ajouter des paramètres si nécessaire -->
                                        <input type="hidden" name="interventionId" value="${intervention.idInterv}" />
                                        <button type="submit" class="btn btn-primary btn-sm">Évaluer</button>
                                    </form>
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <c:if test="${empty interventionStdnt}">
            <p>Aucune intervention trouvée.</p>
        </c:if>
    </div>

</body>
</html>

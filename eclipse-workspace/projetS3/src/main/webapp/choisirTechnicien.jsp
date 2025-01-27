<%@ page import="java.util.List" %>
<%@ page import="interventions.InterventionBean" %>
<%@ page import="interventions.TechnicienBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Choisir un Technicien</title>
    <style>
        /* Style global */
        body {
            font-family: Arial, sans-serif;
            background-color: #f3f4f6;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        /* Conteneur principal */
        .container {
            background-color: #eff9f9;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 80%;
            margin: auto;
        }

        h2, h3 {
            text-align: center;
            color: #333;
        }

        /* Style pour le tableau */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            border-radius: 10px;
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

        td {
            background-color: #fff;
        }

        /* Style pour le bouton */
        .btn {
            padding: 10px 20px;
            font-size: 14px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            background-color: #f5bc24;
            color: white;
            margin-top: 20px;
            display: block;
            margin-left: auto;
            margin-right: auto;
        }

        .btn:hover {
            background-color: darkgreen;
        }

        /* Style pour le message */
        .message {
            color: red;
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Choisir un Technicien pour l'Intervention</h2>

        <!-- Affichage de l'ID de l'intervention -->
        <h3>Intervention ID: ${param.idInterv}</h3>

        <!-- Formulaire pour sélectionner un technicien -->
        <form action="interventions" method="post">
            <input type="hidden" name="action" value="assignTechnician" />
            <input type="hidden" name="idInterv" value="${param.idInterv}"/>

            <table>
                <thead>
                    <tr>
                        <th>ID Technicien</th>
                        <th>Nom du Technicien</th>
                        <th>Spécialité</th>
                        <th>Disponibilité</th>
                        <th>Choisir</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Itération sur les techniciens -->
                    <c:forEach var="technicien" items="${techniciens}">
                        <tr>
                            <td>${technicien.idTech}</td>
                            <td>${technicien.nomTech}</td>
                            <td>${technicien.specialite}</td>
                            <td>${technicien.disponibilite}</td>
                            <td>
                                <input type="radio" name="nomTech" value="${technicien.nomTech}" required />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Bouton pour soumettre le choix -->
            <button type="submit" class="btn">Assigner le Technicien</button>
        </form>

        <!-- Message de confirmation ou d'erreur -->
        <c:if test="${not empty sessionScope.message}">
            <div class="message">${sessionScope.message}</div>
            <c:remove var="message" scope="session"/>
        </c:if>
    </div>
</body>
</html>

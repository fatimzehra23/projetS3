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
            margin-bottom: 40px;
            margin-left: 150px;
            font-size: 30px;
            color: #333;
        }

        /* Table des réclamations */
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
                .form-container {

            background-color: #eff9f9;

            padding: 20px;

            border: 1px solid #ddd;

            border-radius: 8px;

            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);

            width: 100%;

            max-width: 500px;	

            margin-left: 360px;

            margin-top: 25px;

        }



        h2 {

            margin-left: 60px;

        }



        label {

            display: block;

            margin-top: 10px;

            font-weight: bold;

        }



        input, textarea, select {

            width: 100%;

            padding: 10px;

            margin-top: 5px;

            border: 1px solid #ccc;

            border-radius: 4px;

            font-size: 14px;

        }



        textarea {

            resize: vertical;

            height: 150px;

        }



        button {

            width: 100%;

            padding: 10px;

            margin-top: 20px;

            font-size: 16px;

            background-color: #21949e;

            color: white;

            border: none;

            border-radius: 4px;

            cursor: pointer;

        }



        button:hover {

            background-color: darkblue;

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
                <li><a href="adminDashboard.jsp">Accueil</a></li>
                <li><a href="reclamationsRespo?action=list">Consulter Réclamations</a></li>
                <li><a href="intervention-form.jsp">Ajouter une intervention</a></li>
                <li><a href="interventions?action=list">Consulter les Interventions</a></li>
               <li><a href="interventions?statut=terminee">Consulter les évaluations</a></li>           
                <li><a href="TechnicienServlet?action=list">Gérer les techniciens</a></li>
                
            </ul>
        </div>
    </nav>

<div class="form-container">
<h2>${intervention == null ? "Ajouter" : "Modifier"} une Intervention</h2>
            <form action="interventions" method="post">
            <input type="hidden" name="action" value="add">
            
                <!-- Ptiorité -->
                <label for="priority">Priorité :</label>
                <select id="priority" name="priority" required>
                    <option value="Urgent" <c:if test="${param.priority == 'Urgent'}">selected</c:if>>Urgent</option>
                    <option value="Normal" <c:if test="${param.priority == 'Normal'}">selected</c:if>>Normal</option>
                    <option value="Faible" <c:if test="${param.priority == 'Faible'}">selected</c:if>>Faible</option>
                </select>
                <!-- Description -->
                <label for="description">Description :</label>
                <textarea id="description" name="description" rows="4" cols="50" required>
                    <c:if test="${not empty param.description}">${param.description}</c:if>
                </textarea>

                <!-- Date de création -->
                <label for="dateCreation">Date de création :</label>
                <input type="date" id="dateCreation" name="dateCreation" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" required>

                <!-- Date de terminaison -->
                <label for="dateTerminaison">Date de terminaison :</label>
                <input type="date" id="dateTerminaison" name="dateTerminaison" value="<c:if test='${not empty param.dateTerminaison}'>${param.dateTerminaison}</c:if>">

                <!-- Batiment -->
                <label for="batiment">Bâtiment :</label>
                <select id="batiment" name="batiment" required>
                    <option value="A" <c:if test="${param.batiment == 'A'}">selected</c:if>>A</option>
                    <option value="B" <c:if test="${param.batiment == 'B'}">selected</c:if>>B</option>
                    <option value="C" <c:if test="${param.batiment == 'C'}">selected</c:if>>C</option>
                    <option value="D" <c:if test="${param.batiment == 'D'}">selected</c:if>>D</option>
                    <option value="E" <c:if test="${param.batiment == 'E'}">selected</c:if>>E</option>
                </select>

                <!-- Etage -->
                <label for="etage">Étage :</label>
                <select id="etage" name="etage" required>
                    <option value="0" <c:if test="${param.etage == '0'}">selected</c:if>>0</option>
                    <option value="1" <c:if test="${param.etage == '1'}">selected</c:if>>1</option>
                    <option value="2" <c:if test="${param.etage == '2'}">selected</c:if>>2</option>
                    <option value="3" <c:if test="${param.etage == '3'}">selected</c:if>>3</option>
                </select>

                <!-- Numéro de chambre -->
                <label for="numChambre">Numéro de chambre :</label>
                <input type="number" id="numChambre" name="numChambre" value="<c:if test='${not empty param.numChambre}'>${param.numChambre}</c:if>" required>

                <!-- Email -->
                <label for="email">Email (étudiant) :</label>
                <input type="email" id="email" name="email" value="<c:if test='${not empty param.email}'>${param.email}</c:if>" required>

                <!-- Statut -->
                <label for="statut">Statut :</label>
                <select id="statut" name="statut" required>
                    <option value="en attente" <c:if test="${param.statut == 'en attente'}">selected</c:if>>En attente</option>
                    <option value="en cours" <c:if test="${param.statut == 'en cours'}">selected</c:if>>En cours</option>
                    <option value="terminée" <c:if test="${param.statut == 'terminée'}">selected</c:if>>Terminée</option>
                </select>

                <!-- Bouton soumettre -->
                <button type="submit" class="btn btn-add">Soumettre l'intervention</button>
            </form>

        </div>

</body>
</html>
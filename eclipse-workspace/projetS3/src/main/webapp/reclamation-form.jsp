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
            <li><a href="home.jsp"> <i class="fas fa-home"></i>  Accueil</a></li>
            <li><a href="reclamations?action=list"> <i class="fa fa-list"></i> Consulter Réclamations</a></li>
            <li><a href="reclamation-form.jsp"> <i class="fa fa-pencil-alt"></i> Effectuer Réclamation</a></li>
            <li><a href="interventionStdnt?action=list"> <i class="fa fa-tools"></i> Consulter Interventions</a></li>
        </ul>
        </div>
    </nav>

    <div class="form-container">
        <h2>${reclamation == null ? "Ajouter" : "Modifier"} une Réclamation</h2>

        <form action="reclamations" method="post">
            <input type="hidden" name="action" value="${reclamation == null ? 'insert' : 'update'}">
            <input type="hidden" name="idRecl" value="${reclamation != null ? reclamation.idRecl : ''}">

            <label>Sujet:</label>
            <input type="text" name="sujet" value="${reclamation != null ? reclamation.sujet : ''}" required><br>

            <label>Description:</label>
            <textarea name="description" required>${reclamation != null ? reclamation.description : ''}</textarea><br>

            <label>Email:</label>
            <input type="email" name="email" value="${reclamation != null ? reclamation.email : ''}" required><br>

            <label for="batiment">Bâtiment :</label>
             <select name="batiment" id="batiment" required>
                  <option value="A">A</option>
                  <option value="B">B</option>
                  <option value="C">C</option>
                  <option value="D">D</option>
                  <option value="E">E</option>
             </select>
             <br>

            <label>Étage:</label>
            <select name="etage" required>
                <option value="" disabled ${reclamation == null ? 'selected' : ''}>Sélectionnez un étage</option>
                <option value="0" ${reclamation != null && reclamation.etage == '0' ? 'selected' : ''}>0</option>
                <option value="1" ${reclamation != null && reclamation.etage == '1' ? 'selected' : ''}>1</option>
                <option value="2" ${reclamation != null && reclamation.etage == '2' ? 'selected' : ''}>2</option>
                <option value="3" ${reclamation != null && reclamation.etage == '3' ? 'selected' : ''}>3</option>
            </select><br>

            <label>Numéro de Chambre:</label>
            <input type="number" name="numChambre" value="${reclamation != null ? reclamation.numChambre : ''}" required><br>

            <button type="submit">${reclamation == null ? "Ajouter" : "Mettre à jour"}</button>
        </form>
    </div>

</body>
</html>


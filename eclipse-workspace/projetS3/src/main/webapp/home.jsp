<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page d'Accueil</title>
    <style>
        /* Style global pour la page */
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-image: url("${pageContext.request.contextPath}/resources/backgroundLogin.png"); /* Image de fond */
            background-size: cover;
            background-position: center;
            background-size: 100%;
            color: black;
            text-align: center;
            height: 100vh; /* Hauteur pleine de la fenêtre */
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        /* Conteneur principal */
        .content {
            background-color: rgba(255, 255, 255, 0.5); /* Fond semi-transparent */
            padding: 10px 25px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            margin-right: 650px;
        }

        /* Titre */
        h1 {
            font-size: 2 rem;
            margin-bottom: 10px;
        }

        /* Sous-titre */
        p {
            font-size: 1 rem;
            margin-bottom: 20px;
        }

        /* Liste de liens */
        ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        li {
            margin: 10px 0;
            margin-top: 30px;
        }

        a {
            text-decoration: none;
            font-size: 1.2rem;
            color: white;
            background-color: #2e9cdf;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        a:hover {
            background-color: darkblue;
        }
    </style>
</head>
<body>
    <div class="content">
        <h1>Bienvenue sur votre plateforme</h1>
        <p>Comment pouvons-nous vous aider ?</p>
        <ul>
            <li><a href="reclamations?action=list">Consulter Réclamations</a></li>
            <li><a href="reclamation-form.jsp">Effectuer Réclamation</a></li>
            <li><a href="interventionStdnt?action=list">Consulter Interventions</a></li>
        </ul>
    </div>
</body>
</html>

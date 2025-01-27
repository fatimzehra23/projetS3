<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page d'Accueil</title>
    <!-- Inclure Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        /* Style global pour la page */
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-image: url("${pageContext.request.contextPath}/resources/baaaaackLogin.png"); /* Image de fond */
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
            font-size: 1.5rem;
            margin-bottom: 10px;
            margin-right: 650px;
            margin-left: 20px;
            margin-top: 100px;
            color: #2f1554;
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
            margin-top: 15px;
        }

        a {
            text-decoration: none;
            font-size: 1.2rem;
            color: white;
            background-color: #2e9cdf;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            display: flex;
            align-items: center; /* Aligner l'icône avec le texte */
        }

        a i {
            margin-right: 10px; /* Espace entre l'icône et le texte */
        }

        a:hover {
            background-color: darkblue;
        }
    </style>
</head>
<body>
    <h1>Bienvenue sur votre assistant digital pour des services de maintenance rapides</h1>
    <div class="content">
        <p>Comment pouvons-nous vous aider ?</p>
        <ul>
            <li><a href="reclamationsRespo?action=list"><i class="fas fa-file-alt"></i> Consulter les Réclamations</a></li>
            <li><a href="intervention-form.jsp"><i class="fas fa-plus-circle"></i> Ajouter une intervention</a></li>
            <li><a href="interventions?action=list"><i class="fas fa-tasks"></i> Consulter Interventions</a></li>
            <li><a href="interventions?statut=terminee"><i class="fas fa-star"></i> Consulter les évaluations</a></li>
            <li><a href="TechnicienServlet?action=list"><i class="fas fa-users"></i> Gérer les techniciens</a></li>
        </ul>
    </div>
</body>
</html>

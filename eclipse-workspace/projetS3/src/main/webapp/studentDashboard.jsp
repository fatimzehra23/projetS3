<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page d'Accueil</title>
    <!-- Lien vers Font Awesome pour les icônes -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        /* Style global pour la page */
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-image: url("${pageContext.request.contextPath}/resources/baaaaackLogin.png");
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
            background-size: 100%;
            color: black;
            text-align: center;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        /* Conteneur principal */
        .content {
            background-color: rgba(255, 255, 255, 0.5);
            padding: 20px 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            margin-right: 650px;
            margin-top: 20px;
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
            font-size: 1rem;
            margin-bottom: 20px;
        }

        /* Liste de liens */
        ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        li {
            margin: 15px 0;
        }

        a {
            text-decoration: none;
            font-size: 1.2rem;
            color: white;
            background-color: #2e9cdf;
            padding: 10px 20px;
            border-radius: 5px;
            display: flex;
            align-items: center;
            gap: 20px; /* Augmenter l'espace entre l'icône et le texte */
            justify-content: center;
            transition: background-color 0.3s ease;
        }

        a:hover {
            background-color: darkblue;
        }

        /* Icônes */
        .fa {
            font-size: 1.2rem;
        }
    </style>
</head>
<body>
        <h1>Bienvenue sur votre assistant digital pour des services de maintenance rapides</h1>

    <div class="content">
        <p>Comment pouvons-nous vous aider ?</p>
        <ul>
            <li>
                <a href="reclamations?action=list">
                    <i class="fa fa-list"></i> Consulter Réclamations
                </a>
            </li>
            <li>
                <a href="reclamation-form.jsp">
                    <i class="fa fa-pencil-alt"></i> Effectuer Réclamation
                </a>
            </li>
            <li>
                <a href="interventionStdnt?action=list">
                    <i class="fa fa-tools"></i> Consulter Interventions
                </a>
            </li>
        </ul>
    </div>
</body>
</html>

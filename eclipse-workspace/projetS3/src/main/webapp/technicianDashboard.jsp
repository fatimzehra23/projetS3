<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">  
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page d'Accueil</title>
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
            margin-bottom: 20px;
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
        
        /* Style pour le formulaire */
.status-form {
    text-align: left; /* Aligner le texte à gauche */
    margin-top: 20px;
    padding: 15px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background-color: rgba(255, 255, 255, 0.8); /* Fond semi-transparent */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Ombre subtile */
    width: 300px;
    margin: 20px auto; /* Centrer horizontalement */
}

/* Style pour le groupe radio */
.radio-group {
    margin-bottom: 10px;
}

/* Boutons radio et leurs labels */
.status-form input[type="radio"] {
    margin-right: 10px;
}

.status-form label {
    font-size: 1rem;
    color: #333;
}

/* Bouton de soumission */
.status-form button {
    display: inline-block;
    background-color: #2e9cdf;
    color: white;
    font-size: 1rem;
    border: none;
    padding: 5px 15px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease, transform 0.2s ease;
}

.status-form button:hover {
    background-color: darkblue;
    transform: scale(1.05); /* Légère mise en avant au survol */
}
        i {
        margin-right: 8px; /* Espace entre l'icône et le texte */
    }
        
    </style>
</head>
<body>
    <h1>Bienvenue sur votre assistant digital pour des services de maintenance rapides </h1>

    <div class="content">
    

    <p>Votre statut actuel : Disponible</p>

<form action="DisponibiliteServlet" method="post" class="status-form">
    <label>Mettre à jour votre statut :</label><br>
    <div class="radio-group">
        <input type="radio" id="disponible" name="disponibilite" value="DISPONIBLE">
        <label for="disponible">DISPONIBLE</label><br>
        <input type="radio" id="indisponible" name="disponibilite" value="INDISPONIBLE">
        <label for="indisponible">INDISPONIBLE</label><br>
    </div>
    <button type="submit">Mettre à jour</button>
</form>


        <ul>
            <li><a href="technicienInterventions?action=list"> <i class="fas fa-tasks"></i> Consulter les Interventions</a></li>

            
        </ul>
    </div>
</body>
</html>
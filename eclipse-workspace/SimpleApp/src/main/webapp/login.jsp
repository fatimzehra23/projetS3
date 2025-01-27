<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <!-- Lien vers le CSS de Bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

    <!-- Lien vers le JS de Bootstrap -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" defer></script>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
    <style>
        /* Style global pour le corps de la page */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        body {
        font-family: Arial, sans-serif;
        background-image: url("${pageContext.request.contextPath}/resources/Baaack.png"); /* Chemin de votre image */
        background-size: 100%; /* Pour que l'image couvre toute la page */
        background-position: center; /* Pour centrer l'image */
        background-repeat: no-repeat; /* Pour éviter que l'image se répète */
        background-attachment: fixed; /* Pour que l'image reste fixe lors du défilement */
    }

        /* Style pour le conteneur du formulaire */
        .form-container {
            background-color: rgba(255, 255, 255, 0.5);
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 400px; /* Réduit la largeur maximale */
            margin-left: 40px;
            margin-top: 70px;
        }

        h2 {
            text-align: center;
        }

        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }

        button {
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            font-size: 16px;
            background-color: #2e9cdf;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: darkblue;
        }

        .error-message {
            color: red;
            text-align: center;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="row w-100">
        <!-- Formulaire de connexion -->
        <div class="col-md-6 form-container">
            <h2>Connexion</h2>
            <form action="LoginServlet" method="POST">
                <label for="email">Email :</label>
                <input type="email" id="email" name="email" required><br><br>

                <label for="password">Mot de passe :</label>
                <input type="password" id="password" name="password" required><br><br>

                <button type="submit">Se connecter</button>
            </form>

            <c:if test="${not empty errorMessage && not empty loginAttempted}">
                <div class="error-message">
                    <p>${errorMessage}</p>
                </div>
            </c:if>
        </div>
    </div>
</div>


</body>
</html>



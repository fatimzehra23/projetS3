<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Evaluation d'Intervention</title>
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
            padding: 10px;
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

        /* Formulaire */
        .form-container {
            background-color: #eff9f9;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
            margin: 25px auto;
        }

        .form-container h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input,
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .rating {
            display: flex;
            justify-content: center;
            direction: rtl;
            margin-bottom: 15px;
        }

        .rating input {
            display: none;
        }

        .rating label {
            font-size: 30px;
            color: #ddd;
            cursor: pointer;
            margin: 0 5px;
        }

        .rating input:checked ~ label {
            color: #f5b301; /* Couleur de l'étoile sélectionnée */
        }

        .rating label:hover,
        .rating label:hover ~ label {
            color: #f5b301; /* Couleur au survol */
        }

        button {
            background-color: #21949e;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            display: block;
            margin: 0 auto;
        }

        button:hover {
            background-color: #1a7a80;
        }
    </style>
</head>

<body>
    <header>
        <img src="${pageContext.request.contextPath}/resources/Header wooow wow.png" alt="Espace Numérique de l'Étudiant">
    </header>
    <nav>
        <ul>
            <li>
                <a href="studentDashboard.jsp">
                    Accueil
                </a>
            </li>
            <li><a href="reclamations?action=list">Consulter Reclamations</a></li>
            <li><a href="reclamation-form.jsp">Effectuer Reclamation</a></li>
            <li><a href="interventionStdnt?action=list">Consulter Interventions</a></li>
        </ul>
    </nav>
    <div class="form-container">
        <h1>Évaluation de l'Intervention</h1>
        <c:if test="${not empty errorMessage}">
            <p style="color: red;">${errorMessage}</p>
        </c:if>
        <c:if test="${not empty successMessage}">
            <p style="color: green;">${successMessage}</p>
        </c:if>
        <form action="EvaluationServlet" method="post" onsubmit="return validateForm();">
            <label for="idInterv">ID Intervention :</label>
<input type="text" id="idInterv" name="idInterv" value="${idInterv}" readonly />

            <label for="evaluation">Evaluation :</label>
            <div class="rating">
                <input type="radio" id="star5" name="evaluation" value="5">
                <label for="star5"><i class="fas fa-star"></i></label>
                <input type="radio" id="star4" name="evaluation" value="4">
                <label for="star4"><i class="fas fa-star"></i></label>
                <input type="radio" id="star3" name="evaluation" value="3">
                <label for="star3"><i class="fas fa-star"></i></label>
                <input type="radio" id="star2" name="evaluation" value="2">
                <label for="star2"><i class="fas fa-star"></i></label>
                <input type="radio" id="star1" name="evaluation" value="1">
                <label for="star1"><i class="fas fa-star"></i></label>
            </div>

            <label for="commentaire">Commentaire :</label>
            <textarea id="commentaire" name="commentaire" rows="4" placeholder="Ajoutez un commentaire"></textarea>

            <button type="submit">Envoyer l'évaluation</button>
        </form>
    </div>
</body>

</html>

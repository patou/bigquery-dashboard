app.config(function ($translateProvider) {
    $translateProvider.translations("fr", {
        ADMINISTRATION: "Administration",
        HOME: "Home",
        LOGOUT: "Déconnexion",
        LOGIN: "Connexion",
        WELCOME : "Bienvenue sur BigQuery DashBoard!",
        REQUESTS_LIST : "Liste des requêtes",
        LOGIN_ACTION : "Se connecter",
        ADD_NEW_REQUEST : "Ajouter une nouvelle requête",
        REQUEST_LABEL : "Libellé",
        HELP_REQUEST_LABEL : "Libellé de la requête",
        REQUEST : "Requête",
        VALIDATE : "Valider",
        CANCEL : "Annuler",
        DELETE : "Supprimer",
        HELP_REQUEST_COMMENT : "Ajoutez une description",
        REQUEST_COMMENT : "Description",
        SEARCH_FILTER : "Filtrer les résultats"

    });
    $translateProvider.preferredLanguage('fr');
});
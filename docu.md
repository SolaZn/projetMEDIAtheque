Déploiement
Quelle fonctionnalité pour le serveur ?
-Le serveur doit être seulement accessible depuis une adresse locale (pas d'accès depuis l'extérieur)
-Une base de données commune doit être implémentée

Utilisateurs
->Utilisateur : id, nom, login, mot de passe
-Abonné -> dérive de utilisateur
emprunter() des documents, rendre() des documents
-Bibliothécaire -> dérive de utilisateur
ajouter() des documents

Services
Connexion() gérerait les connexions et renverait vers le bon service
Index() afficherait le menu des actions selon le profil connecté
Emprunt() et Rendu() permettrait de retourner ou d'emprunter un document
Ajout() permettrait d'ajouter un document

Structure possible
Modèle MVC:
Vues gérées avec des JSP instanciées à partir des servlets (agissant comme des templates)
Controller géré par la suite de Servlets, se chargeant de lancer un traitement puis de soit, retourner les résultats via le jsp généré, 
soit de les stocker en variables de session
Model géré par persistence et la base de données, sera appelée en début de session pour mettre à jour les données relatives aux objets 
(utilisateurs, documents...)

[image] https://www.oracle.com/a/tech/img/jsp-model2-architecture.gif


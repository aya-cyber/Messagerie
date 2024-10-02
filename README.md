##*Description*
Ce projet est une application Java utilisant JDBC pour gérer un système de messagerie entre employés. Il permet aux utilisateurs (employés) d'envoyer et de recevoir des messages via une base de données MySQL. Ce projet met en œuvre les principes de DAO  avec l'utilisation de PreparedStatement pour sécuriser les requêtes SQL.

##*Fonctionnalités*
Envoi de messages : Les employés peuvent envoyer des messages à d'autres utilisateurs.
Réception des messages : Affichage des messages reçus par un employé.
Utilisation de PreparedStatement : Pour exécuter des requêtes SQL paramétrées, garantissant une meilleure sécurité contre les injections SQL.
Architecture DAO : Séparation des opérations de base de données en différentes couches, avec une interface DAO pour définir les fonctions CRUD et deux classes principales qui implémentent ces fonctionnalités.

##*Technologies Utilisées*
Java : Langage principal utilisé pour développer l'application.
JDBC  : Interface utilisée pour connecter et interagir avec la base de données.
MySQL : Base de données utilisée pour stocker les informations des employés et leurs messages.
PreparedStatement : Utilisé pour exécuter des requêtes SQL paramétrées.
DAO  : Modèle utilisé pour gérer l'accès aux données avec une séparation claire entre les opérations de base de données et la logique métier.

Ce projet est un exemple d'implémentation des opérations CRUD dans un contexte de messagerie interne, tout en garantissant une sécurité et une organisation des couches logicielles


https://github.com/user-attachments/assets/0e94792f-106c-4b6c-9f43-d39b22854cc4


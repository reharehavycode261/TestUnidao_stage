# Projet de Gestion de Régions et Territoires

Ce projet est une application de gestion de bases de données pour les régions et les territoires, implémentée en Java, utilisant une architecture MVC et des annotations pour gérer les entités et leur persistance.

## Objectif du projet

L'application a pour but de fournir une interface simple pour manipuler les données des régions et territoires, tout en intégrant des fonctionnalités avancées telles que :
- **Import/Export de schémas de base de données**
- **Support pour des types de données complexes (JSON, Array, UUID)**
- **Génération automatique de validations (Bean-Validation)**
- **Reconfiguration par glisser-déposer**

## Structure du projet

Le projet suit une architecture en couches, comprenant :

- **Entités** : Définies dans le package `project.entity`, utilisant des annotations pour définir les champs et les séquences.
- **Contrôleurs** : Classes de gestion des requêtes REST, situées dans `project.controller`.
- **Services** : Logique métier centralisée pour séparer la logique des contrôleurs et des DAO.

## Prérequis

- Java 8 ou supérieur
- Maven pour la gestion des dépendances

## Installation

1. Clonez le dépôt : `git clone <URL-du-repo>`
2. Allez dans le répertoire : `cd <nom-du-repo>`
3. Compilez le projet : `mvn clean install`
4. Exécutez l'application : `mvn spring-boot:run`

## Utilisation

Une fois l'application démarrée, elle offre des points d'accès REST pour manipuler les données des régions et territoires. Vous pouvez interagir avec ces API via des outils comme Postman ou directement via une interface web si disponible.

## Contribution

Pour contribuer, veuillez cloner le dépôt, créer votre branche de fonctionnalité et soumettre une pull request pour examen.

## Licence

Ce projet est sous licence MIT. Voir `LICENSE` pour plus d'informations.
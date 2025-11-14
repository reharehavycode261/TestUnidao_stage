# Projet de Recherche Avancée

## Description
Ce projet utilise Spring Boot pour implémenter une application web avec une architecture MVC. La fonctionnalité principale de ce projet est la recherche avancée, qui permet aux utilisateurs de rechercher des données avec des filtres, de bénéficier d'une auto-complétion et de sauvegarder leurs requêtes pour une utilisation ultérieure.

## Fonctionnalités
- **Recherche avancée** : Effectuez des recherches avec différents critères en utilisant de multiples filtres pour affiner les résultats.
- **Auto-complétion** : Propose des suggestions basées sur les termes de recherche entrés par l'utilisateur pour améliorer l'expérience de recherche.
- **Sauvegarde des requêtes** : Permet de stocker les requêtes de recherche pour une récupération facile dans le futur.

## Architecture du Projet
- **Backend** : Utilisation de Spring Boot pour gérer les requêtes HTTP, exécuter la logique métier et interagir avec la base de données.
- **Frontend** : Utilisation de JavaScript et de bibliothèques comme JQuery et Bootstrap pour une interface utilisateur réactive et élégante.

## Installation
1. Clonez le repository sur votre machine locale.
2. Assurez-vous d'avoir Java et Maven installés.
3. Exécutez la commande `mvn install` pour construire le projet.
4. Démarrez l'application avec la commande `mvn spring-boot:run`.

## Utilisation
- Accédez à l'interface de recherche à partir de votre navigateur.
- Utilisez les champs de filtre pour préciser votre recherche.
- Les fonctionnalités d'auto-complétion fonctionnent automatiquement lorsque vous tapez dans le champ de recherche.
- Vous pouvez sauvegarder vos requêtes couramment utilisées et les récupérer via l'interface utilisateur.

## Tests
- Des tests unitaires et d'intégration sont inclus pour garantir le bon fonctionnement des fonctionnalités de recherche avancée.

## Contributeurs
- Projet développé par l'équipe de @vydata.

## Licence
Ce projet est sous la licence MIT. Voir le fichier LICENSE pour plus d'informations.
# Projet Spring Boot MVC

Ce projet utilise l'architecture MVC avec Java et Spring Boot. Il est structuré pour fournir plusieurs fonctionnalités avancées pour la gestion des territoires et des régions, ainsi qu'une interface utilisateur moderne.

## Fonctionnalités du Projet

1. **Recherche Avancée**
   - Intègre des filtres, de l'auto-complétion et la possibilité de sauvegarder des requêtes.
   - Optimisée pour une utilisation rapide et efficace, améliorant l'expérience utilisateur.

2. **Notifications Multi-Canaux**
   - Notifications in-app, par e-mail et push.
   - Configurables selon les préférences de l'utilisateur grâce à l'intégration de Spring Mail et Firebase Cloud Messaging.

3. **Internationalisation et Mode Sombre**
   - Support de plusieurs langues grâce à Thymeleaf et Spring Boot.
   - Un mode sombre intégré pour améliorer le confort visuel.

4. **Gestion des Fonctionnalités avec Feature Flags**
   - Déploiements progressifs et contrôle granulaire des fonctionnalités du projet.

5. **Monitoring de Performance et d’Erreurs**
   - Implémentation de métriques, logs, et traces couplées à un système d'alertes pour une supervision efficace.

6. **Tests E2E et CI/CD**
   - Tests de bout en bout et un pipeline d’intégration continue avec des scans de sécurité.
   
7. **Tri et Pagination Côté Client**
   - Ajout de fonctionnalités de tri par colonnes et pagination dans les listes affichées.

8. **Gestion Uniforme des États Vides, Loaders et Erreurs**
   - Utilisation de composants JavaScript simples pour unifier l'affichage des états vides, loaders et messages d'erreur.

## Architecture du projet

Le projet suit l'architecture en couches classique, avec des services manipulant les données échangées entre les couches de présentation et de persistance.

### Conventions de Code

- **Classes** : Utilisation du PascalCase pour les classes.
- **Imports** : Privilégier les imports depuis les packages `project`, `mg`.

## Installation et Configuration

1. Cloner le dépôt du projet.
2. Configurer la base de données dans le fichier `database.json`.
3. Lancer l'application avec un serveur Spring Boot.

## Usage

Vous pouvez lancer l'application en exécutant la classe `Main` du package principal.

## Contribution

Les contributions sont les bienvenues. Avant de commencer, merci de consulter le guide de contribution du projet et de respecter les conventions de code mentionnées.
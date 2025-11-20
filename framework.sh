#!/bin/bash

# Étape 1: Analyser la structure du projet
echo "Analyzing project structure to identify packages..."

# Chemin vers le répertoire source
SRC_DIR="./src"

# Fonction pour générer un README pour chaque package
generate_readme() {
    local package_dir=$1
    local package_name

    # Détermine le nom du package à partir du chemin du répertoire
    package_name=$(basename "$package_dir")

    # Chemin vers le fichier README à générer
    local readme_file="$package_dir/README.md"

    # Vérifie si un fichier README.md existe déjà
    if [[ ! -f $readme_file ]]; then
        echo "Generating README for package: $package_name"

        # Écrit un simple contenu dans le README
        echo "# Package: $package_name" > "$readme_file"
        echo "" >> "$readme_file"
        echo "This package is part of the project structure." >> "$readme_file"
        echo "Add more detailed documentation here about the package functionalities." >> "$readme_file"
    else
        echo "README already exists for package: $package_name. Skipping..."
    fi
}

# Recherche recursivement de tous les sous-repertoires dans src/
for dir in $(find "$SRC_DIR" -type d); do
    # Ignore le répertoire de base lui-même
    if [ "$dir" != "$SRC_DIR" ]; then
        # Étape 2: Créer un fichier README pour chaque package
        generate_readme "$dir"
    fi
done

echo "README generation complete."
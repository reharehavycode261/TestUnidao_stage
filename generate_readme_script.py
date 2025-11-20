import os

def generate_readme_in_packages(base_directory):
    """
    Parcourt les répertoires à partir de base_directory et génère un README.md dans chaque package.
    Un package est défini comme un répertoire contenant au moins un fichier .java.
    """
    for root, dirs, files in os.walk(base_directory):
        # Identifier les packages en cherchant des fichiers .java
        if any(file.endswith('.java') for file in files):
            readme_path = os.path.join(root, 'README.md')
            if not os.path.exists(readme_path):
                with open(readme_path, 'w') as readme_file:
                    # Ecrire une description par défaut dans le README
                    package_name = os.path.basename(root)
                    readme_file.write(f"# Package {package_name}\n\n")
                    readme_file.write("Ce package contient les classes Java suivantes:\n\n")
                    for file in files:
                        if file.endswith('.java'):
                            readme_file.write(f"- {file}\n")

if __name__ == "__main__":
    # Mettre ici le chemin du répertoire racine du projet
    project_root = "."
    generate_readme_in_packages(project_root)
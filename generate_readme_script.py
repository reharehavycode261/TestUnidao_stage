import os

def generate_readme_for_package(package_path):
    readme_content = f"# {os.path.basename(package_path)}\n\n"
    readme_content += "Ce répertoire contient le code source pour le package.\n"
    readme_content += "Veuillez consulter la documentation pour plus de détails.\n"

    readme_file = os.path.join(package_path, 'README.md')
    with open(readme_file, 'w') as readme:
        readme.write(readme_content)

def main():
    src_dir = 'src'
    
    for root, dirs, files in os.walk(src_dir):
        for directory in dirs:
            package_path = os.path.join(root, directory)
            generate_readme_for_package(package_path)

if __name__ == "__main__":
    main()
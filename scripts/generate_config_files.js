const fs = require('fs');

// Template pour package.json
const packageJsonTemplate = {
    name: "mon-projet",
    version: "1.0.0",
    description: "Description de mon projet",
    main: "index.js",
    scripts: {
        start: "node index.js",
        test: "echo \"Erreur: pas de test spécifié\" && exit 1"
    },
    author: "",
    license: "ISC",
    dependencies: {}
};

// Fonction pour générer package.json
function generatePackageJson() {
    fs.writeFile('package.json', JSON.stringify(packageJsonTemplate, null, 2), (err) => {
        if (err) throw err;
        console.log('Le fichier package.json a été généré avec succès.');
    });
}

// Appel de la fonction pour générer package.json
generatePackageJson();
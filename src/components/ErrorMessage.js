export default function ErrorMessage({ error }) {
    return (
        <div className="error-message">
            <p>Erreur : {error || "Une erreur s'est produite. Veuillez réessayer."}</p>
        </div>
    );
}
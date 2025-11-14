export default function EmptyState({ message }) {
    return (
        <div className="empty-state">
            <p>{message || "Aucun contenu disponible."}</p>
        </div>
    );
}
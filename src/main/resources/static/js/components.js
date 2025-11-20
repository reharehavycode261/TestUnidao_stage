document.addEventListener("DOMContentLoaded", function() {
    class UIComponents {
        static showEmptyState(container, message) {
            container.innerHTML = `<div class="empty-state">${message}</div>`;
        }

        static showLoader(container) {
            container.innerHTML = '<div class="loader">Loading...</div>';
        }

        static showError(container, message) {
            container.innerHTML = `<div class="error-message">${message}</div>`;
        }
    }

    window.UIComponents = UIComponents;
});
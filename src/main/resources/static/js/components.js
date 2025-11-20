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

        static makeListDraggable(listSelector) {
            const list = document.querySelector(listSelector);
            let draggedElement = null;

            list.addEventListener('dragstart', (e) => {
                draggedElement = e.target;
                e.dataTransfer.effectAllowed = 'move';
                e.dataTransfer.setData('text/plain', null);
            });

            list.addEventListener('dragover', (e) => {
                e.preventDefault();
                e.dataTransfer.dropEffect = 'move';
                
                const target = e.target;
                if (target && target !== draggedElement && target.nodeName === 'LI') {
                    target.classList.add('drag-over');
                }
            });

            list.addEventListener('dragleave', (e) => {
                e.target.classList.remove('drag-over');
            });

            list.addEventListener('drop', (e) => {
                e.preventDefault();

                const target = e.target;
                if (target && target !== draggedElement && target.nodeName === 'LI') {
                    target.classList.remove('drag-over');
                    list.insertBefore(draggedElement, target.nextSibling || target);
                }
            });

            list.addEventListener('dragend', () => {
                draggedElement = null;
            });
        }
    }

    window.UIComponents = UIComponents;
});
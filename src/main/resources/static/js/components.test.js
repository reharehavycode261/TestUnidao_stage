import { test, expect } from '@jest/globals';

// Mocking the document and window objects for testing
document.body.innerHTML = `
    <ul id="test-list">
        <li draggable="true">Item 1</li>
        <li draggable="true">Item 2</li>
        <li draggable="true">Item 3</li>
    </ul>
`;

const { UIComponents } = window;

test('UIComponents.makeListDraggable should allow list items to be rearranged', () => {
    UIComponents.makeListDraggable('#test-list');

    const list = document.getElementById('test-list');
    const items = list.querySelectorAll('li');

    // Simulate drag and drop
    const dragEvent = new DragEvent('dragstart', { bubbles: true });
    items[0].dispatchEvent(dragEvent);
    const dragoverEvent = new DragEvent('dragover', { bubbles: true });
    items[1].dispatchEvent(dragoverEvent);
    const dropEvent = new DragEvent('drop', { bubbles: true });
    items[1].dispatchEvent(dropEvent);

    expect(list.children[0].textContent).toBe('Item 2');
    expect(list.children[1].textContent).toBe('Item 1');
});
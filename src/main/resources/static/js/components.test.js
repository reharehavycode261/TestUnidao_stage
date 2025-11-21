import { test, expect } from '@jest/globals';

// Mocking the document and window objects
document.body.innerHTML = '<div id="test-container"></div>';
const container = document.getElementById('test-container');

// Importing the UIComponents class from the global window object
const { UIComponents } = window;

test('UIComponents.showEmptyState should display the correct empty state message', () => {
    const message = "No data available";
    UIComponents.showEmptyState(container, message);
    expect(container.innerHTML).toBe('<div class="empty-state">No data available</div>');
});

test('UIComponents.showLoader should display the loader correctly', () => {
    UIComponents.showLoader(container);
    expect(container.innerHTML).toBe('<div class="loader">Loading...</div>');
});

test('UIComponents.showError should display the correct error message', () => {
    const errorMessage = "An error occurred";
    UIComponents.showError(container, errorMessage);
    expect(container.innerHTML).toBe('<div class="error-message">An error occurred</div>');
});

test('UIComponents.showEmptyState should handle empty message gracefully', () => {
    UIComponents.showEmptyState(container, '');
    expect(container.innerHTML).toBe('<div class="empty-state"></div>');
});

test('UIComponents.showError should handle empty error message gracefully', () => {
    UIComponents.showError(container, '');
    expect(container.innerHTML).toBe('<div class="error-message"></div>');
});

test('UIComponents.showEmptyState should throw error if container is null', () => {
    expect(() => UIComponents.showEmptyState(null, 'Message')).toThrow();
});

test('UIComponents.showLoader should throw error if container is null', () => {
    expect(() => UIComponents.showLoader(null)).toThrow();
});

test('UIComponents.showError should throw error if container is null', () => {
    expect(() => UIComponents.showError(null, 'Error')).toThrow();
});
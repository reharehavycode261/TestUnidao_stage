import { test, expect } from '@jest/globals';

// Mocking the DOM environment
document.body.innerHTML = `
  <button id="dark-mode-toggle">Toggle Dark Mode</button>
`;

// Import the script to be tested
require('../public/script.js');

test('should toggle dark mode class on body when button is clicked', () => {
  const button = document.getElementById('dark-mode-toggle');
  const body = document.body;

  // Initial state: dark mode should not be active
  expect(body.classList.contains('dark-mode')).toBe(false);

  // Simulate button click to activate dark mode
  button.click();
  expect(body.classList.contains('dark-mode')).toBe(true);

  // Simulate button click to deactivate dark mode
  button.click();
  expect(body.classList.contains('dark-mode')).toBe(false);
});

test('should not throw error when button is clicked multiple times', () => {
  const button = document.getElementById('dark-mode-toggle');

  expect(() => {
    for (let i = 0; i < 10; i++) {
      button.click();
    }
  }).not.toThrow();
});
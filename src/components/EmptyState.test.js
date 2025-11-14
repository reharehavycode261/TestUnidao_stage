import { test, expect } from '@jest/globals';
import { render } from '@testing-library/react';
import EmptyState from '../src/components/EmptyState';

test('renders the default message when no message is provided', () => {
    const { getByText } = render(<EmptyState />);
    const defaultMessage = getByText("Aucun contenu disponible.");
    expect(defaultMessage).toBeTruthy();
});

test('renders the provided message', () => {
    const customMessage = "Pas de données à afficher.";
    const { getByText } = render(<EmptyState message={customMessage} />);
    const renderedMessage = getByText(customMessage);
    expect(renderedMessage).toBeTruthy();
});

test('does not render the default message when a custom message is provided', () => {
    const customMessage = "Pas de données à afficher.";
    const { queryByText } = render(<EmptyState message={customMessage} />);
    const defaultMessage = queryByText("Aucun contenu disponible.");
    expect(defaultMessage).toBe(null);
});
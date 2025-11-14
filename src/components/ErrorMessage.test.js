import { test, expect } from '@jest/globals';
import { render } from '@testing-library/react';
import ErrorMessage from '../src/components/ErrorMessage';

// Test suite for ErrorMessage component
test('ErrorMessage displays the provided error message', () => {
    const errorText = "This is a test error message.";
    const { getByText } = render(<ErrorMessage error={errorText} />);
    
    // Assert that the error message is displayed correctly
    expect(getByText(`Erreur : ${errorText}`)).toBeTruthy();
});

test('ErrorMessage displays default message when no error is provided', () => {
    const defaultMessage = "Une erreur s'est produite. Veuillez réessayer.";
    const { getByText } = render(<ErrorMessage error={null} />);
    
    // Assert that the default error message is displayed
    expect(getByText(`Erreur : ${defaultMessage}`)).toBeTruthy();
});

test('ErrorMessage displays default message when an empty string is provided as error', () => {
    const defaultMessage = "Une erreur s'est produite. Veuillez réessayer.";
    const { getByText } = render(<ErrorMessage error="" />);
    
    // Assert that the default error message is displayed
    expect(getByText(`Erreur : ${defaultMessage}`)).toBeTruthy();
});
import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import Loader from '../src/components/Loader';
import { test, expect } from '@jest/globals';

test('renders the Loader component with spinner and loading text', () => {
    render(<Loader />);
    
    // Check if the loader div is present
    const loaderDiv = screen.getByRole('status');
    expect(loaderDiv).toBeInTheDocument();

    // Check if the spinner is present
    const spinnerDiv = loaderDiv.querySelector('.spinner');
    expect(spinnerDiv).toBeInTheDocument();

    // Check if the loading text is present
    const loadingText = screen.getByText('Chargement...');
    expect(loadingText).toBeInTheDocument();
});

test('Loader component has the correct class names', () => {
    render(<Loader />);
    
    // Check if the loader div has the correct class
    const loaderDiv = screen.getByRole('status');
    expect(loaderDiv).toHaveClass('loader');

    // Check if the spinner div has the correct class
    const spinnerDiv = loaderDiv.querySelector('.spinner');
    expect(spinnerDiv).toHaveClass('spinner');
});
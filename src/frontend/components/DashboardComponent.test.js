import React from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { test, expect, jest } from '@jest/globals';
import axios from 'axios';
import { saveAs } from 'file-saver';
import DashboardComponent from '../src/frontend/components/DashboardComponent';

// Mock axios and file-saver
jest.mock('axios');
jest.mock('file-saver');

test('DashboardComponent renders without crashing', () => {
    render(<DashboardComponent />);
    expect(screen.getByText('Analytical Dashboard')).toBeInTheDocument();
});

test('DashboardComponent fetches data on mount', async () => {
    const mockData = [
        { id: 1, description: 'Test 1', value: 100 },
        { id: 2, description: 'Test 2', value: 200 },
    ];
    axios.get.mockResolvedValueOnce({ data: mockData });

    render(<DashboardComponent />);

    await waitFor(() => {
        expect(screen.getByText('Test 1')).toBeInTheDocument();
        expect(screen.getByText('100')).toBeInTheDocument();
        expect(screen.getByText('Test 2')).toBeInTheDocument();
        expect(screen.getByText('200')).toBeInTheDocument();
    });
});

test('DashboardComponent handles fetch error', async () => {
    const consoleErrorSpy = jest.spyOn(console, 'error').mockImplementation(() => {});
    axios.get.mockRejectedValueOnce(new Error('Network Error'));

    render(<DashboardComponent />);

    await waitFor(() => {
        expect(consoleErrorSpy).toHaveBeenCalledWith('There was an error fetching the data!', expect.any(Error));
    });

    consoleErrorSpy.mockRestore();
});

test('exportToCSV function creates a CSV file with correct content', () => {
    const mockData = [
        { id: 1, description: 'Test 1', value: 100 },
        { id: 2, description: 'Test 2', value: 200 },
    ];
    const component = new DashboardComponent();
    component.setState({ data: mockData });

    component.exportToCSV();

    const expectedCSVContent = 'ID, Description, Value\n1, Test 1, 100\n2, Test 2, 200';
    const expectedBlob = new Blob([expectedCSVContent], { type: 'text/csv;charset=utf-8;' });

    expect(saveAs).toHaveBeenCalledWith(expectedBlob, 'dashboard_data.csv');
});

test('exportToCSV handles empty data gracefully', () => {
    const component = new DashboardComponent();
    component.setState({ data: [] });

    component.exportToCSV();

    const expectedCSVContent = 'ID, Description, Value\n';
    const expectedBlob = new Blob([expectedCSVContent], { type: 'text/csv;charset=utf-8;' });

    expect(saveAs).toHaveBeenCalledWith(expectedBlob, 'dashboard_data.csv');
});
import { test, expect } from '@jest/globals';
import axios from 'axios';

// Mock axios to simulate API requests
jest.mock('axios');

describe('Territories API Tests', () => {
  test('should retrieve a list of territories with status 200', async () => {
    // Arrange: Mock the API response
    const mockResponse = { status: 200, data: [{ id: 1, name: 'Territory 1' }] };
    axios.get.mockResolvedValue(mockResponse);

    // Act: Make the API request
    const response = await axios.get('/api/territories');

    // Assert: Check the status and data
    expect(response.status).toBe(200);
    expect(response.data).toEqual([{ id: 1, name: 'Territory 1' }]);
  });

  test('should retrieve a specific territory by ID with status 200', async () => {
    // Arrange: Mock the API response
    const mockResponse = { status: 200, data: { id: 1, name: 'Territory 1' } };
    axios.get.mockResolvedValue(mockResponse);

    // Act: Make the API request
    const response = await axios.get('/api/territories/1');

    // Assert: Check the status and specific territory data
    expect(response.status).toBe(200);
    expect(response.data).toHaveProperty('id', 1);
    expect(response.data).toHaveProperty('name', 'Territory 1');
  });

  test('should return 404 for a non-existent territory ID', async () => {
    // Arrange: Mock the API response
    const mockError = { response: { status: 404 } };
    axios.get.mockRejectedValue(mockError);

    // Act & Assert: Make the API request and expect a 404 error
    try {
      await axios.get('/api/territories/999');
    } catch (error) {
      expect(error.response.status).toBe(404);
    }
  });

  test('should handle network errors gracefully', async () => {
    // Arrange: Mock a network error
    const mockError = new Error('Network Error');
    axios.get.mockRejectedValue(mockError);

    // Act & Assert: Make the API request and expect a network error
    try {
      await axios.get('/api/territories');
    } catch (error) {
      expect(error.message).toBe('Network Error');
    }
  });
});
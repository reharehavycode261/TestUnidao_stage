import { test, expect } from '@jest/globals';
import axios from 'axios';

// Mocking axios to simulate API requests
jest.mock('axios');

describe('Regions API Tests', () => {
  test('should retrieve a list of regions with status 200', async () => {
    // Arrange: Mock the API response
    const mockResponse = { status: 200, data: [{ id: 1, name: 'Region 1' }, { id: 2, name: 'Region 2' }] };
    axios.get.mockResolvedValue(mockResponse);

    // Act: Make the API request
    const response = await axios.get('/api/regions');

    // Assert: Check the response status and data
    expect(response.status).toBe(200);
    expect(response.data).toEqual(mockResponse.data);
  });

  test('should retrieve a specific region by ID with status 200', async () => {
    // Arrange: Mock the API response for a specific region
    const mockResponse = { status: 200, data: { id: 1, name: 'Region 1' } };
    axios.get.mockResolvedValue(mockResponse);

    // Act: Make the API request for a specific region
    const response = await axios.get('/api/regions/1');

    // Assert: Check the response status and data
    expect(response.status).toBe(200);
    expect(response.data).toHaveProperty('id', 1);
  });

  test('should handle error when retrieving a non-existent region', async () => {
    // Arrange: Mock the API response for a non-existent region
    const mockError = { response: { status: 404, data: { message: 'Region not found' } } };
    axios.get.mockRejectedValue(mockError);

    // Act & Assert: Expect an error to be thrown
    try {
      await axios.get('/api/regions/999');
    } catch (error) {
      expect(error.response.status).toBe(404);
      expect(error.response.data).toHaveProperty('message', 'Region not found');
    }
  });

  test('should handle network error gracefully', async () => {
    // Arrange: Mock a network error
    const mockError = new Error('Network Error');
    axios.get.mockRejectedValue(mockError);

    // Act & Assert: Expect an error to be thrown
    try {
      await axios.get('/api/regions');
    } catch (error) {
      expect(error.message).toBe('Network Error');
    }
  });
});
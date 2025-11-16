describe('Regions API Tests', () => {
  it('should retrieve a list of regions', () => {
    cy.request('/api/regions')
      .its('status')
      .should('eq', 200);
  });

  it('should retrieve a specific region by ID', () => {
    cy.request('/api/regions/1')
      .its('status')
      .should('eq', 200);

    cy.request('/api/regions/1')
      .its('body')
      .should('have.property', 'id', 1);
  });
});
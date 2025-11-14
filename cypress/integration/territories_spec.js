describe('Territories API Tests', () => {
  it('should retrieve a list of territories', () => {
    cy.request('/api/territories')
      .its('status')
      .should('eq', 200);
  });

  it('should retrieve a specific territory by ID', () => {
    cy.request('/api/territories/1')
      .its('status')
      .should('eq', 200);

    cy.request('/api/territories/1')
      .its('body')
      .should('have.property', 'id', 1);
  });
});
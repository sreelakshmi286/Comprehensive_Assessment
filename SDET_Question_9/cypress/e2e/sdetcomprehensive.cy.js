describe('My Test suite', () => {
    let testData;

    before(() => {
      cy.visit('https://automationpanda.com/2021/12/29/want-to-practice-test-automation-try-these-demo-sites')
      cy.fixture('testData.json').then((data) =>{
        testData=data;
      })
    })
  
    it('should verify the title of the page', () => {
      cy.title().should('include',testData.expectedTitle)
    })
  
    it('should click on Speaking and verify the title of the page and should verify if "keynote Addresses" is present and check the text', () => {
      cy.fixture('testData').then((data) => {
        cy.visit('https://automationpanda.com/speaking/')
        cy.title().should('include',testData.expectedPageTitle)

        cy.contains(testData.keynoteAddressText).should('exist');
       cy.assertElementText('.your-keynote-address-selector',testData.expectedText)
        // cy.get('.wp-block-heading',{timeout:10000}).should('be.visible')
        // cy.contains('keynote Adresses').invoke('text').should('include',testData.expectedText)
      })
    })

  })
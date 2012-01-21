package grails2spock

import grails.plugin.spock.UnitSpec
import grails2spock.BookStoreService
import grails2spock.Author
import grails2spock.Book
class BookStoreServiceSpec extends UnitSpec {

    def "when creating thesis a new book is created"() {
        setup:
            mockLogging(BookStoreService, true)
            def guttenbergService = new BookStoreService()
            mockDomain(Author, [authorInstance])
            mockDomain(Book)


        when:
            guttenbergService.createThesis(authorInstance.id)

        then:
            Book.count() == 1

        where:
            authorInstance = new Author(firstname: "John", lastname: "Doe")
    }

    def "when creating thesis author is assigned to thesis"() {
        //2. Setup mocks
        setup:
            //Enable logging for GuttenbergService
            mockLogging(BookStoreService, true)
            //Create GuttenbergServce
            def bookStoreService = new BookStoreService()
            //Use the author object created in the "where" statement to create mocked domain objects.
            //In this step, the author instance get's the ID assigned
            mockDomain(Author, [authorInstance])
            //Mock domain class book (required by the guttenberg service)
            mockDomain(Book)

            //3. Call the method to test
        when:
            def thesis = bookStoreService.createThesis(authorInstance.id)

        then:
            //4. Make asserts on the result
            thesis != null
            thesis.author.id == authorInstance.id

            //1. Create a dummy author instance used for the test. This instance has NO id yet
        where:
            authorInstance = new Author(firstname: "John", lastname: "Doe")
    }  
    def "when creating guttenberg thesis title is set"() {
        setup:
            mockLogging(BookStoreService, true)
            def bookStoreService = new BookStoreService()
            mockDomain(Author, [authorInstance])
            mockDomain(Book)


        when:
            def thesis = bookStoreService.createThesis(authorInstance.id)

        then:
            thesis != null
            thesis.title == "My copied thesis"

        where:
            authorInstance = new Author(firstname: "John", lastname: "Doe")
  }

}
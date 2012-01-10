package grails2spock
import grails.plugin.spock.*
import spock.lang.Unroll
class BookSpec extends UnitSpec{
	def "find book by title"(){
		setup:
			mockDomain(Book)
			mockDomain(Author)
			
		when:
			new Book(title: title, author: new Author(firstname:"John", lastname:"Doe").save()).save()
			
		then:
			Book.findByTitle(title) != null
		
		where:
			title = "Lean"
	}
	
	def "book should display it's title as a result of toString"(){
		setup:
			mockDomain(Book)
			mockDomain(Author)
			
		when:
			def book = new Book(title: title, author: new Author(firstname:"Twin", lastname:"Panichsombat"))
			
		then:
			book.toString() == title
			
		where:
			title = "Lean"
	}
	
	def "book title should not longer than 50 characters"(){
		setup:
			mockForConstraintsTests(Book)
			mockDomain(Author)
			
		when:
			def book = new Book(title: title, author: new Author(firstname:"Twin", lastname:"Panichsombat").save())
			book.validate()

		then:
			book.errors.hasFieldErrors("title")
			
		where:
			title = "123456789012345678901234567890123456789012345678901"
			
	}
	
	def "book title should not be blank"(){
		setup:
			mockForConstraintsTests(Book)
			mockDomain(Author)
			
		when:
			def book = new Book(title: title, author: new Author(firstname:"Twin", lastname:"Panichsombat").save())
			book.validate()
			
		then:
			book.errors.hasFieldErrors("title")
			
		where:
			title = ""
	}
	
	
}
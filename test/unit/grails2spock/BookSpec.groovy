package grails2spock
import grails.plugin.spock.*
import spock.lang.Unroll
class BookSpec extends UnitSpec{
	def dummyAuthor// = new Author(firstname:"Twin", lastname:"Panichsombat")
	def dummyBook  // = new Book(title:"", isbn:"")

	def setup(){
		dummyAuthor = new Author(firstname:"Twin", lastname:"Panichsombat")
		dummyBook   = new Book(title:"Lean", isbn:"01234567890")		
		dummyBook.author = dummyAuthor
	}

	def "book should have all these attributes"(){		
		when:
			def book   = dummyBook

		then:
			book.title == dummyTitle	
			book.isbn  == dummyIsbn

		where:
			dummyTitle = "Lean"
			dummyIsbn  = "01234567890"	
	}

	def "find book by title"(){		
		setup:
			mockDomain(Book, [dummyBook])	
			mockDomain(Author, [dummyAuthor])	

		when:			
			def book = dummyBook
			book.save()
			
		then:
			def targetBook = Book.findByTitle(dummyTitle)
			targetBook != null
			targetBook.author.firstname == dummyAuthorFirstname

		
		where:
			dummyTitle = "Lean"
			dummyIsbn  = "01234567890"	
			dummyAuthorFirstname = "Twin"
	}
	
	def "book should display it's title as a result of toString"(){
		when:
			def book = dummyBook
			
		then:
			book.toString() == title
			
		where:
			title = "Lean"
	}
	
	def "book title should not longer than 50 characters"(){
		setup:
			mockForConstraintsTests(Book, [dummyBook])
			mockDomain(Author)
			
		when:
			def book = dummyBook
			book.title = invalidBookTitle			
			book.validate()

		then:
			book.errors.hasFieldErrors("title")
			
		where:
			invalidBookTitle = "123456789012345678901234567890123456789012345678901"
			
	}
	
	def "book title should not be blank"(){
		setup:
			mockForConstraintsTests(Book, [dummyBook])
			mockDomain(Author)
			
		when:
			def book = dummyBook
			book.title = blankTitle
			book.validate()
			
		then:
			book.errors.hasFieldErrors("title")
			
		where:
			blankTitle = ""
	}
	
	def "book isbn must not longer than 10 digit"(){
		setup:
			mockForConstraintsTests(Book, [dummyBook])
			mockDomain(Author)
			
		when:
			def book = dummyBook
			book.isbn = invalidIsbn
			book.validate()

		then:
			book.errors.hasFieldErrors("isbn")
			
		where:
			invalidIsbn = "12345678901"		
	}
	
}
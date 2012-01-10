package grails2spock

import grails.plugin.spock.*
import spock.lang.Unroll

class AuthorSpec extends UnitSpec{
	def "find author by firstname and lastname"() {
    	setup:
    		mockDomain(Author)

    	when:
    		new Author(firstname: firstname, lastname: lastname).save()

    	then:
    		Author.findByFirstnameAndLastname(firstname, lastname) != null

    	where:
    		firstname = "John"
    		lastname = "Doe"
 	}

	@Unroll({"test firstname all constraints: $firstname is $valid"})	
	def "firstname constraints"(){
		setup:
			mockForConstraintsTests(Author)
		
		when:
			def author = new Author(firstname: firstname, lastname: "Panichsombat")
			author.validate()
		
		then:
			author.hasErrors() == !valid
			
	    where:
			firstname               | valid
			"123456789012345678901" | false
			"12345678901234567890"  | true
			""                      | false
	}
	
	@Unroll({"test lastname all constraints: $lastname is $valid"})
	def "lastname constraints"(){
		setup:
			mockForConstraintsTests(Author)
		
		when:
			def author = new Author(firstname:"Twin", lastname: lastname)
			author.validate()
		then:
			author.hasErrors() == !valid
			
		where:
			lastname				| valid
			""						| false
			"123456789012345678901" | false
			"12345678901234567890"	| true
	}
	
	def "valid toString"(){
		setup:
			mockDomain(Author)
		
		when:
			def author = new Author(firstname:"Twin", lastname:"Panichsombat").save()
		
		then: 	
		    author.toString() == "Twin Panichsombat"
	}


	
}

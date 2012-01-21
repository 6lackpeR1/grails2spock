package grails2spock

import grails.plugin.spock.*

class AuthorControllerSpec extends ControllerSpec {
	def 'text action'() { 
		when: 
			controller.text()
		then: 
			mockResponse.contentAsString == "text" 
	}

	def 'index action'() {
    	setup:
    		mockLogging(AuthorController, true)

    	when:
    		controller.index()

    	then:
    		redirectArgs.action == "list"
  	}	
}
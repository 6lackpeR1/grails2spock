package grails2spock
class AuthorController{
	def text = {
    	render "text"
  	}

  	def index = {
        redirect(action: "list", params: params)
    }
}
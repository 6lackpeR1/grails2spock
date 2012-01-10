package grails2spock
class Author{
	String firstname
	String lastname
	
	static constraints = {
		firstname(blank:false, maxSize:20)
		lastname(blank:false, maxSize:20)
	}
	
	String toString(){
		"$firstname $lastname"
	}
}
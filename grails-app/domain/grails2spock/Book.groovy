package grails2spock
class Book{
	String title
	static belongsTo = [author: Author]
	
	static constraints = {
		title(blank:false, maxSize:50)
	}
	
	String toString(){
		"$title"
	}
}
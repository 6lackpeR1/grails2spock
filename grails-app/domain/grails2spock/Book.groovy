package grails2spock
class Book{
	String title
	String isbn
	static belongsTo = [author: Author]
	
	static constraints = {
		title(blank:false, maxSize:50)
		isbn(blank:false, maxSize:10)
	}
	
	String toString(){
		"$title"
	}
}
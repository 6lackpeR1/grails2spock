package grails2spock
class BookStoreService{
	static transactional = true
	def createThesis(Long authorId){
		Author authorInstance = Author.get(authorId)
		//println "Author of thesis $authorInstance"
	    Book bookInstance = new Book(title: "My copied thesis", isbn:"1234567890", author: authorInstance).save()
	    log.debug("Created thesis $bookInstance")
		println "Book Instance $bookInstance"
	    return bookInstance
	}
}
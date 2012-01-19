package grails2spock
class AuthorControllerSpec extends ExtendedControllerSpec{
	  def 'index action'() {
    setup:
    mockLogging(AuthorController, true)

    when:
    controller.index()

    then:
    redirectArgs.action == "list"
  }
}
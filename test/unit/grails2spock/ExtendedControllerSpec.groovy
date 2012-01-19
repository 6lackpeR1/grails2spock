package grails2spock
import grails.plugin.spock.ControllerSpec

abstract class ExtendedControllerSpec extends ControllerSpec{
	def mockI18N = {
		controller.metaClass.message = { Map map -> return "I18N message ($map)"}
	}
}
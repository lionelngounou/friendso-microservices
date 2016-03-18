package sample.friendso.ui

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.boot.context.embedded.ServletRegistrationBean
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet

class Application extends GrailsAutoConfiguration {
    
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Override 
    Closure doWithSpring(){
    	def beans = {->
    		hystrixMetricsStreamServlet(ServletRegistrationBean, new HystrixMetricsStreamServlet(), '/hystrix/stream'){
    			//loadOnStartup = 1
    		}
    	}
    	return beans
    }
}
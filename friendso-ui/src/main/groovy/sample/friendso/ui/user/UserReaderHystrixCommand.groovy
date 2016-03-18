package sample.friendso.ui.user

import grails.plugins.rest.client.*
import com.netflix.hystrix.*

/** @author lionel stephane ngounou
 */
class UserReaderHystrixCommand extends HystrixCommand<Object> {

	private final Closure cmd
	private String name

	public UserReaderHystrixCommand(String name, Closure cmd){
		super(HystrixCommandGroupKey.Factory.asKey("${this.class.simpleName}_$name"))
		this.cmd = cmd
		this.name = name
	}

	@Override
	protected Object run(){
		this.cmd()
	}

	@Override
	protected Object getFallback(){
		null
	}
}
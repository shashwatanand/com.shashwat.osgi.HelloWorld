package com.shashwat.osgi.helloworld;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.shashwat.osgi.helloservice.service.HelloService;

public class Activator implements BundleActivator {

	private static BundleContext context;
	
	ServiceReference<?> serviceReference;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("Starting Hello world OSGi Bundle");
		serviceReference = context.getServiceReference(HelloService.class.getName());
		HelloService service = (HelloService)context.getService(serviceReference);
		System.out.println(service.greetHello());
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stopping Hello world OSGi Bundle");
		context.ungetService(serviceReference);
		Activator.context = null;
	}
}

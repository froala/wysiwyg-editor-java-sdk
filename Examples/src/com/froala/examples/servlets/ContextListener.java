package com.froala.examples.servlets;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	// Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent event) {

		// Create public folder.
		// Eclipse deploys the apps to
		// workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/
		// So public folder is located here:
		// workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Examples/public
		ServletContext servletContext = event.getServletContext();
		String contextpath = servletContext.getRealPath("/");
		String publicFolderPath = contextpath + "public";
		File path = new File(publicFolderPath);

		if (!path.exists()) {
			path.mkdirs();
		}

		System.out.println("Public folder is located here: " + publicFolderPath);
	}
}

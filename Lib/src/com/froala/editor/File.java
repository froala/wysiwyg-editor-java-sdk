package com.froala.editor;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.froala.editor.file.FileOptions;

/**
 * 
 * @author florin@froala.com
 * File functionality.
 */
public final class File {

	public static final String multipartContentType = "multipart/form-data";
	
	/**
	 * Private constructor.
	 */
	private File() {
		
	}
	
	/**
	 * 
	 * @param req Servlet HTTP request.
	 * @param file Route Server route where the file will be uploaded. This route must be public to be accesed by the editor.
	 * @return Object with link.
	 * @throws Exception 
	 */
	public static Map<Object, Object> upload(HttpServletRequest req, String fileRoute) throws Exception {
		
		return upload(req, fileRoute, new FileOptions());
	}
	
	/**
	 * 
	 * @param req Servlet HTTP request.
	 * @param fileRoute Server route where the file will be uploaded. This route must be public to be accesed by the editor.
	 * @param options File options.
	 * @return Object with link.
	 * @throws Exception
	 */
	public static Map<Object, Object> upload(HttpServletRequest req, String fileRoute, FileOptions options) throws Exception {
			
		if (req.getContentType() == null ||
			req.getContentType().toLowerCase().indexOf(multipartContentType) == -1 ) {
			
			throw new Exception("Invalid contentType. It must be " + multipartContentType);
		}
		
		Part filePart = req.getPart(options.getFieldname());
		
		if (filePart == null) {
			throw new Exception("Fieldname is not correct. It must be: " + options.getFieldname());
		}
		
		// Generate random name.
		String extension = FilenameUtils.getExtension(Utils.getFileName(filePart));
		String name = Utils.generateUniqueString() + "." + extension;
		
		final String linkName = fileRoute + name;
		
	    InputStream fileContent = filePart.getInputStream();
	    
	    java.io.File targetFile = new java.io.File(getAbsoluteServerPath(req, linkName));
	    
	    FileUtils.copyInputStreamToFile(fileContent, targetFile);
	    
	    Map<Object, Object> linkObject = new HashMap<Object, Object>();
	    linkObject.put("link", linkName);
		return linkObject;
	}
	
	public static String getAbsoluteServerPath(HttpServletRequest req, String relativePath) {
		return req.getServletContext().getRealPath(relativePath);
	}
}

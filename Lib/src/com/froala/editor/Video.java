package com.froala.editor;

import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

import com.froala.editor.video.VideoOptions;


public class Video {

	/**
	 * Content type string used in http multipart.
	 */
	public static final String multipartContentType = "multipart/form-data";

	/**
	 * Private constructor.
	 */
	private Video() {

	}

	/**
	 * Video default options.
	 */
	public static final VideoOptions defaultOptions = new VideoOptions();

	/**
	 * Uploads a Video to disk.
	 *
	 * @param req
	 *            Servlet HTTP request.
	 * @param VideoRoute
	 *            Route Server route where the Video will be uploaded. This route
	 *            must be public to be accesed by the editor.
	 * @return Object with link.
	 * @throws Exception
	 */
	public static Map<Object, Object> upload(HttpServletRequest req, String VideoRoute) throws Exception {

		return upload(req, VideoRoute, defaultOptions);
	}

	/**
	 * Uploads a Video to disk.
	 *
	 * @param req
	 *            Servlet HTTP request.
	 * @param VideoRoute
	 *            Server route where the Video will be uploaded. This route must
	 *            be public to be accesed by the editor.
	 * @param options
	 *            Video options. Defaults to {@link #defaultOptions} which has
	 *            </br>
	 *            Fieldname: "Video" </br>
	 *            Validation:
	 *            <ul>
	 *            <li>Extensions: "txt", "pdf", "doc"</li>
	 *            <li>Mime Types: "text/plain", "application/msword",
	 *            "application/x-pdf", "application/pdf"</li>
	 *            </ul>
	 * @return Object with link.
	 * @throws Exception
	 */
	public static Map<Object, Object> upload(HttpServletRequest req, String fileRoute, VideoOptions options)
			throws Exception {
		
		String name = null;
	    
		if (options == null) {
			options = defaultOptions;
		}

		if (req.getContentType() == null || req.getContentType().toLowerCase().indexOf(multipartContentType) == -1) {

			throw new Exception("Invalid contentType. It must be " + multipartContentType);
		}

		Part filePart = req.getPart(options.getFieldname());

		if (filePart == null) {
			throw new Exception("Fieldname is not correct. It must be: " + options.getFieldname());
		}

		// Generate random name.
		String extension = FilenameUtils.getExtension(Utils.getFileName(filePart));
		extension = (extension != null && extension != "") ? "." + extension : extension;
		name = UUID.randomUUID().toString() + extension ;
		
		String linkName = "files/" + name;

		String absoluteServerPath = getAbsoluteServerPath(req, linkName);

		// Save the file on server.
		 java.io.File file = new java.io.File(fileRoute, name);
	
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath());
        }

        catch (Exception e) {
        	
        	System.out.println("<br/> ERROR: " + e);
        }

		if (options.getValidation() != null
				&& !options.getValidation().check(absoluteServerPath, filePart.getContentType())) {

			delete(req, linkName);
			throw new Exception("File does not meet the validation.");
		}

		Map<Object, Object> linkObject = new HashMap<Object, Object>();
		linkObject.put("link", linkName);
		
		return linkObject;
	}

	/**
	 * Get absolute server path.
	 *
	 * @param req
	 *            Used to get the servlet context.
	 * @param relativePath
	 *            Relative path.
	 * @return Absolute path.
	 */
	public static String getAbsoluteServerPath(HttpServletRequest req, String relativePath) {
		return req.getServletContext().getRealPath(relativePath);
	}

	/**
	 * Delete a file from disk.
	 *
	 * @param req
	 *            Used to get the servlet context.
	 * @param src
	 *            Server file path.
	 */
	public static void delete(HttpServletRequest req, String src) {

		String filePath = getAbsoluteServerPath(req, src);

		java.io.File file = new java.io.File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}
	public static String getFileName(final Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	
}

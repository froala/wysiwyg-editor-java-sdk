package com.froala.editor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.froala.editor.image.ImageOptions;

/**
 *
 * @author florin@froala.com Image functionality.
 */
public final class Image {

	/**
	 * Private constructor.
	 */
	private Image() {

	}

	/**
	 * File default options.
	 */
	public static final ImageOptions defaultOptions = new ImageOptions();

	/**
	 * Uploads an image to disk.
	 *
	 * @param req
	 *            Servlet HTTP request.
	 * @param file
	 *            Route Server route where the file will be uploaded. This route
	 *            must be public to be accesed by the editor.
	 * @return Object with link.
	 * @throws Exception
	 */
	public static Map<Object, Object> upload(HttpServletRequest req, String fileRoute) throws Exception {

		return upload(req, fileRoute, defaultOptions);
	}

	/**
	 * Uploads an image to disk.
	 *
	 * @param req
	 *            Servlet HTTP request.
	 * @param fileRoute
	 *            Server route where the file will be uploaded. This route must
	 *            be public to be accesed by the editor.
	 * @param options
	 *            Image options.
	 * @return Object with link.
	 * @throws Exception
	 */
	public static Map<Object, Object> upload(HttpServletRequest req, String fileRoute, ImageOptions options)
			throws Exception {

		if (options == null) {
			options = defaultOptions;
		}

		return File.upload(req, fileRoute, options);
	}

	/**
	 * Delete an image from disk.
	 *
	 * @param req
	 *            Used to get the servlet context.
	 * @param src
	 *            Server file path.
	 */
	public static void delete(HttpServletRequest req, String src) {
		File.delete(req, src);
	}
}

package com.froala.editor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.froala.editor.video.VideoOptions;

/**
 * Video functionality.
 *
 * @author florin@froala.com
 */
public final class Video {

	/**
	 * Private constructor.
	 */
	private Video() {

	}

	/**
	 * File default options.
	 */
	public static final VideoOptions defaultOptions = new VideoOptions();

	/**
	 * Uploads a video to disk.
	 *
	 * @param req
	 *            Servlet HTTP request.
	 * @param fileRoute
	 *            Route Server route where the file will be uploaded. This route
	 *            must be public to be accesed by the editor.
	 * @return Object with link.
	 * @throws Exception
	 */
	public static Map<Object, Object> upload(HttpServletRequest req, String fileRoute) throws Exception {

		return upload(req, fileRoute, defaultOptions);
	}

	/**
	 * Uploads a video to disk.
	 *
	 * @param req
	 *            Servlet HTTP request.
	 * @param fileRoute
	 *            Server route where the file will be uploaded. This route must
	 *            be public to be accesed by the editor.
	 * @param options
	 *            Video options. Defaults to {@link #defaultOptions} which has
	 *            </br>
	 *            Fieldname: "file" </br>
	 *            Validation:
	 *            <ul>
	 *            <li>Extensions: ".mp4", ".webm", ".ogg"</li>
	 *            <li>Mime Types: "video/mp4", "video/webm", "video/ogg"</li>
	 *            </ul>
	 *
	 * @return Object with link.
	 * @throws Exception
	 */
	public static Map<Object, Object> upload(HttpServletRequest req, String fileRoute, VideoOptions options)
			throws Exception {

		if (options == null) {
			options = defaultOptions;
		}

		return File.upload(req, fileRoute, options);
	}

	/**
	 * Delete a video from disk.
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

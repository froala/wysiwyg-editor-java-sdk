package com.froala.editor.video;

import com.froala.editor.file.FileValidation;

/**
 * Video Validation.
 * 
 * @author florin@froala.com
 */
public class VideoValidation extends FileValidation {

	/**
	 * Allowed video validation default extensions.
	 */
	public static final String[] allowedVideoExtsDefault = new String[] { "mp4", "webm", "ogg" };

	/**
	 * Allowed video validation default mimetypes.
	 */
	public static final String[] allowedVideoMimeTypesDefault = new String[] { "video/mp4", "video/webm", "video/ogg" };

	/**
	 * Init default video validation settings.
	 */
	@Override
	protected void initDefault() {

		allowedExts = allowedVideoExtsDefault;
		allowedMimeTypes = allowedVideoMimeTypesDefault;
	}

	/**
	 * Constructor. Validates default videos with: - allowed file extensions:
	 * ".mp4", ".webm", ".ogg" - allowed mime types:
	 * "video/mp4", "video/webm", "video/ogg"
	 */
	public VideoValidation() {
		super();
	}

	/**
	 * Constructor.
	 *
	 * @param allowedExts
	 *            Allowed validation video extensions.
	 * @param allowedMimeTypes
	 *            Allowed validation video mimetypes.
	 */
	public VideoValidation(String[] allowedExts, String[] allowedMimeTypes) {
		super(allowedExts, allowedMimeTypes);
	}

}

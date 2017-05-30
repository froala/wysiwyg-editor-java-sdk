package com.froala.editor.video;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;

import com.froala.editor.CustomValidation;

public class VideoValidation {

	/**
	 * Allowed Video validation default extensions.
	 */
	public static final String[] allowedVideoExtsDefault = new String[] { "mp4", "webm", "ogg" };
	/**
	 * Allowed Video validation default mimetypes.
	 */
	public static final String[] allowedVideoMimeTypesDefault = new String[] { "video/mp4", "video/webm", "video/ogg" };

	/**
	 * Allowed validation extensions.
	 */
	protected String[] allowedExts;

	/**
	 * Allowed validation mimetypes.
	 */
	protected String[] allowedMimeTypes;

	/**
	 * Custom Video validation.
	 */
	protected CustomValidation customValidation;

	/**
	 * Init default Video validation settings.
	 */
	protected void initDefault() {

		allowedExts = allowedVideoExtsDefault;
		allowedMimeTypes = allowedVideoMimeTypesDefault;
	}

	/**
	 * Constructor. Validates default Videos with: - allowed Video extensions:
	 * "mp4", "webm", "ogg" - allowed mime types: "text/plain",
	 * "video/mp4", "video/webm", "video/ogg"
	 */
	public VideoValidation() {

		initDefault();
	}

	/**
	 * Constructor.
	 *
	 * @param allowedExts
	 *            Allowed validation Video extensions.
	 * @param allowedMimeTypes
	 *            Allowed validation Video mimetypes.
	 */
	public VideoValidation(String[] allowedExts, String[] allowedMimeTypes) {

		initDefault();

		if (allowedExts != null) {
			this.allowedExts = allowedExts;
		}

		if (allowedMimeTypes != null) {
			this.allowedMimeTypes = allowedMimeTypes;
		}
	}

	/**
	 * Constructor.
	 *
	 * @param customValidation
	 *            Custom validation.
	 */
	public VideoValidation(CustomValidation customValidation) {

		initDefault();
		this.customValidation = customValidation;
	}

	/**
	 * Check if Video is valid. Use only the custom function if provided. Else
	 * check if the Video has an allowed extension and mimetype.
	 *
	 * @param VideoPath
	 *            Video path.
	 * @param mimeType
	 *            Video mimetype
	 * @return
	 */
	public boolean check(String VideoPath, String mimeType) {

		if (customValidation != null) {
			return customValidation.validate(VideoPath, mimeType);
		}

		return ArrayUtils.contains(allowedExts, FilenameUtils.getExtension(VideoPath))
				&& ArrayUtils.contains(allowedMimeTypes, mimeType.toLowerCase());
	}
}

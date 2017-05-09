package com.froala.editor.video;

import com.froala.editor.video.VideoValidation;

public class VideoOptions {

	/**
	 * Default Froala Editor tag name that points to the file.
	 */
	public static final String fieldnameDefault = "file";

	/**
	 * Tag name that points to the File.
	 */
	private String fieldname;

	/**
	 * Get tag name that points to the Video.
	 */
	public String getFieldname() {
		return fieldname;
	}

	/**
	 * Set tag name that points to the Video.
	 */
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	/**
	 * Video validation.
	 */
	private VideoValidation validation;

	/**
	 * Get Video validation.
	 */
	public VideoValidation getValidation() {
		return validation;
	}

	/**
	 * Set Video validation.
	 */
	public void setValidation(VideoValidation validation) {
		this.validation = validation;
	}

	/**
	 * Init default Video upload settings.
	 */
	protected void initDefault() {
		setValidation(new VideoValidation());
	}

	/**
	 * Constructor. Uses default options: - fieldname "Video" - validation
	 * default VideoValidation. To change them, use getters and setters.
	 */
	public VideoOptions() {

		// Set default fieldname.
		setFieldname(fieldnameDefault);

		// Init default settings.
		initDefault();
	}
}

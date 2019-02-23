package com.froala.editor.video;

import com.froala.editor.file.FileOptions;

/**
 * Video Options used for uploading.
 * 
 * @author florin@froala.com
 */
public class VideoOptions extends FileOptions {

	/**
	 * Init default video upload settings.
	 */
	@Override
	protected void initDefault() {
		setValidation(new VideoValidation());
	}

	/**
	 * Constructor. Uses default options: - fieldname "file" - validation
	 * default VideoValidation. To change them, use getters and setters.
	 */
	public VideoOptions() {
		super();
	}

}

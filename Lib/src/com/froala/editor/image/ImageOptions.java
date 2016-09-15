package com.froala.editor.image;

import com.froala.editor.file.FileOptions;

/**
 * 
 * @author florin@froala.com
 * Image Options used for uploading.
 */
public class ImageOptions extends FileOptions{

	/**
	 * Init default image upload settings.
	 */
	@Override
	protected void initDefault() {
		setValidation(new ImageValidation());
	}
	
	/**
	 * Constructor.
	 * Uses default options:
	 * 	- fieldname "file"
	 *  - validation default ImageValidation.
	 *  To change them, use getters and setters.
	 */
	public ImageOptions() {
		super();
	}
}

package com.froala.editor.file;

/**
 * File Options used for uploading.
 *
 * @author florin@froala.com
 */
public class FileOptions {

	/**
	 * Default Froala Editor tag name that points to the file.
	 */
	public static final String fieldnameDefault = "file";

	/**
	 * Tag name that points to the File.
	 */
	private String fieldname;

	/**
	 * Get tag name that points to the File.
	 */
	public String getFieldname() {
		return fieldname;
	}

	/**
	 * Set tag name that points to the File.
	 */
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	/**
	 * File validation.
	 */
	private FileValidation validation;

	/**
	 * Get File validation.
	 */
	public FileValidation getValidation() {
		return validation;
	}

	/**
	 * Set File validation.
	 */
	public void setValidation(FileValidation validation) {
		this.validation = validation;
	}

	/**
	 * Init default File upload settings.
	 */
	protected void initDefault() {
		setValidation(new FileValidation());
	}

	/**
	 * Constructor. Uses default options: - fieldname "File" - validation
	 * default FileValidation. To change them, use getters and setters.
	 */
	public FileOptions() {
		
		// Set default fieldname.
		setFieldname(fieldnameDefault);
		
		// Init default settings.
		initDefault();
	}
}

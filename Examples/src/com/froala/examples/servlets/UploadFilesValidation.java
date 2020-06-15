package com.froala.examples.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import com.froala.editor.CustomValidation;
import com.froala.editor.File;
import com.froala.editor.file.FileOptions;
import com.froala.editor.file.FileValidation;
import com.froala.editor.image.ImageOptions;
import com.froala.editor.image.ImageValidation;
import com.froala.editor.video.VideoOptions;
import com.froala.editor.video.VideoValidation;
import com.google.gson.Gson;

/**
 * Servlet implementation class UploadFileValidation
 */
@WebServlet("/upload_files_validation")
@MultipartConfig
public class UploadFilesValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFilesValidation() {
		super();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fileRoute = "/public/";

		FileOptions options = new FileOptions();
		options.setFieldname("myFiles");
		String fieldName = options.getFieldname();
		if(ArrayUtils.contains(ImageValidation.allowedImageMimeTypesDefault, request.getPart(fieldName).getContentType().toLowerCase())) {
			options = new ImageOptions();
			options.setFieldname(fieldName);
		}
		if(ArrayUtils.contains(VideoValidation.allowedVideoMimeTypesDefault, request.getPart(fieldName).getContentType().toLowerCase())) {
			options = new VideoOptions();
			options.setFieldname(fieldName);
		}
		options.setValidation(new FileValidation(new CustomValidation() {

			@Override

			public boolean validate(String filePath, String mimeType) {
				if(ArrayUtils.contains(FileValidation.allowedFileMimeTypesDefault, mimeType.toLowerCase())) {
					java.io.File f = new java.io.File(filePath);
					long size = f.length();
					if (size > 10 * 1024 * 1024) {
						return false;
					}
					return true;
				}
                BufferedImage image;
				try {
					image = ImageIO.read(new java.io.File(filePath));
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}

				if (image == null) {
					return false;
				}

				int width = image.getWidth();
				int height = image.getHeight();

				if (width != height) {
					return false;
				}

				java.io.File f = new java.io.File(filePath);
				long size = f.length();

				if (size > 10 * 1024 * 1024) {
					return false;
				}
				return true;
			}
		}));

		Map<Object, Object> responseData;
		try {
			responseData = File.upload(request, fileRoute, options);
		} catch (Exception e) {
			e.printStackTrace();
			responseData = new HashMap<Object, Object>();
			responseData.put("error", e.toString());
		}
		String jsonResponseData = new Gson().toJson(responseData);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponseData);
	}

}

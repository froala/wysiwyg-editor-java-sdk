package com.froala.examples.servlets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.froala.editor.CustomValidation;
import com.froala.editor.Image;
import com.froala.editor.image.ImageOptions;
import com.froala.editor.image.ImageValidation;
import com.google.gson.Gson;

/**
 * Servlet implementation class UploadImageValidation
 */
@WebServlet("/upload_image_validation")
@MultipartConfig
public class UploadImageValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadImageValidation() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fileRoute = "/PATH TO/YOUR PROJECT/WORKSPACE/WEBCONTENT/WEB-INF/SOME FOLDER/";

		ImageOptions options = new ImageOptions();
		options.setFieldname("myImage");
		options.setValidation(new ImageValidation(new CustomValidation() {

			@Override
			public boolean validate(String filePath, String mimeType) {

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

				return true;
			}
		}));

		Map<Object, Object> responseData;
		try {
			responseData = Image.upload(request, fileRoute, options);
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

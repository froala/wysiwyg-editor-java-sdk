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

import com.froala.editor.File;
import com.froala.editor.file.FileOptions;
import com.google.gson.Gson;

/**
 * Servlet implementation class UploadFiles
 */
@WebServlet("/upload_files")
@MultipartConfig
public class UploadFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFiles() {
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

		// No validation.
		FileOptions options = new FileOptions();
		options.setValidation(null);

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
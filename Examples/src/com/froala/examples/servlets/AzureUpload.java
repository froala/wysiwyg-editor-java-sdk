package com.froala.examples.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.froala.editor.azure.AzureConfig;
import com.google.gson.Gson;

/**
 * Servlet implementation class AzureUpload
 */
@WebServlet("/azure_upload")
public class AzureUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AzureUpload() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AzureConfig config = new AzureConfig();
		config.account = System.getenv("AZURE_ACCOUNT");
		config.container = System.getenv("AZURE_CONTAINER");
		config.accessKey = System.getenv("AZURE_ACCESS_KEY");
		config.SASToken = System.getenv("AZURE_SAS_TOKEN");
		config.uploadURL = System.getenv("AZURE_UPLOAD_URL");

		String jsonResponseData = new Gson().toJson(config);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponseData);
	}

}

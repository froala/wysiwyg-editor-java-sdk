package com.froala.examples.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.froala.editor.Image;
import com.google.gson.Gson;

/**
 * Servlet implementation class LoadImage
 */
@WebServlet("/load_images")
public class LoadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadImage() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fileRoute = "/PATH TO/YOUR PROJECT/WORKSPACE/WEBCONTENT/WEB-INF/SOME FOLDER/";
		ArrayList<Object> responseData;
		try {
			responseData = Image.list(request, fileRoute);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		String jsonResponseData = new Gson().toJson(responseData);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponseData);
	}

}

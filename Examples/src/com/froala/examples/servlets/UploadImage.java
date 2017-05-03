package com.froala.examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.froala.editor.Image;
import com.google.gson.Gson;

/**
 * Servlet implementation class UploadImage
 */
@WebServlet("/upload_image")
@MultipartConfig
public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadImage() {
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
		final PrintWriter writer = response.getWriter();

		Map<Object, Object> responseData = null;
		try {
			
			responseData = Image.upload(request, fileRoute); 	// Use default
																// image
																// validation.
			
		} catch (Exception e) {
			
			e.printStackTrace();
			responseData = new HashMap<Object, Object>();
			responseData.put("error", e.toString());
			
		} finally {
			
			String jsonResponseData = new Gson().toJson(responseData);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonResponseData);
			
//			System.out.println("the response data:"+ responseData);
			if (writer != null) {
				System.out.println("writer is diff from null");
                writer.close();
            }
			//response.getWriter().close();
			
		}
		
	}

}

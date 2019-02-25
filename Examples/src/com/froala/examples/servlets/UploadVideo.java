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

import com.froala.editor.Video;
import com.google.gson.Gson;

/**
 * Servlet implementation class UploadVideo
 */
@WebServlet("/upload_video")
@MultipartConfig
public class UploadVideo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadVideo() {
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

		Map<Object, Object> responseData;
		try {
			responseData = Video.upload(request, fileRoute); // Use default
																// video
																// validation.
		} catch (Exception e) {
			e.printStackTrace();
			responseData = new HashMap<Object, Object>();
			responseData.put("error", e.toString());
		}
		// Wait for 5 secs for video upload
        synchronized (responseData) {
            try
            {
                responseData.wait(5000);
                String jsonResponseData = new Gson().toJson(responseData);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonResponseData);
            }
            catch ( InterruptedException e )
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
	}

}

package com.froala.examples.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.froala.editor.S3;
import com.froala.editor.s3.S3Config;
import com.google.gson.Gson;

/**
 * Servlet implementation class AmazonHash
 */
@WebServlet("/amazon_hash")
public class AmazonHash extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AmazonHash() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<Object, Object> hash;
		S3Config config = new S3Config();
		config.bucket = System.getenv("AWS_BUCKET");
		config.region = System.getenv("AWS_REGION");
		config.keyStart = System.getenv("AWS_KEY_START");
		config.acl = System.getenv("AWS_ACL");
		config.accessKey = System.getenv("AWS_ACCESS_KEY");
		config.secretKey = System.getenv("AWS_SECRET_ACCESS_KEY");

		try {
			hash = S3.getHash(config);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		String jsonResponseData = new Gson().toJson(hash);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponseData);
	}

}

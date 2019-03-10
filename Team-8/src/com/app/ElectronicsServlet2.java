package com.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ElectronicsServlet2
 */
@WebServlet("/electronics2")
public class ElectronicsServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con = null;
	PreparedStatement pstmt;
	ResultSet rs = null;
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team8", "root", "root");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		
		Integer itemId = Integer.parseInt(request.getParameter("itemId"));
		Integer qtyDesired = Integer.parseInt(request.getParameter("qtyDesired"));
		Integer c_id = (Integer) session.getAttribute("c_id");
		
		try {
			pstmt =con.prepareStatement("insert into cart values(0, ?, ?, 0, ?)");
			pstmt.setInt(1, itemId);
			pstmt.setInt(2, qtyDesired);
			pstmt.setInt(3, c_id);
			
			int i = pstmt.executeUpdate();
			if(i == 1) {
				response.sendRedirect("electronics");
			}
			else {
				response.sendRedirect("error.html");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

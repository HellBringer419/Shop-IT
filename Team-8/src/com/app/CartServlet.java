package com.app;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con = null;
	PreparedStatement pstmt1, pstmt2;
	ResultSet rs1 = null, rs2 = null;
	
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
		try {
			HttpSession session = request.getSession();
			Integer c_id = (Integer) session.getAttribute("c_id");
			pstmt1 =con.prepareStatement("SELECT item_id, name, price FROM Godown WHERE item_id IN (SELECT item_id FROM Cart WHERE c_id = ? AND is_purchased = 0);");
			
			
			pstmt1.setInt(1, c_id);
			
			PrintWriter out = response.getWriter();
			rs1 = pstmt1.executeQuery();
			String itemName;
			int itemQty, itemId;
			double itemPrice;
			double price = 0.0;
			out.println("<html> <body> <h1 align='center'> Cart </h1>\r\n");
			while(rs1.next()) {
				itemId = rs1.getInt("item_id");
				itemName = rs1.getString("name");
				itemPrice = rs1.getDouble("price");
				
				pstmt2 = con.prepareStatement("SELECT qty FROM Cart WHERE c_id = ? AND is_purchased = 0 AND item_id = ?");
				pstmt2.setInt(1, c_id);
				pstmt2.setInt(2, itemId);
				rs2 = pstmt2.executeQuery();
				
				if(rs2.next()) {
					itemQty = rs2.getInt("qty");
					out.println( "<div> <span> " + itemName + " </span> <img height='100px' width='100px' alt='" + itemName + "' src='./Assets/" + itemId + ".jpg' \"> <span> " + itemQty + " </span>");
					price = price + (itemQty * itemPrice);
					out.println("<span> price: "+ (itemQty * itemPrice) +" </div> ");
				}
			}
			out.println(" <button> <a href='bill'> Pay Amount </a> " + price + " </button>");
			out.println("</body> </html>");
			
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

package com.app;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/books")
public class BookServlet extends HttpServlet {
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
		try {
			pstmt =con.prepareStatement("SELECT * FROM Godown WHERE item_type = 'books'; ");
			PrintWriter out = response.getWriter();
			rs = pstmt.executeQuery();
			String itemName;
			int itemId;
			out.println("<html> <body> <h1 align='center'> Books </h1>\r\n");
			while(rs.next()) {
				itemName = rs.getString("name");
				itemId = rs.getInt("item_id");
				out.println( "<div> <span> " + itemName + " </span> <img alt=' height='100px' width='100px' " + itemName + " src='./Assets/" + itemId + ".jpg '> ");
				out.println("<span><form action='books2' method='post'> <input type='number' name='itemId' value='" + itemId + "' hidden /> <input type='number' min='1' name='qtyDesired' required /> <input type='submit' value='Add to Cart'/>  </form> </span> </div>");
			}
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
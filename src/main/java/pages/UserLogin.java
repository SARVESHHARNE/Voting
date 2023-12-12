package pages;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.CandidateDaoImpl;
import Dao.UserDaoImpl;
import pojos.User;

import static utils.Dbutils.*;
/**
 * Servlet implementation class UserLogin
 */
//@WebServlet(urlPatterns = "/login" , loadOnStartup = 1)
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDaoImpl ud;
	CandidateDaoImpl cd;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	
	public void init() throws ServletException {
		ServletContext Contextconfig = getServletContext();
		ServletConfig config=getServletConfig();
		try {
			openConnection(Contextconfig.getInitParameter("url"),Contextconfig.getInitParameter("user"),Contextconfig.getInitParameter("pass"));
			ud=new UserDaoImpl();
			cd=new CandidateDaoImpl();
		} catch (SQLException e) {
			throw new ServletException("Error in connection ",e);
		}
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User u = ud.authenticateUser(request.getParameter("em"), request.getParameter("pass"));
			session.setAttribute("user_info", u);
			System.out.println(u);
			session.setAttribute("user_dao", ud);
			session.setAttribute("candidate_dao", cd);
			if(u.getRole().equals("admin")) {
				response.sendRedirect("adminpage");
			}else if(u.getRole().equals("voter")&&!u.isStatus()) {
				response.sendRedirect("candidatelist");
			}else {
				response.sendRedirect("logoutpage");
			}
			
		} catch (SQLException e) {
			throw new ServletException("Error in connection ",e);
		}
	}

}

package pages;

import static utils.Dbutils.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CandidateDaoImpl;
import Dao.UserDaoImpl;
import pojos.User;

/**
 * Servlet implementation class RegisterPage
 */
@WebServlet(urlPatterns = "/registerpages")
public class RegisterPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDaoImpl ud;
	CandidateDaoImpl cd;
	@Override
	public void destroy() {
		try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


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
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ud.registerVoter(new User(request.getParameter("fname"),request.getParameter("lname"), request.getParameter("em"),request.getParameter("pass"),Date.valueOf(request.getParameter("date"))));
			response.sendRedirect("login.html");
		} catch (SQLException e) {
			throw new ServletException("Error in connection ",e);
		}
	}

}

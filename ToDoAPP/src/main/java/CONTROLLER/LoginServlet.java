package CONTROLLER;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.LoginDAO;

import MODEL.Login;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDAO loginDao;
	public void init() {
		loginDao=new LoginDAO();
	}
       
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("login/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		authenciated(request, response);
	}
	public void authenciated(HttpServletRequest request, HttpServletResponse response)throws SecurityException,IOException, ServletException {
		String userName=request.getParameter("userName");
		String passWord=request.getParameter("passWord");
		Login login=new Login();
		login.setUserName(userName);
		login.setPassWord(passWord);
		try {
			if(loginDao.validated(login)) {
				RequestDispatcher rq=request.getRequestDispatcher("todo/todo-list.jsp");
				rq.forward(request, response);
						
				
			}
			else {
				HttpSession httpSession=request.getSession();
				httpSession.getAttribute(userName);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

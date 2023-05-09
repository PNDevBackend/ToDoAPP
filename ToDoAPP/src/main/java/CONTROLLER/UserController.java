package CONTROLLER;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import MODEL.User;
import DAO.UserDAO;


@WebServlet("/register")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDao;
    public void init() {
    	userDao=new UserDAO();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("register/register.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		register(request,response);
	}
	public void register(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String userName=request.getParameter("userName");
		String passWord=request.getParameter("passWord");
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		User user=new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setPassWord(passWord);
		try {
			int result=userDao.registerUser(user);
			if(result==1) {
				request.setAttribute("NOTIFICATION","User register successfully");
				
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("register/register.jsp");
		dispatcher.forward(request, response);
		
	}

}

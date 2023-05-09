package CONTROLLER;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ToDoDAO;
import DAO.ToDoDAOImpl;
import MODEL.ToDo;


@WebServlet("/")
public class ToDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ToDoDAO todoDAO;

	public void init() {
		todoDAO = new ToDoDAOImpl();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToDoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=request.getRequestDispatcher("todo/todo-form.jsp");
		dispatcher.forward(request, response);
		
	}
	private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		
		
		
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		
		ToDo newTodo=new ToDo(title, username, description, LocalDate.now(),isDone);
		todoDAO.insertWork(newTodo);
		response.sendRedirect("list");
		
	}
	private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("id"));
		todoDAO.deleteWork(id);
		response.sendRedirect("list");
	}
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		ToDo exits=todoDAO.selectSingleWork(id);
		RequestDispatcher dispatcher=request.getRequestDispatcher("todo/todo-form.jsp");
		request.setAttribute("existToDo", exits);
		dispatcher.forward(request, response);
		
	}
	private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
		
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		ToDo updateTodo = new ToDo(id, title, username, description, targetDate, isDone);
		
		todoDAO.updateWork(updateTodo);
		
		response.sendRedirect("list");
		
	}
	private void listTodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ToDo> listTodo=todoDAO.selectAllWorks();
		request.setAttribute("listTodo", listTodo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
		dispatcher.forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertTodo(request, response);
				break;
			case "/delete":
				deleteTodo(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateTodo(request, response);
				break;
			case "/list":
				listTodo(request, response);
				break;
			default:
				RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
				dispatcher.forward(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	

}

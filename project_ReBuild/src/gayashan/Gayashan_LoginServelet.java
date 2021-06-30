package gayashan;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class Gayashan_LoginServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gayashan_LoginDio gayashan_LoginDio;   
    
	public void init() {
		gayashan_LoginDio = new Gayashan_LoginDio();
	}
	


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Gayashan_Login gayashan_Login = new Gayashan_Login();
		gayashan_Login.setUsername(username);
		gayashan_Login.setPassword(password);
		
		try {
			if (gayashan_LoginDio.validate(gayashan_Login)) {
				//HttpSession session = request.getSession();
				// session.setAttribute("username",username);
				response.sendRedirect("Gayashan_LoginSucsussfull.jsp");
			} else {
				HttpSession session = request.getSession();
				//session.setAttribute("user", username);
				response.sendRedirect("Gayashan_Login.jsp");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}

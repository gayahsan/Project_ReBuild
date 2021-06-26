package gayashan;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class GayaCustomerSevelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private GayaCustomerDio gayaCustomerDio = new GayaCustomerDio();
	
    public GayaCustomerSevelet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Gayashan_Registration.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pName = request.getParameter("pName");
        String pPhone = request.getParameter("pPhone");
        String email = request.getParameter("email");
        String username= request.getParameter("username");
        String password = request.getParameter("password");
        

        GayaCustomer customer = new GayaCustomer();
        customer.setpName(pName);
        customer.setpPhone(pPhone);
        customer.setEmail(email);
        customer.setUsername(username);
        customer.setPassword(password);
        
        try {
			gayaCustomerDio.registerCustomer(customer);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Gayashan_CustomerDetails.jsp");
		dispatcher.forward(request, response);
	}

}

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@javax.annotation.security.DeclareRoles("hey")
@javax.annotation.security.RolesAllowed("hey")
public class SecuredServlet extends HttpServlet {
    public void init() throws ServletException {
          // Do required initialization
          String message = "Hello World";
       }

       public void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

          // Set response content type
          response.setContentType("text/html");

          // Actual logic goes here.
          PrintWriter out = response.getWriter();
          out.println("<h1>" + "message" + "</h1>");
       }

       public void destroy() {
          // do nothing.
       }
}

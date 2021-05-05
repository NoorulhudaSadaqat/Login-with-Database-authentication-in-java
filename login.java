import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;


public class login extends HttpServlet {
  
  //Process the HTTP Get request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
	response.setContentType("text/html");
    
	// get PrintWriter object
	PrintWriter out = response.getWriter();

    String name=request.getParameter("uname");
    String upass=request.getParameter("password");
    String email=request.getParameter("email");


    out.println("<!DOCTYPE html><html lang='en'><head>");
    out.println("<meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'>");
 out.println("<meta http-equiv='X-UA-Compatible' content='ie=edge'>");
 out.println("<title>error</title>");
 out.println("</head>");
 out.println("<html>");
    out.println("<head><title>Response</title><style>");
    out.println("body{");
        out.println("background-color:black;");
    out.println("}");
    out.println(".text");
    out.println("{");
        out.println("color: blanchedalmond;");
        out.println("font-family: Arial, Helvetica, sans-serif;");
        out.println("text-align: center;");
        out.println("padding-top: 200px;");
    out.println("}");
    out.println("</style></head>");
    out.println("<body><div class='text'>");

    try{

    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://127.0.0.1/user-info";

    Connection connect=DriverManager.getConnection(url, "root", "root");

    Statement state=connect.createStatement();

     String query="Select * from signin where email='"+email+"' ";
   
     ResultSet result = state.executeQuery( query );
   
     if(result.next()){
	    String password = result.getString("password");
            if(password.equals(upass)){	out.println("<h1>successfully Loggedin</h1>"); 		}
	    else{	out.println("<h1>Email or password does not match</h1>"); 		}
           
	    
	  }
     
     else{
	    
	  out.println("<h1>Unsuccessful Attempt</h1>");
            out.println("<small>This user is not registered</small>");

          }
       out.println("</div></body></html>");

           state.close();
           connect.close();

    }catch(Exception e){

      out.println(e);
    }

  }

}

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;


public class signUp extends HttpServlet {
  
  //Process the HTTP Get request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
	response.setContentType("text/html");
    
	// get PrintWriter object
	PrintWriter out = response.getWriter();

    String name=request.getParameter("uname");
    String pass=request.getParameter("password");
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

	    out.println("<h1>Cannot Make Account on this Gmail!!! Already Exist</h1>");

	  }
     
     else{
    	    query = "INSERT INTO signin(email,password,username)VALUES('"+ email + "','" + pass+ "','" + name+ "') ";

            System.out.println(query);

            int res = state.executeUpdate( query );

            if(res==1){	out.println("<h1>Insertion successful</h1>"); 		}
	    else{	out.println("<h1>Record could not be inserted.</h1>"); 		}

            

          }

           out.println("</body></html>");
     

           state.close();
           connect.close();

    }catch(Exception e){

      out.println(e);
    }

  }

}

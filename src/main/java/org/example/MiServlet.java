package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/MiServlet")
public class MiServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID =1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//conexion a la base
        Connection con = null;
        PreparedStatement ps = null;
        String url = "jdbc:mysql://127.0.0.1/basesitan=user=root&password=1234";
        String sql = "INSERT INTO TABLAN(correo,nombre,sexo) VALUES (?,?,?)";
        int row =0;
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();

        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url);
        System.out.println(con.isClosed());
        ps = con.prepareStatement(sql);

        if (ps == null){
            return;
        }
        ps.setString(1,req.getParameter("exampleInputEmail"));
        ps.setString(2,req.getParameter("exampleInputName"));
        ps.setString(3,req.getParameter("exampleselect"));

        row = ps.executeUpdate();
        con.close();

        }    catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Hola desde Servlet</h1>");
        out.println("<p>Tomcat 9 + IntelliJ + Maven</p>");
        out.println("</body>");
        out.println("</html>");
        }
        @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        }
}
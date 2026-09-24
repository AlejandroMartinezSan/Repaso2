package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/MiServlet")
public class MiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String correo = req.getParameter("exampleInputEmail1");
        String nombre = req.getParameter("exampleInputName1");
        String sexoValor = req.getParameter("exampleselect");

        String sexo;
        if ("1".equals(sexoValor)) {
            sexo = "Masculino";
        } else if ("2".equals(sexoValor)) {
            sexo = "Femenino";
        } else {
            sexo = "No especificado";
        }

        String url = "jdbc:mysql://127.0.0.1/basesitan?user=root&password=1234";
        String sql = "INSERT INTO TABLAN(correo,nombre,sexo) VALUES (?,?,?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url);
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, correo);
                ps.setString(2, nombre);
                ps.setString(3, sexo);

                int filas = ps.executeUpdate();
                req.setAttribute("filas", filas);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e);
        }

        req.setAttribute("correo", correo);
        req.setAttribute("nombre", nombre);
        req.setAttribute("sexo", sexo);

        req.getRequestDispatcher("/resultado.jsp").forward(req, resp);
    }
}
package br.com.ticketmaster.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.ticketmaster.modelo.Operador;
import br.com.ticketmaster.dao.OperadorDao;


public class TrataLogin extends Comando {

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response ) throws ClassNotFoundException, SQLException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String login = request.getParameter("login");
        
        OperadorDao dao = new OperadorDao();
        Operador user = dao.pesquisar(login);
        if (user != null && user.getNome().equals(login)) {
            /*Cookie cookie = new Cookie("identifica", login);
             response.addCookie(cookie);*/
            HttpSession session = request.getSession();
            session.setAttribute("identifica", user);
            session.setAttribute("nome", login);

            RequestDispatcher rd = request.getRequestDispatcher("tela_operador.jsp");
            rd.include(request, response);
        } else {
            out.println("<h1> Ocorreu um erro durante o processo de autenticação </h1>" );
            response.sendRedirect("login.jsp");
        }
        out.close();
    }
}

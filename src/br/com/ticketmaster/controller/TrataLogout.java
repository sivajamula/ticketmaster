package br.com.ticketmaster.controller;


import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




public class TrataLogout extends Comando {

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response ) throws ClassNotFoundException, SQLException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        

        HttpSession session = request.getSession();
        session.setAttribute("identifica", "");
        session.setAttribute("nome", "");
        session.invalidate(); 
        
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.include(request, response);
        
        
    }
}

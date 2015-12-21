package br.com.ticketmaster.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ticketmaster.jdbc.ConnectionFactory;
import br.com.ticketmaster.jdbc.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {
     
    HashMap controle;
    
    
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        ServletContext contexto = getServletContext();
        String USER = contexto.getInitParameter("user");
        
        controle = new HashMap();
        
        controle.put("login","br.com.ticketmaster.controller.TrataLogin");
        controle.put("deslogar","br.com.ticketmaster.controller.TrataLogout");
        
        controle.put("cad_endereco","br.com.ticketmaster.controller.TrataEndereco");
        controle.put("cad_sala","br.com.ticketmaster.controller.TrataSala");
        controle.put("cad_evento","br.com.ticketmaster.controller.TrataEvento");
        controle.put("cad_sessao","br.com.ticketmaster.controller.TrataSessao");
        controle.put("cad_ticket","br.com.ticketmaster.controller.TrataTicket");
        controle.put("cad_operador","br.com.ticketmaster.controller.TrataOperador");
        
        controle.put("tela_cadastro","tela_cadastro.jsp");
        controle.put("tela_operador","tela_operador.jsp");
        
        try {

        	ConnectionFactory db = new ConnectionFactory();
            //Connection conex1= DBConnection.getConnection();
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
       
        
        
    }
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String servletName = request.getParameter("servlet");
        String ClassDestino = (String)controle.get(servletName);
        
        try{
            Class classe = Class.forName(ClassDestino);
            Comando comando = (Comando) classe.newInstance();
            comando.executar(request, response);
        } catch (ClassNotFoundException ex) {
            throw new ServletException(ex);            
        } catch (InstantiationException iex) {
            throw new ServletException(iex);
        } catch (IllegalAccessException iaex) {
            throw new ServletException(iaex);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

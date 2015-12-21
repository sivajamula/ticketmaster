package br.com.ticketmaster.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ticketmaster.dao.OperadorDao;
import br.com.ticketmaster.modelo.Operador;

public class TrataOperador extends Comando {
	@Override
    public void executar(HttpServletRequest request, HttpServletResponse response ) throws ClassNotFoundException, SQLException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        
        int id = Integer.parseInt(request.getParameter("id"));
        String acao = request.getParameter("acao");
        String nome = request.getParameter("nome");
        //request.getParameter("data_nasc")
        Calendar date = Calendar.getInstance();
		date.set(2015, 12, 14);
		Date data_nasc = date.getTime();
        
        OperadorDao dao = new OperadorDao();
        
        if(id == 0){
        	Operador oper = new Operador( nome,  data_nasc );
            dao.adiciona(oper);
        	
		}else{
			Operador operador = new Operador ( id, nome,  data_nasc );
			
			if (acao != null && acao.equals("excluir")) {   
				dao.remove(operador);
			}else{
				dao.altera(operador);
				
			}
        	
        }
        
        response.sendRedirect("tela_operador.jsp?resposta=ok");
        
	}
}
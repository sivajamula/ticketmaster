package br.com.ticketmaster.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ticketmaster.dao.EnderecoDao;
import br.com.ticketmaster.modelo.Endereco;


public class TrataEndereco extends Comando {
	@Override
    public void executar(HttpServletRequest request, HttpServletResponse response ) throws ClassNotFoundException, SQLException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        
        int id_endereco = Integer.parseInt(request.getParameter("id_endereco"));
        String acao = request.getParameter("acao");
        String rua = request.getParameter("rua");
        int numero = Integer.parseInt(request.getParameter("numero"));
        String complemento = request.getParameter("complemento");
        String uf = request.getParameter("estado");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        
        EnderecoDao dao = new EnderecoDao();
        
        if(id_endereco == 0){
        	Endereco ender = new Endereco ( bairro,  complemento,  uf,  cidade,  numero,  rua);
        	dao.adiciona(ender);
        	
		}else{
			Endereco ender = new Endereco ( id_endereco, bairro,  complemento,  uf,  cidade,  numero,  rua);
			
			if (acao != null && acao.equals("excluir")) {   
				dao.remove(ender);
			}else{
				dao.altera(ender);
				
			}
        	
        }
        
        response.sendRedirect("tela_endereco.jsp?resposta=ok");
        
    }
}

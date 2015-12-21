package br.com.ticketmaster.controller;


import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class Comando {
    
    public abstract void executar(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, ServletException ;
   
}

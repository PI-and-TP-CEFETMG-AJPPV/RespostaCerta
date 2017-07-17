/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.UserManagement;
import br.cefetmg.respostaCerta.model.service.UserManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Terror
 */
public class AlteraDados {
    public static String processa(HttpServletRequest request){
        try {
            UserManagement userMan = new UserManagementImpl(new UserDAOImpl());
            User user = userMan.getUserById((Long)request.getSession().getAttribute("usuario"));
            
            if(!request.getParameter("alteraNomeUsuario").equals("")){
                user.setNomeUsuario((String)request.getParameter("alteraNomeUsuario"));
            }
            
            if(!request.getParameter("alteraEmailUsuario").equals("")){
                user.setLoginUsuario((String)request.getParameter("alteraEmailUsuario"));
            }
            
            userMan.updateUser(user.getIdUsuario(), user);
            
            request.setAttribute("usuario", user);
            request.setAttribute("mensagem", "Dados alterados com sucesso!");
            return "Perfil.jsp";
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            request.setAttribute("mensagem", "Dados alterados com sucesso!");
            return "Perfil.jsp";
        }
    }
}
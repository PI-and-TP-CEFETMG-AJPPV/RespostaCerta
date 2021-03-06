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
public class DeletaConta {
    public static String processa(HttpServletRequest request){
        try {
            UserManagement userMan = new UserManagementImpl(new UserDAOImpl());
            userMan.removeUser((Long)request.getSession().getAttribute("usuario"));
            return Logout.processa(request);
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }
}

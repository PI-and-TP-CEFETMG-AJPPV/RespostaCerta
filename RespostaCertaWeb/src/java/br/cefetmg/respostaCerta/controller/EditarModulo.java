/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ModuleDAOImpl;
import br.cefetmg.respostaCerta.model.dao.SubjectDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.ModuleManagement;
import br.cefetmg.respostaCerta.model.service.ModuleManagementImpl;
import br.cefetmg.respostaCerta.model.service.SubjectManagement;
import br.cefetmg.respostaCerta.model.service.SubjectManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author umcan
 */
public class EditarModulo {
    public static String processa(HttpServletRequest request){
        SubjectManagement subMan = new SubjectManagementImpl(new SubjectDAOImpl());
        ModuleManagement modMan = new ModuleManagementImpl(new ModuleDAOImpl());
        try {
            Subject dominio=null;
            if(request.getParameter("idtNovo").equals("1")){
               dominio = new Subject(request.getParameter("novaDisciplina"));
               subMan.registerSubject(dominio);
            }else{
                dominio = subMan.getSubjectById(Long.parseLong(request.getParameter("disciplina")));
            }
            Module m = new Module();
            m.setDominio(dominio);
            m.setIdModulo(Long.parseLong(request.getParameter("idModulo")));
            m.setNomeModulo(request.getParameter("nomeModulo"));
            modMan.updateModule(m.getIdModulo(), m);
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
        return PagGerenciarCadastro.processa(request);
    }
}
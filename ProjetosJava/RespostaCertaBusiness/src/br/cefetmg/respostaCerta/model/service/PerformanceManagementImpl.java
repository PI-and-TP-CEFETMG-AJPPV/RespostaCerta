/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;

/**
 *
 * @author adalbs
 */
public class PerformanceManagementImpl implements PerformanceManagement{

    /**
     *
     * @param usuario
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Double calculateErrors(User usuario) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param usuario
     * @param modulo
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Double calculateErrorsByModule(User usuario, Module modulo) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param usuario
     * @param disciplina
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Double calculateErrosBySubject(User usuario, Subject disciplina) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

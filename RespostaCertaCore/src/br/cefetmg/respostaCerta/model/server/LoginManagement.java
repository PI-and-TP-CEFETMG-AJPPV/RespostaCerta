/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.server;

import br.cefetmg.respostaCerta.model.service.*;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author umcan
 */
public interface LoginManagement extends Remote{
    public User loginUser(String email, String password) throws BusinessException, PersistenceException, RemoteException;
}

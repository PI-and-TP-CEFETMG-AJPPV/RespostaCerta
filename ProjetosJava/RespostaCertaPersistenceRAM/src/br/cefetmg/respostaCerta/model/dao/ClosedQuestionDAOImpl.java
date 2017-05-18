/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author umcan
 */
public class ClosedQuestionDAOImpl implements ClosedQuestionDAO{
    private static ClosedQuestionDAOImpl closedDAO = null;        

    private static final HashMap<Long, ClosedQuestion> closedQuestionDB = new HashMap<>();    
    private static long closedCount;
    
    public ClosedQuestionDAOImpl() { 
        closedCount = 0;
    }

    public static ClosedQuestionDAOImpl getInstance() {
        
        if (closedDAO == null)
            closedDAO = new ClosedQuestionDAOImpl();
        
        return  closedDAO;
    }
    
    @Override
    synchronized public void insert(ClosedQuestion questaoFechada) throws PersistenceException {

        if (questaoFechada == null)
            throw new PersistenceException("Entidade não pode ser nula.");                
        
        Long questId = questaoFechada.getIdQuestao();
        
        if ((questId != null) && closedQuestionDB.containsKey(questId))
            throw new PersistenceException("Duplicação de chave.");
        
        questId = ++closedCount;
        questaoFechada.setIdQuestao(questId);
        closedQuestionDB.put(questId, questaoFechada);
    }
    
    @Override
    synchronized public void update(ClosedQuestion closedQuestion) throws PersistenceException {

        if (closedQuestion == null)
            throw new PersistenceException("Entidade não pode ser nula.");              
        
        Long closedId = closedQuestion.getIdQuestao();

        if (closedId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");        
        
        if (!closedQuestionDB.containsKey(closedId))
            throw new PersistenceException("Não existe entidade com a chave " + closedId + ".");
        
        closedQuestionDB.replace(closedId, closedQuestion);
    }

    @Override
    synchronized public ClosedQuestion delete(Long closedId) throws PersistenceException {
        if (closedId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!closedQuestionDB.containsKey(closedId))
            throw new PersistenceException("Não existe entidade com a chave " + closedId + ".");
        
        return closedQuestionDB.remove(closedId);
    }

    @Override
    public ClosedQuestion getClosedQuestionById(Long closedId) throws PersistenceException {
        
        if (closedId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!closedQuestionDB.containsKey(closedId))
            throw new PersistenceException("Não existe entidade com a chave " + closedId + ".");
        
        return closedQuestionDB.get(closedId);        
        
    }

    @Override
    public List<ClosedQuestion> listAll() throws PersistenceException {
        List<ClosedQuestion> closedList = new ArrayList<>();
        
        Iterator<ClosedQuestion> iterator = closedQuestionDB.values().iterator();
	while (iterator.hasNext())
            closedList.add(iterator.next());
        
        return closedList;
    }
}
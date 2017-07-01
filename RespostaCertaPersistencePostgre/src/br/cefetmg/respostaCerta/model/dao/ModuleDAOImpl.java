/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.util.db.ConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author umcan
 */
public class ModuleDAOImpl implements ModuleDAO{

    private static ModuleDAOImpl moduleDAO = null;        
  
    private static long moduleCount;
    
    /**
     *
     */
    public ModuleDAOImpl() { 
        moduleCount = 0;
    }

    /**
     *
     * @return
     */
    public static ModuleDAOImpl getInstance() {
        
        if (moduleDAO == null)
            moduleDAO = new ModuleDAOImpl();
        
        return  moduleDAO;
    }
    
    /**
     *
     * @param module
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(Module module) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO Modulo (idDominio, nomeModulo, descModulo) VALUES(?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, module.getDominio().getIdDominio());
            pstmt.setString(2, module.getNomeModulo());
            pstmt.setString(3, module.getDescModulo());
            pstmt.executeQuery();
            pstmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
    /**
     *
     * @param module
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(Module module) throws PersistenceException {
        try{
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE forum SET idDominio = ?, nomeModulo = ?, descModulo = ? WHERE idModulo = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, module.getDominio().getIdDominio());
            pstmt.setString(2, module.getNomeModulo());
            pstmt.setString(3, module.getDescModulo());
            pstmt.setLong(1, module.getIdModulo());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close(); 
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param moduleId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public Module delete(Long moduleId) throws PersistenceException {
        try {
            Module modulo= this.getModuleById(moduleId);
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM modulo WHERE idModulo = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, moduleId);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
            return modulo;
        } catch (PersistenceException | ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @param moduleId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Module getModuleById(Long moduleId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * "
                    + "FROM modulo a "
                    + "JOIN dominio b ON a.idDominio=b.idDominio "
                    + "WHERE a.idModulo = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, moduleId);
            ResultSet rs = pstmt.executeQuery();
            Subject sub = new Subject();
            Module mod = new Module();
            if (rs.next()) {
                mod.setDescModulo(rs.getString("descModulo"));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setNomeModulo(rs.getString("nomeModulo"));
                sub.setDescDominio(rs.getString("descDominio"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                mod.setDominio(sub);
            }
            rs.close();
            pstmt.close();
            connection.close();
            return mod;
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<Module> listAll() throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * "
                    + "FROM modulo a "
                    + "JOIN dominio b ON a.idDominio=b.idDominio";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Module> lista = new ArrayList<>();
            while(rs.next()) {
                Subject sub = new Subject();
                Module mod = new Module();
                mod.setDescModulo(rs.getString("descModulo"));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setNomeModulo(rs.getString("nomeModulo"));
                sub.setDescDominio(rs.getString("descDominio"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                mod.setDominio(sub);
                lista.add(mod);
            }
            rs.close();
            pstmt.close();
            connection.close();
            return lista;
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public List<Module> getModulesSubject(long subjectId) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * "
                    + "FROM modulo a "
                    + "JOIN dominio b ON a.idDominio=b.idDominio "
                    + "WHERE b.idDominio = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, subjectId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Module> lista = new ArrayList<>();
            while(rs.next()) {
                Subject sub = new Subject();
                Module mod = new Module();
                mod.setDescModulo(rs.getString("descModulo"));
                mod.setIdModulo(rs.getLong("idModulo"));
                mod.setNomeModulo(rs.getString("nomeModulo"));
                sub.setDescDominio(rs.getString("descDominio"));
                sub.setIdDominio(rs.getLong("idDominio"));
                sub.setNomeDominio(rs.getString("nomeDominio"));
                mod.setDominio(sub);
                lista.add(mod);
            }
            rs.close();
            pstmt.close();
            connection.close();
            return lista;
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entities.Formation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HELA
 */
public interface IServicesF<T> {

/**
 *
 * @author House
 */
    void ajouter(Formation f) throws SQLException;
  
    void delete(Formation f) throws SQLException;
    void update(Formation f) throws SQLException;
    List<T> readAll() throws SQLException;
    
}
    


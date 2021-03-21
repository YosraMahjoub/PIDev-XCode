/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entities.Event;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface InterfaceService<T> {
    
    
    public void Ajouter(T object) throws SQLException;
    public void Modifier(T object) throws SQLException;
    public void Supprimer(T object) throws SQLException;
    public List<T> Afficher() throws SQLException;
    public T AfficherParId(int id) throws SQLException;
    
    
}

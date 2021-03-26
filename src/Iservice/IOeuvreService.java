/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import java.util.List;

/**
 *
 * 
 */
public interface IOeuvreService <T> {
    public void ajouterO(T o, int id);
    public void modifierO(T o);
    public void supprimerO(T o);
    public List <T> afficherLO();
     public List<T> displayAll();
    public T afficherO(int id);
}

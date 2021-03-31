/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import java.util.List;

/**
 *
 * @author Mega-PC
 */
public interface Icmdservices <T> {
    public void ajouter(T o);
    public void Updatecmd(T o);
    public void Delcmd(T o);
    public List <T> afficherLO();
    public List<T> displayAll();
    public void getAll();
    public T afficherO(int id);
}

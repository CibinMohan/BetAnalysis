/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.DTO;
import java.util.List;
import javax.ejb.Remote;
import pogo.CartItem;

/**
 *
 * @author cibin
 */
@Remote
public interface ProductsFacadeRemote {

    void create(Products products);

    void edit(Products products);

    void remove(Products products);

    Products find(Object id);

    List<Products> findAll();

    List<Products> findRange(int[] range);

    int count();

    List<DTO> findItems(String name);
    
    boolean confirmBuy(DTO dto);
    
    List<CartItem> addItem(DTO dto);

    List<CartItem> removeItem( DTO dto);
    
    List<CartItem> editItem( DTO dto);
}

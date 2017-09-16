/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.DTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pogo.CartItem;

/**
 *
 * @author cibin
 */
@Stateful
public class ProductsFacade extends AbstractFacade<Products> implements ProductsFacadeLocal, ProductsFacadeRemote {

    @PersistenceContext(unitName = "EAP-ejbPU")
    private EntityManager em;
    private List<Products> pro = new ArrayList<>();
    private List<CartItem> item = new ArrayList<>();
    private List<DTO> availableProducts;
    private double total;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsFacade() {
        super(Products.class);

    }

    @Override
    public List<DTO> findItems(String name) {
        try {

            pro = findAll();
            availableProducts = new ArrayList<>();

            for (Products p : pro) {
                if (p.getName().toLowerCase().startsWith(name.toLowerCase())) {
                    DTO dto = new DTO();
                    dto.setProductid(p.getProductid());
                    dto.setName(p.getName());
                    dto.setQuantity(p.getQuantity());
                    dto.setSellingprice(p.getSellingprice());
                    availableProducts.add(dto);
                }

            }

        } catch (Exception e) {
        }
        return availableProducts;
    }
    
    
    
     @Override
    public boolean confirmBuy(DTO dto){
        
              try {
             
                    Products p = getEntityManager().find(Products.class,dto.getProductid());
                    p.setQuantity(dto.getQuantity());
                    return true;
             
         } catch (Exception e) {
             return false;
         }            
                
    }

    @Override
    public List<CartItem> addItem(DTO dto) {
        CartItem c = new CartItem();
        
       c.setName(dto.getName());
       c.setQty(dto.getCost());
        pro = findAll();
        
        for (Products d : pro) {
            String id = d.getProductid();
            if (d.getName().toLowerCase().equals(dto.getName().toLowerCase())) {
                c.setProductId(id);
                c.setSellPrice(d.getSellingprice());
                c.setBalance(d.getQuantity() - dto.getCost());
            }

        }

        item.add(c);
        return item;
    }

    @Override
    public List<CartItem> removeItem(DTO dto) {
        
        for (CartItem c : item) {
            if (c.getName().toLowerCase().equals(dto.getName().toLowerCase())) {
                item.remove(c);break;
            }
       }
        return item;
    }
    
     @Override
    public List<CartItem> editItem( DTO dto) {
        
       for (CartItem c : item) {
            if (c.getName().toLowerCase().equals(dto.getName().toLowerCase())) {
                c.setQty(c.getQty()-dto.getQuantity());
            }
        }
        return item;
    }
  
}

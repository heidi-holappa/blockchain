package com.mycompany.blockchainsample;

import java.util.ArrayList;

/**
 *
 * @author Heidi Holappa
 */
public class Blockchain {
    
    private ArrayList<Block> blockchain;

    public Blockchain() {
        
        this.blockchain = new ArrayList<>();
    }
    
    public void addBlock(Block block) {
        this.blockchain.add(block);
    }
    
    public void getAll() {
        
        int size = blockchain.size();
        
        for (int i = 0; i < size; i++){
            System.out.println(blockchain.get(i).toString());
        }
            
    }
    
    public String getPreviousHash() {
        int previousId = blockchain.size()-1;
        return blockchain.get(previousId).getHash();
    }
    
    public void alterData(int id, String data) {
        this.blockchain.get(id).alterData(data);
        
    }
    
    public boolean isValid() {
        int size = blockchain.size();
        for (int i = 0; i < size; i++) {
            if (!blockchain.get(i).getHash().equals(blockchain.get(i+1).getPreviousHash())) {
                return false; 
            }        
            
        }
        
        return true; 
        
    }
    
    public int generateId() {
        return this.blockchain.size();
    }
    
}

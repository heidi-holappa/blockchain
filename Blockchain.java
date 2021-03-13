package com.mycompany.blockchainsample;

import java.util.ArrayList;

/**
 *
 * @author Heidi Holappa
 */

// This class contains the list of Block objects and methods for handling the list 
public class Blockchain {
    
    private ArrayList<Block> blockchain;
    private int prefix;

    // Creates the blockchain. Prefix is set to 4 by default. 
    public Blockchain() {
        this.blockchain = new ArrayList<>();
        this.prefix = 4;
        
    }
    
    public void addBlock(Block block) {
        this.blockchain.add(block);
    }
    
    // Prints the objects in the list to the console
    public void getAll() {    
        int size = blockchain.size();    
        for (int i = 0; i < size; i++){
            System.out.println(blockchain.get(i).toString());
        }
            
    }
    
    // Returns the String hash of the lastest Block on the list. 
    public String getPreviousHash() {
        int previousId = blockchain.size()-1;
        return blockchain.get(previousId).getHash();
    }
    
    // This method initiates altering a previously entered data-item
    // Gets a Block object and calls it's method alterData()
    public void alterData(int id, String data) {
        this.blockchain.get(id).alterData(data, this.prefix);
    }
    
    // In this demo the hash is also altered when data is altered
    // Even if the hash was not altered, this method would still spot the change in the chain.
    public boolean isValid() {
        int size = blockchain.size()-1;
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
    
    public int getPrefix() {
        return this.prefix;
    }
    
    // Even with 8 zeros it takes quite a while to 'mine' the block.
    public void setPrefix(int n) {
        if (0 < n && n <9) {
            this.prefix = n;
            
        } else {
            System.out.println("PLEASE INSERT VALUE BETWEEN 1-8 ");
        }
        
    }
    
}

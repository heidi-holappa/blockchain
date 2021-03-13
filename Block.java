
package com.mycompany.blockchainsample;

import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.MessageDigest;
import java.lang.System;
import java.util.Date;
import java.sql.Timestamp;



/**
 *
 * @author Heidi Holappa
 */

public class Block {

    private int id;
    private String data;
    private int nonce;
    private String hash;
    private String previousHash;
    public long date; 
  
  
    // Create a block
    public Block(int id, String data, String previousHash, long date) {
        this.id = id;
        this.data = data;
        this.previousHash = previousHash;
        this.date = date;
        this.hash = "NOTMINED";

    
    
    }
  
    @Override
    public String toString() {
        return "ID: " + this.id 
                + " / DATA: " + this.data 
                + " / PREVIOUS_HASH " + this.previousHash
                + " / NONCE: " + this.nonce
                + " / DATE " + this.date
                + " / HASH: " + this.hash;
    }
    
    // This method is used when the block has already been mined
    // The nonce is set in a way that the hash starts with an amount of zeros that equals to the prefix at the time
    // Just calculating the has is quite fast. 
    public String getHash() {
        return calculateBlockHash();
    }
    
    public String getPreviousHash() {
        return this.previousHash;
    }
    
    public void alterData(String data, int prefix) {
        this.data = data;
        this.hash = "NOTMINED";
        this.hash = mineBlock(prefix);
    }
    
    // Taken from Baeldungs example
    // This method does the mining based on the set prefix
    // By default the prefix is 4 which means that there needs to be 4 zeros in the beginning of the hash. 
    public String mineBlock(int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!hash.substring(0, prefix).equals(prefixString)) {
            nonce++;
            hash = calculateBlockHash();
        }

        return hash; 
    }
  

  
    // Taken and slightly altered from the Baeldung tutorial on blockchain
    public String calculateBlockHash() {
        String dataToHash = previousHash + Long.toString(date) + Integer.toString(nonce) +  data;
        
        MessageDigest digest = null;
        byte[] bytes = null;
        
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(UTF_8));
        } catch (Exception e) {
            System.out.println("ABORT: Hash creation failed.");
        }
        
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        
        return buffer.toString();
    }

    
}

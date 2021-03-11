
package com.mycompany.blockchainsample;

import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.MessageDigest;
import java.util.Date;
import java.sql.Timestamp;



/**
 *
 * @author Heidi Holappa
 */

public class Block {

    private int id;
    private String data;
    private String privateKey;
    private String hash;
    private String previousHash;
    public long date; 
  
  
    // Create a block
    public Block(int id, String data, String previousHash, long date) {
        this.id = id;
        this.data = data;
        this.previousHash = previousHash;
        this.date = date;
        this.hash = calculateBlockHash();

    // call a method that creates the public key and perhaps a private key? 
    
    }
  
    @Override
    public String toString() {
        return "ID: " + this.id 
                + " / DATA: " + this.data 
                + " / PREVIOUSHASH " + this.previousHash 
                + " / HASH: " + this.hash 
                + " / DATE " + this.date;
    }
    
    public String getHash() {
        return this.hash;
    }
    
    public String getPreviousHash() {
        return this.previousHash;
    }
    
    public void alterData(String data) {
        this.data = data;
        this.hash = calculateBlockHash();
    }
  

  
    // Taken and slightly altered from the Baeldung tutorial on blockchain
    public String calculateBlockHash() {
        String dataToHash = this.previousHash + this.date + this.data;
        
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

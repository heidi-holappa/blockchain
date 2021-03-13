
package com.mycompany.blockchainsample;
import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author Heidi Holappa
 */

import java.util.Scanner;

public class BlockchainMain {
    
    public static void main(String[] args) {
        
        // Create an ArrayList for the blocks
        Blockchain blockchain = new Blockchain();
        
        // create a genesis-block
        int genesisId = 0;
        String genesisData = "GENESIS_BLOCK";
        String genesisPreviousHash = "NA";
        Date genesisGetDate = new Date();
        long genesisTimestamp = genesisGetDate.getTime();
        
        Block genesisBlock = new Block(genesisId, genesisData, genesisPreviousHash, genesisTimestamp);
        genesisBlock.mineBlock(blockchain.getPrefix());
        // add genesis-block to the ArrayList
        blockchain.addBlock(genesisBlock);
        
        while (true) {
            System.out.println("1: ADD BLOCK");
            System.out.println("2: ADD MULTIPLE BLOCKS");
            System.out.println("3: CHECK VALIDITY");
            System.out.println("4: ALTER DATA");
            System.out.println("5: PRINT BLOCKS");
            System.out.println("6: ALTER PREFIX");
            System.out.println("7: DEMO PROOF OF WORK WITH PREFIX 1-6");
            System.out.println("0: END");
            
            Scanner reader = new Scanner(System.in);
            String input = reader.nextLine();
            
            // Add a single block to the chain
            if (input.equals("1")) {
                System.out.print("ENTER DATA: ");
                String blockData = reader.nextLine();
                
                long startTime = System.nanoTime();
                
                int blockId = blockchain.generateId();
                String blockPreviousHash = blockchain.getPreviousHash();
                Date blockDate = new Date();
                long blockTimestamp = genesisGetDate.getTime();
                Block block = new Block(blockId, blockData, blockPreviousHash, blockTimestamp);
                block.mineBlock(blockchain.getPrefix());
                blockchain.addBlock(block);
                
                long endTime = System.nanoTime();
                long elapsedTime = (endTime - startTime) / 1000000;
                System.out.println("####");
                System.out.println("ELAPSED TIME: " + elapsedTime + " MILLISECONDS");
                System.out.println("####");
                continue; 
            
            // Add multiple blocks with automatically generated data-entry.
            } else if (input.equals("2")) {
                System.out.print("NUMBER OF BLOCKS TO BE ADDED: ");
                int number = Integer.valueOf(reader.nextLine());
                
                long startTime = System.nanoTime();
                
                for (int i = 0; i < number; i++) {
                    int blockId = blockchain.generateId();
                    String blockData = "DATA ENTRY FOR ID " + blockId;
                    String blockPreviousHash = blockchain.getPreviousHash();
                    Date blockDate = new Date();
                    long blockTimestamp = genesisGetDate.getTime();
                    Block block = new Block(blockId, blockData, blockPreviousHash, blockTimestamp);
                    block.mineBlock(blockchain.getPrefix());
                    blockchain.addBlock(block);
                }
                
                long endTime = System.nanoTime();
                long elapsedTime = (endTime - startTime) / 1000000;
                System.out.println("####");
                System.out.println("ELAPSED TIME: " + elapsedTime + " MILLISECONDS");
                System.out.println("####");
                
                continue; 

            // Check is chain valid. For block's which id is between 0 <= id < n, where n = (blockchain.size() - 1), the previousHash of block m+1 must equal the hash of block m. 
            } else if (input.equals("3")) {
                System.out.println("---------------");
                System.out.println("CHAIN VALID: " + blockchain.isValid());
                System.out.println("---------------");
            
            // Change data on selected block to see what happens to the chain. 
            } else if (input.equals("4")) {
                System.out.print("ENTER ID: ");
                int alterId = Integer.valueOf(reader.nextLine());
                System.out.print("ENTER DATA: ");
                String newData = reader.nextLine();
                if (alterId < 0 || alterId > blockchain.generateId()-1) {
                    System.out.println("ERROR. INVALID ID. PLEASE TRY AGAIN");
                    continue;
                } else if (alterId == blockchain.generateId()-1) {
                    System.out.println("---------------");
                    System.out.println("WARNING! ID CAN NOT BE THE LAST BLOCK IN THE CHAIN. PLEASE TRY AGAIN");
                    System.out.println("---------------");
                    continue; 
                }
                blockchain.alterData(alterId, newData);                
                System.out.println("---------------");
                System.out.println("DATA ALTERED.");
                System.out.println("---------------");
                continue;
            
            // Print all items in the ArrayList blockchain to console.     
            } else if (input.equals("5")) {
                blockchain.getAll();
                continue; 
            } else if (input.equals("6")) {
                System.out.print("ENTER VALUE BETWEEN 1-8: ");
                int newPrefix = Integer.valueOf(reader.nextLine());
                blockchain.setPrefix(newPrefix);
                continue; 
            // Calculates the 'cost' of finding a hash with the set prefix by brute force.
            // The cost is starting to really show with prefix values 4 and higher. 
            // On my old machine (HP Elitebook 1040 G1) it takes app. 10 minutes to mine one block with prefix 6. 
            } else if (input.equals("7")) {
                int newPrefix = 1; 
                blockchain.setPrefix(newPrefix);
                for (int i=0; i<6; i++) {
                    long startTime = System.nanoTime();
                    
                    
                    int blockId = blockchain.generateId();
                    String blockData = "DATA ENTRY FOR ID " + blockId;
                    String blockPreviousHash = blockchain.getPreviousHash();
                    Date blockDate = new Date();
                    long blockTimestamp = genesisGetDate.getTime();
                    Block block = new Block(blockId, blockData, blockPreviousHash, blockTimestamp);
                    block.mineBlock(blockchain.getPrefix());
                    blockchain.addBlock(block);
                    
                    
                    long endTime = System.nanoTime();
                    long elapsedTime = (endTime - startTime) / 1000000;
                    System.out.println("####");
                    System.out.println("PREFIX: " + newPrefix);
                    System.out.println("ELAPSED TIME: " + elapsedTime + " MILLISECONDS");
                    System.out.println("####");
                    System.out.println("CREATED BLOCK: "+ block.toString());
                    System.out.println("####");
                    
                    newPrefix++;
                    blockchain.setPrefix(newPrefix);
                    
                    
                }
                
                blockchain.setPrefix(4);
                continue;
            
            } else if (input.equals("0")) {
                break;
                
            } else {
                System.out.println("---------------");
                System.out.println("INVALID COMMAND. TRY AGAIN. PRESS 0 TO END.");
                System.out.println("---------------");
            }
            
            
        }

    }
}

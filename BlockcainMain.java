
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
        
        // add genesis-block to the ArrayList
        blockchain.addBlock(genesisBlock);
        
        while (true) {
            System.out.println("1: ADD BLOCK");
            System.out.println("2: PRINT BLOCKS");
            System.out.println("3: CHECK VALIDITY");
            System.out.println("4: ALTER DATA");
            System.out.println("0: END");
            
            Scanner reader = new Scanner(System.in);
            String input = reader.nextLine();
            
            if (input.equals("1")) {
                System.out.print("ENTER DATA: ");
                String blockData = reader.nextLine();
                int blockId = blockchain.generateId();
                String blockPreviousHash = blockchain.getPreviousHash();
                Date blockDate = new Date();
                long blockTimestamp = genesisGetDate.getTime();
                Block block = new Block(blockId, blockData, blockPreviousHash, blockTimestamp);
                blockchain.addBlock(block);
                continue; 
                
            } else if (input.equals("2")) {
                blockchain.getAll();
                continue; 
            } else if (input.equals("3")) {
                System.out.println(blockchain.isValid());
                
            
            } else if (input.equals("4")) {
                System.out.print("ENTER ID: ");
                int alterId = Integer.valueOf(reader.nextLine());
                System.out.print("ENTER DATA: ");
                String newData = reader.nextLine();
                if (alterId < 0 || alterId > blockchain.generateId()-1) {
                    System.out.println("INVALID ID. PLEASE TRY AGAIN");
                    continue;
                } else if (alterId == blockchain.generateId()-1) {
                    System.out.println("ID CAN NOT BE THE LAST BLOCK IN THE CHAIN");
                    continue; 
                }
                blockchain.alterData(alterId, newData);                
                continue;
            
            } else if (input.equals("0")) {
                break;
                
            } else {
                System.out.println("INVALID COMMAND. TRY AGAIN. PRESS 0 TO END.");
            }
            
            
        }
        
        
        // Get all blocks
        
        
        

        // Create a simple console-UI for submitting blocks and then options to violate the chain and to see what happens

    }

    
}

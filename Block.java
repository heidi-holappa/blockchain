import java.time.LocalDateTime;

public class Block {
  
  private int id;
  private String data;
  private String privateKey;
  private String Hash;
  private String previousHash;
  public LocalDateTime date; 
  
  
  // Create a block
  public Block(int id, String data, String perviousHash, LocalDateTime date) {
    this.id = id;
    this.message = message;
    this.previousPublicKey = previousPublicKey;
    this.date = date;
    
    // call a method that creates the public key and perhaps a private key? 
    
  }
  
  // Encrypt the privateKey
  // Maybe add a publicKey?
  
    
  
  
}

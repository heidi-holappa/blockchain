import java.time.LocalDateTime;

public class Block {
  
  private int id;
  private String message;
  private String privateKey;
  private String publicKey;
  private String previousPublicKey;
  public LocalDateTime date; 
  
  
  // Create a block
  public Block(int id, String message, String previousPublicKey, String privateKey, LocalDateTime date) {
    this.id = id;
    this.message = message;
    this.previousPublicKey = previousPublicKey;
    this.date = date;
    
    // call a method that creates the public key and perhaps a private key? 
    
  }
  
  // Encrypt the privateKey
  // Maybe add a publicKey?
  
    
  
  
}

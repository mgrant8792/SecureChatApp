import javax.crypto.SecretKey;

public class User {
    private String name;
    private SecretKey sharedKey;

    public User(String name, SecretKey key) {
        this.name = name;
        this.sharedKey = key;
    }

    public String getName() {
        return name;
    }

    // Send encrypted message
    public String sendMessage(String message) throws Exception {
        return CryptoUtils.encrypt(message, sharedKey);
    }

    // Receive (decrypt) message
    public void receiveMessage(String encryptedMessage, String sender) throws Exception {
        String decrypted = CryptoUtils.decrypt(encryptedMessage, sharedKey);
        System.out.println(sender + " → " + name + ": " + decrypted);
    }
}

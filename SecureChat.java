import javax.crypto.SecretKey;
import java.util.Scanner;

public class SecureChat {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("🔐 Secure Chat Demo");
        System.out.print("Enter name for User 1: ");
        String user1Name = sc.nextLine();

        System.out.print("Enter name for User 2: ");
        String user2Name = sc.nextLine();

        // Generate one shared key for both users
        SecretKey sharedKey = CryptoUtils.generateKey();

        User user1 = new User(user1Name, sharedKey);
        User user2 = new User(user2Name, sharedKey);

        System.out.println("\n🔑 Shared Encryption Key: " + CryptoUtils.keyToString(sharedKey));
        System.out.println("Type 'exit' to quit.\n");

        // Simulate message exchange
        while (true) {
            System.out.print(user1Name + " > ");
            String msg1 = sc.nextLine();
            if (msg1.equalsIgnoreCase("exit")) break;

            String encryptedMsg = user1.sendMessage(msg1);
            user2.receiveMessage(encryptedMsg, user1Name);
            System.out.println("[Encrypted]: " + encryptedMsg + "\n");

            System.out.print(user2Name + " > ");
            String msg2 = sc.nextLine();
            if (msg2.equalsIgnoreCase("exit")) break;

            String encryptedMsg2 = user2.sendMessage(msg2);
            user1.receiveMessage(encryptedMsg2, user2Name);
            System.out.println("[Encrypted]: " + encryptedMsg2 + "\n");
        }

        System.out.println("Chat ended securely.");
        sc.close();
    }
}

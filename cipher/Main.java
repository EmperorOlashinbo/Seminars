package se.hkr.cipher;

public class Main {
    public static void main(String[] args) {
        Cipher shiftCipher = new ShiftCipher(3);
        Cipher replacementCipher = new ReplacementCipher("ZYXWVUTSRQPONMLKJIHGFEDCBA");
        String originalText = "Hello, World!";
        String encryptedTextShift = shiftCipher.encrypt(originalText);
        String decryptedTextShift = shiftCipher.decrypt(encryptedTextShift);
        String encryptedTextReplacement = replacementCipher.encrypt(originalText);
        String decryptedTextReplacement = replacementCipher.decrypt(encryptedTextReplacement);

        System.out.println("--- Encryption and Decryption Application ---");
        System.out.println();
        System.out.println("Original Text: \"" + originalText + "\"");
        System.out.println();
        System.out.println("Encrypted Text (Shift Cipher): \"" + encryptedTextShift + "\"");
        System.out.println("Encrypted Text (Replacement Cipher): \"" + encryptedTextReplacement + "\"");
        System.out.println();
        System.out.println("Decrypted Text (Shift Cipher): \"" + decryptedTextShift + "\"");
        System.out.println("Decrypted Text (Replacement Cipher): \"" + decryptedTextReplacement + "\"");
    }
}

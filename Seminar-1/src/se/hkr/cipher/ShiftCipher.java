package se.hkr.cipher;

public class ShiftCipher implements Cipher {
    private int shift;
    public  ShiftCipher(int shift) {
        this.shift = shift;
    }
    public String encrypt(String text) {
        StringBuilder encryptedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                encryptedText.append((char) ((ch - base + shift) % 26 + base));
            }
            else {
                encryptedText.append(ch);
            }
        }
        return encryptedText.toString();
    }
    public String decrypt(String text) {
        StringBuilder decryptedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                decryptedText.append((char) ((ch - base - shift + 26) % 26 + base));
            }
            else {
                decryptedText.append(ch);
            }
        }
        return decryptedText.toString();
    }
}


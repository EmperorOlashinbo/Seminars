package se.hkr.cipher;

public class ReplacementCipher implements Cipher {
    private String replacementLetters;
    public ReplacementCipher(String replacementLetters) {
        this.replacementLetters = replacementLetters;
    }
    public String encrypt(String text) {
        StringBuilder encryptedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                int index = ch - base;
                index = (index + replacementLetters.length()) % replacementLetters.length();
                encryptedText.append(replacementLetters.charAt(index));
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
                int index = replacementLetters.indexOf(ch);
                index = (index + replacementLetters.length()) % replacementLetters.length();
                decryptedText.append((char) (index + base));
            }
            else {
                decryptedText.append(ch);
            }
        }
        return decryptedText.toString();
    }
}

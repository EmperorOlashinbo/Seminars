package se.hkr.cipher;

interface Cipher {
    String encrypt(String text);
    String decrypt(String text);
}
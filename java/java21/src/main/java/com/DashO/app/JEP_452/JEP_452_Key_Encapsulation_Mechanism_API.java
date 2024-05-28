package com.DashO.app.JEP_452;

import javax.crypto.DecapsulateException;
import javax.crypto.KEM;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class JEP_452_Key_Encapsulation_Mechanism_API {
public static void jep_452() {

    // Generate an XDH (X25519) key pair
    KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance("XDH");
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    kpg.initialize(255);
    KeyPair kp = kpg.generateKeyPair();
    System.out.println( "1 "+ kpg);
    System.out.println( "2 "+ kp);

    // Create a KEM instance for DHKEM
    KEM kem = null;
    try {
        kem = KEM.getInstance("DHKEM");
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
    System.out.println( "3 "+kem);

    // Create an encapsulator using the public key
    KEM.Encapsulator enc = null;
    try {
        enc = kem.newEncapsulator(kp.getPublic());
    } catch (
            InvalidKeyException e) {
        throw new RuntimeException(e);
    }
    System.out.println( "4 " + enc);

    // Encapsulate a secret key
    KEM.Encapsulated encap = enc.encapsulate();
    System.out.println( "5 " + encap);

    // Get the secret key, the ciphertext, and the parameters
    SecretKey sk = encap.key();
    byte[] ct = encap.encapsulation();
    byte[] params = encap.params();
    System.out.println( "6 " + sk);
    System.out.println( "7 " + ct);

    // Create a decapsulator using the private key
    KEM.Decapsulator dec = null;
    try {
        dec = kem.newDecapsulator(kp.getPrivate());
    } catch (InvalidKeyException e) {
        throw new RuntimeException(e);
    }
    System.out.println( "9 "+dec);

    // Decapsulate the secret key from the ciphertext
    SecretKey sk2 = null;
    try {
        sk2 = dec.decapsulate(ct);
    } catch (
            DecapsulateException e) {
        throw new RuntimeException(e);
    }
    System.out.println( "10 "+sk2);

// Check that the secret keys are equal
    assert sk.equals(sk2);



    //-----------

    // Generate an XDH (X25519) key pair
    KeyPairGenerator kpg1 = null;
    try {
        kpg1 = KeyPairGenerator.getInstance("XDH");
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
    kpg.initialize(255);
    System.out.println( "1 " +kpg1);
    KeyPair kp1 = kpg.generateKeyPair();
    System.out.println( "2 " + kp1);
    // Create a KEM instance for DHKEM
    KEM kem1 = null;
    try {
        kem1 = KEM.getInstance("DHKEM");
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
    System.out.println( "3 " + kem1);

    // Create an encapsulator using the public key
    KEM.Encapsulator enc1 = null;
    try {
        enc1 = kem.newEncapsulator(kp.getPublic());
    } catch (InvalidKeyException e) {
        throw new RuntimeException(e);
    }
    System.out.println( "4 "+ enc1);

    // Encapsulate a secret key
    KEM.Encapsulated encap1 = enc.encapsulate();
    System.out.println( "5 " + encap1);

    // Get the secret key, the ciphertext, and the parameters
    SecretKey sk1 = encap.key();
    byte[] ct1 = encap.encapsulation();
    byte[] params1 = encap.params();
    System.out.println( "6 " + sk1);
    System.out.println( "7 " + ct1);

    // Create a decapsulator using the private key
    KEM.Decapsulator dec1 = null;
    try {
        dec1 = kem.newDecapsulator(kp.getPrivate());
    } catch (InvalidKeyException e) {
        throw new RuntimeException(e);
    }
    System.out.println( "8 "+dec1);

    // Decapsulate the secret key from the ciphertext
    SecretKey sk21 = null;
    try {
        sk21 = dec.decapsulate(ct);
    } catch (DecapsulateException e) {
        throw new RuntimeException(e);
    }
    System.out.println( "9 "+ sk21);

// Check that the secret keys are equal
    assert sk.equals(sk21);
}

}

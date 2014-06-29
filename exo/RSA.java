import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
	public static void main(String[] args) throws IOException {
		SecureRandom random = new SecureRandom();
		BigInteger p, q, n, phi, e, d, mes, chi, dechi;
		
		p = BigInteger.probablePrime(64, random);
		q = BigInteger.probablePrime(64, random);
		n = p.multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		do {
			e = BigInteger.probablePrime(32, random);
		}while(e.gcd(phi).intValue()!=1);
		d = e.modInverse(phi);
		
		BufferedReader message1 =new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Veuiller saisir le texte à chiffrer: ");
		String plaintext = message1.readLine();
		byte[] plaintext_byte = plaintext.getBytes();
		mes = new BigInteger(plaintext_byte);
		chi = mes.modPow(e, n);
		dechi = chi.modPow(d, n);
		byte[] dechi_byte = dechi.toByteArray();
		String dechi_string = new String(dechi_byte);
		
		System.out.println("Paramètres");
		
		System.out.println("p= "+p);
		System.out.println("q= "+q);
		System.out.println("n= "+n);
		System.out.println("phi= "+phi);
		System.out.println("d= "+d);
		System.out.println("Message clair en caractère= "+plaintext);
		System.out.println("Message clair en grand entier= "+mes);
		System.out.println("Message chiffré en grand entier= "+chi);
		System.out.println("Message chiffré en caractère= "+new String(chi.toByteArray()));
		System.out.println("Message déchiffré en grand entier= "+dechi);
		System.out.println("Message déchiffré en caractère= "+dechi_string);
	}

}
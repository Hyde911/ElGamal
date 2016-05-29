/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elgamal.services;

import bigint.BigInt;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author marr
 */
public class ElGamalAlgorithm {

    public ElGamalAlgorithm() {

    }

    public byte[] encrypt(byte[] in, Map<String, BigInt> keys) throws Exception {
        if (!checkKeys(keys)){
            throw new IncompleteKeysException();
        }
//        generateKeys(keys);
        List<BigInt> message = chopMessage(in);
        List<BigInt> cryptogram = new LinkedList<>();

        BigInt a;
        BigInt b;
        for (int j = 0; j < message.size(); j++) {
            Random ran = new Random();
            int k = ran.nextInt(700);
            while (k < 600) {
                k = ran.nextInt(700);
            }
            BigInt kk = new BigInt(k);
            a = keys.get("g").modPow(kk, keys.get("p"));
            cryptogram.add(a);
            b = keys.get("y").modPow(kk, keys.get("p")).multiply(message.get(j));
            b = b.mod(keys.get("p"));
            cryptogram.add(b);
        }

        List<Byte> crypt = new LinkedList<>();
        for (BigInt word : cryptogram) {
            int fill = 0;
            int len = word.toByteArray().length;
            for (int i = 0; i < len; i++) {
                crypt.add(word.toByteArray()[i]);
                fill++;
            }
            for (int i = fill; i < 64; i++) {
                crypt.add((byte) 0x00);
            }
        }
        byte[] data = new byte[crypt.size()];
        for (int i = 0; i < crypt.size(); i++) {
            if (i % 30 == 0) {
            }
            data[i] = crypt.get(i);
        }
        cryptogram.clear();

        return data;
    }

    public byte[] decrypt(byte[] crypt, Map<String, BigInt> keys) throws Exception {
        if (!checkKeys(keys)) {
            throw new IncompleteKeysException();
        }
        List<BigInt> cryptogram = chopCryptogram(crypt);
        List<byte[]> decrypted = new ArrayList<>();
        BigInt one = new BigInt(1);
        for (int i = 0; i < cryptogram.size();) {
            BigInt a = cryptogram.get(i);
            BigInt b = cryptogram.get(i + 1);
            BigInt m1 = a.modPow(keys.get("p").subtract(one).subtract(keys.get("x")), keys.get("p"));
            BigInt m2 = (m1.multiply(b)).mod(keys.get("p"));
            decrypted.add(m2.toByteArray());
            i += 2;
        }

        byte[] message = mergeMessage(decrypted);
        try {
            return Base64.getDecoder().decode(message);
        } catch (Exception ex) {
            return message;
        }

    }

    private BigInteger generatePnumber(int bits) {
        Random r = new Random();
        return BigInteger.probablePrime(bits, r);
    }

    private BigInteger generatePrivateKey(BigInteger p) {
        Random ran = new Random();
        int bits = 0;
        while (bits < 200) {
            bits = ran.nextInt(510);
        }
        BigInteger x = new BigInteger(bits, 0, ran);

        while (x.compareTo(p.subtract(BigInteger.valueOf(5))) > 0
                || x.compareTo(BigInteger.valueOf(0)) < 0) {
            x = new BigInteger(bits, 0, ran);
        }
        return x;
    }

    public Map<String, BigInt> generateKeys() {
        Map<String, BigInt> keys = new HashMap<>();
        keys.clear();
        BigInteger p = generatePnumber(512);
        BigInt pI = new BigInt(p.toString());
        keys.put("p", pI);
        BigInteger x = generatePrivateKey(p);
        BigInt xI = new BigInt(x.toString());
        keys.put("x", xI);
        BigInteger g = BigInteger.valueOf(700);
        BigInt gI = new BigInt(g.toString());
        keys.put("g", gI);
        BigInteger y = g.modPow(x, p);
        BigInt yI = new BigInt(y.toString());
        keys.put("y", yI);
        return keys;
    }

    private List<BigInt> chopMessage(byte[] rawData) {
        List<BigInt> message = new LinkedList<>();
        byte[] baseData = Base64.getEncoder().encode(rawData);
        int len = baseData.length;
        for (int i = 0; i < len; i += 64) {
            if (i + 64 > len) {
                BigInt tmp = new BigInt(Arrays.copyOfRange(baseData, i, len));
                message.add(tmp);
                break;
            }
            BigInt tmp = new BigInt(Arrays.copyOfRange(baseData, i, i + 64));
            message.add(tmp);
        }
        return message;
    }

    private List<BigInt> chopCryptogram(byte[] cryptoData) {
        for (int i = 0; i < cryptoData.length; i++) {
            if (i % 30 == 0) {
            }
        }
        List<BigInt> cryptogram = new LinkedList<>();
        int cryptLength = cryptoData.length;
        for (int i = 0; i < cryptLength; i += 64) {
            cryptogram.add(new BigInt(Arrays.copyOfRange(cryptoData, i, i + 64)));
        }
        return cryptogram;
    }

    private byte[] mergeMessage(List<byte[]> message) {
        int it = 0;
        for (byte[] b : message) {
            it += b.length;
        }
        byte[] decrypted = new byte[it];
        it = 0;
        for (int i = 0; i < message.size(); i++) {
            for (int j = 0; j < message.get(i).length; j++) {
                decrypted[it++] = message.get(i)[j];
            }
        }
        return decrypted;
    }

    private boolean checkKeys(Map<String, BigInt> keys) {
        if (keys == null) {
            return false;
        } else if (!keys.containsKey("p") || keys.get("p") == null) {
            return false;
        } else if (!keys.containsKey("x") || keys.get("x") == null) {
            return false;
        } else if (!keys.containsKey("g") || keys.get("g") == null) {
            return false;
        } else if (!keys.containsKey("y") || keys.get("y") == null) {
            return false;
        }
        return true;
    }

    public class IncompleteKeysException extends Exception {

        public IncompleteKeysException() {
            super("Keys map is incomplete");
        }
    }
}

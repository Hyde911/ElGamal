/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elgamal.gui;

import bigint.BigInt;
import elgamal.services.ElGamalAlgorithm;
import elgamal.services.FilesService;
import java.io.File;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author marr
 */
public class GUIHelper {

    private final ElGamalAlgorithm elGamal;
    private volatile File openFile;
    private volatile File cipherFile;

    public GUIHelper() {
        this.elGamal = new ElGamalAlgorithm();
    }

    public byte[] encryptText(String message, Map<String, BigInt> keys) throws Exception {
        return elGamal.encrypt(message.getBytes(), keys);
    }

    public byte[] decryptText(String cipher, Map<String, BigInt> keys) throws Exception {
        return elGamal.decrypt(Base64.getDecoder().decode(cipher.getBytes()), keys);
    }

    public Path openFile(JFrame parent) throws NoFileException {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            openFile = fc.getSelectedFile();
            return openFile.toPath();
        } else {
            throw new NoFileException();
        }
    }

    public Path openCipher(JFrame parent) throws NoFileException {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            cipherFile = fc.getSelectedFile();
            return cipherFile.toPath();
        } else {
            throw new NoFileException();
        }
    }

    public void encryptFile(JFrame parent, Map<String, BigInt> keys) throws Exception {
        byte[] ciph = elGamal.encrypt(FilesService.ReadFile(openFile), keys);
        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            FilesService.WriteFile(fc.getSelectedFile(), ciph);
        } else {
            throw new NoFileException();
        }
    }

    public void decryptCipher(JFrame parent, Map<String, BigInt> keys) throws Exception {
        byte[] decr = elGamal.decrypt(FilesService.ReadFile(cipherFile), keys);
        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            FilesService.WriteFile(fc.getSelectedFile(), decr);
        } else {
            throw new NoFileException();
        }

    }

    public Map<String, BigInt> newKeys(int bitLentgh) {
        return elGamal.generateKeys(bitLentgh);
    }

    public String splitHexString(String hex) {
        StringBuilder sb = new StringBuilder("");
        sb.append(hex.charAt(0));
        sb.append(hex.charAt(1));
        for (int i = 2; i < hex.length(); i++) {
            if (i % 2 == 0) {
                sb.append(':');
            }
            sb.append(hex.charAt(i));
        }
        return sb.toString();
    }

    public String mergeHexString(String hex) {
        String[] split = hex.split(":");
        String ret =String.join("", split);
        return ret;
    }

    public class NoFileException extends Exception {

    }
}

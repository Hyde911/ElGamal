/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elgamal.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author marr
 */
public class FilesService {

    public static byte[] ReadFile(File file) throws FileNotFoundException, IOException {
        byte[] data = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(data);
        fis.close();
        return data;
    }

    public static void WriteFile(File file, byte[] data) throws IOException {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
    }

}

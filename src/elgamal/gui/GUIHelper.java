/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elgamal.gui;

import bigint.BigInt;
import java.util.Map;

/**
 *
 * @author marr
 */
public class GUIHelper {
    
    public static String splitHexString(String hex){
        StringBuilder sb = new StringBuilder("");
        sb.append(hex.charAt(0));
        sb.append(hex.charAt(1));
        for (int i = 2; i < hex.length(); i++){
            if (i % 2 == 0){
                sb.append(':');
            }
            sb.append(hex.charAt(i));
        }
        return sb.toString();
    }
    
    public static String mergeHexString(String hex){
        String [] split = hex.split(":");
        return String.join("", split);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elgamal.gui;

import bigint.BigInt;
import elgamal.services.ElGamalAlgorithm;
import elgamal.services.ElGamalAlgorithm.IncompleteKeysException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author marr
 */
public class MainWindow extends javax.swing.JFrame {

    private final ElGamalAlgorithm elGamal;
    private final Map<String, BigInt> keys;
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        keys = new HashMap();
        elGamal = new ElGamalAlgorithm();
        initLookAndFeel();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonReadFile = new javax.swing.JButton();
        jLabelLoadedFile = new javax.swing.JLabel();
        jButtonEncryptFile = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButtonLoadCipher = new javax.swing.JButton();
        jLabelLoadedCipher = new javax.swing.JLabel();
        jButtonSaveCipher = new javax.swing.JButton();
        jButtonDecipher = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextMessage = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButtonEncryptMessage = new javax.swing.JButton();
        jScrollPaneKeys = new javax.swing.JScrollPane();
        jTextPKey = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jButtonDecryptMessage = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPaneKeys1 = new javax.swing.JScrollPane();
        jTextYKey = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPaneKeys3 = new javax.swing.JScrollPane();
        jTextGKey = new javax.swing.JTextArea();
        jScrollPaneKeys2 = new javax.swing.JScrollPane();
        jTextXKey = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextCipherBase64 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextCipherRaw = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jButtonNewKeys = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ElGamal");

        jButtonReadFile.setText("Wczytaj plik");
        jButtonReadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReadFileActionPerformed(evt);
            }
        });

        jLabelLoadedFile.setText("Brak pliku");

        jButtonEncryptFile.setText("Zaszyfruj Plik");

        jLabel1.setText("Wygenerowane Klucze");

        jButtonLoadCipher.setText("Wczytaj szyfrogram");
        jButtonLoadCipher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadCipherActionPerformed(evt);
            }
        });

        jLabelLoadedCipher.setText("Brak Pliku");

        jButtonSaveCipher.setText("Zapisz Szyfrogram");

        jButtonDecipher.setText("Odszyfruj Szyfrogram");

        jTextMessage.setColumns(20);
        jTextMessage.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextMessage.setLineWrap(true);
        jTextMessage.setRows(5);
        jScrollPane1.setViewportView(jTextMessage);

        jLabel2.setText("Wiadomość");

        jButtonEncryptMessage.setText("Zaszyfruj wiadomość");
        jButtonEncryptMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEncryptMessageActionPerformed(evt);
            }
        });

        jTextPKey.setColumns(20);
        jTextPKey.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextPKey.setLineWrap(true);
        jTextPKey.setRows(3);
        jScrollPaneKeys.setViewportView(jTextPKey);

        jLabel3.setText("Szyfrogram (base64)");

        jButtonDecryptMessage.setText("Odszyfruj");
        jButtonDecryptMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDecryptMessageActionPerformed(evt);
            }
        });

        jLabel4.setText("Klucz publiczny p");

        jLabel5.setText("Klucz prywatny x");

        jTextYKey.setColumns(20);
        jTextYKey.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextYKey.setLineWrap(true);
        jTextYKey.setRows(3);
        jScrollPaneKeys1.setViewportView(jTextYKey);

        jLabel6.setText("Klucz publiczny g");

        jTextGKey.setColumns(20);
        jTextGKey.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextGKey.setLineWrap(true);
        jTextGKey.setRows(1);
        jScrollPaneKeys3.setViewportView(jTextGKey);

        jTextXKey.setColumns(20);
        jTextXKey.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextXKey.setLineWrap(true);
        jTextXKey.setRows(3);
        jScrollPaneKeys2.setViewportView(jTextXKey);

        jLabel7.setText("Klucz publiczny y");

        jTextCipherBase64.setColumns(20);
        jTextCipherBase64.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextCipherBase64.setLineWrap(true);
        jTextCipherBase64.setRows(5);
        jScrollPane3.setViewportView(jTextCipherBase64);

        jTextCipherRaw.setColumns(20);
        jTextCipherRaw.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextCipherRaw.setLineWrap(true);
        jTextCipherRaw.setRows(5);
        jScrollPane4.setViewportView(jTextCipherRaw);

        jLabel8.setText("Szyfrogram (ory.)");
        jLabel8.setToolTipText("");

        jButtonNewKeys.setText("Wygeneruj nowe klucze");
        jButtonNewKeys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewKeysActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonReadFile, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLoadedFile, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPaneKeys1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                    .addComponent(jScrollPaneKeys3)
                    .addComponent(jScrollPaneKeys2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonEncryptFile, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(jButtonNewKeys, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDecipher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSaveCipher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonLoadCipher)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLoadedCipher, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPaneKeys))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonEncryptMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDecryptMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonReadFile)
                            .addComponent(jLabelLoadedFile, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonEncryptFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonNewKeys, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPaneKeys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addComponent(jScrollPane1))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonEncryptMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPaneKeys3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPaneKeys1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jButtonSaveCipher, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonLoadCipher)
                                    .addComponent(jLabelLoadedCipher, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonDecipher, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(jButtonDecryptMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPaneKeys2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonReadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReadFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonReadFileActionPerformed

    private void jButtonLoadCipherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadCipherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonLoadCipherActionPerformed

    private void jButtonEncryptMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEncryptMessageActionPerformed
        byte [] message = jTextMessage.getText().getBytes();
        byte [] cipher = elGamal.encrypt(message, keys);
        jTextCipherRaw.setText(new String (cipher));
        jTextCipherBase64.setText(new String(Base64.getEncoder().encode(cipher)));
        writeKeys();
    }//GEN-LAST:event_jButtonEncryptMessageActionPerformed

    private void jButtonDecryptMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDecryptMessageActionPerformed

        try {
            readKeys();
            byte [] message = elGamal.decrypt(Base64.getDecoder().decode(jTextCipherBase64.getText().getBytes()), keys);
            jTextMessage.setText(new String (message));
        } catch (IncompleteKeysException ex) {
            JOptionPane.showMessageDialog(this, "Lista kluczy jest niekompletna", "Błąd kluczy", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (Exception ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
//        jTextMessage.setText(new String(message));
    }//GEN-LAST:event_jButtonDecryptMessageActionPerformed

    private void jButtonNewKeysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewKeysActionPerformed
        elGamal.generateKeys(keys);
        writeKeys();
    }//GEN-LAST:event_jButtonNewKeysActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDecipher;
    private javax.swing.JButton jButtonDecryptMessage;
    private javax.swing.JButton jButtonEncryptFile;
    private javax.swing.JButton jButtonEncryptMessage;
    private javax.swing.JButton jButtonLoadCipher;
    private javax.swing.JButton jButtonNewKeys;
    private javax.swing.JButton jButtonReadFile;
    private javax.swing.JButton jButtonSaveCipher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelLoadedCipher;
    private javax.swing.JLabel jLabelLoadedFile;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPaneKeys;
    private javax.swing.JScrollPane jScrollPaneKeys1;
    private javax.swing.JScrollPane jScrollPaneKeys2;
    private javax.swing.JScrollPane jScrollPaneKeys3;
    private javax.swing.JTextArea jTextCipherBase64;
    private javax.swing.JTextArea jTextCipherRaw;
    private javax.swing.JTextArea jTextGKey;
    private javax.swing.JTextArea jTextMessage;
    private javax.swing.JTextArea jTextPKey;
    private javax.swing.JTextArea jTextXKey;
    private javax.swing.JTextArea jTextYKey;
    // End of variables declaration//GEN-END:variables

    private void writeKeys() {
        jTextPKey.setText((keys.get("p").toString()));
        jTextXKey.setText((keys.get("x").toString()));
        jTextGKey.setText((keys.get("g").toString()));
        jTextYKey.setText((keys.get("y").toString()));
    }

    private void readKeys() {
        keys.clear();
        keys.put("p", new BigInt(jTextPKey.getText()));
        keys.put("x", new BigInt(jTextXKey.getText()));
        keys.put("g", new BigInt(jTextGKey.getText()));
        keys.put("y", new BigInt(jTextYKey.getText()));
    }

    private static void initLookAndFeel() {
        String lookAndFeel = null;
        lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException e) {
        } catch (Exception e) {
            //TODO :)
        }
    }
}

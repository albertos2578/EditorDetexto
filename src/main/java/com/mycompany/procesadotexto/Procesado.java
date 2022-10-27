/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.procesadotexto;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author alber
 */
public class Procesado extends javax.swing.JFrame implements Runnable {
    String horas,minutos,segundos;
    Thread hilo;
    JFileChooser selecciona= new JFileChooser();
     private File archivo;

   String FechaYHora;
    String UltimoEdit;
    int tamaano= 14;
  
    BufferedReader entrada;
    FileOutputStream salida;
     private AcercaDe dialogo = new AcercaDe(this,true);
     private String fuentes[];
     private DefaultListModel dlm;
     
    

	
private void DatosAdicionales(){
    BasicFileAttributes attrs = null;
     try {
     attrs = Files.readAttributes(archivo.toPath(), BasicFileAttributes.class);
     } catch (IOException ex) {
    Logger.getLogger(Procesado.class.getName()).log(Level.SEVERE, null, ex);
     }
     FileTime time = attrs.creationTime();
    String pattern = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String formatted = simpleDateFormat.format( new Date( time.toMillis() ) );
    FechaYHora=formatted;

    UltimoEdit=simpleDateFormat.format(archivo.lastModified());
  
        }
    
                
    /**
     * Creates new form Procesado
     */
    public Procesado() {
        dlm= new DefaultListModel();
        fuentes = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        initComponents();
        lista.setModel(dlm);
        cargarComponentes();
         bfecha.setText(fecha());
        hilo=new Thread(this);    
        hilo.start();
        setVisible(true);
        
        
        doc=jTextPane1.getStyledDocument();
        estilo= jTextPane1.addStyle("miEstilo", null);
        
    }
    private void cargarComponentes(){
        for(int i= 10; i<=30; i++){
             
            combo.addItem(i+"");
        }
        for(String fuente:fuentes){
            dlm.addElement(fuente);
        }
    }
     public void hora(){
        Calendar calendario = new GregorianCalendar();
        Date horaactual= new Date();
        calendario.setTime(horaactual);
        horas=calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);
        minutos=calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundos= calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
    }
      public static String fecha(){
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(fecha);
    }
     
      public void run(){
        Thread current=Thread.currentThread();
        while(current==hilo){
            hora();
            horita.setText(horas+":"+minutos+":"+segundos);
           
        }
    }
    
    public String AbrirArchivos(File archivo){
        String documento="";
       
        try{
            entrada = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "utf-8"));

             String peso= String.valueOf(archivo.length());   
             ruta.setText(" "+ruta.getText()+" "+archivo.getAbsolutePath());
             tamano.setText(" "+tamano.getText()+" "+peso+ " Bytes");
          
            int ascci;
                while((ascci=entrada.read())!=-1){
                    
                char caracter= (char) ascci;
                documento+=caracter;
            }
                     DatosAdicionales();
         
        }catch(Exception e){
            
        } return documento;
    }
    public String guardar(File archivo,String documento){
        String mensaje=null;
        try{
            salida=new FileOutputStream(archivo+".txt");
            byte[] bytxt= documento.getBytes();
            salida.write(bytxt);
            mensaje= "archivo guardado";
            
        }catch(Exception e){
            
        }
                return mensaje;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        bfecha = new javax.swing.JLabel();
        horita = new javax.swing.JLabel();
        ruta = new javax.swing.JLabel();
        tamano = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel4 = new javax.swing.JPanel();
        abrir = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        AcercaDe = new javax.swing.JButton();
        colores = new javax.swing.JButton();
        combo = new javax.swing.JComboBox<>();
        camelcase = new javax.swing.JButton();
        cambiarletras = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Editor de texto");
        setMinimumSize(new java.awt.Dimension(744, 407));
        setPreferredSize(new java.awt.Dimension(744, 407));

        jPanel1.setLayout(new java.awt.BorderLayout(1, 0));

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

        bfecha.setText("dd/MM/YYYY");
        jPanel3.add(bfecha);

        horita.setText("00:00:00");
        jPanel3.add(horita);

        ruta.setText("Ruta Archivo");
        jPanel3.add(ruta);

        tamano.setText("Tamaño Archivo:");
        jPanel3.add(tamano);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jScrollPane2.setViewportView(jTextPane1);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel4.setMinimumSize(new java.awt.Dimension(340, 51));
        jPanel4.setPreferredSize(new java.awt.Dimension(340, 51));
        java.awt.GridBagLayout jPanel4Layout = new java.awt.GridBagLayout();
        jPanel4Layout.columnWidths = new int[] {6};
        jPanel4.setLayout(jPanel4Layout);

        abrir.setText("abrir");
        abrir.setMinimumSize(new java.awt.Dimension(100, 25));
        abrir.setPreferredSize(new java.awt.Dimension(100, 25));
        abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirActionPerformed(evt);
            }
        });
        jPanel4.add(abrir, new java.awt.GridBagConstraints());

        guardar.setText("guardar");
        guardar.setMinimumSize(new java.awt.Dimension(100, 25));
        guardar.setPreferredSize(new java.awt.Dimension(100, 25));
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel4.add(guardar, new java.awt.GridBagConstraints());

        AcercaDe.setText("Acerca de");
        AcercaDe.setMinimumSize(new java.awt.Dimension(100, 25));
        AcercaDe.setPreferredSize(new java.awt.Dimension(100, 25));
        AcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcercaDeActionPerformed(evt);
            }
        });
        jPanel4.add(AcercaDe, new java.awt.GridBagConstraints());

        colores.setIcon(new javax.swing.ImageIcon("C:\\Users\\alber\\Downloads\\color_colors_11217.png")); // NOI18N
        colores.setMaximumSize(new java.awt.Dimension(65, 25));
        colores.setMinimumSize(new java.awt.Dimension(65, 25));
        colores.setPreferredSize(new java.awt.Dimension(65, 25));
        colores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coloresActionPerformed(evt);
            }
        });
        jPanel4.add(colores, new java.awt.GridBagConstraints());

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        combo.setPreferredSize(new java.awt.Dimension(50, 25));
        combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboItemStateChanged(evt);
            }
        });
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });
        jPanel4.add(combo, new java.awt.GridBagConstraints());

        camelcase.setIcon(new javax.swing.ImageIcon("C:\\Users\\alber\\Downloads\\468camel_100360.png")); // NOI18N
        camelcase.setText("Camelcase");
        camelcase.setMaximumSize(new java.awt.Dimension(131, 25));
        camelcase.setMinimumSize(new java.awt.Dimension(131, 25));
        camelcase.setPreferredSize(new java.awt.Dimension(131, 25));
        camelcase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                camelcaseActionPerformed(evt);
            }
        });
        jPanel4.add(camelcase, new java.awt.GridBagConstraints());

        cambiarletras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODO MAYUSCULA", "todo minuscula" }));
        cambiarletras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarletrasActionPerformed(evt);
            }
        });
        jPanel4.add(cambiarletras, new java.awt.GridBagConstraints());

        jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        lista.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lista);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.LINE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
       if(selecciona.showDialog(null, "guardar")==JFileChooser.APPROVE_OPTION){
              archivo=selecciona.getSelectedFile();
                if(archivo.getName().endsWith("txt")){
                  String documento= jTextPane1.getText();
                  String mensaje=guardar(archivo, documento);
                  if(mensaje!=null){
                      JOptionPane.showMessageDialog(null, mensaje);
                  }else{
                      JOptionPane.showMessageDialog(null, "archivo incompatible");
                  }
                  }else{
                      JOptionPane.showMessageDialog(null, "Guardar documento en .txt");
                          }
                  }
                      
          
       
    }//GEN-LAST:event_guardarActionPerformed

    private void abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirActionPerformed
      if(selecciona.showDialog(null, "Abrir")==JFileChooser.APPROVE_OPTION){
          archivo=selecciona.getSelectedFile();
          if(archivo.canRead()){
              if(archivo.getName().endsWith("txt")){
                  String documento=AbrirArchivos(archivo);
                  jTextPane1.setText(documento);
              }else{
                  JOptionPane.showMessageDialog(null, "archivo no compatible");
              
              }
          }
      }
    }//GEN-LAST:event_abrirActionPerformed

    private void AcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcercaDeActionPerformed
         dialogo.setup(FechaYHora,UltimoEdit);
        dialogo.setVisible(true);
    }//GEN-LAST:event_AcercaDeActionPerformed

    private void coloresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coloresActionPerformed
        try{
            StyleConstants.setForeground(estilo, JColorChooser.showDialog(this,"seleccione un color", Color.yellow));
            doc.setCharacterAttributes(jTextPane1.getSelectionStart(), jTextPane1.getSelectionEnd() - jTextPane1.getSelectionStart(), jTextPane1.getStyle("miEstilo"),true);
        
        }catch (Exception ex){
            
        }
    }//GEN-LAST:event_coloresActionPerformed

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboActionPerformed

    private void comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboItemStateChanged
            Font f = jTextPane1.getFont();
            jTextPane1.setFont(new Font(f.getName(),Font.PLAIN,Integer.parseInt(String.valueOf(combo.getSelectedItem()))));
    }//GEN-LAST:event_comboItemStateChanged

    private void listaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaValueChanged
        Font f = jTextPane1.getFont();
        jTextPane1.setFont(new Font(String.valueOf(dlm.getElementAt(lista.getSelectedIndex())),Font.PLAIN,f.getSize()));
    }//GEN-LAST:event_listaValueChanged

    private void camelcaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_camelcaseActionPerformed
     String Camel = jTextPane1.getText();
     String finala= "";
     String inputstring = Camel;
     String result="";
      
        char first= inputstring.charAt(0);
        char firsttouppercase= Character.toUpperCase(first);
        result=result+firsttouppercase;
        for(int i=1; i<inputstring.length();i++){
            char current=inputstring.charAt(i);
            char previous=inputstring.charAt(i-1);
            if(previous==' '){
                char currenttouppercase=Character.toUpperCase(current);
                result=result+currenttouppercase;
            }else{
                char currenttolowercase = Character.toLowerCase(current);
                result=result+currenttolowercase;
            }
            
        }
     jTextPane1.setText(result);
     
    }//GEN-LAST:event_camelcaseActionPerformed

    private void cambiarletrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarletrasActionPerformed
      if (cambiarletras.getSelectedItem()=="TODO MAYUSCULA"){
           jTextPane1.setText(jTextPane1.getText().toUpperCase());
          
      }else if(cambiarletras.getSelectedItem()=="todo minuscula"){
           jTextPane1.setText(jTextPane1.getText().toLowerCase());
      }
    }//GEN-LAST:event_cambiarletrasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Procesado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Procesado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Procesado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Procesado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Procesado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AcercaDe;
    private javax.swing.JButton abrir;
    private javax.swing.JLabel bfecha;
    private javax.swing.JComboBox<String> cambiarletras;
    private javax.swing.JButton camelcase;
    private javax.swing.JButton colores;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel horita;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JList<String> lista;
    private javax.swing.JLabel ruta;
    private javax.swing.JLabel tamano;
    // End of variables declaration//GEN-END:variables

StyledDocument doc;
 Style estilo;
}


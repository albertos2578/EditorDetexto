/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.procesadotexto;

import java.io.File;

/**
 *
 * @author alber
 */
public class AcercaDe extends javax.swing.JDialog {
  private Procesado ventanaPadre;
 

  
    /**
     * Creates new form AcercaDe
     */
    public AcercaDe(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
      
     
        
        initComponents();
         ventanaPadre = (Procesado)parent;
        
    }
     public void setup(String fechaYHora,String UltimoEdit){
       System.out.println(fechaYHora);
       System.out.println(UltimoEdit);
            if (fechaYHora != null){
           jTextField1.setText("Fecha de la creacion del archivo: "+fechaYHora);
              jTextField2.setText("Fecha de la ultima modificacion del archivo: "+UltimoEdit);
            }else{
                   jTextField1.setText("Abre algun documento");
              jTextField2.setText("y veras datos adicionales");
            }
    
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
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos Adicionales");
        setMinimumSize(new java.awt.Dimension(830, 180));
        setPreferredSize(new java.awt.Dimension(830, 180));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.GridLayout());

        jTextField1.setText("jTextField1");
        jPanel1.add(jTextField1);

        jTextField2.setText("jTextField2");
        jPanel1.add(jTextField2);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 140));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}

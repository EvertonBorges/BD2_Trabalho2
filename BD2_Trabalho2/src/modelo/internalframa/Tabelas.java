package modelo.internalframa;

import java.awt.Point;
import java.util.List;
import javax.swing.JInternalFrame;
import modelo.lista.ListaTabelaModelo;
import principal.Principal;

public class Tabelas extends javax.swing.JInternalFrame {
    private final List<String> atributos;
    
    public Tabelas(String title, List<String> atributos) {
        this.atributos = atributos;
        initComponents();
        setTitle(title.toUpperCase());
        preencherLista();
        //verificarRelacionamento();
    }
    
    /*
    private void verificarRelacionamento() {
        ConsultarInformationSchema consulta = new ConsultarInformationSchema();
        for (JInternalFrame frame: Principal.getFramesInternas()) {
            try {
                relacionamentos = consulta.relacionamento(getTitle(), frame.getTitle());
            } catch (BDException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
    }
    */
    
    public List<String> getAtributos() {
        return atributos;
    }
    
    private void preencherLista(){
        ListaTabelaModelo modelo = new ListaTabelaModelo(atributos);
        listaAtributos.setModel(modelo);
    }
    
    private Point posicaoNorte(){
        return new Point(getLocation().x + getWidth()/2, getLocation().y);
    }
    
    private Point posicaoLeste(){
        return new Point(getLocation().x + getWidth(), getLocation().y + getHeight()/2);
    }
    
    private Point posicaoSul(){
        return new Point(getLocation().x + getWidth()/2, getLocation().y + getHeight());
    }
    
    private Point posicaoOeste(){
        return new Point(getLocation().x, getLocation().y + getHeight()/2);
    }
    
    public Point getCentro() {
        return new Point(getLocation().x + (getWidth()/2), getLocation().y + (getHeight()/2));
    }
    
    public Point posicao(int i){
        switch (i){
            case 0: return posicaoNorte();
            case 1: return posicaoLeste();
            case 2: return posicaoSul();
            case 3: return posicaoOeste();
            default: return null;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        for (JInternalFrame frame: Principal.getFramesInternas()) {
            if (frame.getTitle().equals(this.getTitle())) {
                Principal.getFramesInternas().remove(frame);
                break;
            }
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

        jScrollPane2 = new javax.swing.JScrollPane();
        listaAtributos = new javax.swing.JList();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setClosable(true);
        setResizable(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        listaAtributos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        listaAtributos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(listaAtributos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList listaAtributos;
    // End of variables declaration//GEN-END:variables
}

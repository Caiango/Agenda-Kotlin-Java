package ui;

import validações.contato;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//interface java
public class ContactForm extends JFrame {
    private JPanel PainelPrincipal;
    private JTextField textFieldNome;
    private JTextField textFieldTelefone;
    private JButton salvarButton;
    private JButton cancelarButton;

    //variavel criada para acessar os metodos da classe contato em validações
    private contato mContato;
    //tamanho da janela
    public ContactForm(){
        setContentPane(PainelPrincipal);
        setSize(600,350);
        setVisible(true);

     //posição da janela
        Dimension dim = getToolkit().getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setListeners();
        //tenho de dar um valor para mContato para não dar erro.(incializei ela)
        mContato = new contato();
    }
    // função de ações dos botões
     private void setListeners(){
        cancelarButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainForm();
                dispose();
            }
        });
        salvarButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Esse try catch pega a Exception que criei em validação
                try{
                    //pego o texto que digitei dentro do textfield com o getText
                    String nome = textFieldNome.getText();
                    String fone = textFieldTelefone.getText();

                    mContato.salvar(nome, fone);

                    new MainForm();
                    dispose();
                } catch(Exception excp){
                    //joga uma nova janela com o erro mostrando
                    JOptionPane.showMessageDialog(new JFrame(), excp.getMessage());
                }
            }
        });

     }

}

//VER OS COMENTÁRIOS DE CONTACTFORM.JAVA :D
package ui;

import Entidades.*;
import validações.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;


public class MainForm extends JFrame {
    private JPanel PainelPrincipal;
    private JButton buttonNovoContato;
    private JButton buttonRemover;
    private JTable tableContatos;
    private JLabel ContadorLabel;
    //variavel criada para acessar os metodos da classe contato em validações
    private contato mContato;
    //declarar variaveis que vou utilizar dentro de toda a classe
    private String mNome = "";
    private String mFone = "";

    public MainForm() {
        setContentPane(PainelPrincipal);
        setSize(600, 350);
        setVisible(true);

        Dimension dim = getToolkit().getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setListeners();
        //tenho de dar um valor para mContato para não dar erro.(incializei ela)
        mContato = new contato();
        carregarContatos();
    }

    //função para mostrar listar os contatos na tela principal
    private void carregarContatos() {
        //variavel criada para ser chamada pelo MainForm e listar os contatos
        List<ContactEntity> listarCont = mContato.getlist();
        //criando variável para colocar o nome das colunas
        String[] nomecolunas = {"Nome", "Telefone"};
        //criando tabela
        DefaultTableModel model = new DefaultTableModel(new Object[0][0], nomecolunas);
        //preenchendo com os dados de ContactEntity
        for (ContactEntity i : listarCont) {
            Object[] o = new Object[2];

            o[0] = i.getNome();
            o[1] = i.getFone();
            model.addRow(o);
        }
        tableContatos.clearSelection();
        tableContatos.setModel(model);
        //colocandoo contador na label
        ContadorLabel.setText(mContato.getContadorList());
    }

    private void setListeners() {
        buttonNovoContato.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContactForm();
                dispose();
            }
        });
        //implementaçao para selecionar a linha e coluna qd for preciso deletar
        tableContatos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {

                    if (tableContatos.getSelectedRow() != -1) {
                        mNome = tableContatos.getValueAt(tableContatos.getSelectedRow(), 0).toString();
                        mFone = tableContatos.getValueAt(tableContatos.getSelectedRow(), 1).toString();
                    }
                }
            }
        });

        buttonRemover.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Esse try catch pega a Exception que criei em validação
                try {
                    mContato.deletar(mNome, mFone);
                    carregarContatos();
                    mFone = "";
                    mNome = "";
                } catch (Exception excp){
                        //joga uma nova janela com o erro mostrando
                        JOptionPane.showMessageDialog(new JFrame(), excp.getMessage());
                    }
                }
            });
        }

    }

package controller.Cadastro;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import business.ClienteDAO;
import model.*;
import model.Categoria.Acessorio;
import model.Cliente.Cliente;
import services.*;
import util.*;

public class LocacaoController extends JFrame implements ActionListener {

    JLabel nm_cliente_l = new JLabel("Nome Cliente: ");
    JComboBox<Cliente> nm_cliente_f;

    JLabel nr_cnh_l = new JLabel("Nº CNH: ");
    JTextField nr_cnh_f = new JTextField(8);

    JLabel nr_idade_l = new JLabel("Idade");
    JTextField nr_idade_f = new JTextField(2);

  public LocacaoController(){

    super("Nova Locação de Motocicleta");
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    nm_cliente_f = new JComboBox<Cliente>();
        
    nr_cnh_l.setBounds(20,30,140,20);
    this.add(nr_cnh_l);    
    nr_cnh_f.setBounds(160,30,180,20);
    this.add(nr_cnh_f);

    nm_cliente_l.setBounds(20,60,140,20);
    this.add(nm_cliente_l);  
    nm_cliente_f.setBounds(160,60,180,20);
    this.add(nm_cliente_f);

    //Layout JFrame
    setLayout(null);
    this.setSize(360, 200);
    setAction();

  }

  private void setAction(){
    nr_cnh_f.addKeyListener(new KeyAdapter(){public void keyReleased(KeyEvent e1) {
      JTextField textField1 = (JTextField) e1.getSource();
      String text1 = textField1.getText().toUpperCase();
      nm_cliente_f.removeAllItems();
      nm_cliente_f.addItem(new Cliente(0,"Selecione"));
      ArrayList<Cliente> clientes = getClientes(text1);
      for (Cliente cliente : clientes) {
        nm_cliente_f.addItem(cliente);
      }
    }}); 
    
    nm_cliente_f.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
          JComboBox<Cliente> comboBox = (JComboBox<Cliente>) event.getSource();
          System.out.println(comboBox.getSelectedItem());
          Cliente cliente = (Cliente) comboBox.getSelectedItem();
          nr_cnh_f.setText(cliente.NR_CNH);
          nr_cnh_f.setEditable(false);
          nr_idade_f.setText(calIdade(cliente.DT_NASCIMENTO));
      }
    });
  }


  //Parei aqui
  private String calIdade(String DT_NASCIMENTO) {
    String idade = util.calIdade(DT_NASCIMENTO);
    return idade;
  }

  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    
  }

  private ArrayList<Cliente> getClientes(String NR_CNH){
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    ClienteService clienteService = new ClienteService();
    clientes = clienteService.getClientes(NR_CNH);
    return clientes;
  }
  
  
}
package controller.Cadastro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.Message;
import util.Util;

/* HtmlDemo.java needs no other files. */
public class ClienteController extends JPanel implements ActionListener {
  
  JLabel nm_cliente_l = new JLabel("Nome Cliente: ");
  JTextField nm_cliente_f = new JTextField(20);

  JLabel dt_nascimento_l = new JLabel("Data Nascimento: ");
  JTextField dt_nascimento_f = new JTextField(8);

  JLabel nr_cnh_l = new JLabel("Nº CNH: ");
  JTextField nr_cnh_f = new JTextField(10);

  JButton saveCliente = new JButton("Salvar");

  public ClienteController() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setAction();
    
    saveCliente.setMnemonic(KeyEvent.VK_C);
    saveCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
    //saveCliente.addActionListener(this);

        
    JPanel clientePanel = new JPanel();
    clientePanel.setLayout(new BoxLayout(clientePanel, BoxLayout.PAGE_AXIS));
    clientePanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,30));
    clientePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
        .createTitledBorder("Cadastrar novo Cliente"), BorderFactory
        .createEmptyBorder(10, 10, 10, 10)));
    clientePanel.add(nm_cliente_l);    
    clientePanel.add(nm_cliente_f);
    clientePanel.add(nr_cnh_l);  
    clientePanel.add(nr_cnh_f);  
    clientePanel.add(dt_nascimento_l); 
    //dt_nascimento_f.addActionListener(this);
    clientePanel.add(dt_nascimento_f);  
    clientePanel.add(saveCliente);

    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    add(clientePanel);
  }

  private void setAction()
  {
    nm_cliente_f.addKeyListener(new KeyAdapter(){public void keyReleased(KeyEvent e1) {
      JTextField textField1 = (JTextField) e1.getSource();
      String text1 = textField1.getText().toUpperCase();
      nm_cliente_f.setText(text1);
    }});  
    
    dt_nascimento_f.addKeyListener(new KeyAdapter(){public void keyReleased(KeyEvent e2) {
      JTextField textField2 = (JTextField) e2.getSource();
      String text2 = textField2.getText();
      dt_nascimento_f.setText(Util.dataFormat(text2));
    }});
    
    saveCliente.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e) {

    model.Cliente.Cliente cliente = new model.Cliente.Cliente();
    business.ClienteDAO clientedao = new business.ClienteDAO();
    String message = "Cliente salvo com sucesso!";
    Message resultado;

    cliente.DT_NASCIMENTO = dt_nascimento_f.getText();
    cliente.NM_CLIENTE = nm_cliente_f.getText();
    cliente.NR_CNH = nr_cnh_f.getText();
    resultado = clientedao.adicionaClientes(cliente);
    
    if(resultado.status){
      JOptionPane.showMessageDialog(null, message);
    }else{
      if(resultado.message.contains("[SQLITE_CONSTRAINT_UNIQUE]")){
        JOptionPane.showMessageDialog(null, "Erro ao salvar cliente \nO cliente já existe na base!");
      }else{
        JOptionPane.showMessageDialog(null, "Erro ao salvar cliente \n" + resultado.message);
      }
    }
    setVisible(false);
  }   
}
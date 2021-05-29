package controller.Edicao;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.Message;
import model.Cliente.Cliente;
import services.ClienteService;
import util.Util;

/* HtmlDemo.java needs no other files. */
public class ClienteController extends JFrame implements ActionListener {
  
  JLabel nm_cliente_l = new JLabel("Nome Cliente: ");
  JTextField nm_cliente_f = new JTextField(20);

  JLabel dt_nascimento_l = new JLabel("Data Nascimento: ");
  JTextField dt_nascimento_f = new JTextField(8);

  JLabel nr_cnh_l = new JLabel("Nº CNH: ");
  JTextField nr_cnh_f = new JTextField(10);

  JButton saveCliente = new JButton("Salvar");

  public int CD_CLIENTE;
  private Cliente cliente;

  public ClienteController(int CD_CLIENTE) {
    super("Cadastro de Cliente");
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLayout(null);
    this.CD_CLIENTE = CD_CLIENTE;

    cliente = getCliente(this.CD_CLIENTE);

    setAction();
    
    
    nm_cliente_l.setBounds(20,30,140,20);
    this.add(nm_cliente_l);    
    nm_cliente_f.setText(cliente.NM_CLIENTE);
    nm_cliente_f.setBounds(160,30,180,20);
    this.add(nm_cliente_f);

    nr_cnh_l.setBounds(20,60,140,20);
    this.add(nr_cnh_l);  
    nr_cnh_f.setText(cliente.NR_CNH);
    nr_cnh_f.setBounds(160,60,180,20);
    this.add(nr_cnh_f);  

    dt_nascimento_l.setBounds(20,90,140,20);
    this.add(dt_nascimento_l); 
    dt_nascimento_f.setText(cliente.DT_NASCIMENTO);
    dt_nascimento_f.setBounds(160,90,180,20);
    this.add(dt_nascimento_f);  

    saveCliente.setBounds(20,120,120,20);
    this.add(saveCliente);

    this.setSize(360, 200);
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

    model.Cliente.Cliente cliente;
    ClienteService clienteService = new ClienteService();
    String message = "Cliente atualizado com sucesso!";
    Message resultado;

    cliente = new model.Cliente.Cliente(
                          this.CD_CLIENTE, 
                          nm_cliente_f.getText(),
                          dt_nascimento_f.getText(),
                          nr_cnh_f.getText(),
                          "S"
                          );
    
    resultado = clienteService.atualizarCliente(cliente);
    
    if(resultado.status){
      JOptionPane.showMessageDialog(null, message);
    }else{
      JOptionPane.showMessageDialog(null, "Erro ao salvar cliente \n" + resultado.message);
    }
    setVisible(false);
  } 
  
  private Cliente getCliente(int CD_CLIENTE){
    Cliente cliente = new Cliente();
    ClienteService clienteService = new ClienteService();
    try {
      cliente = clienteService.getCliente(CD_CLIENTE);
    } catch (Exception e) {
      cliente = null;
    }
    return cliente;
  }
}
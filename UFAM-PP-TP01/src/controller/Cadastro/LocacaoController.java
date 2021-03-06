package controller.Cadastro;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Message;
import model.Cliente.Cliente;
import model.Moto.Locacao;
import model.Moto.Moto;
import model.Moto.Opcional;
import model.Moto.Status;
import services.*;
import util.*;

public class LocacaoController extends JFrame implements ActionListener {

    JLabel nr_cnh_l = new JLabel("Nº CNH: ");
    JTextField nr_cnh_f = new JTextField();  
  
    JLabel nm_cliente_l = new JLabel("Nome Cliente: ");
    JComboBox<Cliente> nm_cliente_f;

    JLabel nr_idade_l = new JLabel("Idade: ");
    JTextField nr_idade_f = new JTextField();

    JLabel lc_retirada_l = new JLabel("Local Retirada: ");
    JTextField lc_retirada_f = new JTextField();
    
    JLabel dt_retirada_l = new JLabel("Data Retirada: ");
    JTextField dt_retirada_f = new JTextField();
    
    JLabel ds_modelo_l = new JLabel("Modelo: ");
    JComboBox<Moto> ds_modelo_f;

    JLabel ds_marca_l = new JLabel("Marca: ");
    JComboBox<String> ds_marca_f;

    JLabel ds_status_l = new JLabel("Status: ");
    JComboBox<Status> ds_status_f;

    JLabel lc_devolucao_l = new JLabel("Local Devolucao: ");
    JTextField lc_devolucao_f = new JTextField();
    
    JLabel dt_devolucao_l = new JLabel("Data Devolucao: ");
    JTextField dt_devolucao_f = new JTextField();

    JLabel ds_opcionais_l = new JLabel("Opcionais: ");
    JList<Opcional> ds_opcionais_f;  

    JLabel vl_custo_moto_l = new JLabel("Custo Moto: ");
    JTextField vl_custo_moto_f =  new JTextField();
    
    JLabel vl_custo_opcional_l = new JLabel("Custo Opcional: ");
    JTextField vl_custo_opcional_f =  new JTextField();
    
    JLabel vl_custo_total_l = new JLabel("Custo Total: ");
    JTextField vl_custo_total_f =  new JTextField();
    
    JLabel nr_diarias_l = new JLabel("Diárias: ");
    JTextField nr_diarias_f =  new JTextField();

    JButton saveLocacao;
    

  public LocacaoController(){

    super("Nova Locação de Motocicleta");
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    vl_custo_moto_f.setEditable(false);
    vl_custo_opcional_f.setEditable(false);
    vl_custo_total_f.setEditable(false);
    nr_diarias_f.setEditable(false);

    saveLocacao = new JButton("Salvar");

    nm_cliente_f = new JComboBox<Cliente>();
    
    ds_modelo_f = new JComboBox<Moto>();
    ds_marca_f = new JComboBox<String>();
        
    ds_status_f = new JComboBox<Status>();
    for (Status status : getLocStatus()) {
      ds_status_f.addItem(status);
    }

    ArrayList<Opcional> opcionais = listaOpcionais();
    DefaultListModel<Opcional> defaultop = new DefaultListModel<Opcional>();
    defaultop.addAll(opcionais);
    ds_opcionais_f = new JList<Opcional>(defaultop);
    
    //Informações Cliente
    JLabel cliente_l = new JLabel("-- Informações do Cliente --");   
    cliente_l.setBounds(250,0,200,20);
    this.add(cliente_l);

    nr_cnh_l.setBounds(20,30,120,20);
    this.add(nr_cnh_l);    
    nr_cnh_f.setBounds(160,30,180,20);
    this.add(nr_cnh_f);

    nm_cliente_l.setBounds(360,30,120,20);
    this.add(nm_cliente_l);  
    nm_cliente_f.setBounds(500,30,180,20);
    this.add(nm_cliente_f);

    nr_idade_l.setBounds(20,60,120,20);
    this.add(nr_idade_l);
    nr_idade_f.setBounds(160,60,40,20);
    nr_idade_f.setEditable(false);
    this.add(nr_idade_f);

    JSeparator s1 = new JSeparator();
    s1.setOrientation(SwingConstants.HORIZONTAL);
    s1.setBounds(20,90,680,20);
    this.add(s1);

    //Informações Data e Local da Locação
    JLabel locacao_l = new JLabel("-- Informações da Locacao --");   
    locacao_l.setBounds(250,100,200,20);
    this.add(locacao_l);

    lc_retirada_l.setBounds(20,120,120,20);
    this.add(lc_retirada_l);
    lc_retirada_f.setBounds(160,120,80,20);
    this.add(lc_retirada_f);

    lc_devolucao_l.setBounds(360,120,130,20);
    this.add(lc_devolucao_l);
    lc_devolucao_f.setBounds(500,120,80,20);
    this.add(lc_devolucao_f);

    dt_retirada_l.setBounds(20,150,120,20);
    this.add(dt_retirada_l);
    dt_retirada_f.setBounds(160,150,120,20);
    this.add(dt_retirada_f);

    dt_devolucao_l.setBounds(360,150,130,20);
    this.add(dt_devolucao_l);
    dt_devolucao_f.setBounds(500,150,120,20);
    this.add(dt_devolucao_f);

    JSeparator s2 = new JSeparator();
    s2.setOrientation(SwingConstants.HORIZONTAL);
    s2.setBounds(20,180,680,20);
    this.add(s2);

    //Informações Motocicleta
    
    JLabel moto_l = new JLabel("-- Informações da Moto --");   
    moto_l.setBounds(250,190,200,20);
    this.add(moto_l);

    ds_marca_l.setBounds(20,210,120,20);
    this.add(ds_marca_l);
    ds_marca_f.setBounds(160,210,180,20);
    ds_marca_f.setEditable(false);
    this.add(ds_marca_f);

    ds_modelo_l.setBounds(20,240,120,20);
    this.add(ds_modelo_l);
    ds_modelo_f.setBounds(160,240,180,20);
    ds_modelo_f.setEditable(false);
    this.add(ds_modelo_f);

    ds_status_l.setBounds(360,210,120,20);
    this.add(ds_status_l);
    ds_status_f.setBounds(500,210,180,20);
    ds_status_f.setEditable(false);
    this.add(ds_status_f);

    ds_opcionais_l.setBounds(360,240,120,20);
    this.add(ds_opcionais_l);
    ds_opcionais_f.setBounds(500,240,180,60);
    this.add(ds_opcionais_f);

    JSeparator s3 = new JSeparator();
    s3.setOrientation(SwingConstants.HORIZONTAL);
    s3.setBounds(20,320,680,20);
    this.add(s3);

    JLabel custos_l = new JLabel("-- Custos Gerais --");   
    custos_l.setBounds(250,330,200,20);
    this.add(custos_l);

    vl_custo_moto_l.setBounds(20,350,120,20);
    this.add(vl_custo_moto_l);
    vl_custo_moto_f.setText("0.00");
    vl_custo_moto_f.setBounds(20,380,120,20);

    this.add(vl_custo_moto_f);

    JLabel opvezes = new JLabel("*");
    opvezes.setBounds(145,380,120,20);
    this.add(opvezes);
    
    nr_diarias_l.setBounds(160,350,120,20);
    this.add(nr_diarias_l);
    nr_diarias_f.setBounds(160,380,120,20);
    nr_diarias_f.setText("0");
    this.add(nr_diarias_f);

    JLabel opmais = new JLabel("+");
    opmais.setBounds(285,380,120,20);
    this.add(opmais);
    
    vl_custo_opcional_l.setBounds(300,350,120,20);
    this.add(vl_custo_opcional_l);
    vl_custo_opcional_f.setBounds(300,380,120,20);
    vl_custo_opcional_f.setText("0.00");
    this.add(vl_custo_opcional_f);

    JLabel opigual = new JLabel("=");
    opigual.setBounds(425,380,120,20);
    this.add(opigual);

    vl_custo_total_l.setBounds(440,350,120,20);
    this.add(vl_custo_total_l);
    vl_custo_total_f.setBounds(440,380,120,20);
    this.add(vl_custo_total_f);  

    saveLocacao.setBounds(580,380,120,20);
    this.add(saveLocacao);
    
    //Layout JFrame
    setLayout(null);
    this.setSize(720, 450);
    setAction();

  }

  private void setAction(){
    nr_cnh_f.addKeyListener(new KeyAdapter(){public void keyReleased(KeyEvent e1) {
      if(nr_cnh_f.getText().length() > 4){
        try {
          nm_cliente_f.removeAllItems();
          ArrayList<Cliente> clientes = getClientes(nr_cnh_f.getText());
          for (Cliente cliente : clientes) {
            nm_cliente_f.addItem(cliente);
          }
        } catch (Exception e) {
          nm_cliente_f.removeAllItems();
        }
      }
    }}); 
    
    nm_cliente_f.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
          Cliente cliente = (Cliente) nm_cliente_f.getSelectedItem();
          if(cliente.CD_CLIENTE != 0){
            nr_cnh_f.setText(cliente.NR_CNH);
            nr_cnh_f.setEditable(false);
            nr_idade_f.setText(calIdade(cliente.DT_NASCIMENTO));
          }else{
            nr_cnh_f.setEditable(true);
          }
      }
    });

    dt_retirada_f.addKeyListener(new KeyAdapter(){public void keyReleased(KeyEvent e) {
      JTextField textField = (JTextField) e.getSource();
      String text = textField.getText();
      dt_retirada_f.setText(Util.dataFormat(text));
      dt_devolucao_f.setText("");
    }});

    lc_retirada_f.addKeyListener(new KeyAdapter(){public void keyReleased(KeyEvent e) {
      JTextField textField = (JTextField) e.getSource();
      String text = textField.getText().toUpperCase();
      lc_retirada_f.setText(text);
    }}); 

    dt_devolucao_f.addKeyListener(new KeyAdapter(){public void keyReleased(KeyEvent e) {
      JTextField textField = (JTextField) e.getSource();
      String text = textField.getText();
      dt_devolucao_f.setText(Util.dataFormat(text));
      if(dt_devolucao_f.getText().length() == 10){
        nr_diarias_f.setText(String.format("%d",CalcDiarias()));
        sumTotal();
        ArrayList<String> marcas = getMarcas();
        ds_marca_f.removeAllItems();
        for (String marca : marcas) {
          ds_marca_f.addItem(marca);
        }  
      }
    }});

    lc_devolucao_f.addKeyListener(new KeyAdapter(){public void keyReleased(KeyEvent e) {
      JTextField textField = (JTextField) e.getSource();
      String text = textField.getText().toUpperCase();
      lc_devolucao_f.setText(text);
    }}); 

    ds_marca_f.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if(ds_marca_f.getSelectedItem().toString() != "Selecione"){
          ArrayList<Moto> motos = getModelMoto();
          ds_modelo_f.removeAllItems();
          for (Moto moto : motos) {
            ds_modelo_f.addItem(moto);
          }
        }        
      }
    });

    ds_modelo_f.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        Moto moto = (Moto) ds_modelo_f.getSelectedItem();
        vl_custo_moto_f.setText(String.format("%.2f",moto.VL_CUSTO));
        sumTotal();
      }
    });

    ds_opcionais_f.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent evt) {
        try {
          if (evt.getValueIsAdjusting()){
            ArrayList<Opcional> opcionais = ( ArrayList<Opcional>) ds_opcionais_f.getSelectedValuesList();
            if(opcionais.get(0).DS_OPCIONAL != ""){
              vl_custo_opcional_f.setText(String.format("%.2f",sumOpcionais(opcionais)));  
              sumTotal();
            }  
          }  
        } catch (Exception e) {
          vl_custo_opcional_f.setText("0.00");
          sumTotal();
        }
        
      }
    });  
    
    saveLocacao.addActionListener(this);
  }

  private String calIdade(String DT_NASCIMENTO) {
    String idade = String.format("%d",Util.calIdade(DT_NASCIMENTO));
    return idade;
  }

  private ArrayList<Cliente> getClientes(String NR_CNH){
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    ClienteService clienteService = new ClienteService();
    clientes = clienteService.getClientes(NR_CNH);
    return clientes;
  } 

  private ArrayList<Moto> getModelMoto(){
    String DT_INICIO = dt_retirada_f.getText();
    String DT_FIM = dt_devolucao_f.getText();
    String DS_MARCA = ds_marca_f.getSelectedItem().toString();
    ArrayList<Moto> modelos = new ArrayList<Moto>();
    LocacaoService locacaoService = new LocacaoService();
    modelos = locacaoService.getModelMoto(DT_INICIO, DT_FIM, DS_MARCA);
    return modelos;
  }

  private ArrayList<String> getMarcas(){
    String DT_INICIO = dt_retirada_f.getText();
    String DT_FIM = dt_devolucao_f.getText();
    ArrayList<String> marcas = new ArrayList<String>();
    LocacaoService locacaoService = new LocacaoService();
    marcas = locacaoService.getDSMoto(DT_INICIO, DT_FIM);
    return marcas;
  }

  private ArrayList<Status> getLocStatus(){
    LocacaoService locacaoService = new LocacaoService();
    return locacaoService.getLocStatus();
  }

  private ArrayList<Opcional> listaOpcionais(){
    ArrayList<Opcional> opcionais = new ArrayList<Opcional>();
    LocacaoService locacaoService = new LocacaoService();
    opcionais = locacaoService.listaOpcionais();
    return opcionais;
  }

  private long CalcDiarias(){
    String DT_INICIO = dt_retirada_f.getText();
    String DT_FIM = dt_devolucao_f.getText();
    return Util.diffDates(DT_INICIO, DT_FIM);
  }

  private float sumOpcionais(ArrayList<Opcional> op){
    float sum = 0;
    for (Opcional opcional : op) {
      sum = sum + opcional.VL_OPCIONAL;
    }
    return sum;
  }

  private void sumTotal(){
    float sum = 0;
    float vlm = Float.parseFloat(vl_custo_moto_f.getText().replaceAll(",", "."));
    float diar = Float.parseFloat(nr_diarias_f.getText().replaceAll(",", "."));
    float vlo = Float.parseFloat(vl_custo_opcional_f.getText().replaceAll(",", "."));

    sum = (vlm * diar) + vlo;
    vl_custo_total_f.setText(String.format("%.2f",sum));
  }

  
  public void actionPerformed(ActionEvent e) {
    LocacaoService locacaoService = new LocacaoService();
    Message resultado;
    Moto moto = (Moto) ds_modelo_f.getSelectedItem();
    Cliente cliente = (Cliente) nm_cliente_f.getSelectedItem();
    Status status = (Status) ds_status_f.getSelectedItem();
    ArrayList<Opcional> opcionals = new ArrayList<Opcional>();
    Float total = Float.parseFloat(vl_custo_total_f.getText().replaceAll(",", "."));

    for (Opcional opcional : ds_opcionais_f.getSelectedValuesList()) {
      opcionals.add(opcional);
    }

    Locacao locacao = new Locacao(
      0,
      moto,
      cliente,
      Util.dataFormatSQL(dt_retirada_f.getText()),
      lc_retirada_f.getText(),
      Util.dataFormatSQL(dt_devolucao_f.getText()),
      lc_devolucao_f.getText(),
      status,
      total,
      opcionals
    );

    resultado = locacaoService.adicionaLocacao(locacao);

    if(resultado.status){
      JOptionPane.showMessageDialog(null, "Locação criada com sucesso!");
      setVisible(false);
    }else{
        JOptionPane.showMessageDialog(null, "Erro ao salvar cliente \n" + resultado.message);
    }
  }
}
package controller.Edicao;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import model.*;
import model.Categoria.Acessorio;
import services.*;

/* HtmlDemo.java needs no other files. */
public class MotoController extends JFrame implements ActionListener {
  
  public JFrame framePai;
  public int CD_MOTO;
  private model.Moto.Moto motoobj;
  JLabel ds_categoria_moto_l = new JLabel("Categoria: ");
  ArrayList<model.Categoria.Moto> catmotos;
  JComboBox<model.Categoria.Moto> ds_categoria_moto_f;
  
  JLabel ds_marca_l = new JLabel("Marca: ");
  JTextField ds_marca_f = new JTextField(10);
  
  JLabel ds_modelo_l = new JLabel("Modelo: ");
  JTextField ds_modelo_f = new JTextField(10);

  JLabel nr_ano_l = new JLabel("Ano: ");
  JTextField nr_ano_f = new JTextField(6);

  JLabel tp_categoria_motor_l = new JLabel("Tipo Motor: ");
  ArrayList<model.Categoria.Motor> catmotor;
  JComboBox<model.Categoria.Motor> tp_categoria_motor_f;

  JLabel cp_tanque_l = new JLabel("Cap. Tanque: ");
  JTextField cp_tanque_f = new JTextField(3);

  JLabel av_consumo_l = new JLabel("Média Consumo: ");
  JTextField av_consumo_f = new JTextField(3);

  JLabel vl_custo_l = new JLabel("Valor Custo: ");
  JTextField vl_custo_f = new JTextField(4);

  JLabel ds_acessorio_moto_l = new JLabel("Acessorios: ");
  DefaultListModel<model.Categoria.Acessorio> catacessorios = getAcessorioCat();
  JList<model.Categoria.Acessorio> ds_acessorio_moto_f;
  int selectedAcessorios[];

  JButton saveMoto = new JButton("Salvar");

    public MotoController(int CD_MOTO){
      super("Edição de Motocicleta");
      setAction();

      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.setLayout(null);
      catmotos = new ArrayList<model.Categoria.Moto>(getMotoCat());
      catmotor = new ArrayList<model.Categoria.Motor>(getMotorCat());

      this.CD_MOTO = CD_MOTO;
      motoobj = getMoto(CD_MOTO);    

      ds_categoria_moto_f = new JComboBox<model.Categoria.Moto>();
      ds_categoria_moto_f.addItem(new model.Categoria.Moto(0,"Selecione"));
      for (model.Categoria.Moto moto : catmotos) {
        ds_categoria_moto_f.addItem(moto);
        if(moto.CD_CATEGORIA == motoobj.CATMOTO.CD_CATEGORIA){
          ds_categoria_moto_f.setSelectedItem(moto); 
        }
      }
      
      tp_categoria_motor_f = new JComboBox<model.Categoria.Motor>();
      tp_categoria_motor_f.addItem(new model.Categoria.Motor(0,"Selecione"));
      for (model.Categoria.Motor motor : catmotor) {
        tp_categoria_motor_f.addItem(motor);
        if(motor.CD_MOTOR == motoobj.TP_MOTOR.CD_MOTOR){
          tp_categoria_motor_f.setSelectedItem(motor);
        }
      }
      
      ds_acessorio_moto_f = new JList<model.Categoria.Acessorio>(catacessorios);
      selectedAcessorios = new int[motoobj.Acessorios.size()];

      
      int tam = 0;
      for (int i = 0; i <  ds_acessorio_moto_f.getModel().getSize(); i++) {
        for (Acessorio acessorio : motoobj.Acessorios) {
          if(ds_acessorio_moto_f.getModel().getElementAt(i).CD_ACESSORIO == acessorio.CD_ACESSORIO){
            selectedAcessorios[tam] = i;
            tam++;
          }
        }
      }  
      
      
      for (int secAcee : selectedAcessorios) {
        System.out.println(secAcee);  
      }
      ds_acessorio_moto_f.setSelectedIndices(selectedAcessorios);
      
      ds_categoria_moto_l.setBounds(20,30,120,20);
      this.add(ds_categoria_moto_l);    
      ds_categoria_moto_f.setBounds(100,30,120,20);
      this.add(ds_categoria_moto_f);
      ds_marca_l.setBounds(20,60,120,20);
      this.add(ds_marca_l);    
      ds_marca_f.setText(motoobj.DS_MARCA);
      ds_marca_f.setBounds(100,60,120,20);
      this.add(ds_marca_f);
      ds_modelo_l.setBounds(20,90,120,20);
      this.add(ds_modelo_l);  
      ds_modelo_f.setText(motoobj.DS_MODELO);  
      ds_modelo_f.setBounds(100,90,120,20);
      this.add(ds_modelo_f);
      nr_ano_l.setBounds(240,30,120,20);
      this.add(nr_ano_l);  
      nr_ano_f.setText(String.format("%d",motoobj.NR_ANO));    
      nr_ano_f.setBounds(340,30,120,20);
      this.add(nr_ano_f);
      tp_categoria_motor_l.setBounds(240,60,120,20);
      this.add(tp_categoria_motor_l);    
      tp_categoria_motor_f.setBounds(340,60,120,20);
      this.add(tp_categoria_motor_f);
      cp_tanque_l.setBounds(240,90,120,20);
      this.add(cp_tanque_l);    
      cp_tanque_f.setText(String.format("%.2f",motoobj.CP_TANQUE)); 
      cp_tanque_f.setBounds(340,90,120,20);
      this.add(cp_tanque_f);
      av_consumo_l.setBounds(480,30,130,20);
      this.add(av_consumo_l); 
      av_consumo_f.setText(String.format("%.2f",motoobj.AV_CONSUMO));    
      av_consumo_f.setBounds(620,30,120,20);
      this.add(av_consumo_f);
      vl_custo_l.setBounds(480,60,120,20);
      this.add(vl_custo_l); 
      vl_custo_f.setText(String.format("%.2f",motoobj.VL_CUSTO));   
      vl_custo_f.setBounds(620,60,120,20);
      this.add(vl_custo_f);
      ds_acessorio_moto_l.setBounds(480,90,120,20);
      this.add(ds_acessorio_moto_l);    
      ds_acessorio_moto_f.setBounds(620,90,120,60);
      this.add(ds_acessorio_moto_f);
      saveMoto.setBounds(20,120,120,20);
      this.add(saveMoto);
      this.setSize(760, 200);

    }

    private model.Moto.Moto getMoto(int codigo){
      model.Moto.Moto motos;
      MotoService motoService = new MotoService();
      motos = motoService.getMoto(codigo);
      return motos;
    }

    private ArrayList<model.Categoria.Moto> getMotoCat(){
        ArrayList<model.Categoria.Moto> catmotos = new ArrayList<model.Categoria.Moto>();
        MotoService catmotoserv = new MotoService();
        try {
          catmotos = catmotoserv.listarCatMotos();
          return catmotos;
        } catch (Exception e) {return null;}    
    } 
    
    private ArrayList<model.Categoria.Motor> getMotorCat(){
      ArrayList<model.Categoria.Motor> catmotor = new ArrayList<model.Categoria.Motor>();
      MotorService catmotorserv = new MotorService();
      try {
        catmotor = catmotorserv.listarMotor();
        return catmotor;
      } catch (Exception e) {return null;}    
    }

    private DefaultListModel<model.Categoria.Acessorio> getAcessorioCat(){
      DefaultListModel<model.Categoria.Acessorio> catacessorio = new DefaultListModel<model.Categoria.Acessorio>();
      AcessorioService catmotorserv = new AcessorioService();
      try {
        catacessorio.addAll(catmotorserv.listarAcessorios());
        return catacessorio;
      } catch (Exception e) {return null;}    
    }

    private void setAction(){
      saveMoto.addActionListener(this);
    }

  public void actionPerformed(ActionEvent e) {

    MotoService motoserv = new MotoService();
    String message = "Cliente atualizado com sucesso!";
    Message resultado;
    model.Categoria.Moto categoria_moto = new model.Categoria.Moto(0, "");
    model.Categoria.Motor categoria_motor = new model.Categoria.Motor(0, "");;
    ArrayList<Acessorio> Acessorios = new ArrayList<Acessorio>(); 
    
    for (model.Categoria.Moto moto : catmotos) {
      if(moto == ds_categoria_moto_f.getSelectedItem()){
        categoria_moto = moto;
      }
    }

    for (model.Categoria.Motor motor : catmotor) {
      if(motor == tp_categoria_motor_f.getSelectedItem()){
        categoria_motor = motor;
      }
    }

    for (model.Categoria.Acessorio acessorio : ds_acessorio_moto_f.getSelectedValuesList()) {
      Acessorios.add(acessorio);
    }

    model.Moto.Moto moto = new model.Moto.Moto(
      CD_MOTO,
      categoria_moto,  
      ds_marca_f.getText(),
      ds_modelo_f.getText(),
      Integer.parseInt(nr_ano_f.getText()),
      categoria_motor,
      Float.parseFloat(cp_tanque_f.getText()),
      Float.parseFloat(av_consumo_f.getText()),
      Float.parseFloat(vl_custo_f.getText()),
      Acessorios
    );

    System.out.println(moto);
    resultado = motoserv.atualizaMoto(moto);
    
    if(resultado.status){
      JOptionPane.showMessageDialog(null, message);
    }else{
        JOptionPane.showMessageDialog(null, "Erro ao salvar cliente \n" + resultado.message);
    }

    this.setVisible(false);
  }
}
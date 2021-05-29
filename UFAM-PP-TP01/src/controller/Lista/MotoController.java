package controller.Lista;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import model.Moto.Moto;
import services.MotoService;

public class MotoController extends JPanel {
  
  JLabel ds_categoria_moto_l = new JLabel("Categoria: ");
  ArrayList<model.Categoria.Moto> catmotos;
  JComboBox<model.Categoria.Moto> ds_categoria_moto_f;
  JPanel motoPanel = new JPanel();
  JTable motos;
  JComboBox<String> acao;
  JScrollPane sp;
  ArrayList<Moto> lstMotos;
  String[] columnNames = {  "Código", "Categoria", "Marca", "Modelo",
  "Ano", "Motor", "Cap. Tanque", "Média Consumo",
  "Custo", "Ação"};
  TableModel model;
  Object[][] data;
  
  public MotoController() {

    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    lstMotos = listarMotos();    
    data = new String[lstMotos.size()][columnNames.length];
    
    for(int i = 0; i < lstMotos.size(); i++){    
        data[i][0] =  String.format("%d",lstMotos.get(i).CD_MOTO);
        data[i][1] =  String.format("%s",lstMotos.get(i).CATMOTO.DS_CATEGORIA);
        data[i][2] =  String.format("%s",lstMotos.get(i).DS_MARCA);
        data[i][3] =  String.format("%s",lstMotos.get(i).DS_MODELO);
        data[i][4] =  String.format("%d",lstMotos.get(i).NR_ANO);
        data[i][5] =  String.format("%s",lstMotos.get(i).TP_MOTOR.DS_MOTOR);
        data[i][6] =  String.format("%.2f",lstMotos.get(i).CP_TANQUE);
        data[i][7] =  String.format("%.2f",lstMotos.get(i).AV_CONSUMO);
        data[i][8] =  String.format("%.2f",lstMotos.get(i).VL_CUSTO); 
        data[i][9] =  "Selecione";
    }
    model = new TableEditModel();
    motos = new JTable(data, columnNames);
    motos.setModel(model);
    
    TableColumn proficient = motos.getColumn("Ação");
    acao = new JComboBox<String>();
    acao.addItem("Selecione");
    acao.addItem("Editar");
    acao.addItem("Deletar");

    proficient.setCellEditor(new DefaultCellEditor(acao));

    motos.getModel().addTableModelListener(new TableModelListener(){
      public void tableChanged(TableModelEvent tev){
        int CD_MOTO = Integer.parseInt(motos.getModel().getValueAt(tev.getFirstRow(), 0).toString());
        String COMMAND = motos.getModel().getValueAt(tev.getFirstRow(), tev.getColumn()).toString();
        if(COMMAND == "Editar"){
          controller.Edicao.MotoController EditarMoto = new controller.Edicao.MotoController(CD_MOTO);
          EditarMoto.CD_MOTO = CD_MOTO;
          EditarMoto.setVisible(true);
        }
        else{
          if(COMMAND == "Deletar"){
            int result = JOptionPane.showConfirmDialog(null, "Certeza que deseja deletar?","Confirmação de remoção",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
              if(removerMoto(CD_MOTO)){
                JOptionPane.showMessageDialog(null, "Registro deletado!");
              }else{
                JOptionPane.showMessageDialog(null, "Registro falhou ao ser deletado!");
              }
            }
          }
        }
      }
    });
        
    sp = new JScrollPane(motos);

    motoPanel.setLayout(null);
    motoPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
        .createTitledBorder("Listagem de Motos"), BorderFactory
        .createEmptyBorder(10, 10, 10, 10)));

    sp.setBounds(20,60,840,300);
    motoPanel.add(sp);

    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    add(motoPanel);
  }

  private Boolean removerMoto(int CD_MOTO){
    Boolean motos = false;
    MotoService motoService = new MotoService();
    motos = motoService.removeMoto(CD_MOTO);
    return motos;
  }

  private ArrayList<Moto> listarMotos(){
    ArrayList<Moto> motos;
    MotoService motoService = new MotoService();
    motos = motoService.listarMotos();
    return motos;
  }

  /* Classe TableEditModel Referenciada em 
   * https://allaboutbasic.com/2010/12/27/jtable-java-how-to-make-jtable-cells-editable-or-not-editable-using-defaulttablemodel/
   */
  class TableEditModel extends DefaultTableModel{
    TableEditModel(){
      super(data,columnNames);
    }
  
    public boolean isCellEditable(int row,int cols){
      if(cols==9 ){return true;}
        return false;
    }
  }
}


package controller.Lista;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import model.Moto.Locacao;
import services.LocacaoService;
import util.Util;

public class LocacaoController extends JFrame {
  
  JLabel ds_categoria_moto_l = new JLabel("Categoria: ");
  ArrayList<model.Categoria.Moto> catmotos;
  JComboBox<model.Categoria.Moto> ds_categoria_moto_f;
  JTable locacoes;
  JComboBox<String> acao;
  JScrollPane sp;
  ArrayList<Locacao> lstLocacoes;
  String[] columnNames = {  "Código", "Modelo", "Cliente", "Retirada","Devolucao","Valor","Status", "Ação"};
  TableModel model;
  Object[][] data;
  
  public LocacaoController() {
    super("Locações Ativas");
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    
    lstLocacoes = listaLocacoes();    
    data = new String[lstLocacoes.size()][columnNames.length];
    
    for(int i = 0; i < lstLocacoes.size(); i++){    
        data[i][0] =  String.format("%d",lstLocacoes.get(i).CD_LOCACAO);
        data[i][1] =  String.format("%s",lstLocacoes.get(i).moto.DS_MODELO);
        data[i][2] =  String.format("%s",lstLocacoes.get(i).cliente.NM_CLIENTE);
        data[i][3] =  String.format("%s",Util.dataFormatSQLReverse(lstLocacoes.get(i).DT_RETIRADA));
        data[i][4] =  String.format("%s",Util.dataFormatSQLReverse(lstLocacoes.get(i).DT_DEVOLUCAO));
        data[i][5] =  String.format("%.2f",lstLocacoes.get(i).VL_TOTAL);
        data[i][6] =  String.format("%s",lstLocacoes.get(i).status.DS_LOCACAO);
        data[i][7] =  "Selecione";
    }

    model = new TableEditModel();
    locacoes = new JTable(data, columnNames);
    locacoes.setModel(model);
    
    TableColumn proficient = locacoes.getColumn("Ação");
    acao = new JComboBox<String>();
    acao.addItem("Selecione");
    acao.addItem("Editar");
    
    proficient.setCellEditor(new DefaultCellEditor(acao));

    locacoes.getModel().addTableModelListener(new TableModelListener(){
      public void tableChanged(TableModelEvent tev){
        int CD_LOCACAO = Integer.parseInt(locacoes.getModel().getValueAt(tev.getFirstRow(), 0).toString());
        String COMMAND = locacoes.getModel().getValueAt(tev.getFirstRow(), tev.getColumn()).toString();
        if(COMMAND == "Editar"){
          controller.Edicao.LocacaoController EditarLocacao = new controller.Edicao.LocacaoController(CD_LOCACAO);
          EditarLocacao.setVisible(true);
        }
      }
    });
        
    sp = new JScrollPane(locacoes);

    this.setLayout(null);
    
    sp.setBounds(20,20,610,220);
    this.setSize(650, 360);
    this.add(sp);
  }

  private ArrayList<Locacao> listaLocacoes(){
    ArrayList<Locacao> locacoes;
    LocacaoService locacaoService = new LocacaoService();
    locacoes = locacaoService.listaLocacoes();
    return locacoes;
  }

  /* Classe TableEditModel Referenciada em 
   * https://allaboutbasic.com/2010/12/27/jtable-java-how-to-make-jtable-cells-editable-or-not-editable-using-defaulttablemodel/
   */
  class TableEditModel extends DefaultTableModel{
    TableEditModel(){
      super(data,columnNames);
    }
  
    public boolean isCellEditable(int row,int cols){
      if(cols==7 ){return true;}
        return false;
    }
  }
}


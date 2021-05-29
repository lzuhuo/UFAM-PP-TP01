package controller.Lista;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import model.Cliente.Cliente;
import services.ClienteService;

public class ClienteController extends JFrame {
  
  JLabel ds_categoria_moto_l = new JLabel("Categoria: ");
  ArrayList<model.Categoria.Moto> catmotos;
  JComboBox<model.Categoria.Moto> ds_categoria_moto_f;
  JTable clientes;
  JComboBox<String> acao;
  JScrollPane sp;
  ArrayList<Cliente> lstClientes;
  String[] columnNames = {  "Código", "Nome", "Nº CNH", "Nascimento", "Ação"};
  TableModel model;
  Object[][] data;
  
  public ClienteController() {
    super("Listagem de Clientes");
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    
    lstClientes = listarClientes();    
    data = new String[lstClientes.size()][columnNames.length];
    
    for(int i = 0; i < lstClientes.size(); i++){    
        data[i][0] =  String.format("%d",lstClientes.get(i).CD_CLIENTE);
        data[i][1] =  String.format("%s",lstClientes.get(i).NM_CLIENTE);
        data[i][2] =  String.format("%s",lstClientes.get(i).NR_CNH);
        data[i][3] =  String.format("%s",lstClientes.get(i).DT_NASCIMENTO);
        data[i][4] =  "Selecione";
    }
    model = new TableEditModel();
    clientes = new JTable(data, columnNames);
    clientes.setModel(model);
    
    TableColumn proficient = clientes.getColumn("Ação");
    acao = new JComboBox<String>();
    acao.addItem("Selecione");
    acao.addItem("Editar");
    acao.addItem("Deletar");

    proficient.setCellEditor(new DefaultCellEditor(acao));

    clientes.getModel().addTableModelListener(new TableModelListener(){
      public void tableChanged(TableModelEvent tev){
        int CD_CLIENTE = Integer.parseInt(clientes.getModel().getValueAt(tev.getFirstRow(), 0).toString());
        String COMMAND = clientes.getModel().getValueAt(tev.getFirstRow(), tev.getColumn()).toString();
        if(COMMAND == "Editar"){
          controller.Edicao.ClienteController EditarCliente = new controller.Edicao.ClienteController(CD_CLIENTE);
          EditarCliente.CD_CLIENTE = CD_CLIENTE;
          EditarCliente.setVisible(true);
        }
        else{
          if(COMMAND == "Deletar"){
            int result = JOptionPane.showConfirmDialog(null, "Certeza que deseja deletar?","Confirmação de remoção",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
              if(removerCliente(CD_CLIENTE)){
                JOptionPane.showMessageDialog(null, "Registro deletado!");
              }else{
                JOptionPane.showMessageDialog(null, "Registro falhou ao ser deletado!");
              }
            }
          }
        }
      }
    });
        
    sp = new JScrollPane(clientes);

    this.setLayout(null);
    
    sp.setBounds(20,20,550,220);
    this.setSize(590, 300);
    this.add(sp);
  }

  private Boolean removerCliente(int CD_CLIENTE){
    Boolean cliente = false;
    ClienteService motoService = new ClienteService();
    cliente = motoService.removeCliente(CD_CLIENTE);
    return cliente;
  }

  private ArrayList<Cliente> listarClientes(){
    ArrayList<Cliente> clientes;
    ClienteService clienteService = new ClienteService();
    clientes = clienteService.listarClientes();
    return clientes;
  }

  /* Classe TableEditModel Referenciada em 
   * https://allaboutbasic.com/2010/12/27/jtable-java-how-to-make-jtable-cells-editable-or-not-editable-using-defaulttablemodel/
   */
  class TableEditModel extends DefaultTableModel{
    TableEditModel(){
      super(data,columnNames);
    }
  
    public boolean isCellEditable(int row,int cols){
      if(cols==4 ){return true;}
        return false;
    }
  }
}


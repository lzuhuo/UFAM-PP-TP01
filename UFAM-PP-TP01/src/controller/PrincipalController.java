package controller;
import java.awt.event.*;
import javax.swing.*;

public class PrincipalController extends JFrame {
  public PrincipalController() {
	  super("Locadora EmpinaMoto");
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  JMenuBar bar = new JMenuBar();
    
	  //Menu Arquivo
	  JMenu menu = new JMenu("Arquivo");
	  menu.setMnemonic('a');
	  bar.add(menu);

	  JMenuItem exit = new JMenuItem("Sair");
	  exit.setMnemonic('s');
	  exit.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			 System.out.println("Exit performed");
			 PrincipalController.this.dispose();
			 System.exit(0);
		  }
	  });
	  menu.add(exit);

	  //Menu Moto
	  menu = new JMenu("Moto");
	  menu.setMnemonic('o');
	  bar.add(menu);
    
	  //Adiconar Listener
	  EditListener l = new EditListener();
	  JMenuItem mi;
	  mi = menu.add(new JMenuItem("Cadastrar", 'm'));
	  mi.addActionListener(l);
	  mi.setName("moto_novo");
	  mi = menu.add(new JMenuItem("Listagem", 'l'));
	  mi.setName("moto_lista");
	  mi.addActionListener(l);
    
	  //Menu Cliente
	  menu = new JMenu("Cliente");
	  menu.setMnemonic('e');
	  bar.add(menu);
    
	  //Adiconar Listener
	  l = new EditListener();
	  mi = menu.add(new JMenuItem("Cadastrar", 'c'));
	  mi.addActionListener(l);
	  mi.setName("cliente_novo");
	  mi = menu.add(new JMenuItem("Listagem", 'm'));
	  mi.addActionListener(l);
	  mi.setName("cliente_lista");

	  //Menu Locação
	  menu = new JMenu("Locação");
	  menu.setMnemonic('e');
	  bar.add(menu);
    
	  //Adiconar Listener
	  l = new EditListener();
	  mi = menu.add(new JMenuItem("Cadastrar", 'c'));
	  mi.addActionListener(l);
	  mi.setName("locacao_novo");
	  mi = menu.add(new JMenuItem("Listagem", 'm'));
	  mi.addActionListener(l);
	  mi.setName("locacao_lista");
    
	  setJMenuBar(bar);
	  
	  pack();
	  setResizable(false);
	  setSize(900, 450);
	  setVisible(true);
  }

  private class EditListener implements ActionListener {
	  public void actionPerformed(ActionEvent e) {
		JMenuItem jm = (JMenuItem) e.getSource();
		String modulo = jm.getName();
		
		getContentPane().removeAll();	
		
		switch (modulo) {
			case "moto_novo":
				JFrame moto_novo = new controller.Cadastro.MotoController();
				moto_novo.setVisible(true);
				break;
			case "moto_lista":
				getContentPane().add(new controller.Lista.MotoController());
				break;
			case "cliente_novo":
				JFrame cliente_novo = new controller.Cadastro.ClienteController();
				cliente_novo.setVisible(true);
				break;
			case "cliente_lista":
				JFrame cliente_lista = new controller.Lista.ClienteController();
				cliente_lista.setVisible(true);
				break;
			case "locacao_novo":
				JFrame locacao_novo = new controller.Cadastro.LocacaoController();
				locacao_novo.setVisible(true);
				break;
			case "locacao_lista":
				JFrame locacao_lista = new controller.Lista.LocacaoController();
				locacao_lista.setVisible(true);
				break;
			default:
				break;
		}

		
		getContentPane().revalidate(); 
		getContentPane().repaint();		
      }
  }
}
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
    
	  //Menu Locação
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
				getContentPane().add(new controller.Cadastro.MotoController());
				break;
			case "moto_lista":
				getContentPane().add(new controller.Lista.MotoController());
				break;
			case "cliente_novo":
				getContentPane().add(new controller.Cadastro.ClienteController());
				break;
			default:
				break;
		}

		
		getContentPane().revalidate(); 
		getContentPane().repaint();		
      }
  }
}
package Products;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Text.Files;


public class Agent extends JFrame  {
	
	String[] fields = new String[]{"Product","Amount"};
	int index = 0;
	Files file = new Files();
	ManageProduct prod = new ManageProduct();
	
	
	Object[] row ;
	Object[] col ;
    

	Object[][] data = new Object[][]{{"laptop",5 },{"pc",6}}; // asta e demo sa vezi ca mere restul programului

	GridBagLayout layout = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	JTable table = new JTable();
	JButton sell = new JButton("Sell");
	JButton admin = new JButton("Admin");
	
	public void make() {
	
		file.read();
		file.createProducts();
		row = new Object[]{file.getNames()};
		col = new Object[]{file.getAmount()};
		/* asta nu mere nu stiu de ce, citeste obiectele din fisierul text dupa cum vezi in consola,le si imparte si
		 * transforma da nu le pune in tabel :-??.
		 */
		
	}

	public Agent(String str) {
	
		
		super("Agent GUI " + str);
		make();
		this.setLayout(layout);
		//
		DefaultTableModel model = (DefaultTableModel) table.getModel();

	    model.addRow(row);
	    model.addColumn(col);
	    
	    //
		table = new JTable(data,fields);
	    //table = new JTable();// asta adauga in tabel datele din text
	    //table.setModel(model);//
	    
		sell.addActionListener( e -> {
			
			int row = table.convertRowIndexToModel(table.getSelectedRow());
			int col = table.convertColumnIndexToModel(table.getSelectedColumn());
			String in = JOptionPane.showInputDialog(this,"Select amount to sell" );
			String temp = table.getValueAt(row, col).toString();
			int subb = Integer.parseInt(in);
			
			if(subb > Integer.parseInt(temp)) {
				JOptionPane.showMessageDialog(this, "Eror! not enough products","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			index = Integer.parseInt(temp);
			index = Integer.parseInt(temp) - subb;
			String s = table.getValueAt(row, col-1).toString();
			prod.amount = index;
			prod.name = s;
			prod.serialOut();
			if(index == 0) {
				
				
				file.write(s.getBytes(),"out.txt");
			}
			
			System.out.println(table.getValueAt(row, col));
			table.setValueAt(index, row, col);
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 0;
		c.gridy = 0;
		this.add(new Label("Products " +
		"                          Amount"),c);
    	c.weightx = 1;
	
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 0;
		c.gridy = 1;
		this.add(table,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.6;
		c.gridx = 4;
		c.gridy = 1;
		this.add(sell,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.6;
		c.gridx = 4;
		c.gridy = 2;
		
		admin.addActionListener(e -> {
		
			JFrame frame = new JFrame("Add Agent");
			
			JTextField text = new JTextField(15);
			JButton ok = new JButton("OK");
			/*
			 * aici mai vine chestia cu produsul se face la fel ca si cu Agentul
			 * 
			 */
			ok.addActionListener(k -> {
				
				new Agent("Hello Agent: " + text.getText());
				
			});
			frame.add(new JLabel("The name:"));
			frame.setLayout(new FlowLayout());
			
			frame.add(text);
			frame.add(ok);
			frame.setSize(250,250);
			frame.setLocation(new Point(252,175));
			frame.setVisible(true);
		});
		this.add(admin,c);
		this.setSize(400, 250);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}

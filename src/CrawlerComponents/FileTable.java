package CrawlerComponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DP_project.DB;
import Interfaces.Command;
import Interfaces.Visitor;

@SuppressWarnings("serial")
public class FileTable extends JMenuItem implements Command ,Visitor{

	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	JTable table;
	JFrame frameFileTable;
	Thread updateTable;
	DefaultTableModel model ;
	JScrollPane scrollPane;
	JLabel labelFile;
	JPanel jpFileList = new JPanel(new BorderLayout());
	ArrayList<String> filename=new ArrayList<String>();


	public FileTable(String name) {
		super(name);
		init();
	}


	@Override
	public void exec() {

		frameFileTable.setVisible(true);
		visit(DB.createDB());

	}



	public void init() {

		frameFileTable=new JFrame("File List");
		labelFile =new JLabel("Displaying a File Lisf ",JLabel.CENTER);



		model = new DefaultTableModel();

		model.addColumn(""); 
		model.addColumn(""); 



		table = new JTable(model);


		//Creating a JPanel and adding JLabel that contains the image

		jpFileList = new JPanel(new BorderLayout());

		jpFileList.add(table, BorderLayout.CENTER );

		//Adding JPanel to JScrollPane
		scrollPane = new JScrollPane(jpFileList);

		//Adding JLabel and JScrollPane to JFrame
		frameFileTable.add(labelFile,BorderLayout.NORTH);
		frameFileTable.add(scrollPane,BorderLayout.CENTER);


		frameFileTable.setSize(600, 300);
		frameFileTable.setLocation((dim.width-frameFileTable.getSize().width)/2, (dim.height-frameFileTable.getSize().height)/2);
		
	}


	@Override
	public void visit(DB db) {
		updateTable = new Thread() {
			public void run() {
				int i=0;
				while (true) {

					for(Map.Entry<String,JProgressBar> e:DB.createDB().getDB().entrySet()) {
						String fileName=e.getKey().substring(e.getKey().lastIndexOf("/")+1, e.getKey().length());
						if(!filename.contains(fileName)) {
							filename.add(fileName);
							model.addRow(new Object[]{fileName,"---- "+i+" ----"});	
							i++;
						}
					}
					//table = new JTable(model);
				
					try {
						Thread.sleep(500);
					} catch (Exception e) {

					}
				}
			}
		};updateTable.start();		
	}



}

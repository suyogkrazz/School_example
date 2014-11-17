package company;

import java.awt.BorderLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
public class postLogin extends JFrame {

	private JPanel contentPane;
	private JTable table_1;
	private JComboBox comboBoxallname ;
	private 	JComboBox comboBoxselect;
	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					postLogin  frame = new postLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection con= null;
	private JTextField textFieldeid;
	private JLabel lblNewLabel;
	private JTextField textFieldname;
	private JTextField textFieldsurname;
	private JTextField textFieldage;
	private JButton btndelete;
	private JTextField textFieldsearch;
	public void refreshTable(){
		try {
			String query="select Eid,name,surname,age from EmployeeInfo ";
			PreparedStatement pst= con.prepareStatement(query);
			ResultSet rs= pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fillcombobox(){
		try {
				comboBoxallname.removeAllItems();
			String query="select * from EmployeeInfo ";
			PreparedStatement pst= con.prepareStatement(query);
			ResultSet rs= pst.executeQuery();

			while(rs.next()){
				
				comboBoxallname.addItem(rs.getString("name"));
			}
		
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void dispose() {
		try{
			this.setVisible(false);
		}
		catch(Exception e){
		e.printStackTrace();	
		}
		
		
	}
	public postLogin() {
		con= sqlconnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 455);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnNew = new JMenu("New");
		mnFile.add(mnNew);
		
		JMenuItem mntmProject = new JMenuItem("project");
		mnNew.add(mntmProject);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		mnFile.add(mntmExit);
		
		JSeparator separator = new JSeparator();
		menuBar.add(separator);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnlogout = new JButton("Log out");
		btnlogout.setFont(new Font("Trajan Pro", Font.BOLD, 11));
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Login  log= new Login();
				log.set(true);
				contentPane.setVisible(false);
				dispose();
				
			}

		

		
		});
		btnlogout.setBounds(622, 0, 100, 36);
		contentPane.add(btnlogout);
		
		JButton btnLoadEmployeeTable = new JButton("Load Employee Table");
		btnLoadEmployeeTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLoadEmployeeTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="select Eid,name,surname,age from EmployeeInfo ";
					PreparedStatement pst= con.prepareStatement(query);
					ResultSet rs= pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
				
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();	
			}
		});
		btnLoadEmployeeTable.setBounds(272, 11, 172, 36);
		contentPane.add(btnLoadEmployeeTable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(206, 125, 532, 286);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int row =table_1.getSelectedRow();
					String EID =(table_1.getModel().getValueAt(row, 0)).toString();
					String query="select * from  EmployeeInfo where EID='"+EID+"'";
					PreparedStatement pst= con.prepareStatement(query);
					ResultSet rs= pst.executeQuery();
					while(rs.next()){
						textFieldeid.setText(rs.getString("EID"));
						textFieldname.setText(rs.getString("name"));
						textFieldsurname.setText(rs.getString("surname"));
						textFieldage.setText(rs.getString("age"));
					}
					pst.close();
					
						
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table_1);
		
		lblNewLabel = new JLabel("EID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 120, 60, 20);
		contentPane.add(lblNewLabel);
		
		textFieldeid = new JTextField();
		textFieldeid.setBounds(96, 123, 100, 20);
		contentPane.add(textFieldeid);
		textFieldeid.setColumns(10);
		
		JLabel lblname = new JLabel("Name:");
		lblname.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblname.setBounds(10, 164, 60, 17);
		contentPane.add(lblname);
		
		textFieldname = new JTextField();
		textFieldname.setBounds(96, 165, 100, 20);
		contentPane.add(textFieldname);
		textFieldname.setColumns(10);
		
		textFieldsurname = new JTextField();
		textFieldsurname.setBounds(96, 209, 100, 24);
		contentPane.add(textFieldsurname);
		textFieldsurname.setColumns(10);
		
		JLabel lblsurname = new JLabel("surname:");
		lblsurname.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblsurname.setBounds(10, 209, 79, 19);
		contentPane.add(lblsurname);
		
		JLabel lblage = new JLabel("Age:");
		lblage.setVerticalAlignment(SwingConstants.BOTTOM);
		lblage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblage.setBounds(10, 254, 60, 20);
		contentPane.add(lblage);
		
		textFieldage = new JTextField();
		textFieldage.setBounds(96, 256, 100, 20);
		contentPane.add(textFieldage);
		textFieldage.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="insert into  EmployeeInfo (EID,name,surname,age) values (?,?,?,?) ";
					PreparedStatement pst= con.prepareStatement(query);
					pst.setString(1, textFieldeid.getText());
					pst.setString(2, textFieldname.getText());
					pst.setString(3, textFieldsurname.getText());
					pst.setString(4, textFieldage.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null,"data saved!!");
					pst.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				
				refreshTable();	
				fillcombobox();
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSave.setBounds(0, 303, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="Update  EmployeeInfo set EID='"+textFieldeid.getText()+"' , name='"+textFieldname.getText()+"' , surname='"+textFieldsurname.getText()+"' , age='"+textFieldage.getText()+"' where EID='"+textFieldeid.getText()+"'";
					PreparedStatement pst= con.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null,"data updated!!");
					pst.close();
					
						
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			
				refreshTable();	
				fillcombobox();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBounds(96, 303, 100, 23);
		contentPane.add(btnUpdate);
		
		btndelete = new JButton("Delete");
		btndelete.setFont(new Font("Tahoma", Font.BOLD, 16));
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action= JOptionPane.showConfirmDialog(null,"do you want to delete the data?", "Delete", JOptionPane.YES_NO_CANCEL_OPTION);
				if(action==0){
				try {
					String query="delete from  EmployeeInfo where EID='"+textFieldeid.getText()+"'";
					PreparedStatement pst= con.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null,"data deleted!!");
					pst.close();
					
						
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				}
				
				refreshTable();	
				fillcombobox();
			}
		});
		btndelete.setBounds(0, 339, 89, 24);
		contentPane.add(btndelete);
		
		 comboBoxallname = new JComboBox();
		 comboBoxallname.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		try {
					String query="select * from  EmployeeInfo where name=?";
					PreparedStatement pst= con.prepareStatement(query);
					pst.setString(1,(String)comboBoxallname.getSelectedItem());
					ResultSet rs= pst.executeQuery();
					while(rs.next()){
						textFieldeid.setText(rs.getString("EID"));
						textFieldname.setText(rs.getString("name"));
						textFieldsurname.setText(rs.getString("surname"));
						textFieldage.setText(rs.getString("age"));
					}
					pst.close();
					
						
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		 	}
		 });
		comboBoxallname.setFont(new Font("Tahoma", Font.BOLD, 16));
		comboBoxallname.setBounds(25, 55, 139, 20);
		contentPane.add(comboBoxallname);
		
		JLabel lblNewLabel_1 = new JLabel("Select Emp:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(54, 30, 89, 31);
		contentPane.add(lblNewLabel_1);
		
		textFieldsearch = new JTextField();
		textFieldsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
						String selection =(String)comboBoxselect.getSelectedItem();
					String query="select  Eid,name,surname,age from  EmployeeInfo where "+selection+" like ?";
					PreparedStatement pst= con.prepareStatement(query);
					pst.setString(1,"%"+textFieldsearch.getText()+ "%");
					ResultSet rs= pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					
						
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		textFieldsearch.setBounds(521, 57, 148, 24);
		contentPane.add(textFieldsearch);
		textFieldsearch.setColumns(10);
		
		comboBoxselect = new JComboBox();
		comboBoxselect.setModel(new DefaultComboBoxModel(new String[] {"EID", "name", "surname", "age"}));
		comboBoxselect.setBounds(411, 57, 100, 18);
		contentPane.add(comboBoxselect);
		
		refreshTable();
		fillcombobox();
	}
}

package company;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class Login {
	Connection con=null;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	private JTextField textFieldusername;
	private JPasswordField passwordField;
	public Login() {
		
		con = sqlconnection.dbConnector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(163, 47, 86, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(163, 100, 88, 27);
		frame.getContentPane().add(lblNewLabel_1);
		
		textFieldusername = new JTextField();
		textFieldusername.setBounds(248, 48, 130, 30);
		frame.getContentPane().add(textFieldusername);
		textFieldusername.setColumns(10);
		
		JButton btnlogin = new JButton("Login");
		btnlogin.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER){
					try {
						String query="select * from EmployeeInfo where username=? and password=?";
						PreparedStatement state =  con.prepareStatement(query);
						state.setString(1, textFieldusername.getText());
						state.setString(2, passwordField.getText());
						ResultSet rs= state.executeQuery();
						int count=0;
						while(rs.next()){
							count=count+1;
							
						}
						if(count==1){
							JOptionPane.showMessageDialog(null,"Correct");
							frame.setVisible(false);
							postLogin  pl= new postLogin();
							pl.setVisible(true);
							
							
						}
						else if(count>1){
							JOptionPane.showMessageDialog(null, "Duplicate username and password");
						}
						else{
							JOptionPane.showMessageDialog(null, "incorrect combination!try again later!!");
						}
						rs.close();
						state.close();
					} 
					catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
						
					}
			    }
				
			}
		});
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="select * from EmployeeInfo where username=? and password=?";
					PreparedStatement state =  con.prepareStatement(query);
					state.setString(1, textFieldusername.getText());
					state.setString(2, passwordField.getText());
					ResultSet rs= state.executeQuery();
					int count=0;
					while(rs.next()){
						count=count+1;
						
					}
					if(count==1){
						JOptionPane.showMessageDialog(null,"Correct");
						frame.setVisible(false);
						postLogin  pl= new postLogin();
						pl.setVisible(true);
						
						
					}
					else if(count>1){
						JOptionPane.showMessageDialog(null, "Duplicate username and password");
					}
					else{
						JOptionPane.showMessageDialog(null, "incorrect combination!try again later!!");
					}
					rs.close();
					state.close();
				} 
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
					
				}
			}
		});
		btnlogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnlogin.setBounds(190, 173, 173, 40);
		frame.getContentPane().add(btnlogin);

		passwordField = new JPasswordField();
		passwordField.setBounds(248, 100, 127, 30);
		frame.getContentPane().add(passwordField);
		JLabel label = new JLabel("");
		label.setBackground(Color.BLACK);
		Image img =new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(10, 47, 149, 166);
		frame.getContentPane().add(label);
	}

	public void set(boolean b) {
		if(b==true){
			frame.setVisible(true);
		}
		
	}


	

	
}

package hms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import com.mysql.cj.xdevapi.Statement;

import java.awt.Font;
import java.awt.Window;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class Login_main {

	private JFrame frame;
	private JTextField user;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Login_main();
//				try {
//					//Login_main window = new Login_main();
//				//window.frame.setVisible(true);
//			//		window.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			}
		
		});
	}

	/**
	 * Create the application.
	 */
	public Login_main() {
		
		initialize();
		Connect();
		//Login_main window = new Login_main();
		//window.frame.setVisible(true);
	}
	Connection conn;
	PreparedStatement pst;
	public void Connect() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?zeroDateTimeBehavior=CONVERT_TO_NULL","root","xamp@root");
       }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HOSPITAL LOGIN");
		lblNewLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(144, 27, 196, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(30, 105, 65, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(30, 151, 65, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		user = new JTextField();
		user.setBounds(121, 102, 86, 20);
		frame.getContentPane().add(user);
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(121, 148, 86, 20);
		frame.getContentPane().add(pass);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			//private Object frame;

			public void actionPerformed(ActionEvent e) {
				String un=user.getText();
		        String p=pass.getText();
		        try{
		            
		        	java.sql.Statement st=conn.createStatement();
		        	String sql="Select * from user_login;";
		            ResultSet rs= ((java.sql.Statement) st).executeQuery(sql);
		            int flag=0;
		            while(rs.next()){
		                String username=rs.getString("username");
		                String password=rs.getString("password");
		           if(un.equals(username)&&p.equals(password)){
		        	   //((Window) this.frame).setVisible(false);
		        	   if(un.equals("reception"))
		        		   new Reception();	
		        	   else
		        		   new display_pmh();
		        	   frame.setVisible(false);
		        	   flag=1;
		        	   
		        	   break;
		        	}
		           }
		           if(flag==0)
		             {
		               JOptionPane.showMessageDialog(null,"wrong username and pass");
		              }
		            }
		        
		           catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null,ex);
		        }
		    }

			
			}
		);
		submit.setBounds(216, 211, 89, 23);
		frame.getContentPane().add(submit);
	}
}

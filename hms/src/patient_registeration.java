package hms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class patient_registeration extends JFrame implements ActionListener {
    private JLabel titleLabel, nameLabel, dobLabel, genderLabel, mobile1Label, mobile2Label;
    private JTextField nameField, dobField, mobile1Field, mobile2Field;
    private JComboBox genderComboBox;
    private JButton registerButton;

   Connection conn;
     PreparedStatement pst;
   // private ResultSet rs;

    public patient_registeration() {
    	JFrame frame;
    	frame=new JFrame();
        frame.setTitle("New Patient Registration");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(427, 331);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(30, 134, 81, 26);
        mobile1Label = new JLabel("Mobile Number 1:");
        mobile1Label.setBounds(30, 171, 106, 26);
        mobile2Label = new JLabel("Mobile Number 2:");
        mobile2Label.setBounds(30, 208, 106, 26);
        dobField = new JTextField();
        dobField.setBounds(124, 97, 140, 26);
        mobile1Field = new JTextField();
        mobile1Field.setBounds(134, 171, 120, 31);
        mobile2Field = new JTextField();
        mobile2Field.setBounds(149, 213, 91, 26);

        genderComboBox = new JComboBox(new String[]{"Male", "Female", "Other"});
        genderComboBox.setBounds(154, 134, 81, 26);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.add(dobField);
        //frame.getContentPane().add(titleLabel);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 63, 70, 24);
        inputPanel.add(nameLabel);
        dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(30, 102, 70, 17);
        inputPanel.add(dobLabel);
        inputPanel.add(genderLabel);
        inputPanel.add(genderComboBox);
        inputPanel.add(mobile1Label);
        //        frame.getContentPane().add(nameLabel);
        //        frame.getContentPane().add(dobLabel);
        //        frame.getContentPane().add(genderLabel);
        //        frame.getContentPane().add(mobile1Label);
        //        frame.getContentPane().add(mobile2Label);
                
                
                
                
                nameField = new JTextField();
                nameField.setBounds(126, 63, 137, 24);
                inputPanel.add(nameField);
        inputPanel.add(mobile1Field);
        inputPanel.add(mobile2Label);
        inputPanel.add(mobile2Field);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        
                titleLabel = new JLabel("New Patient Registration");
                titleLabel.setBounds(103, 11, 226, 37);
                inputPanel.add(titleLabel);
                titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
                titleLabel.setHorizontalAlignment(JLabel.CENTER);
                
                        registerButton = new JButton("Register");
                        registerButton.setBounds(20, 258, 116, 23);
                        inputPanel.add(registerButton);
                                
                                JButton Back = new JButton("Back");
                                
                                Back.setBounds(312, 258, 89, 23);
                                inputPanel.add(Back);
                        registerButton.addActionListener(this);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
        Back.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Reception();
        		
        		setVisible(false);
        	}
        });
        try {
            // Connect to XAMPP database
        	Class.forName("com.mysql.cj.jdbc.Driver");
           conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?zeroDateTimeBehavior=CONVERT_TO_NULL","root","xamp@root");
 
            //connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            registerPatient();
        } 
        //else if (e.getSource() == clearButton) {
            //clearFields();
        }
    

    private void registerPatient() {
        String name = nameField.getText();
        String dob = dobField.getText();
        String gender = (String) genderComboBox.getSelectedItem();
        if(gender.equals("Male"))
        	gender="M";
        else if(gender.equals("Female"))
        	gender="F";
        else if(gender.equals("Other"))
        	gender="O";
       
        
        String mobile1 = mobile1Field.getText();
        String mobile2 = mobile2Field.getText();
        
        int mob1=Integer.parseInt(mobile1);
        int mob2=Integer.parseInt(mobile2);
        
        try {
			pst=conn.prepareStatement("INSERT INTO patient_demo (name,Date_Of_Birth,Gender)values(?,?,?)");
			pst.setString(1, name);
			pst.setString(2, dob);
			pst.setString(3, gender);
			pst.executeUpdate();
			
			java.sql.Statement st=conn.createStatement();
        	String sql="SELECT * FROM patient_demo ORDER BY patient_id DESC";
        	ResultSet rs= ((java.sql.Statement) st).executeQuery(sql);
        	//int rs=((java.sql.Statement) st).executeQuery(sql);
        	//System.out.print(rs);
        	int pid = 0;
        	while(rs.next())
        	{
        		 pid=rs.getInt("patient_id");
        		 break;
        	}
        	
        	
        	pst=conn.prepareStatement("INSERT INTO mobile_numbers (Patient_id, Mobile_number)values(?,?)");
        	pst.setInt(1, pid);
        	pst.setInt(2, mob1);
        	pst.executeUpdate();
        	if(mobile2!="")	
        	{
        		pst=conn.prepareStatement("INSERT INTO mobile_numbers (Patient_id, Mobile_number)values(?,?)");
            	pst.setInt(1, pid);
            	pst.setInt(2, mob2);
            	pst.executeUpdate();
        	}
        	 JOptionPane.showMessageDialog(null, "New patient registered successfully");
			//String sqlstmt="Select"
			//pst=conn.prepareStatement("INSERT INTO mobile_numbers (Name, Date_of_Birth, Gender)values(?,?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }
    public static void main(String[] args) {
        new patient_registeration();
    }
    }
       // try {



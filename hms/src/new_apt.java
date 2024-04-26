package hms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class new_apt extends JFrame implements ActionListener {
    private JLabel patientIdLabel, doctorIdLabel, appDateLabel, appTimeLabel, paymentStatusLabel, deptIdLabel, transIdLabel;
    private JTextField patientIdField, appDateField, appTimeField, trans;
    private JButton submitButton;

     Connection conn;
     PreparedStatement preparedStatement;
     private JComboBox dept_id;
     private JComboBox dept_idcombo;
     private JComboBox dr_id;
     private JComboBox ps;
     private JComboBox ps_1;
     private JButton btnNewButton;

    public new_apt() {
        setTitle("New Patient Appointment");
        setSize(431, 318);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize labels and fields
        patientIdLabel = new JLabel("Patient ID:");
        patientIdLabel.setBounds(0, 0, 192, 34);
        doctorIdLabel = new JLabel("Doctor ID:");
        doctorIdLabel.setBounds(0, 34, 192, 34);
        appDateLabel = new JLabel("Appointment Date:");
        appDateLabel.setBounds(0, 68, 192, 34);
        appTimeLabel = new JLabel("Appointment Time:");
        appTimeLabel.setBounds(0, 102, 192, 34);
        paymentStatusLabel = new JLabel("Payment Status:");
        deptIdLabel = new JLabel("Department ID:");
        deptIdLabel.setBounds(192, 136, 192, 34);
        transIdLabel = new JLabel("Transaction ID:");
        transIdLabel.setBounds(192, 170, 192, 34);

        patientIdField = new JTextField();
        patientIdField.setBounds(192, 0, 192, 34);
        appDateField = new JTextField();
        appDateField.setBounds(192, 68, 192, 34);
        appTimeField = new JTextField();
        appTimeField.setBounds(192, 102, 192, 34);
        trans = new JTextField();
        trans.setBounds(0, 170, 192, 34);

        // Initialize form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.add(patientIdLabel);
        formPanel.add(patientIdField);
        formPanel.add(doctorIdLabel);
        formPanel.add(appDateLabel);
        formPanel.add(appDateField);
        formPanel.add(appTimeLabel);
        formPanel.add(appTimeField);
        formPanel.add(deptIdLabel);
        formPanel.add(trans);
        formPanel.add(transIdLabel);

        // Add components to the frame
        getContentPane().add(formPanel, BorderLayout.CENTER);
        
        //JComboBox dr_id = new JComboBox();
        
        dr_id= new JComboBox(new String[]{"1", "2", "3","4","5","6","7","8","9","10"});
        dr_id.setBounds(191, 34, 193, 34);
        formPanel.add(dr_id);
        
        //dept_id = new JComboBox();
        dept_idcombo= new JComboBox(new String[]{"9001","9002","9003","9004","9005"});
        dept_idcombo.setBounds(0, 136, 192, 34);
        formPanel.add(dept_idcombo);
        
        
        JLabel Payment_Status = new JLabel("Payment_Status");
        Payment_Status.setBounds(192, 214, 100, 14);
        formPanel.add(Payment_Status);
        
        ps = new JComboBox();
        ps_1= new JComboBox(new String[]{"C", "F"});
        ps_1.setBounds(0, 206, 192, 39);
        formPanel.add(ps_1);
        
                submitButton = new JButton("Submit");
                submitButton.setBounds(233, 256, 172, 23);
                formPanel.add(submitButton);
                
                btnNewButton = new JButton("Back");
                btnNewButton.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		new apts();
                		setVisible(false);
                	}
                });
                btnNewButton.setBounds(10, 256, 143, 23);
                formPanel.add(btnNewButton);
                submitButton.addActionListener(this);

        // Connect to the database
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?zeroDateTimeBehavior=CONVERT_TO_NULL","root","xamp@root");
            preparedStatement = conn.prepareStatement("INSERT INTO appointments (patient_id, doctor_id, app_date, app_time, payment_status, dept_id, trans_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
            
            
            
            
            
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            dispose();
        }

        setVisible(true);
    }
public void actionPerformed(ActionEvent e) {
    // Get values from input fields
    String patientId = patientIdField.getText();
    String appDate = appDateField.getText();
    String appTime = appTimeField.getText();
    String pay_stat = (String)ps_1.getSelectedItem();
    String transId = trans.getText();
   
  	String doc_id = (String) dr_id.getSelectedItem();
 int doctor_id=Integer.parseInt(doc_id);
    
    String dept_id = (String) dept_idcombo.getSelectedItem();
    int d_id=Integer.parseInt(dept_id);

    try {
        preparedStatement.setString(1, patientId);
        preparedStatement.setInt(2, doctor_id);
        preparedStatement.setString(3, appDate);
        preparedStatement.setString(4, appTime);
        preparedStatement.setString(5, pay_stat);
        preparedStatement.setInt(6, d_id);
        preparedStatement.setString(7, transId);

        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(null, "New appointment added successfully!");
            //dispose();
        }
    } catch (SQLException ex) {
     
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

	public static void main(String[] args) 
	{
		new new_apt();
	}
}


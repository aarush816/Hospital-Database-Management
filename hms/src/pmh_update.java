package hms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class pmh_update extends JFrame implements ActionListener {
    // GUI components
    private JTextField patientIDTextField, pastDiseasesTextField, medicationTextField, doctor1TextField, doctor2TextField, dateDiagnosedTextField, surgeriesTextField, allergiesTextField;
    private JButton submitButton;
    private JButton btnNewButton;
    

    
    public pmh_update() {
        // Set up the GUI
        setTitle("Enter Patient Medical History");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(null);
        
        // Add GUI components
        JLabel label = new JLabel("Patient ID:");
        label.setBounds(10, 14, 177, 17);
        panel.add(label);
        patientIDTextField = new JTextField();
        patientIDTextField.setBounds(197, 14, 177, 17);
        panel.add(patientIDTextField);
        
        JLabel label_1 = new JLabel("Past Diseases:");
        label_1.setBounds(10, 41, 177, 17);
        panel.add(label_1);
        pastDiseasesTextField = new JTextField();
        pastDiseasesTextField.setBounds(197, 41, 177, 17);
        panel.add(pastDiseasesTextField);
        
        JLabel label_2 = new JLabel("Medication:");
        label_2.setBounds(10, 68, 177, 17);
        panel.add(label_2);
        medicationTextField = new JTextField();
        medicationTextField.setBounds(197, 68, 177, 17);
        panel.add(medicationTextField);
        
        JLabel label_3 = new JLabel("Doctor 1:");
        label_3.setBounds(10, 95, 177, 17);
        panel.add(label_3);
        doctor1TextField = new JTextField();
        doctor1TextField.setBounds(197, 95, 177, 17);
        panel.add(doctor1TextField);
        
        JLabel label_4 = new JLabel("Doctor 2:");
        label_4.setBounds(10, 122, 177, 17);
        panel.add(label_4);
        doctor2TextField = new JTextField();
        doctor2TextField.setBounds(197, 122, 177, 17);
        panel.add(doctor2TextField);
        
        JLabel label_5 = new JLabel("Date Diagnosed (yyyy-mm-dd):");
        label_5.setBounds(10, 149, 177, 17);
        panel.add(label_5);
        dateDiagnosedTextField = new JTextField();
        dateDiagnosedTextField.setBounds(197, 149, 177, 17);
        panel.add(dateDiagnosedTextField);
        
        JLabel label_6 = new JLabel("Surgeries:");
        label_6.setBounds(10, 176, 177, 17);
        panel.add(label_6);
        surgeriesTextField = new JTextField();
        surgeriesTextField.setBounds(197, 176, 177, 17);
        panel.add(surgeriesTextField);
        
        JLabel label_7 = new JLabel("Allergies:");
        label_7.setBounds(10, 203, 177, 17);
        panel.add(label_7);
        allergiesTextField = new JTextField();
        allergiesTextField.setBounds(197, 203, 177, 17);
        panel.add(allergiesTextField);
        
        submitButton = new JButton("Submit");
        submitButton.setBounds(197, 231, 177, 17);
        submitButton.addActionListener(this);
        panel.add(submitButton);
        
        getContentPane().add(panel);
        
        btnNewButton = new JButton("Back");
        
        btnNewButton.setBounds(10, 231, 159, 23);
        panel.add(btnNewButton);
        setVisible(true);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Reception();
        		setVisible(false);
        	}
        });
        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Get the input values from the text fields
            String patientID = patientIDTextField.getText();
            String pastDiseases = pastDiseasesTextField.getText();
            String medication = medicationTextField.getText();
            String doctor1 = doctor1TextField.getText();
            String doctor2 = doctor2TextField.getText();
            String dateDiagnosed = dateDiagnosedTextField.getText();
            String surgeries = surgeriesTextField.getText();
            String allergies = allergiesTextField.getText();
            
            try {
                // Establish a connection to the database
                //Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            	Connection conn;
                
                Class.forName("com.mysql.cj.jdbc.Driver");
               
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?zeroDateTimeBehavior=CONVERT_TO_NULL","root","xamp@root");

                // Create a PreparedStatement to insert the data into the table
                String query = "INSERT INTO patient_medical_history (patient_id, past_dis, medication, doctor1_id, doctor2_id, date_diagnosed, surgeries, allergies) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, patientID);
                pstmt.setString(2, pastDiseases);
                pstmt.setString(3, medication);
                pstmt.setString(4, doctor1);
                pstmt.setString(5, doctor2);
                pstmt.setString(6, dateDiagnosed);
                pstmt.setString(7, surgeries);
                pstmt.setString(8, allergies);
                
                // Execute the PreparedStatement to insert the data into the table
                pstmt.executeUpdate();
                
                // Close the PreparedStatement and database connection
                pstmt.close();
                conn.close();
                
                // Show a message dialog to indicate success
                JOptionPane.showMessageDialog(this, "Patient medical history has been successfully saved to the database.");
            } catch (SQLException ex) {
                // Show an error message dialog if there is an error inserting the data into the table
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }
    public static void main(String[] args) {
        new pmh_update();
    }
}



package hms;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class display_pmh {
    JFrame frame;
    JTable table;
    private JLabel lblNewLabel;
    private JButton btnNewButton;
    private JTextField id;

    public display_pmh() {
        frame = new JFrame("Patient Medical History");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the table with the appropriate column headers
        String[] columnHeaders = {"Patient ID", "Past Disease", "Medication", "Doctor 1 ID", "Doctor 2 ID", "Date Diagnosed", "Surgeries", "Allergies"};
        DefaultTableModel tableModel = new DefaultTableModel(columnHeaders, 0);
        frame.getContentPane().setLayout(null);
        table = new JTable(tableModel);

        // Add the table to a scroll pane and add it to the frame
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 55, 794, 295);
        frame.getContentPane().add(scrollPane);
        
        lblNewLabel = new JLabel("Patient Id");
        lblNewLabel.setBounds(173, 25, 59, 14);
        frame.getContentPane().add(lblNewLabel);
        
        btnNewButton = new JButton("Submit");
        btnNewButton.setBounds(403, 21, 89, 23);
        frame.getContentPane().add(btnNewButton);
        
        id = new JTextField();
        id.setBounds(264, 22, 86, 20);
        
        frame.getContentPane().add(id);
        id.setColumns(10);
        
        JButton btnNewButton_1 = new JButton("Back");
       
        btnNewButton_1.setBounds(10, 21, 89, 23);
        frame.getContentPane().add(btnNewButton_1);
        Connection conn;
        try {
            // Connect to the database
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?zeroDateTimeBehavior=CONVERT_TO_NULL","root","xamp@root");
 
            //String url = "jdbc:mysql://localhost:3306/hospital";
            //String user = "your_username";
            //String password = "your_password";
            //Connection conn = DriverManager.getConnection(url, user, password);

            // Execute the query to retrieve patient medical history
            String query = "SELECT * FROM patient_medical_history";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Add the results to the table model
            while (rs.next()) {
                int patientID = rs.getInt("patient_id");
                String pastDisease = rs.getString("past_dis");
                String medication = rs.getString("medication");
                String doctor1ID = rs.getString("doctor1_id");
                String doctor2ID = rs.getString("doctor2_id");
                String dateDiagnosed = rs.getString("date_diagnosed");
                String surgeries = rs.getString("surgeries");
                String allergies = rs.getString("allergies");

                Object[] row = {patientID, pastDisease, medication, doctor1ID, doctor2ID, dateDiagnosed, surgeries, allergies};
                tableModel.addRow(row);
            }
            btnNewButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		String patient_id=id.getText();
            		int pid=Integer.parseInt(patient_id);
            		try {
            			String query = "SELECT * FROM patient_medical_history";
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        int flag=0;
						while(rs.next())
						{
							if(rs.getInt("patient_id")==pid)
							{
								tableModel.setRowCount(0);
								int patientID = rs.getInt("patient_id");
				                String pastDisease = rs.getString("past_dis");
				                String medication = rs.getString("medication");
				                String doctor1ID = rs.getString("doctor1_id");
				                String doctor2ID = rs.getString("doctor2_id");
				                String dateDiagnosed = rs.getString("date_diagnosed");
				                String surgeries = rs.getString("surgeries");
				                String allergies = rs.getString("allergies");
				                Object[] row = {patientID, pastDisease, medication, doctor1ID, doctor2ID, dateDiagnosed, surgeries, allergies};
				                tableModel.addRow(row);
				                flag=1;
				                break;
							}
							
						}
						if(flag==0)
						{
							JOptionPane.showMessageDialog(null,"Patient history not found");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            });
            // Close the database connection and resources
            //rs.close();
            //stmt.close();
            //conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setVisible(true);
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Login_main();
        		frame.setVisible(false);
        	}
        });
    }

    public static void main(String[] args) {
        new display_pmh();
    }
}
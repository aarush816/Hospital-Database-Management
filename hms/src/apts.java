package hms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class apts {
    JFrame frame;
    JTable table;
    Connection conn;

    public apts() {
        frame = new JFrame("Appointments");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the table with the appropriate column headers
        String[] columnHeaders = {"Patient ID", "Doctor ID", "Date", "Time", "Pay Status", "Dept ID", "Transaction ID"};
        DefaultTableModel tableModel = new DefaultTableModel(columnHeaders, 0);
        frame.getContentPane().setLayout(null);
        table = new JTable(tableModel);

        // Add the table to a scroll pane and add it to the frame
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 46, 611, 264);
        frame.getContentPane().add(scrollPane);
        
        JLabel lblNewLabel = new JLabel("Appointments");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel.setBounds(225, 11, 121, 24);
        frame.getContentPane().add(lblNewLabel);
        
        JButton btnNewButton = new JButton("new_apt");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new new_apt();
        		frame.setVisible(false);
        	}
        });
        btnNewButton.setBounds(430, 321, 89, 23);
        frame.getContentPane().add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Back");
        
        btnNewButton_1.setBounds(75, 321, 89, 23);
        frame.getContentPane().add(btnNewButton_1);

        try {
            // Connect to the database
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?zeroDateTimeBehavior=CONVERT_TO_NULL","root","xamp@root");
  

            // Execute the query to retrieve appointments
            String query = "SELECT * FROM appointments";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Add the results to the table model
            while (rs.next()) {
                int patientID = rs.getInt("patient_id");
                int doctorID = rs.getInt("doctor_id");
                String date = rs.getString("app_date");
                String time = rs.getString("app_time");
                String payStatus = rs.getString("payment_status");
                int deptID = rs.getInt("dept_id");
                int transID = rs.getInt("trans_id");

                Object[] row = {patientID, doctorID, date, time, payStatus, deptID, transID};
                tableModel.addRow(row);
            }

            // Close the database connection and resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        frame.setVisible(true);
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Reception();
        		frame.setVisible(false);
        	}
        });
    }

    public static void main(String[] args) {
        new apts();
    }
}

package hms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Reception implements ActionListener {
    JFrame frame;
    JButton btnRegister, btnAppointments, btnUpdateRecord, btnLogout;

    public Reception() {
        frame = new JFrame("Hospital Reception");
        frame.setSize(418, 322);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        btnAppointments = new JButton("Appointments");
        btnAppointments.setBounds(129, 97, 150, 40);
        btnUpdateRecord = new JButton("Update Patient Record");
        btnUpdateRecord.setBounds(129, 153, 168, 40);
        btnLogout = new JButton("Logout");
        btnLogout.setBounds(266, 223, 76, 23);
        btnAppointments.addActionListener(this);
        btnUpdateRecord.addActionListener(this);
        btnLogout.addActionListener(this);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(btnAppointments);
        frame.getContentPane().add(btnUpdateRecord);
        
                btnRegister = new JButton("Patient Registration");
                btnRegister.setBounds(129, 41, 150, 40);
                
                        btnRegister.addActionListener(this);
                        
                                frame.getContentPane().add(btnRegister);
        frame.getContentPane().add(btnLogout);

        frame.setVisible(true);
        
        Connect();
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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            new patient_registeration();
        } else if (e.getSource() == btnAppointments) {
            new apts();
        } else if (e.getSource() == btnUpdateRecord) {
            new pmh_update();
        }  else if (e.getSource() == btnLogout) {
            // Logout button clicked
        	new Login_main();
        	
        }
        this.frame.setVisible(false);
    }

    public static void main(String[] args) {
        new Reception();
    }
}

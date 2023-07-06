
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class Home {
    private JFrame frame;
    private JTextField userNameTextField;
    private JTextField userIdTextField;
    private JTextField emailTextField;
    private JTextField passTextField;
    private JTextField mNameTextField;
    private JTextField mIdTextField;
    private JTextField tIdTextField;
    private JTextField tNameTextField;
    private JTextField tDescTextField;
    private JTextField rNameTextField;
    private JTextField rIdTextField;
    private JTextField rTypeTextField;
    private JTextField rUrlTextField;

    private JMenuBar mBar;
    private JMenu mnuHelp;
    private JMenuItem abt;
    
    
    public Home() {
    	

    	
        frame = new JFrame("Database Reads!");
        frame.setSize(700, 1200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        mBar = new JMenuBar();
        mnuHelp = new JMenu("Help");
        abt = new JMenuItem("About");
        mnuHelp.add(abt);
        mBar.add(mnuHelp);
        frame.setJMenuBar(mBar);

        mnuHelp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == abt) {
                	try {
                    String details;
                    details = "This project is to enable users login and access " + "\n" +
                            "Modules, Topics and Resources of DBMS" + "\n" +
                            "You can insert,update and delete these details";
                    JOptionPane.showMessageDialog(null, details, "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                	}
                	catch(Exception e3)
                	{
                		e3.printStackTrace();
                	}
                }
            }
        });
        
        JLabel label=new JLabel("DATABASE READS");
        label.setBounds(180,20,900,50);
        label.setFont(new Font("Times New Roman",Font.PLAIN,30));
        JLabel label1=new JLabel("USER DETAILS");
        label1.setBounds(10,50,700,25);
        label1.setFont(new Font("Times New Roman",Font.PLAIN,20));
        
        
        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(20, 80, 80, 25);

        userIdTextField = new JTextField();
        userIdTextField.setBounds(100, 80, 200, 25);
        

        JLabel userNameLabel = new JLabel("Username:");
        userNameLabel.setBounds(20, 110, 80, 25);
        
        userNameTextField = new JTextField();
        userNameTextField.setBounds(100, 110, 200, 25);
        

        JLabel emailLabel = new JLabel("Email Id:");
        emailLabel.setBounds(20, 140, 80, 25);
        
        emailTextField = new JTextField();
        emailTextField.setBounds(100, 140, 200, 25);
        

        JLabel passLabel = new JLabel("Pass:");
        passLabel.setBounds(20, 170, 80, 25);
        passTextField = new JTextField();
        passTextField.setBounds(100, 170, 200, 25);
        
        frame.add(label);
        frame.add(label1);
        frame.add(userIdLabel);
        frame.add(userIdTextField);
        frame.add(userNameLabel);
        frame.add(userNameTextField);
        frame.add(emailLabel);
        frame.add(emailTextField);
        frame.add(passLabel);
        frame.add(passTextField);
        
        
        JButton i1 = new JButton("Submit");
        i1.setBounds(20, 210, 80, 25);
        frame.add(i1);
        i1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertUser();
            }

			private void insertUser() {
				String userId = userIdTextField.getText();
	             String userName = userNameTextField.getText();
	             String email = emailTextField.getText();
	            String pass = passTextField.getText();
	            try {
	            	Class.forName("oracle.jdbc.driver.OracleDriver");
	    			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
	                
	                Statement statement = con.createStatement();

	                String query = "INSERT INTO users (u_id, u_name,email,pass) VALUES ('" + userId + "', '" + userName + "','" + email + "','" + pass + "')";
	                statement.executeUpdate(query);

	                JOptionPane.showMessageDialog(null, "Data inserted successfully!","INFORMATION", JOptionPane.INFORMATION_MESSAGE);
	            } catch (Exception e) 
	            {  JOptionPane.showMessageDialog(null, "Data cannot be inserted unique constraint","WARNING", JOptionPane.INFORMATION_MESSAGE);}
	            
			}
        });
        
        
        JButton u1 = new JButton("Modify");
        u1.setBounds(110, 210, 80, 25);
        frame.add(u1);
        u1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
            private void updateUser() {
				String userId = userIdTextField.getText();
	             String userName = userNameTextField.getText();
	             String email = emailTextField.getText();
	            String pass = passTextField.getText();
	            try {
	            	Class.forName("oracle.jdbc.driver.OracleDriver");
	    			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
	                
	                Statement statement = con.createStatement();

	            String query = "UPDATE users SET u_name = '" + userName + "',email = '" + email + "',pass = '" + pass + "' WHERE u_id = '" + userId + "'";
	            statement.executeUpdate(query);

	            JOptionPane.showMessageDialog(null, "Data modified successfully!");
            }
	            catch (Exception e) 
	            {   JOptionPane.showMessageDialog(null, "Data cannot be inserted ","WARNING", JOptionPane.INFORMATION_MESSAGE);}
	            userIdTextField.setText("");
	            userNameTextField.setText("");
	            emailTextField.setText("");
	            passTextField.setText("");
            }
        });

        JButton d1 = new JButton("Delete");
        d1.setBounds(200, 210, 80, 25);
        frame.add(d1);
        d1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                deleteUser();
            }
            private void deleteUser() {
                String userId = userIdTextField.getText();

                try {
                	Class.forName("oracle.jdbc.driver.OracleDriver");
    			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
                   Statement statement = con.createStatement();

                    String query = "DELETE FROM users WHERE u_id = '" + userId + "'";
                    statement.executeUpdate(query);

                    JOptionPane.showMessageDialog(null, "Data deleted successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                userIdTextField.setText("");
	            userNameTextField.setText("");
	            emailTextField.setText("");
	            passTextField.setText("");
            }
            });
        
        
      //VIEW USERS TABLE
        JButton v1 = new JButton("View");
        v1.setBounds(290, 210, 80, 25);
        frame.add(v1);
        v1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                viewData();
            }
            public void viewData() {
            try 
            {
            	Class.forName("oracle.jdbc.driver.OracleDriver");
    			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
                   Statement statement = con.createStatement();
           
            String query = "SELECT u_id, u_name,email,pass FROM users"; 
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            // Create a table model to hold the data
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("User ID");
            tableModel.addColumn("User Name");
            tableModel.addColumn("Email");
            tableModel.addColumn("Pass");

            while (rs.next()) {
            	String userId = rs.getString("u_id");
                String userName = rs.getString("u_name");
                String em = rs.getString("email");
                String pa = rs.getString("pass");

                tableModel.addRow(new Object[] { userId, userName, em, pa });
            }

            rs.close();
            pstmt.close();
            JTable dataTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(dataTable);
            JFrame frame = new JFrame("View Data");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(scrollPane);
            frame.pack();
            frame.setVisible(true);
        } 
    	catch (Exception e)
    	{
            System.out.println("Error viewing data: " + e.getMessage());
        }
    	}});
    	
        
        
        
        
        
        
        JLabel label2=new JLabel("MODULE DETAILS");
        label2.setBounds(10,250,700,25);
        label2.setFont(new Font("Times New Roman",Font.PLAIN,20));
        frame.add(label2);

        JLabel mIdLabel = new JLabel("Module ID:");
        mIdLabel.setBounds(20, 280, 80, 25);
        frame.add(mIdLabel);
        mIdTextField = new JTextField();
        mIdTextField.setBounds(100, 280, 200, 25);
        frame.add(mIdTextField);

        JLabel mNameLabel = new JLabel("Module Name:");
        mNameLabel.setBounds(20, 310, 80, 25);
        frame.add(mNameLabel);
        mNameTextField = new JTextField();
        mNameTextField.setBounds(100, 310, 200, 25);
        frame.add(mNameTextField);

        JButton i2 = new JButton("Submit");
        i2.setBounds(20, 350, 80, 25);
        frame.add(i2);
        
        i2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
				String modId = mIdTextField.getText();
	             String modName = mNameTextField.getText();
	             
	            try {
	            	Class.forName("oracle.jdbc.driver.OracleDriver");
	    			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
	                  Statement statement = con.createStatement();
	                  String query = "INSERT INTO modules (m_id, m_name) VALUES ('" + modId + "', '" + modName + "')";
	                statement.executeUpdate(query);

	                JOptionPane.showMessageDialog(null, "Data inserted successfully!","INFORMATION", JOptionPane.INFORMATION_MESSAGE);
	            } catch (Exception e2) 
	            {   JOptionPane.showMessageDialog(null, "Data cannot be inserted unique constraint","WARNING", JOptionPane.INFORMATION_MESSAGE);}
	            
			}
        });
        

        JButton u2 = new JButton("Modify");
        u2.setBounds(110, 350, 80, 25);
        frame.add(u2);
        u2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        String modId = mIdTextField.getText();
        String modName = mNameTextField.getText();
        
       try {
       	Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
           
           Statement statement = con.createStatement();

       String query = "UPDATE modules SET m_name = '" + modName + "' WHERE m_id = '" + modId + "'";
       statement.executeUpdate(query);

       JOptionPane.showMessageDialog(null, "Data modified successfully!");
   }
       catch (Exception e2) 
       {   JOptionPane.showMessageDialog(null, "Data cannot be inserted ","WARNING", JOptionPane.INFORMATION_MESSAGE);}
       mIdTextField.setText("");
       mNameTextField.setText("");
   }
});

        JButton d2 = new JButton("Delete");
        d2.setBounds(200, 350, 80, 25);
        frame.add(d2);
        d2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        String modId = mIdTextField.getText();

        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
           Statement statement = con.createStatement();

            String query = "DELETE FROM modules WHERE m_id = '" + modId + "'";
            statement.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Data deleted successfully!");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        mIdTextField.setText("");
        mNameTextField.setText("");
            }
   
    });
        
        
        JButton v2 = new JButton("View");
        v2.setBounds(290, 350, 80, 25);
        frame.add(v2);
        v2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                viewData();
            }
            public void viewData() {
            try 
            {
            	Class.forName("oracle.jdbc.driver.OracleDriver");
    			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
                   Statement statement = con.createStatement();
           
            String query = "SELECT m_id, m_name FROM modules"; 
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            // Create a table model to hold the data
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Module ID");
            tableModel.addColumn("Module Name");
            

            while (rs.next()) {
            	String modId = rs.getString("m_id");
                String modName = rs.getString("m_name");
                tableModel.addRow(new Object[] { modId, modName});
            }

            rs.close();
            pstmt.close();
            JTable dataTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(dataTable);
            JFrame frame = new JFrame("View Data");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(scrollPane);
            frame.pack();
            frame.setVisible(true);
        } 
    	catch (Exception er)
    	{
            System.out.println("Error viewing data: " + er.getMessage());
        }
    	}});
    	
        

        JLabel label3=new JLabel("TOPIC DETAILS");
        label3.setBounds(10,390,700,25);
        label3.setFont(new Font("Times New Roman",Font.PLAIN,20));
        frame.add(label3);

        JLabel tIdLabel = new JLabel("Topic ID:");
        tIdLabel.setBounds(20, 420, 80, 25);
        frame.add(tIdLabel);
        tIdTextField = new JTextField();
        tIdTextField.setBounds(100, 420, 200, 25);
        frame.add(tIdTextField);

        JLabel tNameLabel = new JLabel("Topic Name:");
        tNameLabel.setBounds(20, 450, 80, 25);
        frame.add(tNameLabel);
        tNameTextField = new JTextField();
        tNameTextField.setBounds(100, 450, 200, 25);
        frame.add(tNameTextField);

        JLabel tDescLabel = new JLabel("Topic Description:");
        tDescLabel.setBounds(20, 480, 80, 25);
        frame.add(tDescLabel);
        tDescTextField = new JTextField();
        tDescTextField.setBounds(100, 480, 200, 25);
        frame.add(tDescTextField);

        JButton i3 = new JButton("Submit");
        i3.setBounds(20, 520, 80, 25);
        frame.add(i3);
        i3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	String modId = mIdTextField.getText();
				String topId = tIdTextField.getText();
	             String topName = tNameTextField.getText();
	             String topDesc = tDescTextField.getText();
	             
	            try {
	            	Class.forName("oracle.jdbc.driver.OracleDriver");
	    			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
	                  Statement statement = con.createStatement();
	                  String query = "INSERT INTO topics (t_id, t_name,description) VALUES ('" + topId + "', '" + topName + "', '" + topDesc + "')";
	                statement.executeUpdate(query);
	                
	                String query1 = "INSERT INTO have (m_id,t_id) VALUES ('" + modId + "', '" + topId + "')";
	                statement.executeUpdate(query1);

	                JOptionPane.showMessageDialog(null, "Data inserted successfully!","INFORMATION", JOptionPane.INFORMATION_MESSAGE);
	            } catch (Exception e2) 
	            {  
	            JOptionPane.showMessageDialog(null, "Data cannot be inserted foreign key constraint!","INFORMATION", JOptionPane.INFORMATION_MESSAGE);}
	            tIdTextField.setText("");
	            tNameTextField.setText("");
	            tDescTextField.setText("");
	          
			}
        });
        

        JButton u3 = new JButton("Modify");
        u3.setBounds(110, 520, 80, 25);
        frame.add(u3);
        u3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        String topId = tIdTextField.getText();
        String topName = tNameTextField.getText();
        String topDesc = tDescTextField.getText();
        try {
       	Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
           
           Statement statement = con.createStatement();

       String query = "UPDATE topics SET t_name = '" + topName + "', description = '" + topDesc + "' WHERE t_id = '" + topId + "'";
       statement.executeUpdate(query);

       JOptionPane.showMessageDialog(null, "Data modified successfully!");
   }
       catch (Exception e2) 
       {  JOptionPane.showMessageDialog(null, "Data cannot be inserted foreign key constraint!","INFORMATION", JOptionPane.INFORMATION_MESSAGE);}
        tIdTextField.setText("");
        tNameTextField.setText("");
        tDescTextField.setText("");
      
   }
});

        JButton d3 = new JButton("Delete");
        d3.setBounds(200, 520, 80, 25);
        frame.add(d3);
        d3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        String topId = tIdTextField.getText();

        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
           Statement statement = con.createStatement();

            String query = "DELETE FROM topics WHERE t_id = '" + topId + "'";
            statement.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Data deleted successfully!");
        } catch (Exception e2) {
            e2.printStackTrace();}
        tIdTextField.setText("");
        tNameTextField.setText("");
        tDescTextField.setText("");
      
            }
          });

        JButton v3 = new JButton("View");
        v3.setBounds(290, 520, 80, 25);
        frame.add(v3);
        v3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                viewData();
            }
            public void viewData() {
            try 
            {
            	Class.forName("oracle.jdbc.driver.OracleDriver");
    			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
                   Statement statement = con.createStatement();
           
            String query = "SELECT t_id, t_name,description FROM topics"; 
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            // Create a table model to hold the data
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Topic ID");
            tableModel.addColumn("Topic Name");
            tableModel.addColumn("Description");
            
            while (rs.next()) {
            	String topId = rs.getString("t_id");
                String topName = rs.getString("t_name");
                String des = rs.getString("description");
                

                tableModel.addRow(new Object[] { topId, topName, des});
            }

            rs.close();
            pstmt.close();
            JTable dataTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(dataTable);
            JFrame frame = new JFrame("View Data");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(scrollPane);
            frame.pack();
            frame.setVisible(true);
        } 
    	catch (Exception e)
    	{
            System.out.println("Error viewing data: " + e.getMessage());
        }
    	}});
    	
        
        JLabel label4=new JLabel("RESOURCE DETAILS");
        label4.setBounds(10,560,700,25);
        label4.setFont(new Font("Times New Roman",Font.PLAIN,20));
        frame.add(label4);
        
        JLabel rIdLabel = new JLabel("Resource ID:");
        rIdLabel.setBounds(20, 600, 1200, 25);
        frame.add(rIdLabel);
        rIdTextField = new JTextField();
        rIdTextField.setBounds(140, 600, 200, 25);
        frame.add(rIdTextField);
       

        JLabel rNameLabel = new JLabel("Resource Name:");
        rNameLabel.setBounds(20, 600, 120, 25);
        frame.add(rNameLabel);
        rNameTextField = new JTextField();
        rNameTextField.setBounds(140, 600, 200, 25);
        frame.add(rNameTextField);

        JLabel rTypeLabel = new JLabel("Resource Type:");
        rTypeLabel.setBounds(20, 630, 120, 25);
        frame.add(rTypeLabel);
        rTypeTextField = new JTextField();
        rTypeTextField.setBounds(140, 630, 200, 25);
        frame.add(rTypeTextField);

        JLabel rUrlLabel = new JLabel("Resource URL:");
        rUrlLabel.setBounds(20, 660, 120, 25);
        frame.add(rUrlLabel);
        rUrlTextField = new JTextField();
        rUrlTextField.setBounds(140, 660, 200, 25);
        frame.add(rUrlTextField);

        JButton i4 = new JButton("Submit");
        i4.setBounds(20, 700, 80, 25);
        frame.add(i4);
        i4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

				String topId = tIdTextField.getText();
				String resId = rIdTextField.getText();
	             String resName = rNameTextField.getText();
	             String resType = rTypeTextField.getText();
	             String resUrl = rUrlTextField.getText();
	             
	            try {
	            	Class.forName("oracle.jdbc.driver.OracleDriver");
	    			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
	                  Statement statement = con.createStatement();
	                  String query = "INSERT INTO resources (r_id, t_id,r_name,typeof,url) VALUES ('" + resId + "', '" + topId + "', '" + resName + "', '" + resType + "', '" + resUrl + "')";
	                statement.executeUpdate(query);

	                JOptionPane.showMessageDialog(null, "Data inserted successfully!","INFORMATION", JOptionPane.INFORMATION_MESSAGE);
	            } catch (Exception e2) 
	            {  
	            JOptionPane.showMessageDialog(null, "Data cannot be inserted foreign key constraint!","INFORMATION", JOptionPane.INFORMATION_MESSAGE);}
	            rIdTextField.setText("");
	            rNameTextField.setText("");
	            rTypeTextField.setText("");
	            rUrlTextField.setText("");
	          
			}
        });

        JButton u4 = new JButton("Modify");
        u4.setBounds(110, 700, 80, 25);
        frame.add(u4);
        u4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        String topId = tIdTextField.getText();
        String resId = rIdTextField.getText();
        String resName = rNameTextField.getText();
        String resType = rTypeTextField.getText();
        String resUrl = rUrlTextField.getText();
        
        try {
       	Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
           
           Statement statement = con.createStatement();

       String query = "UPDATE resources SET t_id = '" + topId + "',r_name = '" + resName + "', typeof = '" + resType + "',url = '" + resUrl + "' WHERE r_id = '" + resId + "'";
       statement.executeUpdate(query);

       JOptionPane.showMessageDialog(null, "Data modified successfully!");
   }
       catch (Exception e2) 
       {  JOptionPane.showMessageDialog(null, "Data cannot be inserted foreign key constraint!","INFORMATION", JOptionPane.INFORMATION_MESSAGE);}
        rIdTextField.setText("");
        rNameTextField.setText("");
        rTypeTextField.setText("");
        rUrlTextField.setText("");
   }
});


        JButton d4 = new JButton("Delete");
        d4.setBounds(200, 700, 80, 25);
        frame.add(d4);
        d4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        String resId = rIdTextField.getText();

        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
           Statement statement = con.createStatement();

            String query = "DELETE FROM resources WHERE r_id = '" + resId + "'";
            statement.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Data deleted successfully!");
        } catch (Exception e2) {
            e2.printStackTrace();}
        rIdTextField.setText("");
        rNameTextField.setText("");
        rTypeTextField.setText("");
        rUrlTextField.setText("");
            }
   
    });
        
        
        
        //VIEW USERS TABLE
        JButton v4 = new JButton("View");
        v4.setBounds(290, 700, 80, 25);
        frame.add(v4);
        v4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                viewData();
            }
            public void viewData() {
            try 
            {
            	Class.forName("oracle.jdbc.driver.OracleDriver");
    			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
                   Statement statement = con.createStatement();
           
            String query = "SELECT t_id, r_id, r_name, typeof, url FROM resources"; 
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
           
             //List<String> data = new ArrayList<>();
             //JComboBox<String> comboBox = new JComboBox<>(new DefaultComboBoxModel<>(data.toArray(new String[0])));
             //.add(comboBox);

            // Create a table model to hold the data
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Topic ID");
            tableModel.addColumn("Resources ID");
            tableModel.addColumn("Resources Name");
            tableModel.addColumn("TypeOf");
            tableModel.addColumn("URL");

            while (rs.next()) {
            	String topId = rs.getString("t_id");
            	String resId = rs.getString("r_id");
                String resName = rs.getString("r_name");
                String typ = rs.getString("typeof");
                String ur = rs.getString("url");

                tableModel.addRow(new Object[] { topId, resId, resName, typ, ur });
            }

            rs.close();
            pstmt.close();
            JTable dataTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(dataTable);
            JFrame frame = new JFrame("View Data");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(scrollPane);
            frame.pack();
            frame.setVisible(true);
        } 
    	catch (Exception e)
    	{
            System.out.println("Error viewing data: " + e.getMessage());
        }
    	}});
    	
        
        JLabel label5=new JLabel("User Fav Resource");
        label5.setBounds(10,740,700,25);
        label5.setFont(new Font("Times New Roman",Font.PLAIN,20));
        frame.add(label5);
       
        JLabel user1 = new JLabel("User ID:");
        user1.setBounds(20, 770, 80, 25);
        frame.add(user1);
        JTextField userT = new JTextField();
        userT.setBounds(100, 770, 200, 25);
        frame.add(userT);
        JButton s6 = new JButton("Submit");
        s6.setBounds(50, 800, 80, 25);
        frame.add(s6);
        JTextField topT = new JTextField();
        topT.setBounds(100, 830, 200, 25);
        frame.add(topT);
        s6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

				String uId = userT.getText();
				int resId = 0 ;
				 try {
					 
				 
	            	Class.forName("oracle.jdbc.driver.OracleDriver");
	    			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","vansh","anu");
	                  Statement statement = con.createStatement();
	                  String query = "select r_id from user_fav_res where u_id='"+ uId +"'";
	                  ResultSet rs = statement.executeQuery(query);
	                  while(rs.next())
	                    resId =rs.getInt("r_id");
	                  String str;
	                  str=Integer.toString(resId);
	                  topT.setText(str);
	                  
	                   JOptionPane.showMessageDialog(null, "Data exerted successfully!","INFORMATION", JOptionPane.INFORMATION_MESSAGE);
	                 
				 }
	                  catch (Exception e2) 
	            {  
	            JOptionPane.showMessageDialog(null, "Data cannot be exerted","INFORMATION", JOptionPane.INFORMATION_MESSAGE);}
	           
			}
        });
        JLabel top1 = new JLabel("Resource IDs:");
        top1.setBounds(20, 830, 80, 25);
        frame.add(top1);
        
        
        
        
        
        
        frame.setVisible(true);
    }
            
   

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Home();
            }
        });
    }
}






import java.sql.*; 
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.Date;

public class Project
{
	JFrame login = new JFrame("Login");
	JFrame form = new JFrame("Form");
	Connection conn;
	Project()
	{
		JTabbedPane jtp = new JTabbedPane();	//	object created of JTabbedPane
		   JPanel p1 = new JPanel();			//   \
		   JPanel p2 = new JPanel();			//	  |-->	object created of JPanel
		   JPanel p3 = new JPanel();			//   /
		   
		   
		   //			****************** Main Form start ********************
		   p1.setLayout(null);			
		   p2.setLayout(null);				//	layout of JPanel1
		   p3.setLayout(null);
		   
		   JPanel top = new JPanel();
		   top.setLayout(null);
		   top.setBounds(0,21,1366,100);
		   top.setBackground(new Color(88,127,245));
		   
		   JLabel projectname = new JLabel("Car Accessories System.");
		   projectname.setFont(new Font("Catamaran",Font.PLAIN,40));
		   projectname.setBounds(250,30,500,50);
		   top.add(projectname);
		   JButton Blogout = new JButton("Logout");
		   Blogout.setBounds(1100,20,110,28);
		   top.add(Blogout);
		   
		   JPanel ver = new JPanel();
		   ver.setLayout(null);
		   ver.setBounds(0,100,238,620);
		   ver.setBackground(new Color(88,150,245));
		   
		   jtp.setBounds(240,120,1000,550);
		   jtp.add(p1,"Car Accessories Form");
		   jtp.add(p2,"Update Stock");
		   jtp.add(p3,"Check Stock");
		   
		   	JLabel Lename=new JLabel("Editer Name: ");
			JLabel Lid=new JLabel("Accessories ID: ");
			JLabel Laname=new JLabel("Accessories Name: ");
			JLabel Lrec=new JLabel("Recommendation: ");
			JLabel Lrange=new JLabel("Range: ");
			JLabel Lprice=new JLabel("Price: ");
			JLabel Lstock=new JLabel("Available Stock: ");
			
			Date date = new Date();
			JTextField Tename=new JTextField("Editer Name",25);
			JTextField Tid=new JTextField("Accessories ID: ",25);
			JTextField Taname=new JTextField("Accessories Name: ",25);
				JRadioButton Rrec1=new JRadioButton("Businessman");
				JRadioButton Rrec2=new JRadioButton("Community");
				JRadioButton Rrec3=new JRadioButton("None");
				JRadioButton Rrange1=new JRadioButton("Low");
				JRadioButton Rrange2=new JRadioButton("Medium");
				JRadioButton Rrange3=new JRadioButton("High");
			JTextField Tprice=new JTextField("Price: ",25);
			JTextField Tstock=new JTextField("Stock",25);
			
			JButton Bsave=new JButton("Save");
			JButton Breset=new JButton("Reset");
			
			Lename.setBounds(100,30,150,25);
			Lid.setBounds(100,100,150,25);
			Laname.setBounds(100,140,150,25);
			Lrec.setBounds(100,180,150,25);
				Rrec1.setBounds(130,210,150,25);
				Rrec2.setBounds(280,210,150,25);
				Rrec3.setBounds(430,210,150,25);
			Lrange.setBounds(100,250,150,25);
				Rrange1.setBounds(130,280,150,25);
				Rrange2.setBounds(280,280,150,25);
				Rrange3.setBounds(430,280,150,25);
			Lprice.setBounds(100,320,150,25);
			Lstock.setBounds(100,360,150,25);
			Bsave.setBounds(100,450,90,30);
			Breset.setBounds(400,450,90,30);
			
			Tename.setBounds(250,30,250,25);
			Tid.setBounds(250,100,250,25);
			Taname.setBounds(250,140,250,25);
			Tprice.setBounds(250,320,250,25);
			Tstock.setBounds(250,360,250,25);
			
		    try
		    {
	         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/car", "root", "abhi");
	         Statement stmt = conn.createStatement();
				
			 Bsave.addActionListener(new ActionListener()
			 {  
	            public void actionPerformed(ActionEvent ae) 
	            {    
					try
					{	
						Date dat = new Date();
						String Sdat = date.toString();
						
						String recomm = "";
						if(Rrec1.isSelected())
						{
							recomm = "Businessman";
						}
						if(Rrec2.isSelected())
						{
							recomm = "Community";
						}
						if(Rrec3.isSelected())
						{
							recomm = "None";
						}
						
						int price = Integer.parseInt(Tprice.getText());
						String range = "";
						if(price<5000)
						{
							Rrange1.setSelected(true);
							range = "Low";
						}
						else if(price>10000)
						{
							Rrange2.setSelected(true);
							range = "Medium";
						}
						else
						{
							Rrange3.setSelected(true);
							range = "High";
						}
						
						
						 String sqlInsert = "INSERT INTO `car`.`car_table` (`acc_id`, `editer_name`, `acc_name`, `recomm`, `range`, `price`, `stock`) VALUES ('"+Tid.getText()+"', '"+Tename.getText()+"', '"+Taname.getText()+"', '"+recomm+"', '"+range+"', '"+Tprice.getText()+"', '"+Tstock.getText()+"');";
				         System.out.println("The SQL query is: " + sqlInsert);  	
				         int countInserted = stmt.executeUpdate(sqlInsert);			// inserting record
				         System.out.println(countInserted + " records inserted.\n");
				         
				         Tid.setText("");						//	\
				         Tename.setText("");					//	 \
				         Taname.setText("");					//	  |	-->	resetting all text field.
				         Tprice.setText("");					//	 /
				         Tstock.setText("");					//	/
					}
					catch(SQLException ex) 
					{
						ex.printStackTrace();
					}
	            }  
			 });   
			
			 Breset.addActionListener(new ActionListener() 
			 {  
	            public void actionPerformed(ActionEvent ae) 
	            {       
	            	 Tid.setText("");						//	\
			         Tename.setText("");					//	 \
			         Taname.setText("");					//	  |	-->	resetting all text field.
			         Tprice.setText("");					//	 /
			         Tstock.setText("");					//	/
	            }  
			 }); 
			 
	      } 
		  catch(SQLException ex) 
		   {
	         ex.printStackTrace();
		   }
		    
		    ButtonGroup bg1 = new ButtonGroup();
		    ButtonGroup bg2 = new ButtonGroup();
		    bg1.add(Rrec1);
		    bg1.add(Rrec2);
		    bg1.add(Rrec3);
		    
		    bg2.add(Rrange1);
		    bg2.add(Rrange2);
		    bg2.add(Rrange3);
			  
		  p1.add(Lename);	
		  p1.add(Lid);
		  p1.add(Laname);
		  p1.add(Lrec);
		  p1.add(Rrec1);
		  p1.add(Rrec2);
		  p1.add(Rrec3);
		  p1.add(Rrange1);
		  p1.add(Rrange2);
		  p1.add(Rrange3);
		  p1.add(Lrange);
		  p1.add(Lprice);
		  p1.add(Lstock);
		  p1.add(Bsave);
		  p1.add(Breset);
		  
		  p1.add(Tename);
		  p1.add(Tid);
		  p1.add(Taname);
		  p1.add(Tprice);
		  p1.add(Tstock);
		   //		************* Main Form end ******************
		  
		  
		  
//	 		************* Update Stock Start ***************
		  JLabel ULid,ULstock;							// \
		  JTextField UTid,UTstock;						//  |->	Labels, TextFields, Button in "Updates stock" tab.
		  JButton UBupdate;								// /	
		  
		  ULid = new JLabel("Enter Accessories ID: ");
		  ULstock = new JLabel("Enter Available Stock: ");
		  
		  UTid = new JTextField();
		  UTstock = new JTextField();
		  UBupdate = new JButton("Update Stock");
		  
		  ULid.setBounds(100, 30, 150, 35);
		  ULstock.setBounds(100,70,150,35);
		  UBupdate.setBounds(100,130,135,30);
		  
		  UTid.setBounds(250,30,250,25);
		  UTstock.setBounds(250,70,250,25);
		  
		  UBupdate.addActionListener(new ActionListener() 	// Updating stock
		  {
			  public void actionPerformed(ActionEvent ae)
			  {
				  try 
				  {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/car", "root", "abhi");
					Statement stmt1 = conn.createStatement();
					String updateStockQuery="UPDATE `car`.`car_table` SET `stock` = '"+UTstock.getText()+"' WHERE (`acc_id` = '"+UTid.getText()+"');";
					int updatedCount = stmt1.executeUpdate(updateStockQuery);
					System.out.println(updatedCount+" record updated.");
					UTstock.setText("");
					UTid.setText("");
				  }
				  catch (SQLException e){e.printStackTrace();}
			  }
		  });
		  
		  p2.add(ULid);
		  p2.add(ULstock);
		  p2.add(UTid);
		  p2.add(UTstock);
		  p2.add(UBupdate);
		  
		  
//	 	    ************* Update Stock End ****************
		  

//	 		************* Check Stock Start ***************
		  JLabel CLoutOfStock = new JLabel("Check availablity of Stock:");
		  JLabel CLacc_id = new JLabel("Acc. ID");
		  JLabel CLacc_name = new JLabel("Name");
		  JLabel CLstock = new JLabel("Stock");
		  JButton CBoutOfStock = new JButton("Check");
		  
		  CLacc_id.setBounds(100,100,80,30);
		  CLacc_name.setBounds(200,100,80,30);
		  CLstock.setBounds(350,100,80,30);
		  
		  CLoutOfStock.setBounds(100, 30, 200, 35);
		  CBoutOfStock.setBounds(380,30,100,30);
		  
		  JTextArea CstockInfo = new JTextArea(20,60);
		  CstockInfo.setBounds(100,140,400,400);
		  CstockInfo.setEditable(false);
		  
		  CBoutOfStock.addActionListener(new ActionListener() // Check stock
		  {
			  public void actionPerformed(ActionEvent ae)
			  {
				  try 
				  {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/car", "root", "abhi");
					Statement stmt3 = conn.createStatement();
					
					String checkStockQuery = "SELECT `acc_id`, `acc_name`, `stock` FROM car.car_table where `stock`=0;";
					ResultSet rs=stmt3.executeQuery(checkStockQuery);
					
					
					String rows="";
					while(rs.next())
					{
						rows= rows+rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\n";
					}
					
					CstockInfo.append(rows);
				  }
				  catch (SQLException e){e.printStackTrace();}
			  }
		  });
		  
		  p3.add(CLacc_id);
		  p3.add(CLacc_name);
		  p3.add(CLstock);
		  
		  p3.add(CLoutOfStock);
		  p3.add(CBoutOfStock);
		  p3.add(CstockInfo);
//	 		************* Check Stock End ***************
		  
		  
		  form.add(top);									// Adding Title(Top Panel)
		  form.add(ver);									// Adding left hand side panel
		  form.add(jtp);									// Adding tabbed pane
		  form.setLayout(null);
		  form.setSize(1366,768);
		  
		  
		  
		  //        ************ Logout start ****************
		  Blogout.addActionListener(new ActionListener() 
		  {
			  public void actionPerformed(ActionEvent ae)
			  {
				  form.setVisible(false);
			  }
		  });
		  //        ************ Logout End   ****************
		  
		  
		  //        ************ Login Form start ****************
		  JPanel LoginPanel = new JPanel();
		  LoginPanel.setLayout(null);
		  LoginPanel.setBounds(200,100,450,500);
		  
		  JLabel LLuser, LLpass, LLalert; 
		  JTextField LTuser;
		  
		  LLuser = new JLabel("Enter Username: ");
		  LLpass = new JLabel("Enter Password: ");
		  LLalert = new JLabel("");
		  
		  LTuser = new JTextField();
		  JPasswordField LTpass = new JPasswordField();
		  
		  LLuser.setBounds(50,140,150,25);
		  LLpass.setBounds(50,200,150,25);
		  LTuser.setBounds(180,140,250,25);
		  LTpass.setBounds(180,200,250,25);
		  
		  LoginPanel.add(LLuser);
		  LoginPanel.add(LLpass);
		  LoginPanel.add(LTuser);
		  LoginPanel.add(LTpass);
		  
		  JButton LBlogin = new JButton("Login");
		  LBlogin.setBounds(50,280,150,30);
		  LLalert.setBounds(50,330,300,30);
		  LoginPanel.add(LBlogin);
		  LoginPanel.add(LLalert);
		  
		  LBlogin.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent ae)
			  {
				  String username = LTuser.getText();
				  String password = LTpass.getText();
				  if(username.equals("GPK") && password.equals("gpk@123"))
				  {
					  form.setVisible(true);
					  login.setVisible(false);
				  }
				  else
				  {
					  LLalert.setText("Enter correct user name and password!");
				  }
			  }
		  });
		  LoginPanel.setBackground(Color.gray);
		  login.add(LoginPanel);
		  login.setLayout(null);
		  login.setSize(1366,768);
		  login.setVisible(true);
		  //        ************ Login Form End ****************
	}
	public static void main(String args[])
	{
		Project p = new Project();
		System.out.println("Program is executed successfully.");
	}
}
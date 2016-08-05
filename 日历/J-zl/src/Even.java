import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class Even {
	JFrame jf= new JFrame("记事本");
	JTabbedPane jtp=new JTabbedPane();
	JLabel jl1,jl2,jl3,jl4;
	JPanel jp1,jp2,jp3,jp4,jp5,jp6;
	JButton bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9;
	JPasswordField k1;
	JTextArea sj1,sj2,sj3,sj4;
	int k=0;
	Boolean sf=false;
	Even(final String x,final String y,final String z,final String a){
		
		
	
		jf.setBounds(1100,400,615, 420);
		Container ct1=jf.getContentPane();
		
		
		
		sj1=new JTextArea(11,53);
		sj1.setFont(new Font("宋体", Font.PLAIN, 22));
		sj2=new JTextArea(11,53);
		sj2.setFont(new Font("宋体", Font.PLAIN, 22));
		sj3=new JTextArea(11,53);
		sj3.setFont(new Font("宋体", Font.PLAIN, 22));
		sj4=new JTextArea(11,52);
		sj4.setFont(new Font("宋体", Font.PLAIN, 22));
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		jp5=new JPanel();
		jp6=new JPanel();
		
		 Statement sm=null;
		 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=text";
		  String userName="sa";
		  String userPwd="4444";
		  Connection ct = null;
		  try
		  {
		   Class.forName(driverName);
		   ct=DriverManager.getConnection(dbURL,userName,userPwd);
		    sm=ct.createStatement();
		    ResultSet rs=sm.executeQuery("select*from evens where nian='"+x+"' and yue='"+y+"' and ri='"+z+"'");
		    while(rs.next()){
		    	String s2=rs.getString(8);
		    	if(s2.equals(""+1)){
		    		String s1=rs.getString(7);
		    		sj1.setText(s1);
		    	}else if(s2.equals(""+2)){
		    		String s1=rs.getString(7);
			    	sj2.setText(s1);
		    	}else if(s2.equals(""+3)){
		    		String s1=rs.getString(7);
			    	sj3.setText(s1);
		    	}
		    }
		  }
		  catch(Exception e1)
		  {
		   e1.printStackTrace();
		  }    
		  finally{
			  
				  try {
					  if(sm!=null){
					sm.close();
					  }
					  if(ct!=null){
						  ct.close();
					  }
				} catch (SQLException e2){
					// TODO 自动生成的 catch 块
					e2.printStackTrace();
				}
		  }
		
		
		
		
		bt1=new JButton("登录");
		bt1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				 java.sql.Statement sm=null;
				 Connection ct = null;
				 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
				  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=text";
				  String userName="sa";
				  String userPwd="4444";
					  try
					  {
						  Class.forName(driverName);
						    ct=DriverManager.getConnection(dbURL,userName,userPwd);
						    sm=ct.createStatement();
						    String st=new String(k1.getPassword());
						    ResultSet rs=sm.executeQuery("select*from evens where jm='"+st+"'and nian='"+x+"'and yue='"+y+"'and ri='"+z+"'" );
						    if(rs.next()){
						    	sf=true;
						    	String st3=rs.getString(7);
						    	sj4.setText(st3);
						    }else{
						    		k=k+1;
						    		sj4.setText("账号或者密码错误");
						    		if(k==3){
						    			jf.setVisible(false);
						    		} 
						    }
					  }
				  catch(Exception e1)
				  {
				   e1.printStackTrace();
				  }    
				  finally{
					  
						  try {
							  if(sm!=null){
							sm.close();
							  }
							  if(ct!=null){
								  ct.close();
							  }
						} catch (SQLException e2){
							// TODO 自动生成的 catch 块
							e2.printStackTrace();
						}
				  }
			}
		});
		bt2=new JButton("删除");
		bt2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				 Statement sm=null;
				 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
				  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=text";
				  String userName="sa";
				  String userPwd="4444";
				  Connection ct = null;
				  try
				  {
					  if(sf){
				   Class.forName(driverName);
				   ct=DriverManager.getConnection(dbURL,userName,userPwd);
				    sm=ct.createStatement();
				    int i1=sm.executeUpdate("delete from evens where nian='"+x+"'and yue='"+y+"'and ri='"+z+"'and cd='4'");
				    sj4.setText("删除成功");
					  }
				  }
				  catch(Exception e1)
				  {
				   e1.printStackTrace();
				  }    
				  finally{
					  
						  try {
							  if(sm!=null){
							sm.close();
							  }
							  if(ct!=null){
								  ct.close();
							  }
						} catch (SQLException e2){
							// TODO 自动生成的 catch 块
							e2.printStackTrace();
						}
				  }
			}
		});
		bt3=new JButton("添加");
		bt3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				 Statement sm=null;
				 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
				  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=text";
				  String userName="sa";
				  String userPwd="4444";
				  Connection ct = null;
				  String cd2;
				  try
				  {
					 
				   Class.forName(driverName);
				   ct=DriverManager.getConnection(dbURL,userName,userPwd);
				    sm=ct.createStatement();
				    String s3=sj4.getText();
				    String st1=new String(k1.getPassword());
				    int i1=sm.executeUpdate("delete from evens where nian='"+x+"'and yue='"+y+"'and ri='"+z+"'and cd='1'and jm='"+st1+"'");
				    int i=sm.executeUpdate("insert into evens values('x','x','"+x+"','"+y+"','"+z+"','"+a+"','"+s3+"','4','"+st1+"')");
				    sj4.setText("添加成功");
					  
				  }
				  catch(Exception e1)
				  {
				   e1.printStackTrace();
				  }    
				  finally{
					  
						  try {
							  if(sm!=null){
							sm.close();
							  }
							  if(ct!=null){
								  ct.close();
							  }
						} catch (SQLException e2){
							// TODO 自动生成的 catch 块
							e2.printStackTrace();
						}
				  }
			}
		});
		
		
		bt4=new JButton("删除");
		bt4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				 Statement sm=null;
				 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
				  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=text";
				  String userName="sa";
				  String userPwd="4444";
				  Connection ct = null;
				  try
				  {
				   Class.forName(driverName);
				   ct=DriverManager.getConnection(dbURL,userName,userPwd);
				    sm=ct.createStatement();
				    int i1=sm.executeUpdate("delete from evens where nian='"+x+"'and yue='"+y+"'and ri='"+z+"'and cd='1'");
				    sj1.setText("删除成功");
				  }
				  catch(Exception e1)
				  {
				   e1.printStackTrace();
				  }    
				  finally{
					  
						  try {
							  if(sm!=null){
							sm.close();
							  }
							  if(ct!=null){
								  ct.close();
							  }
						} catch (SQLException e2){
							// TODO 自动生成的 catch 块
							e2.printStackTrace();
						}
				  }
			}
		});
		bt5=new JButton("添加");
		bt5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				 Statement sm=null;
				 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
				  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=text";
				  String userName="sa";
				  String userPwd="4444";
				  Connection ct = null;
				  String cd2;
				  try
				  {
				   Class.forName(driverName);
				   ct=DriverManager.getConnection(dbURL,userName,userPwd);
				    sm=ct.createStatement();
				    String s3=sj1.getText();
				    int i1=sm.executeUpdate("delete from evens where nian='"+x+"'and yue='"+y+"'and ri='"+z+"'and cd='1'");
				    int i=sm.executeUpdate("insert into evens values('x','x','"+x+"','"+y+"','"+z+"','"+a+"','"+s3+"','1','x')");
				    sj1.setText("添加成功");
				  }
				  catch(Exception e1)
				  {
				   e1.printStackTrace();
				  }    
				  finally{
					  
						  try {
							  if(sm!=null){
							sm.close();
							  }
							  if(ct!=null){
								  ct.close();
							  }
						} catch (SQLException e2){
							// TODO 自动生成的 catch 块
							e2.printStackTrace();
						}
				  }
			}
		});
		bt6=new JButton("删除");
		bt6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				 Statement sm=null;
				 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
				  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=text";
				  String userName="sa";
				  String userPwd="4444";
				  Connection ct = null;
				  try
				  {
				   Class.forName(driverName);
				   ct=DriverManager.getConnection(dbURL,userName,userPwd);
				    sm=ct.createStatement();
				    int i1=sm.executeUpdate("delete from evens where nian='"+x+"'and yue='"+y+"'and ri='"+z+"'and cd='2'");
				    sj2.setText("删除成功");
				  }
				  catch(Exception e1)
				  {
				   e1.printStackTrace();
				  }    
				  finally{
					  
						  try {
							  if(sm!=null){
							sm.close();
							  }
							  if(ct!=null){
								  ct.close();
							  }
						} catch (SQLException e2){
							// TODO 自动生成的 catch 块
							e2.printStackTrace();
						}
				  }
			}
		});
		bt7=new JButton("添加");
		bt7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				 Statement sm=null;
				 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
				  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=text";
				  String userName="sa";
				  String userPwd="4444";
				  Connection ct = null;
				  String cd2;
				  try
				  {
				   Class.forName(driverName);
				   ct=DriverManager.getConnection(dbURL,userName,userPwd);
				    sm=ct.createStatement();
				    String s3=sj2.getText();
				    int i1=sm.executeUpdate("delete from evens where nian='"+x+"'and yue='"+y+"'and ri='"+z+"'and cd='2'");
				    int i=sm.executeUpdate("insert into evens values('x','x','"+x+"','"+y+"','"+z+"','"+a+"','"+s3+"','2','x')");
				    sj2.setText("添加成功");
				  }
				  catch(Exception e1)
				  {
				   e1.printStackTrace();
				  }    
				  finally{
					  
						  try {
							  if(sm!=null){
							sm.close();
							  }
							  if(ct!=null){
								  ct.close();
							  }
						} catch (SQLException e2){
							// TODO 自动生成的 catch 块
							e2.printStackTrace();
						}
				  }
			}
		});
		
		bt8=new JButton("删除");
		bt8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				 Statement sm=null;
				 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
				  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=text";
				  String userName="sa";
				  String userPwd="4444";
				  Connection ct = null;
				  try
				  {
				   Class.forName(driverName);
				   ct=DriverManager.getConnection(dbURL,userName,userPwd);
				    sm=ct.createStatement();
				    int i1=sm.executeUpdate("delete from evens where nian='"+x+"'and yue='"+y+"'and ri='"+z+"'and cd='3'");
				    sj3.setText("删除成功");
				  }
				  catch(Exception e1)
				  {
				   e1.printStackTrace();
				  }    
				  finally{
					  
						  try {
							  if(sm!=null){
							sm.close();
							  }
							  if(ct!=null){
								  ct.close();
							  }
						} catch (SQLException e2){
							// TODO 自动生成的 catch 块
							e2.printStackTrace();
						}
				  }
			}
		});
		bt9=new JButton("添加");
		bt9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				 Statement sm=null;
				 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
				  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=text";
				  String userName="sa";
				  String userPwd="4444";
				  Connection ct = null;
				  String cd2;
				  try
				  {
				   Class.forName(driverName);
				   ct=DriverManager.getConnection(dbURL,userName,userPwd);
				    sm=ct.createStatement();
				    String s3=sj3.getText();
				    int i1=sm.executeUpdate("delete from evens where nian='"+x+"'and yue='"+y+"'and ri='"+z+"'and cd='3'");
				    int i=sm.executeUpdate("insert into evens values('x','x','"+x+"','"+y+"','"+z+"','"+a+"','"+s3+"','3','x')");
				    sj3.setText("添加成功");
				  }
				  catch(Exception e1)
				  {
				   e1.printStackTrace();
				  }    
				  finally{
					  
						  try {
							  if(sm!=null){
							sm.close();
							  }
							  if(ct!=null){
								  ct.close();
							  }
						} catch (SQLException e2){
							// TODO 自动生成的 catch 块
							e2.printStackTrace();
						}
				  }
			}
		});
		
		
		bt1.setBackground(Color.white);
		bt2.setBackground(Color.white);
		bt3.setBackground(Color.white);
		bt4.setBackground(Color.white);
		bt5.setBackground(Color.white);
		bt6.setBackground(Color.white);
		bt7.setBackground(Color.white);
		bt8.setBackground(Color.white);
		bt9.setBackground(Color.white);
		bt1.setSize(200, 200);
		k1=new JPasswordField(10);
		
		
		
		jp1.add(sj1);
		jp1.add(bt4);
		jp1.add(bt5);
		jp2.add(sj2);
		jp2.add(bt6);
		jp2.add(bt7);
		jp3.add(sj3);
		jp3.add(bt8);
		jp3.add(bt9);
		jp4.add(jp5);
		jp4.add(jp6);
		jp5.add(sj4,BorderLayout.CENTER);
		jp6.add(bt2);
		jp6.add(bt3);
		jp6.add(k1);
		jp6.add(bt1,BorderLayout.CENTER);
		jp6.setBackground(Color.gray);
		jp1.setBackground(Color.red);
		jp2.setBackground(Color.green);
		jp3.setBackground(Color.magenta);
		jp4.setBackground(Color.gray);
		jtp.addTab("紧急", null,jp1,"紧急的事件");
		jtp.addTab("学习", null,jp2,"学习的事");
		jtp.addTab("生活", null,jp3,"记录生活的点点滴滴");
		jtp.addTab("加密", null,jp4,"不能说的秘密");
		jtp.setBackground(Color.white);
		ct1.add(jtp);
		jf.setVisible(true);
		
		
		
		
	}
}

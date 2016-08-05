import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Loading {
	JButton bton1;
	JButton bton2;
	String st4,st5;
	JTextField p2;
	JPanel jp1;
	int i=0;
	JPasswordField p1;
	JFrame jm=new JFrame();
	Loading(){
		bton1=new JButton("确定");
		bton1.setBounds(220, 320, 90, 38);
		bton1.setBackground(Color.white);
		bton2=new JButton("注册、修改密码");
		bton2.setBounds(400,320, 90, 38);
		bton2.setBackground(Color.white);
		bton1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				bton1.setBackground(Color.decode(Integer.valueOf("ff9999",16).toString()));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				bton1.setBackground(Color.decode(Integer.valueOf("d64fd6",16).toString()));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				bton1.setBackground(Color.decode(Integer.valueOf("ffffff",16).toString()));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				bton1.setBackground(Color.decode(Integer.valueOf("cc6699",16).toString()));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				 java.sql.Statement sm=null;
				 Connection ct = null;
				 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
				  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=text";
				  String userName="sa";
				  String userPwd="4444";
				  bton1.setBackground(Color.decode(Integer.valueOf("99ff00",16).toString()));
					  try
					  {
						  Class.forName(driverName);
						    ct=DriverManager.getConnection(dbURL,userName,userPwd);
						    sm=ct.createStatement();
						    st4=p2.getText();
						    st5=new String(p1.getPassword());
						    ResultSet rs=sm.executeQuery("select*from evens where zang='"+st4+"'and mi='"+st5+"'" );
						    if(rs.next()){
						    	C1 c1=new C1();
						    	jm.setVisible(false);
						    }else{
						    		i=i+1;
						    		p2.setText("账号或者密码错误");
						    		if(i==3){
						    			jm.setVisible(false);
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
		bton2.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				bton2.setBackground(Color.decode(Integer.valueOf("ff9999",16).toString()));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				bton2.setBackground(Color.decode(Integer.valueOf("d64fd6",16).toString()));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				bton2.setBackground(Color.decode(Integer.valueOf("ffffff",16).toString()));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				bton2.setBackground(Color.decode(Integer.valueOf("cc6699",16).toString()));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				Exc ex=new Exc();  
				bton2.setBackground(Color.decode(Integer.valueOf("ff0000",16).toString()));
			}
		});
		JLabel j2=new JLabel("账号");
		
		
		JLabel j1=new JLabel("密码");
		JLabel jl=new JLabel("日历登陆");
		JPanel f1=new JPanel();
		JPanel f2=new JPanel();
		f1.setLayout(new FlowLayout());
		f2.setLayout(new FlowLayout());
		p1=new JPasswordField(12);
		
		p1.setEchoChar('x');
		p2=new JTextField(12);
		p2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO 自动生成的方法存根
				p2.setText("");
			}
		});
		f1.add(j2);
		f1.add(p2);
		f2.add(j1);
		f2.add(p1);
		j2.setBounds(200,120,250,40);
		j1.setBounds(200,200,250,40);
		p2.setBounds(240,120,250,40);
		p1.setBounds(240,200,250,40);
		jm.add(p1);
		jm.add(p2);
		jm.add(jl);
		jm.add(j1);
		jm.add(j2);
		jm.add(bton1);
		jm.add(bton2);
		
		JLabel lbBg;
		lbBg = new JLabel(new ImageIcon("x.PNG"));
		lbBg.setBounds(0, 0, 800, 500);
		jm.add(lbBg);
		jl.setFont(new Font("宋体", Font.PLAIN, 22));
		j2.setFont(new Font("宋体", Font.PLAIN, 18));
		j1.setFont(new Font("宋体", Font.PLAIN, 18));
		jl.setBounds(315, 20, 400, 90);
		jm.setBounds(300, 300, 740, 450);
		jm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jm.setLayout(null);
		jm.setVisible(true);
		
	}
}
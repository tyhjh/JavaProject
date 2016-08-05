import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;


public class Jm3{
	JFrame jf9=new JFrame();
	JButton jb1=new JButton();
	JButton jb2=new JButton();
	JButton jb3=new JButton();
	JLabel jl1=new JLabel();
	JLabel jl2=new JLabel();
	JTextField jf1;
	JPasswordField jp1;
	JCheckBox ck1;
	JCheckBox ck2;
	String st4,st5;
	int k=0;
	
	Jm3(){
		
		JPanel pnl=new JPanel();
		jf9.add(pnl);
		Icon i=new ImageIcon("p5.png");
		jl1 = new JLabel(i);
		jf9.getLayeredPane().add(jl1,new Integer(Integer.MIN_VALUE));
		jl1.setBounds(0, 0, 540,420);
		
		ck1=new JCheckBox();
		ck2=new JCheckBox();
		ck1.setText("记住密码");
		ck2.setText("自动登录");
		ck1.setForeground(Color.gray);
		ck2.setForeground(Color.gray);
		
		ck1.setBounds(180, 329,100, 23);
		ck2.setBounds(295, 329,100, 23);
		ck1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				
				
			}
		});
		
		
		jf1=new JTextField();
		jp1=new JPasswordField();
		jf1.setBounds(180,245,240,37);
		jp1.setBounds(180,285,240,37);
		
		jb1=new JButton("注册账号");
		Color c=new Color(0,0,255);
		jb1.setBackground(c);
		jb1.setOpaque(false); 
		jb1.setBorder(null);
		jb1.setBounds(410,259,120,37);
		jb2=new JButton("修改密码");
		jb2.setBounds(410,299,120,37);
		jb2.setBackground(c);
		jb2.setOpaque(false); 
		jb2.setBorder(null);
		jb1.setFont(new Font("黑体", Font.PLAIN, 16));
		jb2.setFont(new Font("黑体", Font.PLAIN, 16));
		jb1.setForeground(Color.decode(Integer.valueOf("3399ff",16).toString()));
		jb2.setForeground(Color.decode(Integer.valueOf("3399ff",16).toString()));
		
		Icon p=new ImageIcon("p3.PNG");
		jb3=new JButton(p);
		jb3.setBorder(null);
		jb3.setBounds(180,359,240,37);
jb3.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				jb3.setBackground(Color.decode(Integer.valueOf("ff9999",16).toString()));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				jb3.setBackground(Color.decode(Integer.valueOf("d64fd6",16).toString()));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				jb3.setBackground(Color.decode(Integer.valueOf("ffffff",16).toString()));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				jb3.setBackground(Color.decode(Integer.valueOf("cc6699",16).toString()));
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
				  jb3.setBackground(Color.decode(Integer.valueOf("99ff00",16).toString()));
					  try
					  {
						  Class.forName(driverName);
						    ct=DriverManager.getConnection(dbURL,userName,userPwd);
						    sm=ct.createStatement();
						    st4=jf1.getText();
						    st5=new String(jp1.getPassword());
						    ResultSet rs=sm.executeQuery("select*from evens where zang='"+st4+"'and mi='"+st5+"'" );
						    if(rs.next()){
						    	C1 c1=new C1();
						    	jf9.setVisible(false);
						    }else{
						    		k=k+1;
						    		jf1.setText("账号或者密码错误");
						    		if(k==3){
						    			jf9.setVisible(false);
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
jb1.addMouseListener(new MouseListener() {
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		jb1.setBackground(Color.decode(Integer.valueOf("ff9999",16).toString()));
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		jb1.setBackground(Color.decode(Integer.valueOf("d64fd6",16).toString()));
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		jb1.setBackground(Color.decode(Integer.valueOf("ffffff",16).toString()));
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		jb1.setBackground(Color.decode(Integer.valueOf("cc6699",16).toString()));
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		Exc ex=new Exc();  
		jb1.setBackground(Color.decode(Integer.valueOf("ff0000",16).toString()));
	}
});
jb2.addMouseListener(new MouseListener() {
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		jb2.setBackground(Color.decode(Integer.valueOf("ff9999",16).toString()));
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		jb2.setBackground(Color.decode(Integer.valueOf("d64fd6",16).toString()));
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		jb2.setBackground(Color.decode(Integer.valueOf("ffffff",16).toString()));
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		jb2.setBackground(Color.decode(Integer.valueOf("cc6699",16).toString()));
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		Exc2 ex=new Exc2();  
		jb2.setBackground(Color.decode(Integer.valueOf("ff0000",16).toString()));
	}
});
jf1.addFocusListener(new FocusListener() {
	
	@Override
	public void focusLost(FocusEvent e) {
		// TODO 自动生成的方法存根
		
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		// TODO 自动生成的方法存根
		jf1.setText("");
	}
});
		jf9.add(jp1);
		jf9.add(jf1);
		jf9.add(jb1);
		jf9.add(jb2);
		jf9.add(jb3);
		jf9.setLayout(null);
		jf9.add(ck1);
		jf9.add(ck2);
		jf9.add(jl1);
		jf9.setBounds(500,330,540,420);
		jf9.setUndecorated(true);
		jf9.setVisible(true);
	}
	
}

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Exc {
	String st3="";
	String st2="";
	String st1="";
	JTextField p3;
	JPasswordField p1;
	JPasswordField p2;
	JFrame jm1=new JFrame();
	Exc(){
		JButton bton1=new JButton("ע��");
		JButton bton2=new JButton("�˳�");
		bton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				 Statement sm=null;
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
						    st3=p3.getText();
							 st1=new String(p1.getPassword());
							 st2=new String(p2.getPassword());
						    ResultSet rs=sm.executeQuery("select*from evens where zang='"+st3+"'" );
						    if(rs.next()){
						    	p3.setText("�˺��Ѵ���");
						    }else{
						    	if(st1.equals(st2)&&!st1.equals("")){
						    	int i=sm.executeUpdate("insert into evens values('"+st3+"','"+st2+"','x','x','x','x','x','x','q2e3f5h6k7')");
						    	jm1.setVisible(false);
						    }else if(st1.equals("")||st3.equals("")){
						    	p3.setText("������˺Ų���Ϊ��");
						    	}else{
						    	p3.setText("������������벻һ��");
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
							// TODO �Զ����ɵ� catch ��
							e2.printStackTrace();
						}
				  }
			}
		});
		bton1.setBounds(255, 340, 80, 34);
		bton1.setBackground(Color.white);
		bton2.setBounds(445, 340, 80, 34);
		bton2.setBackground(Color.white);
		bton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				jm1.setVisible(false);
			}
		});
		JLabel j2=new JLabel("�����˺�");
		JLabel j1=new JLabel("��������");
		JLabel j3=new JLabel("�ٴ���������");
		JLabel jl=new JLabel("ע�������˺�");
		jl.setFont(new Font("����", Font.PLAIN, 22));
		JPanel f1=new JPanel();
		JPanel f2=new JPanel();
		JPanel f3=new JPanel();
		f1.setLayout(new FlowLayout());
		f3.setLayout(new FlowLayout());
		f2.setLayout(new FlowLayout());
		p1=new JPasswordField(12);
		p2=new JPasswordField(12);
		p1.setEchoChar('x');
		p2.setEchoChar('x');
		p3=new JTextField(12);
		p3.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO �Զ����ɵķ������
				p3.setText("");
			}
		});
		
		jm1.add(p3);
		jm1.add(p1);
		jm1.add(p2);
		p3.setBounds(260,130,300,40);
		j2.setBounds(160,130,300,40);
		p2.setBounds(260,190,300,40);
		j1.setBounds(160,190,300,40);
		p1.setBounds(260,250,300,40);
		j3.setBounds(122,250,300,40);
		j1.setFont(new Font("����", Font.PLAIN, 22));
		j2.setFont(new Font("����", Font.PLAIN, 22));
		j3.setFont(new Font("����", Font.PLAIN, 22));
		jm1.add(j3);
		jm1.add(j2);
		jm1.add(j1);
		jm1.add(jl);
		
		jm1.add(bton1);
		jm1.add(bton2);
		jl.setBounds(300, 20, 400, 90);
		jm1.setBounds(300, 300, 740, 450);
	
		JLabel lbBg;
		lbBg = new JLabel(new ImageIcon("x.PNG"));
		lbBg.setBounds(0, 0, 800, 500);
		jm1.add(lbBg);
		jm1.setLayout(null);
		jm1.setVisible(true);
	}
}

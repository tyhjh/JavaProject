import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class C1 extends JFrame implements ActionListener{
	String str15;
	String str14;
	String str10;
	String str11;
	String str12;
	String  cd3;
	String str13;
	JPanel jm1;
	JPanel jm2;
	JLabel jm3;
	JButton jb1;
	JButton jb2;
	JButton jb3;
	JButton[] bt=new JButton[39];
	JLabel[] lb=new JLabel[7];
	JLabel l1,l2,l3,l4;
	JTextField year;
	JComboBox mouth;
	JButton bt1,bt2;
	int days=0;
	 int ys;
	 int ms;
	int allyear;
	int allDay=0;
	int xiqi;
	//  
	public void jisuan(){
		
		days=0;
		allDay=0;
		int k=1;
		for(int i=1;i<39;i++)
		{
			bt[i].setText("");
		}
		// TODO 自动生成的方法存根
		String nian=year.getText();
		ys=Integer.parseInt(nian);
		String yue=(String) mouth.getSelectedItem();
		ms=Integer.parseInt(yue);
		
		Daysxiqi xq=new Daysxiqi(ys,ms);
		xiqi=xq.getXiqi();
		days=xq.getDays();
			//显示日历
			for(int i=xiqi;i<days+xiqi;i++){
				bt[i].setText(""+k);
				k=k+1;
			}
	}
	//构造方法、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
	C1(){
		jm1=new JPanel();
		jm2=new JPanel();
		jm3=new JLabel();
		
		l1=new JLabel("年份");
		l2=new JLabel("月份");
		l3=new JLabel("      ");
		l4=new JLabel("      ");
		for(int i=0;i<39;i++){
			bt[i]=new JButton();
		}
			
			for(int l=1;l<39;l++){
				bt[l].setForeground(Color.decode(Integer.valueOf("993366",16).toString()));
			}
			lb[0]=new JLabel("星期一");
			lb[1]=new JLabel("星期二");
			lb[2]=new JLabel("星期三");
			lb[3]=new JLabel("星期四");
			lb[4]=new JLabel("星期五");
			lb[5]=new JLabel("星期六");
			lb[6]=new JLabel("星期日");
		
		year=new JTextField(4);
		String[] mouths={"1","2","3","4","5","6","7","8","9","10","11","12"};
		
		
		
		
		
		
		
		
		mouth=new JComboBox(mouths);
		mouth.setForeground(Color.decode(Integer.valueOf("663366",16).toString()));
		year.setForeground(Color.decode(Integer.valueOf("663366",16).toString()));
		year.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO 自动生成的方法存根
				jisuan();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO 自动生成的方法存根
				
			}
		});
		
		jm1.setLayout(new FlowLayout());
		jm1.setBounds(40, 0, 300, 40);
		jm2.setLayout(new GridLayout(0,7));
		jm2.setBounds(0, 80, 600, 100);
		l2.setBounds(370,5,40,20);
		this.add(l2);
		mouth.setBounds(405,5,40,20);
		this.add(mouth);
		jm1.add(l1);
		jm1.add(year);
		jm1.add(l3);
		jm1.add(l4);
		jm3.setBounds( 0,0, 835, 410);
		jm3.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根，按鼠标
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根，点击鼠标
				jisuan();
				for(int t=1;t<38;t++){bt[t].setBackground(Color.white);}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根，鼠标离开
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根,鼠标移到这里
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根，放开鼠标
				
			}
		});
		bt[0].setBackground(Color.green);
		for(int i=0;i<7;i++){
			jm2.add(lb[i]);
		}
		for(int j=1;j<38;j++){
			jm2.add(bt[j]);
			bt[j].setBackground(Color.white);
		}
		jm2.setSize(600,300);
		//设置点击事件、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
		for(int i=0;i<38;i++){
			bt[i].addActionListener(this);
			bt[i].setActionCommand(""+i);
		}
		this.getContentPane().setLayout(null);
		this.add(jm1);
		this.add(jm2);
		this.add(jm3);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(615, 410);
		this.setLocation(400,400);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String mouth1=df.format(new Date()).substring(5, 7);
		String year1=df.format(new Date()).substring(0, 4);
		str14=df.format(new Date()).substring(8, 10);
		str15=df.format(new Date()).substring(11, 13);
		System.out.println(df.format(new Date()));
		ys=Integer.parseInt(year1);
		ms=Integer.parseInt(mouth1);
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
		    ResultSet rs=sm.executeQuery("select*from evens where nian='"+ys+"' and yue='"+ms+"' and ri='"+str14+"'");
		    System.out.println(year1+mouth1+str14);
		    while(rs.next()){
		    	Even o=new Even(""+ys,""+ms,str14,str14);
		    	System.out.println("fuck");
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
		
		
		Daysxiqi dx=new Daysxiqi(ys,ms);
		xiqi=dx.getXiqi();
		days=dx.getDays();
			//显示日历
			int k=1;
			for(int i=xiqi;i<days+xiqi;i++){
				bt[i].setText(""+k);
				k=k+1;
				year.setText(""+ys);
				mouth.setSelectedItem(""+ms);
			}
	}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO 自动生成的方法存根
	for(int i=1;i<38;i++){
		if(e.getActionCommand().equals(""+i)){
			for(int t=1;t<38;t++){bt[t].setBackground(Color.white);} 
			
			  str12=bt[i].getText();
					if(!str12.equals("")){
						bt[i].setBackground(Color.decode(Integer.valueOf("00cccc",16).toString()));
					  str10=year.getText();
					  str11=(String) mouth.getSelectedItem();
					  Even ev=new Even(str10,str11,str12,str14);
				    
					}
		}
	}
	
}
}
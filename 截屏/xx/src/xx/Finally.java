package xx;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Finally {
	JFrame j1=new JFrame("截图内容识别");
	
	JTextArea ta=new JTextArea();
	Finally(String x,
			String y,
			String s1,
			String s2,
			String s3,
			String s4,
			String s5,
			String s6,
			String s7){
		System.out.println(s7);
		 
		 Statement sm=null;
		 ta.setBounds(0,0,300,300);
		 ta.setText("图片名称"+" "+y+" "+"stratX"+" "+s1+" "+"startY"+" "+s2+""+"overX"+" "+s3+""+"overY"+" "+s4+" "+"X"+" "+s5+" "+"Y"+" "+s6+" "+x+"\n");
			j1.add(ta);
		j1.setBounds(400,200,1000,700);
		j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j1.setVisible(true);
		
	}
}

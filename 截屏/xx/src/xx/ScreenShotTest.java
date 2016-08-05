package xx;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.tools.Tool;
public class ScreenShotTest {
	static boolean b1=false;
	JFrame jf=new JFrame("��ͼ����");
	JButton bt1,bt2,bt3;
	ScreenShotTest(){
		bt1=new JButton("�½�");
		bt2=new JButton("ȡ��");
		bt1.setFont(new Font("����", Font.PLAIN, 20));
		bt2.setFont(new Font("����", Font.PLAIN, 20));
		bt1.setBounds(0,0,100,35);
		bt1.setBackground(Color.WHITE);
		bt2.setBackground(Color.WHITE);
		bt2.setBounds(100,0,100,35);
		bt1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				jf.setVisible(false);
				b1=true;
				EventQueue.invokeLater(new Runnable() { 
					   @Override
					   public void run() {
					    try {
					    	ScreenShotWindow ssw=new ScreenShotWindow();
					        ssw.setVisible(true);	
					     
					    } catch (AWTException e) {
					     e.printStackTrace();
					    }
					   }
					   
					  });

			}
		});
		bt2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				jf.dispose();
				 System.exit(0);
			}
		});
		jf.add(bt1);
		jf.add(bt2);
		jf.setLayout(null);
		jf.setBounds(200,200,200,80);
		jf.setVisible(true);
	}

}



/*
 * ��ͼ����
 */
class ScreenShotWindow extends JWindow
{ 
	
 private int orgx, orgy, endx, endy,x,y,width,height;
    private BufferedImage image=null;
    private BufferedImage tempImage=null;
    private BufferedImage saveImage=null;
    private TextArea tx1;
    private ToolsWindow tools=null;
    String path;
 public ScreenShotWindow() throws AWTException{
   //��ȡ��Ļ�ߴ�
   Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
   this.setBounds(0, 0, d.width, d.height);

   //��ȡ��Ļ
   Robot robot = new Robot();
   image = robot.createScreenCapture(new Rectangle(0, 0, d.width,d.height));

   this.addMouseListener(new MouseAdapter() {
    @Override
   public void mousePressed(MouseEvent e) {
    //����ɿ�ʱ��¼���������꣬�����ز�������
             orgx = e.getX();
             orgy = e.getY();

             if(tools!=null){
              tools.setVisible(false);
              
              
             }
   }
   @Override
   public void mouseReleased(MouseEvent e) {
    //����ɿ�ʱ����ʾ��������
    if(tools==null){
     tools=new ToolsWindow(ScreenShotWindow.this,e.getX(),e.getY());
    }else{
     tools.setLocation(e.getX(),e.getY());
     tools.setSize(120,60);
    }
    tools.setVisible(true);
    tools.toFront();
    
   }
  });

   this.addMouseMotionListener(new MouseMotionAdapter() {

   @Override
   public void mouseDragged(MouseEvent e) {
    //����϶�ʱ����¼���겢�ػ洰��
                endx = e.getX();
                endy = e.getY();

                //��ʱͼ�����ڻ�����Ļ���������Ļ��˸
                Image tempImage2=createImage(ScreenShotWindow.this.getWidth(),ScreenShotWindow.this.getHeight());
                Graphics g =tempImage2.getGraphics();
                g.drawImage(tempImage, 0, 0, null);
                x = Math.min(orgx, endx);
                y = Math.min(orgy, endy);
                width = Math.abs(endx - orgx)+1;
                height = Math.abs(endy - orgy)+1;
                // ����1��ֹwidth��height0
                g.setColor(Color.BLUE);
                g.drawRect(x-1, y-1, width+1, height+1);
                //��1��1���˷�ֹͼƬ���ο򸲸ǵ�
                saveImage = image.getSubimage(x, y, width, height);
                g.drawImage(saveImage, x, y, null);

                ScreenShotWindow.this.getGraphics().drawImage(tempImage2,0,0,ScreenShotWindow.this);
   }
  });
 }

    @Override
    public void paint(Graphics g) {
        RescaleOp ro = new RescaleOp(0.8f, 0, null);
        tempImage = ro.filter(image, null);
        g.drawImage(tempImage, 0, 0, this);
    }
    //����ͼ���ļ�
 public void saveImage() throws IOException {
  JFileChooser jfc=new JFileChooser();
  jfc.setDialogTitle("����");

  //�ļ����������û����˿�ѡ���ļ�
  FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", "jpg");
  jfc.setFileFilter(filter);

  //��ʼ��һ��Ĭ���ļ������ļ������ɵ������ϣ�
  SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
     String fileName = sdf.format(new Date());
     File filePath = FileSystemView.getFileSystemView().getHomeDirectory();
     File defaultFile = new File(filePath + File.separator + fileName + ".jpg");
     jfc.setSelectedFile(defaultFile);

  int flag = jfc.showSaveDialog(this);
  if(flag==JFileChooser.APPROVE_OPTION){
   File file=jfc.getSelectedFile();
   path=file.getPath();
   String stri=path+"1.jpg";
   //����ļ���׺�������û����������׺�������벻��ȷ�ĺ�׺
   if(!(path.endsWith(".jpg")||path.endsWith(".JPG"))){
    path+=".jpg";
   }
   //д���ļ�
   ImageIO.write(saveImage,"jpg",new File(path));
   
   try {     
       String valCode = new OCR().recognizeText(new File(path), "jpg");     
       Finally f1=new Finally(valCode,""+path,""+orgx,""+orgy,""+endx,""+endy,""+width,""+height,stri);
       //���ݿ⡢����������������������������������������������������������������������������������������������������������
       Statement sm=null;
		 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=text";
		  String userName="sa";
		  String userPwd="4444";
		  Connection ct = null;
		  String cd2;
		  CutLittleNumber cut=new CutLittleNumber();     
		   cut.releaseSound(path,stri, 130);
		  try
		  {
		   String str1=path;
		   String str2=""+orgx;
		   String str3=""+orgy;
		   String str4=""+endx;
		   String str5=""+endy;
		   String str6=""+width;
		   String str7=""+height;
		   
		   Class.forName(driverName);
		   ct=DriverManager.getConnection(dbURL,userName,userPwd);
		    sm=ct.createStatement();
		    System.out.println("xxxxx");
		    int i=sm.executeUpdate("insert into Jietu values('"+str1+"','"+str2+"','"+str3+"','"+str4+"','"+str5+"','"+str6+"','"+str7+"','"+valCode+"')");
		 
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
   } catch (IOException e) {     
       e.printStackTrace(); 
       
   } catch (Exception e) {  
       e.printStackTrace();  
   }      
  }
 }
}
/*
 * ��������
 */
class ToolsWindow extends JWindow
{
 private ScreenShotWindow parent;

 public ToolsWindow(ScreenShotWindow parent,int x,int y) {
  this.parent=parent;
  this.init();
  this.setLocation(x, y);
  this.setSize(140, 50);
 
  this.setVisible(true);
 }

 private void init(){

  this.setLayout(new GridLayout());
  this.setSize(140, 50);
  
 
  //���水ť
  JButton saveButton=new JButton(new ImageIcon("p.png"));
  saveButton.setSize(70, 40);
  saveButton.setText("����");
  saveButton.setBackground(Color.white);
  saveButton.setFont(new Font("����", Font.PLAIN, 13));
  saveButton.addActionListener(new ActionListener() { 
   @Override
   public void actionPerformed(ActionEvent e) {
    try {
     parent.saveImage();
   
    } catch (IOException e1) {
     e1.printStackTrace();
    }
   }
  });
  this.add(saveButton);

  //�رհ�ť
  JButton closeButton=new JButton(new ImageIcon("p.png"));
  closeButton.setSize(70, 40);
  closeButton.setText("ȡ��");
  closeButton.setFont(new Font("����", Font.PLAIN, 13));
  closeButton.setBackground(Color.white);
  closeButton.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    ScreenShotTest t1=new ScreenShotTest();
   }
  });
 
  this.add(saveButton);
  this.add(closeButton);
 }
}
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
	JFrame jf=new JFrame("截图工具");
	JButton bt1,bt2,bt3;
	ScreenShotTest(){
		bt1=new JButton("新建");
		bt2=new JButton("取消");
		bt1.setFont(new Font("宋体", Font.PLAIN, 20));
		bt2.setFont(new Font("宋体", Font.PLAIN, 20));
		bt1.setBounds(0,0,100,35);
		bt1.setBackground(Color.WHITE);
		bt2.setBackground(Color.WHITE);
		bt2.setBounds(100,0,100,35);
		bt1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
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
				// TODO 自动生成的方法存根
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
 * 截图窗口
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
   //获取屏幕尺寸
   Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
   this.setBounds(0, 0, d.width, d.height);

   //截取屏幕
   Robot robot = new Robot();
   image = robot.createScreenCapture(new Rectangle(0, 0, d.width,d.height));

   this.addMouseListener(new MouseAdapter() {
    @Override
   public void mousePressed(MouseEvent e) {
    //鼠标松开时记录结束点坐标，并隐藏操作窗口
             orgx = e.getX();
             orgy = e.getY();

             if(tools!=null){
              tools.setVisible(false);
              
              
             }
   }
   @Override
   public void mouseReleased(MouseEvent e) {
    //鼠标松开时，显示操作窗口
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
    //鼠标拖动时，记录坐标并重绘窗口
                endx = e.getX();
                endy = e.getY();

                //临时图像，用于缓冲屏幕区域放置屏幕闪烁
                Image tempImage2=createImage(ScreenShotWindow.this.getWidth(),ScreenShotWindow.this.getHeight());
                Graphics g =tempImage2.getGraphics();
                g.drawImage(tempImage, 0, 0, null);
                x = Math.min(orgx, endx);
                y = Math.min(orgy, endy);
                width = Math.abs(endx - orgx)+1;
                height = Math.abs(endy - orgy)+1;
                // 加上1防止width或height0
                g.setColor(Color.BLUE);
                g.drawRect(x-1, y-1, width+1, height+1);
                //减1加1都了防止图片矩形框覆盖掉
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
    //保存图像到文件
 public void saveImage() throws IOException {
  JFileChooser jfc=new JFileChooser();
  jfc.setDialogTitle("保存");

  //文件过滤器，用户过滤可选择文件
  FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", "jpg");
  jfc.setFileFilter(filter);

  //初始化一个默认文件（此文件会生成到桌面上）
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
   //检查文件后缀，放置用户忘记输入后缀或者输入不正确的后缀
   if(!(path.endsWith(".jpg")||path.endsWith(".JPG"))){
    path+=".jpg";
   }
   //写入文件
   ImageIO.write(saveImage,"jpg",new File(path));
   
   try {     
       String valCode = new OCR().recognizeText(new File(path), "jpg");     
       Finally f1=new Finally(valCode,""+path,""+orgx,""+orgy,""+endx,""+endy,""+width,""+height,stri);
       //数据库、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
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
					// TODO 自动生成的 catch 块
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
 * 操作窗口
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
  
 
  //保存按钮
  JButton saveButton=new JButton(new ImageIcon("p.png"));
  saveButton.setSize(70, 40);
  saveButton.setText("保存");
  saveButton.setBackground(Color.white);
  saveButton.setFont(new Font("宋体", Font.PLAIN, 13));
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

  //关闭按钮
  JButton closeButton=new JButton(new ImageIcon("p.png"));
  closeButton.setSize(70, 40);
  closeButton.setText("取消");
  closeButton.setFont(new Font("宋体", Font.PLAIN, 13));
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
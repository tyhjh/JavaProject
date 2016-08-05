package xx;
 
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 
public class CutLittleNumber {
	//����
	public static void process(int[][]b,int hang,int lie){
		int count = 0;
		int piexnumber = 4;
		//��0����
		for(int i=0;i<hang;i++){
			for(int j=0;j<lie;j++)
			{
		
				if(b[i][j] == 0) count++;
				if(b[i][j] == 1) 
				{
					if(count <=piexnumber ) 
						for(int k=1;k<=count;k++)
							try{
								b[i][j-k] = 1;
							}catch (Exception e) {
								// TODO: handle exception
							}
							
					count =0;
				}
			}
		}
		count = 0;
		for(int j=0;j<lie;j++)
		{
			for(int i=0;i<hang;i++)
			{
		
				if(b[i][j] == 0) count++;
				if(b[i][j] == 1) 
				{
					if(count <=piexnumber ) 
						for(int k=1;k<=count;k++)
							try{
								b[i-k][j] = 1;
							}catch (Exception e) {
								// TODO: handle exception
							}
							
					count =0;
				}
			}
		}
		//��1����
		 count = 0;
		for(int i=0;i<hang;i++){
			for(int j=0;j<lie;j++)
			{
		
				if(b[i][j] == 1) count++;
				if(b[i][j] == 0) 
				{
					if(count <=piexnumber ) 
						for(int k=1;k<=count;k++)
							try{
								b[i][j-k] = 0;
							}catch (Exception e) {
								// TODO: handle exception
							}
							
					count =0;
				}
			}
		}
		count = 0;
		for(int j=0;j<lie;j++)
		{
			for(int i=0;i<hang;i++)
			{
		
				if(b[i][j] == 1) count++;
				if(b[i][j] == 0) 
				{
					if(count <=piexnumber ) 
						for(int k=1;k<=count;k++)
							try{
								b[i-k][j] = 0;
							}catch (Exception e) {
								// TODO: handle exception
							}
							
					
					count =0;
				}
			}
		}
	/*	for(int i=0;i<hang;i++){
			for(int j=0;j<lie;j++)
			{
				if(b[i][j] == 0)System.out.print(b[i][j]);
				else System.out.print(" ");
			}
			System.out.println();
		}*/

		
	}
	
	
	public static int[][][] cutImage(int[][] b,int hang,int lie)
	{//��ͼƬ�ü��ɵ�������
		//�����г�������
		int num1=0,num2=0;
		int hangcut=0;
		int[] liecut1=new int[30];
		liecut1[0]=0;
		int[] liecut2=new int[30];
		liecut2[0]=0;
		int k=1,flag=0,count=0;
		//������ȫ��0�Ŀ���
		for(int i=0;i<hang;i++)
		{
			for(int j=0;j<lie;j++)
			{
				if(b[i][j]==1)
					flag=1;
				else
					count++;
			}
			if(count==lie&&flag==1)
			{
				hangcut=i+1;
		
				break;
			}
			else
				count=0;
		}
	
		
		//�����и��ϰ벿��
		//������ȫ��0�Ŀ���
		flag=0;
		for(int j=0;j<lie;j++)
		{
		for(int i=0;i<hangcut;i++)			
			{
				if(b[i][j]==1)
					flag=1;
				else
					count++;
			}
			if(count==hangcut&&flag==1)
			{
				liecut1[k]=j+1;
			
				k++;
				num1++;
				flag=0;
			}
			else
				count=0;
		}
		
		//�����и��°벿��
		flag=0;
		k=1;
		for(int j=0;j<lie;j++)
		{
		for(int i=hangcut;i<hang;i++)			
			{
				if(b[i][j]==1)
					flag=1;
				else
					count++;
			}
			if(count==(hang-hangcut)&&flag==1)
			{
				liecut2[k]=j+1;
			
				num2++;
				k++;
				flag=0;
			}
			else
			{
				count=0;
			}
		}
		
		//д����ά����
		k=0;
		int[][][] cut=new int[num1+num2+2][hang][lie];
		for(;k<num1-1;k++)
		{
			for(int i=0;i<hangcut;i++)
			{
				for(int j=liecut1[k];j<liecut1[k+1];j++)
				{
					cut[k][i][j]=b[i][j];
					
				}
			}
		}
		k=0;
		for(;k<num2-1;k++)
		{
			for(int i=hangcut;i<hang;i++)
			{
				for(int j=liecut2[k];j<liecut2[k+1];j++)
				{
					try{
						cut[k+num1-1][i][j]=b[i][j];
			
					}catch(Exception e){
						
					}
					
				}
			}
		}
		return cut;
	}
	
	public static void releaseSound(String filepath,String destpath, int Threshold){
		//���˱���ɫ���кڰ׶�ֵ������
		try {
			BufferedImage bi=ImageIO.read(new File(filepath));
			int width=bi.getWidth();
			int height=bi.getHeight();
 			BufferedImage bi2=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
			Raster raster=bi.getRaster();
			WritableRaster wr=bi2.getRaster();
			int[] a=new int[4];
			int[][]b = new int[1000][1000];
			
			for(int i=0;i<width;i++){
				for(int j=0;j<height;j++){
					
					raster.getPixel(i, j, a);
					if((a[0]+a[1]+a[2])/3>Threshold){
						a[0]=255;
						a[1]=255;
						a[2]=255;
						a[3]=255;
						wr.setPixel(i, j, a);
					}else{
						a[0]=0;
						a[1]=0;
						a[2]=0;
						a[3]=255;
						wr.setPixel(i, j, a);
 
					}
					if(a[0] ==255) b[j][i] = 0;
					else b[j][i] = 1;
				}
			}
			
			process(b,height,width);
			cutImage(b,height,width);
						
			ImageIO.write(bi2, "png", new File(destpath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
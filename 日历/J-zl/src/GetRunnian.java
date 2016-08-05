
public class GetRunnian {
	boolean t1;
	public GetRunnian(int i) {
		// TODO Auto-generated constructor stub
		if((i%4==0&&i%100!=0)||i%400==0){
			this.t1=true;
		}else{
			this.t1=false;
		}
	}
	public boolean getT1(){
		return t1;
	}
}

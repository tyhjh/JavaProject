
public class Daysxiqi {
	int allyear;
	int allDay=0;
	int xiqi;
	int days;
	Daysxiqi(int ys,int ms){
		allyear=(ys-1900);
		//计算到年的天数
		for(int i=1900;i<ys;i++){
			GetRunnian jis=new GetRunnian(i);
				if(jis.getT1()){
					allDay=allDay+366;
				}else{
					allDay=allDay+365;
				}
			}
		GetRunnian jis1=new GetRunnian(ys);
				for(int j=1;j<ms;j++){
					if(j==1||j==3||j==5||j==7||j==8||j==10||j==12){
						allDay=allDay+31;
					}else if(j==2){
						if(jis1.getT1()){
							allDay=allDay+29;
							}else if(!jis1.getT1()){
								allDay=allDay+28;
							}
						}else if(j==4||j==6||j==9||j==11){
							allDay=allDay+30;
						}
					}
				
				if(ms==1||ms==3||ms==5||ms==7||ms==8||ms==10||ms==12){
						days=31;
				}else if(ms==2){
					if(jis1.getT1()){
						days=29;
						
						}else if(!jis1.getT1()){
							days=28;
							
						}
					}else if(ms==4||ms==6||ms==9||ms==11){
						days=30;
						
					}
			xiqi=(allDay%7)+1;
			this.xiqi=xiqi;
			
			
	}
	public int getXiqi(){
		return xiqi;
		
	}
	public int getDays(){
		return days;
		
	}
}

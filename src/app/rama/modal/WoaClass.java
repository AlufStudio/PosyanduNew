package app.rama.modal;

public class WoaClass {
	int id,bulan;
	double minsd1,minsd2,minsd3,median,sd1,sd2,sd3;
	String jk;
	
	public WoaClass(){
		//Constructor
	}
	
	public WoaClass(int id,int bulan,double minsd1,double minsd2,double minsd3,double median,double sd1,double sd2,double sd3,String jk){
		this.id = id;
		this.bulan = bulan;
		this.minsd1 = minsd1;
		this.minsd2 = minsd2;
		this.minsd3 = minsd3;
		this.median = median;
		this.sd1 = sd1;
		this.sd2 = sd2;
		this.sd3 = sd3;
		this.jk = jk;
	}
	
	public WoaClass(int bulan,double minsd1,double minsd2,double minsd3,double median,double sd1,double sd2,double sd3,String jk){
		this.bulan = bulan;
		this.minsd1 = minsd1;
		this.minsd2 = minsd2;
		this.minsd3 = minsd3;
		this.median = median;
		this.sd1 = sd1;
		this.sd2 = sd2;
		this.sd3 = sd3;
		this.jk = jk;
	}
	
	public WoaClass(int bulan,double minsd1,double minsd2,double minsd3,double median,double sd1,double sd2,double sd3){
		this.bulan = bulan;
		this.minsd1 = minsd1;
		this.minsd2 = minsd2;
		this.minsd3 = minsd3;
		this.median = median;
		this.sd1 = sd1;
		this.sd2 = sd2;
		this.sd3 = sd3;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return this.id;
	}
	
	public void setBulan(int bulan){
		this.bulan = bulan;
	}
	
	public int getBulan(){
		return this.bulan;
	}
	
	public void setMinSD1(double minsd1){
		this.minsd1 = minsd1;
	}
	
	public double getMinSD1(){
		return this.minsd1;
	}

	public void setMinSD2(double minsd2){
		this.minsd2 = minsd2;
	}
	
	public double getMinSD2(){
		return this.minsd2;
	}
	
	public void setMinSD3(double minsd3){
		this.minsd3 = minsd3;
	}
	
	public double getMinSD3(){
		return this.minsd3;
	}
	
	public void setMedian(double median){
		this.median = median;
	}
	
	public double getMedian(){
		return this.median;
	}
	
	public void setSD1(double sd1){
		this.sd1 = sd1;
	}
	
	public double getSD1(){
		return this.sd1;
	}
	
	public void setSD2(double sd2){
		this.sd2 = sd2;
	}
	
	public double getSD2(){
		return this.sd2;
	}
	
	public void setSD3(double sd3){
		this.sd3 = sd3;
	}
	
	public double getSD3(){
		return this.sd3;
	}
	
	public void setJK(String jk){
		this.jk = jk;
	}
	
	public String getJK(){
		return this.jk;
	}

}

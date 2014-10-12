package app.rama.modal;

public class JadwalClass {
	int _id;
	String _judul,_tanggal,_content;
	
	public JadwalClass(){
		//constructor
	}
	
	public JadwalClass(int id,String judul,String tanggal,String content){
		this._id = id;
		this._judul = judul;
		this._tanggal = tanggal;
		this._content = content;
	}
	
	public JadwalClass(String judul,String tanggal,String content){
		this._judul = judul;
		this._tanggal = tanggal;
		this._content = content;
	}
	
	public void setID(int id){
		this._id = id;
	}
	
	public int getID(){
		return this._id;
	}
	
	public void setJudul(String judul){
		this._judul = judul;
	}
	
	public String getJudul(){
		return this._judul;
	}
	
	public void setTanggal(String tanggal){
		this._tanggal = tanggal;
	}
	
	public String getTanggal(){
		return this._tanggal;
	}
	
	public void setContent(String content){
		this._content = content;
	}
	
	public String getContent(){
		return this._content;
	}
}

package app.rama.modal;

public class TipsClass {
	int _id,_icon,_gambar;
	String _title,_content,_tanggal;
	
	public TipsClass(){
		//constructor
	}
	
	public TipsClass(int id,String title,int gambar,String content,String tanggal,int icon){
		this._id = id;
		this._title = title;
		this._gambar = gambar;
		this._content = content;
		this._tanggal = tanggal;
		this._icon = icon;
	}
	
	public TipsClass(String title,int gambar,String content,String tanggal,int icon){
		this._title = title;
		this._gambar = gambar;
		this._content = content;
		this._tanggal = tanggal;
		this._icon = icon;
	}
	
	public void setID(int id){
		this._id = id;
	}
	
	public int getID(){
		return this._id;
	}
	
	public void setTitle(String title){
		this._title = title;
	}
	
	public String getTitle(){
		return this._title;
	}
	
	public void setGambar(int gambar){
		this._gambar = gambar;
	}
	
	public int getGambar(){
		return this._gambar;
	}
	
	public void setContent(String content){
		this._content = content;
	}
	
	public String getContent(){
		return this._content;
	}
	
	public void setTanggal(String tanggal){
		this._tanggal = tanggal;
	}
	
	public String getTanggal(){
		return this._tanggal;
	}
	
	public void setIcon(int icon){
		this._icon = icon;
	}
	
	public int getIcon(){
		return this._icon;
	}
}

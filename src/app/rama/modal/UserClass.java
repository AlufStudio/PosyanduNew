package app.rama.modal;

public class UserClass {
	int _id;
	String _nama,_tgl,_namaortu,_jk,_gambar;
	
	public UserClass(){
		//constructor
	}
	
	public UserClass(int id,String nama,String tgl,String namaortu,String jk,String gambar){
		this._id = id;
		this._nama = nama;
		this._tgl = tgl;
		this._namaortu = namaortu;
		this._jk = jk;
		this._gambar = gambar;
	}
	
	public UserClass(String nama,String tgl,String namaortu,String jk,String gambar){
		this._nama = nama;
		this._tgl = tgl;
		this._namaortu = namaortu;
		this._jk = jk;
		this._gambar = gambar;
	}
	
	public void setID(int id){
		this._id = id;
	}
	
	public int getID(){
		return this._id;
	}
	
	public void setNama(String nama){
		this._nama = nama;
	}
	
	public String getNama(){
		return this._nama;
	}
	
	public void setTgl(String tgl){
		this._tgl = tgl;
	}
	
	public String getTgl(){
		return this._tgl;
	}
	
	public void setNamaortu(String namaortu){
		this._namaortu = namaortu;
	}
	
	public String getNamaortu(){
		return this._namaortu;
	}
	
	public void setJK(String jk){
		this._jk = jk;
	}
	
	public String getJK(){
		return this._jk;
	}
	
	public void setGambar(String gambar){
		this._gambar = gambar;
	}
	
	public String getGambar(){
		return this._gambar;
	}
	
}

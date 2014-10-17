package app.rama.lib;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import app.rama.modal.JadwalClass;
import app.rama.modal.TipsClass;
import app.rama.modal.UserClass;
import app.rama.modal.WoaClass;

public class DatabaseHandler extends SQLiteOpenHelper{
	
	private static final int DB_VERSION = 1;
	
	private static final String DB_NAME = "posyandu";
	
	private static final String TBL_USER = "ps_user";
	private static final String TBL_TIPS = "ps_tips";
	private static final String TBL_JADWAL = "ps_jadwal";
	private static final String TBL_WOA = "ps_woa";
	
	//Column for table user
	private static final String KEY_ID = "id";
	private static final String KEY_NAMA = "nama";
	private static final String KEY_TGL = "tgl";
	private static final String KEY_JK = "jk";
	private static final String KEY_ORTU = "ortu";
	
	//Column for table tips
	private static final String KEY_TITLE = "title";
	private static final String KEY_GAMBAR = "gambar";
	private static final String KEY_CONTENT = "content";
	
	//Colum for table weight for age
	private static final String KEY_BULAN = "bulan";
	private static final String KEY_MEDIAN = "median";
	private static final String KEY_SD1 = "sd1";
	private static final String KEY_SD2 = "sd2";
	private static final String KEY_SD3 = "sd3";
	private static final String KEY_MINSD1 = "msd1";
	private static final String KEY_MINSD2 = "msd2";
	private static final String KEY_MINSD3 = "msd3";

	public DatabaseHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_TABLE_USER = "CREATE TABLE " + TBL_USER + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_NAMA + " TEXT,"
				+ KEY_TGL + " TEXT,"
				+ KEY_JK + " TEXT,"
				+ KEY_GAMBAR + " TEXT,"
				+ KEY_ORTU + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_USER);
		
		String CREATE_TABLE_TIPS = "CREATE TABLE " + TBL_TIPS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_TITLE + " TEXT,"
				+ KEY_GAMBAR + " TEXT,"
				+ KEY_TGL + " TEXT,"
				+ KEY_CONTENT + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_TIPS);
		
		String CREATE_TABLE_JADWAL = "CREATE TABLE " + TBL_JADWAL + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_TITLE + " TEXT,"
				+ KEY_TGL + " TEXT,"
				+ KEY_CONTENT + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_JADWAL);
		
		String CREATE_TABLE_WOA = "CREATE TABLE " + TBL_WOA + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_BULAN + " INTEGER,"
				+ KEY_JK + " TEXT,"
				+ KEY_MINSD1 + " REAL,"
				+ KEY_MINSD2 + " REAL,"
				+ KEY_MINSD3 + " REAL,"
				+ KEY_MEDIAN + " REAL,"
				+ KEY_SD1 + " REAL,"
				+ KEY_SD2 + " REAL,"
				+ KEY_SD3 + " REAL" + ")";
		db.execSQL(CREATE_TABLE_WOA);
				
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TBL_USER);
		db.execSQL("DROP TABLE IF EXISTS " + TBL_TIPS);
		db.execSQL("DROP TABLE IF EXISTS " + TBL_JADWAL);
		db.execSQL("DROP TABLE IF EXISTS " + TBL_WOA);
		
		onCreate(db);
	}
	
	public void addDetailUser(UserClass user){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAMA, user.getNama());
		cv.put(KEY_ORTU, user.getNamaortu());
		cv.put(KEY_JK, user.getJK());
		cv.put(KEY_GAMBAR, user.getGambar());
		cv.put(KEY_TGL, user.getTgl());  
		
		db.insert(TBL_USER, null, cv);
		db.close();
	}
	
	public void addDetailWOA(int bulan,String jk,double minsd3,double minsd2,double minsd1,double median,double sd1,double sd2,double sd3){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_BULAN, bulan);
		cv.put(KEY_JK, jk);
		cv.put(KEY_MINSD3, minsd3);
		cv.put(KEY_MINSD2, minsd2);
		cv.put(KEY_MINSD1, minsd1);
		cv.put(KEY_MEDIAN, median);
		cv.put(KEY_SD1, sd1);
		cv.put(KEY_SD2, sd2);
		cv.put(KEY_SD3, sd3);
		
		db.insert(TBL_WOA, null, cv);
		db.close();
	}
	
	public void addDetailTips(TipsClass tips){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_TITLE, tips.getTitle());
		cv.put(KEY_GAMBAR, tips.getGambar());
		cv.put(KEY_CONTENT, tips.getContent());
		cv.put(KEY_TGL, tips.getTanggal());
		
		db.insert(TBL_TIPS, null, cv);
		db.close();
	}
	
	public void addDetailJadwal(JadwalClass jadwal){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_TITLE, jadwal.getJudul());
		cv.put(KEY_TGL, jadwal.getTanggal());
		cv.put(KEY_CONTENT, jadwal.getContent());
		
		db.insert(TBL_JADWAL, null, cv);
		db.close();
	}
	
	public void resetDetailUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TBL_USER, null, null);
        db.close();
    }
	
	public void resetDetailTips(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TBL_TIPS, null, null);
        db.close();
    }
	
	public void resetDetailJadwal(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TBL_JADWAL, null, null);
        db.close();
    }
	
	public void resetDetailWOA(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TBL_WOA, null, null);
        db.close();
    }
	
	 public UserClass getUser(int id)
	  {
	    SQLiteDatabase db = this.getReadableDatabase();

	    Cursor cursor = db.query(TBL_USER, new String[]{ KEY_ID, KEY_NAMA, KEY_TGL, KEY_ORTU, KEY_JK, KEY_GAMBAR }, KEY_ID + " = ?", new String[] {String.valueOf(id)}, null, null, null, null);
	    if (cursor != null)
	      cursor.moveToFirst();
	    
	    return new UserClass(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
	  }
	 
	 public WoaClass getWoa(int bulan){
		 SQLiteDatabase db = this.getReadableDatabase();
		 
		 Cursor cursor = db.query(TBL_WOA, new String[] { KEY_ID, KEY_BULAN, KEY_MINSD3, KEY_MINSD2, KEY_MINSD1, KEY_MEDIAN, KEY_SD1, KEY_SD2, KEY_SD3, KEY_JK}, KEY_BULAN + " = ?", new String[] {String.valueOf(bulan)}, null, null, null, null);
		 if(cursor != null){
			 cursor.moveToFirst();
		 }
		 
		 return new WoaClass(cursor.getInt(0),cursor.getInt(1),cursor.getDouble(2),cursor.getDouble(3),cursor.getDouble(4),cursor.getDouble(5),cursor.getDouble(6),cursor.getDouble(7),cursor.getDouble(8),cursor.getString(9));
	 }
	 
	 public List<JadwalClass> getAllJadwal() {
		    List<JadwalClass> jadwalList = new ArrayList<JadwalClass>();
		    // Select All Query
		    String selectQuery = "SELECT  * FROM " + TBL_JADWAL;
		 
		    SQLiteDatabase db = this.getWritableDatabase();
		    Cursor cursor = db.rawQuery(selectQuery, null);
		 
		    // looping through all rows and adding to list
		    if (cursor.moveToFirst()) {
		        do {
		            JadwalClass jadwal = new JadwalClass();
		            jadwal.setID(cursor.getInt(0));
		            jadwal.setJudul(cursor.getString(1));
		            jadwal.setTanggal(cursor.getString(2));
		            jadwal.setContent(cursor.getString(3));
		            // Adding contact to list
		            jadwalList.add(jadwal);
		        } while (cursor.moveToNext());
		    }
		 
		    // return contact list
		    return jadwalList;
		}
}

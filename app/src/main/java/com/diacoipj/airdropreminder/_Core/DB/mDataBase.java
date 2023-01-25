package com.diacoipj.airdropreminder._Core.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.diacoipj.airdropreminder.MainActivity;

import com.diacoipj.airdropreminder.Other.Reminders.FragReminder;
import com.diacoipj.airdropreminder.Setting.mLocalData;
import com.diacoipj.airdropreminder.Setting.nValue;

public class mDataBase extends SQLiteOpenHelper {

    private static  final  String DBNAme="AirDropDB";
    public static  final  int DBVersion=1;

    /*---------------------------------------------- user reminders tbl ----------------------------------------------------*/
    public static  String Reminder_tbl="reminderTable" ,ColID = "id" , ColActive = "active" ,
    ColTitle = "title" ,ColDate = "date" ,ColTime = "time" ,ColType ="type" ,
            ColSound="sound" , ColColor="color" , ColColor2="colorSecond" ,
    ColDesc = "descc" ,ColTag = "tag" ,ColDays="days" ,ColSiteAddress ="link" ,
    ColAvgMoney="avgMoney",ColSignIn ="signIn";
    private  static final String SQl_Command_create_tble_Reminders="CREATE TABLE IF NOT EXISTS "+Reminder_tbl+"("+
            ColID+" INTEGER PRIMARY KEY AUTOINCREMENT  ,"+
            ColActive+" INTEGER ,"+
            ColTitle+" TEXT ,"+
            ColDate+" TEXT ,"+
            ColTime+" TEXT ,"+
            ColType+" INTEGER " +
            ","+ColSound+" INTEGER,"+
            ColColor+" TEXT ,"+
            ColColor2+" TEXT ,"+
            ColDesc+" TEXT , "+
            ColTag+" TEXT , "+
            ColDays+" TEXT ,"+
            ColSiteAddress+" TEXT ,"+
            ColAvgMoney+" TEXT ,"+
            ColSignIn+" TEXT);";
    Context context;
    public mDataBase(@Nullable Context context) {
        super(context, DBNAme, null, DBVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean isFieldExist(SQLiteDatabase db, String tableName, String fieldName)
    {
        boolean isExist = false;
        Cursor res = db.rawQuery("PRAGMA table_info("+tableName+")",null);
        res.moveToFirst();
        do {
            String currentColumn = res.getString(1);
            if (currentColumn.equals(fieldName)) {
                isExist = true;
            }
        } while (res.moveToNext());
        return isExist;
    }


    public  void createDB(){
        try{
            getWritableDatabase().execSQL(SQl_Command_create_tble_Reminders);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public  void insert(String tableName, ContentValues values , dbResults results){
        if(results != null) {
            long rowInserted = getWritableDatabase().insert(tableName, null, values);
            if(results!=null) {
                if (rowInserted != -1) {
                    setId((int) rowInserted);
                    results.onSuccessed();
                }
                else
                    results.onError();
            }
        } else
            getWritableDatabase().insert(tableName, null, values);

    }

    int id ;
    public void setId (int id) {
        this.id = id ;
    }
    public int getId () {
        return id ;
    }

    public  void update(String tableName , ContentValues values ,String whereClause ,String whereArg , dbResults results){
        if(results == null) {
            getWritableDatabase().update(tableName, values, whereClause + "=" + whereArg, null);
        } else {
            long rowInserted =  getWritableDatabase().update(tableName, values, whereClause + " = " + whereArg, null);
            if (rowInserted != -1)
                results.onSuccessed();
            else
                results.onError();
        }
    }

    public  void updateReminder(Context context  , String text , int id ){
        final mDataBase db = new mDataBase(context);
        String update ="UPDATE " + db.Reminder_tbl  + " SET "+ db.ColTitle +" = "+ " '"+text +"' "+ " where "+db.ColID+" = "+id;
        getWritableDatabase().execSQL(update);
        MainActivity.getGlobal().hideMainDialogs();
        MainActivity.getGlobal().FinishFragStartFrag(new FragReminder());
        db.close();
    }

    public  void delete(String tableName ){
       getWritableDatabase().execSQL("delete from "+ tableName);

    }

    public  boolean checkIfExists( String tableName , String conditionName , String conditionResult,int integerCResult){
        String query;
        if(conditionResult!=null && !conditionResult.equals("")){
            query   = "select * from "+tableName+" where "+ conditionName +" = "+"'" +conditionResult+"'" ;
        } else
             query= "select * from "+tableName+" where "+ conditionName +" = "+integerCResult ;

        Cursor cursor = getReadableDatabase().rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return  true;
    }



    public Cursor ReadFromDB(String sqlCode){
        Cursor Reader = getReadableDatabase().rawQuery(sqlCode,null);
        return Reader;

    }

    public void DeleteAll(Context context){
     delete(Reminder_tbl);
     nValue.setGlobal(null);
     mLocalData.SetToken(context , "");
     mLocalData.setEmail(context,"");
     mLocalData.SetCurrentDay(context , "");
     mLocalData.setName(context ,"");
     mLocalData.setFirstReminder(context , false);
    }

}

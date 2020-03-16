package com.academy.shoplist.singleton;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.academy.shoplist.bean.Prodotto;
import com.academy.shoplist.constants.DbConstants;
import com.academy.shoplist.database.DatabaseManager;

public class ShoplistDatabaseManager extends DatabaseManager {
    //Instance
    private static ShoplistDatabaseManager instance;

    private ShoplistDatabaseManager(Context context){
        super(context);
    }

    public static synchronized ShoplistDatabaseManager getInstance(Context context){

        if(instance==null) {
            synchronized (ShoplistDatabaseManager.class){
                if (instance==null) {
                    instance=new ShoplistDatabaseManager(context);
                    instance.open();
                }
            }
        }
        return instance;
    }

    public void addProdotto (Prodotto prodotto){
        database.beginTransaction();
        try{
            ContentValues values= new ContentValues();
            values.put(DbConstants.PRODOTTI_TABLE_NOME,prodotto.getNome());
            values.put(DbConstants.PRODOTTI_TABLE_DESCRIZIONE,prodotto.getDescrizione());
            database.insert(DbConstants.PRODOTTI_TABLE,null,values);
            Log.d("Elemento inserito ", "Prodotto con nome : " + prodotto.getNome());
            database.setTransactionSuccessful();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            database.endTransaction();
        }
    }
}

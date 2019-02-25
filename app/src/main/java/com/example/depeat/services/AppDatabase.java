package com.example.depeat.services;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import com.example.depeat.DAO.OrderDao;
import com.example.depeat.datamodels.Order;

//Non dobbiamo creare la tabella restaurant in quanto l'abbiamo implementata come embedded.

//Ad ogni modifica del database devo andare ad aggiornare il db se no non c'è una corrispondenza tra
//db e codice scritto.

//Devo implementare il singleton che mi evita di farmi creare un oggetto con il costruttore
//Bisogna creare un oggetto di tipo di quella classe se è diversa da null altrimenti mi crea un nuovo
//oggetto. Istanza di app database.

//Il framework tende di astrarre il più possibile perché al di sotto ci sono delle librerie che fanno
//tutto. Creazione di classi java dalle nostre annotation.

@Database(entities = {Order.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //Questo sarà il nostro connettore al DB
    private static AppDatabase INSTANCE;

    public abstract OrderDao orderDao();

    public static AppDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), //Builder serve per creare l'istanza
                            AppDatabase.class, "order-database")
                    //.allowMainThreadQueries()    Non si usa perché non è scalabile poi. Metodo di debug.
                    .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}

package mx.grupo.tepeyac.mexico.aic.siembra.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mx.grupo.tepeyac.mexico.aic.siembra.data.AppDatabase.Companion.DB_NAME
import mx.grupo.tepeyac.mexico.aic.siembra.utils.DatabaseDateConverter
import mx.grupo.tepeyac.mexico.aic.siembra.utils.SingletonHolder

/**
 *@author luisc
 **/
@Database
    (
    entities = [],
    version = 1,
    exportSchema = true
)
@TypeConverters( DatabaseDateConverter::class)
abstract class AppDatabase: RoomDatabase() {

    companion object: SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder( it.applicationContext, AppDatabase::class.java, DB_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }){
        const val DB_NAME = "embarque"
    }
}
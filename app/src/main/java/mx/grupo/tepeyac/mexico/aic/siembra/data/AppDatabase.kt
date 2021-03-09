package mx.grupo.tepeyac.mexico.aic.siembra.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mx.grupo.tepeyac.mexico.aic.siembra.data.AppDatabase.Companion.DB_NAME
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.Area
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.AreaDao
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.actividad.Actividad
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.actividad.ActividadDao
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.actividadTrabajador.ActividadTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia.Asistencia
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.bono.Bono
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.descuento.Descuento
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.extra.Extra
import mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo.Ciclo
import mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo.CicloDao
import mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.Cosecha
import mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.cliente.Cliente
import mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.corte.Corte
import mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.empaque.Empaque
import mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.granel.Granel
import mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.transporte.Transporte
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.Grupo
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoDao
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.TrabajadorDao
import mx.grupo.tepeyac.mexico.aic.siembra.data.producto.Producto
import mx.grupo.tepeyac.mexico.aic.siembra.data.producto.ProductoDao
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.Rancho
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoDao
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.TablaDao
import mx.grupo.tepeyac.mexico.aic.siembra.utils.DatabaseDateConverter
import mx.grupo.tepeyac.mexico.aic.siembra.utils.SingletonHolder

/**
 *@author luisc
 **/
@Database
    (
    entities = [
        Asistencia::class,
        ActividadTrabajador::class,
        Bono::class,
        Descuento::class,
        Extra::class,
        Cosecha::class,
        Cliente::class,
        Corte::class,
        Empaque::class,
        Granel::class,
        Transporte::class,
        Rancho::class,
        Tabla::class,
        Producto::class,
        Area::class,
        Actividad::class,
        Grupo::class,
        Trabajador::class,
        Ciclo::class,
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(DatabaseDateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val ranchoDao: RanchoDao
    abstract val tablaDao: TablaDao
    abstract val productoDao: ProductoDao
    abstract val areaDao: AreaDao
    abstract val actividadDao: ActividadDao
    abstract val grupoDao: GrupoDao
    abstract val trabajadorDao: TrabajadorDao
    abstract val cicloDao: CicloDao

    companion object : SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, AppDatabase::class.java, DB_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }) {
        const val DB_NAME = "siembradb"
    }
}
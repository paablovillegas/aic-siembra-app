package mx.grupo.tepeyac.mexico.aic.siembra.data.area

import mx.grupo.tepeyac.mexico.aic.siembra.data.area.actividad.Actividad
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNot.not
import org.junit.Test

class AreaRepositoryTest {
    companion object {
        val AREA_INTERNA_1 = AreaWithActividades(
            area = Area(1, "60394fc5d475741958ac62ce", "Generales"),
            actividades = listOf(
                Actividad(1, "6039535e6896ab41dc527dc8", "Ingeniero de Campo")
            )
        )
        val AREA_EXTERNA_1 = AreaItem(
            "60394fc5d475741958ac62ce", "GENERALES",
            listOf(
                ActividadItem("6039535e6896ab41dc527dc8", "Ingeniero de Campo")
            )
        )
    }

    @Test
    fun compareArea1Area2_allCorrect() {
        val repo = AreaRepository()
        val areas = listOf(AREA_EXTERNA_1).map { i -> i.getAreaWithActividades() }
        val comparedAreas = repo.compareAreas(listOf(AREA_INTERNA_1), areas)
        val updates = comparedAreas[1]
        assertThat(comparedAreas[0], `is`(emptyList()))
        assertThat(updates, not(`is`(emptyList())))
        assertThat(comparedAreas[2], `is`(emptyList()))

        val areaWithActividades = updates[0]
        assertThat(areaWithActividades.area.id, not(`is`(0L)))

        val tablas = areaWithActividades.actividades
        val tablasEliminadas = tablas.count { i -> i.delete }
        val tablasActualizadas = tablas.count { i -> i.id > 0L && !i.delete }
        val tablasInsertadas = tablas.count { i -> i.id == 0L && !i.delete }
        assertThat(tablasEliminadas, `is`(0))
        assertThat(tablasActualizadas, `is`(1))
        assertThat(tablasInsertadas, `is`(0))
    }

    @Test
    fun compareAreas_oneList_allDeletes() {
        val repo = AreaRepository()
        val areasResponse = listOf(AREA_EXTERNA_1)
        val areas = areasResponse.map { i -> i.getAreaWithActividades() }
        val comparedAreas = repo.compareAreas(areas, emptyList())
        val inserts = comparedAreas[0]
        val updates = comparedAreas[1]
        val deletes = comparedAreas[2]
        assertThat(inserts, `is`(emptyList()))
        assertThat(updates, `is`(emptyList()))
        assertThat(deletes, not(`is`(emptyList())))
    }

    @Test
    fun compareAreas_oneList_allUpdates() {
        val repo = AreaRepository()
        val areasResponse = repo.getAreas()
        val areas = areasResponse.map { i -> i.getAreaWithActividades() }
        val comparedAreas = repo.compareAreas(areas, areas)
        val inserts = comparedAreas[0]
        val updates = comparedAreas[1]
        val deletes = comparedAreas[2]
        assertThat(inserts, `is`(emptyList()))
        assertThat(updates, not(`is`(emptyList())))
        assertThat(deletes, `is`(emptyList()))
    }

    @Test
    fun compareAreas_oneList_allInserts() {
        val repo = AreaRepository()
        val areasResponse = repo.getAreas()
        val areas = areasResponse.map { i -> i.getAreaWithActividades() }
        val comparedAreas = repo.compareAreas(emptyList(), areas)
        val inserts = comparedAreas[0]
        val updates = comparedAreas[1]
        val deletes = comparedAreas[2]
        assertThat(inserts, not(`is`(emptyList())))
        assertThat(updates, `is`(emptyList()))
        assertThat(deletes, `is`(emptyList()))
    }
}
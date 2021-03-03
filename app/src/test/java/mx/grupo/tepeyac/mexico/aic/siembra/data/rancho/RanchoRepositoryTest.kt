package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho

import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class RanchoRepositoryTest {

    companion object {
        val RANCHO_INTERNO_1 = RanchoWithTablas(
            rancho = Rancho(1, "603935b0afbf59217c1afc1d", "Santa Cruz", "SC", false),
            tablas = listOf(
                Tabla(1, "6039390d8f00b52f08780408", "Tabla 1", true, "603935b0afbf59217c1afc1d"),
                Tabla(2, "6039483445bc750a5ce98873", "Tabla 2", false, "603935b0afbf59217c1afc1d"),
                Tabla(3, "6039450b0b62433674e6c5c9", "Tabla 3", false, "603935b0afbf59217c1afc1d"),
            )
        )
        private val RANCHO_EXTERNO_1 =
            RanchoItem(
                "603935b0afbf59217c1afc1d", "SANTA CRUZ", "SC",
                listOf(
                    TablaItem("6039390d8f00b52f08780408", "Tabla 1"),
                    TablaItem("6039483445bc750a5ce98873", "Tabla 2"),
                    TablaItem("6039483445bc750a5pq98873", "Tabla 4"),
                )
            )
    }

    @Test
    fun getTablasrancho() {
        val ranchosResponse = listOf(RANCHO_EXTERNO_1)
        for (rancho in ranchosResponse) {
            val tablas = rancho.getTablasEntities()
            val correct = tablas.all { tabla -> tabla.idRancho != null }
            assertThat(correct, `is`(true))
        }
    }

    @Test
    fun compareRancho1Rancho2_allCorrect() {
        val repo = RanchoRepository()
        val ranchos = listOf(RANCHO_EXTERNO_1).map { i -> i.getRanchoWithTablas() }
        val comparedRanchos = repo.compareRanchos(listOf(RANCHO_INTERNO_1), ranchos)
        val updates = comparedRanchos[1]
        assertThat(comparedRanchos[0], `is`(emptyList()))
        assertThat(updates, not(`is`(emptyList())))
        assertThat(comparedRanchos[2], `is`(emptyList()))

        val ranchoWithTabla = updates[0]
        assertThat(ranchoWithTabla.rancho.id, not(`is`(0L)))

        val tablas = ranchoWithTabla.tablas
        val tablasEliminadas = tablas.count { i -> i.delete }
        val tablasActualizadas = tablas.count { i -> i.id > 0L && !i.delete }
        val tablasInsertadas = tablas.count { i -> i.id == 0L && !i.delete }
        assertThat(tablasEliminadas, `is`(1))
        assertThat(tablasActualizadas, `is`(1))
        assertThat(tablasInsertadas, `is`(1))
    }

    @Test
    fun compareRanchos_oneList_allDeletes() {
        val repo = RanchoRepository()
        val ranchosResponse = listOf(RANCHO_EXTERNO_1)
        val ranchos = ranchosResponse.map { i -> i.getRanchoWithTablas() }
        val comparedRanchos = repo.compareRanchos(ranchos, emptyList())
        val inserts = comparedRanchos[0]
        val updates = comparedRanchos[1]
        val deletes = comparedRanchos[2]
        assertThat(inserts, `is`(emptyList()))
        assertThat(updates, `is`(emptyList()))
        assertThat(deletes, not(`is`(emptyList())))
    }

    @Test
    fun compareRanchos_oneList_allUpdates() {
        val repo = RanchoRepository()
        val ranchosResponse = repo.getRanchos()
        val ranchos = ranchosResponse.map { i -> i.getRanchoWithTablas() }
        val comparedRanchos = repo.compareRanchos(ranchos, ranchos)
        val inserts = comparedRanchos[0]
        val updates = comparedRanchos[1]
        val deletes = comparedRanchos[2]
        assertThat(inserts, `is`(emptyList()))
        assertThat(updates, not(`is`(emptyList())))
        assertThat(deletes, `is`(emptyList()))
    }

    @Test
    fun compareRanchos_oneList_allInserts() {
        val repo = RanchoRepository()
        val ranchosResponse = repo.getRanchos()
        val ranchos = ranchosResponse.map { i -> i.getRanchoWithTablas() }
        val comparedRanchos = repo.compareRanchos(emptyList(), ranchos)
        val inserts = comparedRanchos[0]
        val updates = comparedRanchos[1]
        val deletes = comparedRanchos[2]
        assertThat(inserts, not(`is`(emptyList())))
        assertThat(updates, `is`(emptyList()))
        assertThat(deletes, `is`(emptyList()))
    }
}
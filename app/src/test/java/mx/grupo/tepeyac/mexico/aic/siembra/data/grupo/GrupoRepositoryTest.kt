package mx.grupo.tepeyac.mexico.aic.siembra.data.grupo

import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test
import java.util.*

class GrupoRepositoryTest {
    companion object {
        val GRUPO_INTERNO_1 = GrupoWithTrabajadores(
            grupo = Grupo(1, "603935b0afbf59217c1afc1d", "Santa Cruz", "SC", 2200.0),
            trabajadores = listOf(
                Trabajador(
                    1,
                    "6039390d8f00b52f08780408",
                    "Luis",
                    "Cornejo",
                    idGrupo = "603935b0afbf59217c1afc1d"
                ),
                Trabajador(
                    2,
                    "6039483445bc750a5ce98873",
                    "Luis",
                    "Arreola",
                    idGrupo = "603935b0afbf59217c1afc1d"
                ),
                Trabajador(
                    3,
                    "6039450b0b62433674e6c5c9",
                    "Luis",
                    "Stelo",
                    idGrupo = "603935b0afbf59217c1afc1d"
                ),
            )
        )
        private val GRUPO_EXTERNO_1 =
            GrupoItem(
                "603935b0afbf59217c1afc1d", "SANTA CRUZ", "SC",
                fletes = ResponseFlete(2200.0),
                listOf(
                    ResponseTrabajador(
                        "6039390d8f00b52f08780408",
                        "Luis",
                        "Cornejo",
                        fechaAlta = Date(),
                        salario = ResponseSalario(2200.0)
                    ),
                    ResponseTrabajador(
                        "6039483445bc750a5ce98873",
                        "Luis",
                        "Arreola",
                        fechaAlta = Date(),
                        salario = ResponseSalario(2200.0)
                    ),
                    ResponseTrabajador(
                        "6039390d8f00b23908780408",
                        "Luis",
                        "Estrada",
                        fechaAlta = Date(),
                        salario = ResponseSalario(2200.0)
                    ),
                )
            )
    }

    @Test
    fun parseGrupos() {
        val repo = GrupoRepository()
        val grupos = repo.getGrupos()
        assertThat(grupos.size, `is`(1))
    }

    @Test
    fun compareRancho1Rancho2_allCorrect() {
        val repo = GrupoRepository()
        val grupos = listOf(GRUPO_EXTERNO_1).map { i -> i.getGrupoWithTrabajadores() }
        val comparedRanchos = repo.compareGrupos(listOf(GRUPO_INTERNO_1), grupos)
        val updates = comparedRanchos[1]
        assertThat(comparedRanchos[0], CoreMatchers.`is`(emptyList()))
        assertThat(updates, CoreMatchers.not(CoreMatchers.`is`(emptyList())))
        assertThat(comparedRanchos[2], CoreMatchers.`is`(emptyList()))

        val ranchoWithTabla = updates[0]
        assertThat(ranchoWithTabla.grupo.id, CoreMatchers.not(CoreMatchers.`is`(0L)))

        val tablas = ranchoWithTabla.trabajadores
        val tablasEliminadas = tablas.count { i -> i.delete }
        val tablasActualizadas = tablas.count { i -> i.id > 0L && !i.delete }
        val tablasInsertadas = tablas.count { i -> i.id == 0L && !i.delete }
        assertThat(tablasEliminadas, CoreMatchers.`is`(1))
        assertThat(tablasActualizadas, CoreMatchers.`is`(2))
        assertThat(tablasInsertadas, CoreMatchers.`is`(1))
    }

    @Test
    fun compareRanchos_oneList_allDeletes() {
        val repo = GrupoRepository()
        val ranchosResponse = repo.getGrupos()
        val ranchos = ranchosResponse.map { i -> i.getGrupoWithTrabajadores() }
        val comparedRanchos = repo.compareGrupos(ranchos, emptyList())
        val inserts = comparedRanchos[0]
        val updates = comparedRanchos[1]
        val deletes = comparedRanchos[2]
        assertThat(inserts, CoreMatchers.`is`(emptyList()))
        assertThat(updates, CoreMatchers.`is`(emptyList()))
        assertThat(deletes, CoreMatchers.not(CoreMatchers.`is`(emptyList())))
    }

    @Test
    fun compareRanchos_oneList_allUpdates() {
        val repo = GrupoRepository()
        val ranchosResponse = repo.getGrupos()
        val ranchos = ranchosResponse.map { i -> i.getGrupoWithTrabajadores() }
        val comparedRanchos = repo.compareGrupos(ranchos, ranchos)
        val inserts = comparedRanchos[0]
        val updates = comparedRanchos[1]
        val deletes = comparedRanchos[2]
        assertThat(inserts, CoreMatchers.`is`(emptyList()))
        assertThat(updates, CoreMatchers.not(CoreMatchers.`is`(emptyList())))
        assertThat(deletes, CoreMatchers.`is`(emptyList()))
    }

    @Test
    fun compareRanchos_oneList_allInserts() {
        val repo = GrupoRepository()
        val ranchosResponse = repo.getGrupos()
        val ranchos = ranchosResponse.map { i -> i.getGrupoWithTrabajadores() }
        val comparedRanchos = repo.compareGrupos(emptyList(), ranchos)
        val inserts = comparedRanchos[0]
        val updates = comparedRanchos[1]
        val deletes = comparedRanchos[2]
        assertThat(inserts, CoreMatchers.not(CoreMatchers.`is`(emptyList())))
        assertThat(updates, CoreMatchers.`is`(emptyList()))
        assertThat(deletes, CoreMatchers.`is`(emptyList()))
    }
}
package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class AsistenciaGrupoRepositoryTest {
    @Test
    fun parseAsistenciaGrupo() {
        val repo = AsistenciaGrupoRepository()
        val asistencias = repo.getAsistenciasGrupo()

        assertThat(asistencias.size, `is`(1))
    }
}
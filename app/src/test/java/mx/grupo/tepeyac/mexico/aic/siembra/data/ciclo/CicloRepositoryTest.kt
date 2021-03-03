package mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class CicloRepositoryTest {

    @Test
    fun parseCiclos() {
        val repo = CicloRepository()
        val ciclos = repo.getCiclos()
        assertThat(ciclos.size, `is`(2))
    }
    //TODO: Terminar tests

}
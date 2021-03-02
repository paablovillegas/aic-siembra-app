package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class RanchoRepositoryTest {

    @Test
    fun testParsingJSON() {
        val repo = RanchoRepository();
        val ranchos = repo.getRanchos()
        val comparedRanchos = repo.compareRanchos(emptyList(), ranchos)
        assertThat(comparedRanchos.size, `is`(3))
    }

    @Test
    fun compareRanchos_oneList_allDeletes() {
        val repo = RanchoRepository();
        val ranchos = repo.getRanchos()
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
        val repo = RanchoRepository();
        val ranchos = repo.getRanchos()
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
        val repo = RanchoRepository();
        val ranchos = repo.getRanchos()
        val comparedRanchos = repo.compareRanchos(emptyList(), ranchos)
        val inserts = comparedRanchos[0]
        val updates = comparedRanchos[1]
        val deletes = comparedRanchos[2]

        assertThat(inserts, not(`is`(emptyList())))
        assertThat(updates, `is`(emptyList()))
        assertThat(deletes, `is`(emptyList()))
    }
}
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.flightsearchapp.data.database.Favorite
import com.example.flightsearchapp.data.database.FlightDao
import com.example.flightsearchapp.data.database.FlightDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class FlightDaoTest {
    lateinit var flightDao: FlightDao
    lateinit var flightDatabase: FlightDatabase

    private val firstFavorite = Favorite(
        id = 0,
        departureCode = "GRL",
        destinationCode = "BLD"
    )
    private val secondeFavorite = Favorite(
        id = 1,
        departureCode = "ART",
        destinationCode = "TRA"
    )

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()

        flightDatabase = Room.inMemoryDatabaseBuilder(context, FlightDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        flightDao = flightDatabase.getDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() = flightDatabase.close()

    @Test
    @Throws(Exception::class)
    fun flightDatabase_addFavorites() = runBlocking {
        addOneFavorite()
        var favorites = flightDao.getAllFavorites().first()
        assertEquals(firstFavorite, favorites[0])

        flightDao.insertFavorite(secondeFavorite)
        favorites = flightDao.getAllFavorites().first()
        assertEquals(secondeFavorite, favorites[1])
    }

    @Test
    @Throws(Exception::class)
    fun flightDatabase_deleteFavorites() = runBlocking {
        addTwoFavorites()
        var favorites = flightDao.getAllFavorites().first()
        assertEquals(2, favorites.size)

        flightDao.deleteFavorite(firstFavorite)
        flightDao.deleteFavorite(secondeFavorite)
        favorites = flightDao.getAllFavorites().first()
        assertTrue(favorites.isEmpty())
    }

    private suspend fun addOneFavorite() = flightDao.insertFavorite(firstFavorite)

    private suspend fun addTwoFavorites() {
        flightDao.insertFavorite(firstFavorite)
        flightDao.insertFavorite(secondeFavorite)
    }
}
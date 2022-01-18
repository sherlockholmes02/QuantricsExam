package com.quantrics.guidomia.use_case

import com.google.common.truth.Truth.assertThat
import com.quantrics.guidomia.data.entities.Car
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Created by Darryl Dave P. de Castro on 18/01/2022.
 */
class GetCarsTest {
    private lateinit var getCars: GetCars
    private lateinit var fakeRepository: FakeCarRepository

    @Before
    fun setUp() {
        fakeRepository = FakeCarRepository()
        getCars = GetCars(fakeRepository)

        val carsToInsert = mutableListOf<Car>()
        ('a'..'z').forEachIndexed { index, c ->
            carsToInsert.add(
                Car(
                    id = index,
                    consList = arrayListOf<String>("d", "a", "v", "e"),
                    customerPrice = index.toDouble(),
                    make = c.toString(),
                    marketPrice = 0.0,
                    model = c.toString(),
                    prosList = arrayListOf<String>("d", "a", "v", "e"),
                    rating = 1
                )
            )
        }
        carsToInsert.shuffle()
        runBlocking {
            fakeRepository.insertAllCars(carsToInsert)
        }
    }

    @Test
    fun `Cars has Contents`() = runBlocking {
        val carsSize = getCars().size
        assertThat(carsSize).isGreaterThan(0)
    }

    @Test
    fun `Cars is Ascending by Id`() = runBlocking {
        val cars = getCars()
        for(i in 0..cars.size - 2) {
            assertThat(cars[i].id).isLessThan(cars[i+1].id)
        }
    }

    @Test
    fun `Cars is Ascending by price`() = runBlocking {
        val cars = getCars()
        for(i in 0..cars.size - 2) {
            assertThat(cars[i].customerPrice).isLessThan(cars[i+1].customerPrice)
        }
    }

    @Test
    fun `Cars has null value`() = runBlocking {
        val cars = getCars()
        for(i in 0..cars.size - 2) {
            assertThat(cars[i]).isNull()
        }
    }
}
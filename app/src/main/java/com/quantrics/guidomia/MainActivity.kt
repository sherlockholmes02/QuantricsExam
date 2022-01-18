package com.quantrics.guidomia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.quantrics.guidomia.databinding.ActivityMainBinding
import com.quantrics.guidomia.model.Car
import org.json.JSONArray
import java.io.*

class MainActivity : AppCompatActivity() {

    private val cars: MutableList<Car> = mutableListOf()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        getJsonCarList()
    }

    private fun getJsonCarList() {
        val inputStream: InputStream = resources.openRawResource(R.raw.car_list)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        inputStream.use { inputStream ->
            val reader: Reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }
        val jsonString: String = writer.toString()

        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val gson = Gson()
            val car: Car = gson.fromJson(jsonArray.get(0).toString(), Car::class.java)
            cars.add(car)
        }
    }
}
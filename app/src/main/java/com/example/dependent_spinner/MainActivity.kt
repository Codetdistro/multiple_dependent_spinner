package com.example.dependent_spinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dependent_spinner.data.City
import com.example.dependent_spinner.data.Country
import com.example.dependent_spinner.data.State
import com.example.dependent_spinner.db.CountryDatabase
import com.example.dependent_spinner.model.repository
import com.example.dependent_spinner.model.viewmodel
import com.example.dependent_spinner.model.viewmodelfactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {

    lateinit var  spinerModel:viewmodel
    lateinit var database: CountryDatabase

    var country:String = "India"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = CountryDatabase(this)

        val repo = repository(database)
        val fact = viewmodelfactory(repo)
        spinerModel =ViewModelProvider(this,fact).get(viewmodel::class.java)


        //Insert data in database
        val country:List<Country> = arrayListOf(
            Country(1,"India"),
            Country(2,"United States"),
            Country(3,"Australia"),
            Country(4,"Germany"),
        )

        val state:List<State> = arrayListOf(
            State(1,"India","Haryana"),
            State(2,"India","Delhi"),
            State(3,"United States","California"),
            State(4,"United States","Florida"),
            State(5,"Australia","Sydney"),
            State(6,"India","UP"),
        )

        val city:List<City> = arrayListOf(
            City(1,"Haryana","Fariadabad"),
            City(2,"Haryana","Gurugram"),
            City(3,"UP","Noida"),
            City(4,"Delhi","Kannot place")
        )


        CoroutineScope(Dispatchers.Default).launch {
            database.countryDao().insert(country)
            database.stateDao().insert(state)
            database.cityDao().insert(city)


            val countryAdapter:ArrayAdapter<String> =
                ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_item,spinerModel.getCountry())
            countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            countryAdapter.notifyDataSetChanged()
            country_spinner.adapter = countryAdapter
        }

        country_spinner.setOnItemSelectedListener(this)

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        if(view == country_spinner.selectedView){

            country = country_spinner.getSelectedItem().toString()
            spinerModel.getState(country).observe(this@MainActivity, Observer {
                val stateAdapter:ArrayAdapter<String> =
                    ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_item,it)
                stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                stateAdapter.notifyDataSetChanged()
                state_spinner.adapter = stateAdapter
            })
            state_spinner.setOnItemSelectedListener(this)
        }

        if (view == state_spinner.selectedView){
            spinerModel.getCity(state_spinner.getSelectedItem().toString()).observe(this, Observer {
                val cityAdapter:ArrayAdapter<String> =
                    ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_item,it)
                cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                cityAdapter.notifyDataSetChanged()
                city_spinner.adapter = cityAdapter
            })
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}
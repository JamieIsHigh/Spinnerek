package com.example.spinnerek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var pozycja = 0
        var strang = 0

        val spinner = findViewById<Spinner>(R.id.spinner)
        val opcje = arrayOf("Wybierz działanie: ", "dodawanie", "odejmowanie", "mnożenie", "dzielenie")
        val inp1 = findViewById<EditText>(R.id.editTextText)
        val inp2 = findViewById<EditText>(R.id.editTextText2)
        val tekst1 = findViewById<TextView>(R.id.textView)
        val tekst2 = findViewById<TextView>(R.id.textView2)
        val wynik = findViewById<TextView>(R.id.textView3)
        val guzik = findViewById<Button>(R.id.button)

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opcje)
        spinner.adapter = arrayAdapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 > 0) {
                inp1.isVisible = true
                inp2.isVisible = true
                tekst1.isVisible = true
                tekst2.isVisible = true
                wynik.isVisible = true
                guzik.isVisible = true

                    pozycja = p2
                    val tab = arrayOf("+", "-", "*", "/")
                    tekst1.text = tab[pozycja - 1]

                }
                else {
                    inp1.isVisible = false
                    inp2.isVisible = false
                    tekst1.isVisible = false
                    tekst2.isVisible = false
                    wynik.isVisible = false
                    guzik.isVisible = false
                    pozycja = p2
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        guzik.setOnClickListener {
            if (inp2.text.toString() != "" && inp1.text.toString() != "") {
                if (pozycja == 1) {
                    strang = inp1.text.toString().toInt() + inp2.text.toString().toInt()
                    wynik.text = strang.toString()
                } else if (pozycja == 2) {
                    strang = inp1.text.toString().toInt() - inp2.text.toString().toInt()
                    wynik.text = strang.toString()
                } else if (pozycja == 3) {
                    strang = inp1.text.toString().toInt() * inp2.text.toString().toInt()
                    wynik.text = strang.toString()
                } else if (pozycja == 4) {
                    if (inp2.text.toString() != "0" && inp2.text.toString() != "") {
                        strang = inp1.text.toString().toInt() / inp2.text.toString().toInt()
                        wynik.text = strang.toString()
                    } else {
                        Toast.makeText(applicationContext, "Nie dziel przez 0", Toast.LENGTH_SHORT).show()
                        wynik.text = ""
                    }
                }

            } else {
                Toast.makeText(applicationContext, "Podaj wszystkie wartości", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
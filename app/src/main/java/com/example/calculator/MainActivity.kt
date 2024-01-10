package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var hasNatija = false
    lateinit var btn_0: Button
    lateinit var btn_1: Button
    lateinit var btn_2: Button
    lateinit var btn_3: Button
    lateinit var btn_4: Button
    lateinit var btn_5: Button
    lateinit var btn_6: Button
    lateinit var btn_7: Button
    lateinit var btn_8: Button
    lateinit var btn_9: Button
    lateinit var btn_qoshish: Button
    lateinit var btn_ayrish: Button
    lateinit var btn_kopaytruv: Button
    lateinit var btn_bolish: Button
    lateinit var btn_AC: Button
    lateinit var btn_delete: Button
    lateinit var btn_nuqta: Button
    lateinit var btn_teng: Button
    lateinit var txt_ekran: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_0 = findViewById(R.id.btn_0)
        btn_1 = findViewById(R.id.btn_1)
        btn_2 = findViewById(R.id.btn_2)
        btn_3 = findViewById(R.id.btn_3)
        btn_4 = findViewById(R.id.btn_4)
        btn_5 = findViewById(R.id.btn_5)
        btn_6 = findViewById(R.id.btn_6)
        btn_7 = findViewById(R.id.btn_7)
        btn_8 = findViewById(R.id.btn_8)
        btn_9 = findViewById(R.id.btn_9)
        btn_AC = findViewById(R.id.btn_AC)
        btn_ayrish = findViewById(R.id.btn_ayrish )
        btn_bolish = findViewById(R.id.btn_bolish)
        btn_qoshish = findViewById(R.id.btn_qoshish)
        btn_kopaytruv = findViewById(R.id.btn_kopaytruv)
        btn_delete = findViewById(R.id.btn_delete)
        btn_teng = findViewById(R.id.btn_teng)
        btn_nuqta = findViewById(R.id.btn_nuqta)
        txt_ekran = findViewById(R.id.txt_ekran)

        btn_0.setOnClickListener(View.OnClickListener {
            if (txt_ekran.text != "0"){
                txt_ekran.text = "${txt_ekran.text}0"
            }

        })

        btn_1.setOnClickListener {
            raqamYozish(1)

        }

        btn_2.setOnClickListener {
            raqamYozish(2)

        }

        btn_3.setOnClickListener {
            raqamYozish(3)

        }

        btn_4.setOnClickListener {
            raqamYozish(4)
        }

        btn_5.setOnClickListener {
            raqamYozish(5)

        }

        btn_6.setOnClickListener {
            raqamYozish(6)

        }

        btn_7.setOnClickListener {
            raqamYozish(7)

        }

        btn_8.setOnClickListener {
            raqamYozish(8)

        }

        btn_9.setOnClickListener {
            raqamYozish(9)

        }

        btn_nuqta.setOnClickListener {
            val misol = txt_ekran.text.toString()
            var amalIndex = -1
            for (i in misol.indices) {
                if (misol[i] == '+' || misol[i] == '-' || misol[i] == '*' || misol[i] == '/'){
                    amalIndex = i
                }
            }
            if (amalIndex == -1){
                if (!txt_ekran.text.toString().contains('.')){
                    txt_ekran.text = "${txt_ekran.text}."
                }
            }else {
                val ekranLength = txt_ekran.text.length
                val matn = txt_ekran.text.toString().subSequence(amalIndex, ekranLength)
                if (!matn.contains('.')) {
                    txt_ekran.text = "${txt_ekran.text}."
                }
            }
        }

        btn_AC.setOnClickListener {
            txt_ekran.text = "0"
            hasNatija = false

        }

        btn_delete.setOnClickListener {
            val a = txt_ekran.text
            if (a.length == 1 || a == "-"){
                txt_ekran.text = "0"
            }else
                txt_ekran.text = a.subSequence(0, a.length-1)
        }


        btn_qoshish.setOnClickListener {
            amalYozish("+")
        }

        btn_ayrish.setOnClickListener {
            amalYozish("-")
        }

        btn_kopaytruv.setOnClickListener {
            amalYozish("*")
        }

        btn_bolish.setOnClickListener {
            amalYozish("/")
        }

        btn_teng.setOnClickListener {
            if (hasNatija == false){
                if (
                    txt_ekran.text.endsWith('+') ||
                    txt_ekran.text.endsWith('-') ||
                    txt_ekran.text.endsWith('*') ||
                    txt_ekran.text.endsWith('/')
                ) {} else{
                    hisoblash()
                }
            }
        }
    }




    @SuppressLint("SetTextI18n")
    fun raqamYozish(raqam:Int){

        if (hasNatija){
            txt_ekran.text = "$raqam"
            hasNatija=false
        }else {

            val r = raqam.toString()
            if (txt_ekran.text == "0") {
                txt_ekran.text = r
            } else {
                txt_ekran.text = "${txt_ekran.text}$r"
            }
        }
    }
    @SuppressLint("SetTextI18n")
    fun amalYozish(amalArg:String){
        if (hasNatija) {
            val example = txt_ekran.text.toString()
            for (i in example.indices) {
                if (example[i] == '=') {
                    txt_ekran.text = "${example.subSequence(i + 1, example.length)}$amalArg"
                }
            }
            hasNatija = false
        } else {
            if (txt_ekran.text.toString().endsWith('+') ||
                txt_ekran.text.toString().endsWith('-') ||
                txt_ekran.text.toString().endsWith('*') ||
                txt_ekran.text.toString().endsWith('/')
            ) {
                txt_ekran.text =
                    "${txt_ekran.text.subSequence(0,  txt_ekran.text.length - 1)}$amalArg"
            } else {
                txt_ekran.text = "${txt_ekran.text}$amalArg"
            }
        }

    }


    fun hisoblash(){

        var sonlar = ArrayList<Double>()
        var amallar = ArrayList<Int>()

        val misol = txt_ekran.text.toString()

        var index1 = 0
        for (i in misol.indices) {
            if (amallar.isEmpty()) {
                when (misol[i]) {
                    '+' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(0)
                        index1 = i
                    }
                    '-' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(1)
                        index1 = i
                    }
                    '*' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(2)
                        index1 = i
                    }
                    '/' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(3)
                        index1 = i
                    }
                }
            }else{
                when (misol[i]) {
                    '+' -> {
                        sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                        amallar.add(0)
                        index1 = i
                    }
                    '-' -> {
                        sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                        amallar.add(1)
                        index1 = i
                    }
                    '*' -> {
                        sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                        amallar.add(2)
                        index1 = i
                    }
                    '/' -> {
                        sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                        amallar.add(3)
                        index1 = i
                    }
                }
            }
        }

        sonlar.add(misol.subSequence(index1+1, misol.length).toString().toDouble())

        var count = 0
        var natija = sonlar.first()
        while (count < amallar.size) {

            when (amallar[count]) {
                0 -> {
                    natija += sonlar[count + 1]
                }
                1 -> {
                    natija -= sonlar[count + 1]
                }
                2 -> {
                    natija *= sonlar[count + 1]
                }
                3 -> {
                    natija /= sonlar[count + 1]
                }
            }

            count++
        }

        val intNatija:Int = natija.toInt()
        if (natija / intNatija == 1.0){

            txt_ekran.text = "${txt_ekran.text}= $intNatija"
        }else {
            txt_ekran.text = "${txt_ekran.text}= $natija"
        }
        hasNatija = true
    }
}
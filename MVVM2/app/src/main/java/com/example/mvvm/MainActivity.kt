package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.ViewModel.MainViewModel
import com.example.mvvm.ViewModel.MainViewModelFactory
import com.example.mvvm.adapter.RecyclerAdapter
import com.example.mvvm.model.NicePlace

class MainActivity : AppCompatActivity() {
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel
    private lateinit var mainrecycler: RecyclerView
    private lateinit var but: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainrecycler = findViewById(R.id.recycler)
        val application = requireNotNull(this).application
        val factory = MainViewModelFactory()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        but = findViewById(R.id.button)
        but.setOnClickListener {
            addData()
        }

        initialiseAdapter()
    }
    private fun initialiseAdapter(){
        mainrecycler.layoutManager = viewManager
        observeData()
    }

    fun observeData(){
        viewModel.lst.observe(this, Observer{
            Log.i("data",it.toString())
            mainrecycler.adapter= RecyclerAdapter(viewModel, it, this)
        })
    }


    fun addData(){
        val txtplce = findViewById<EditText>(R.id.titletxt)
        val title=txtplce.text.toString()
        if(title.isBlank()){
            Toast.makeText(this,"Enter value!",Toast.LENGTH_LONG).show()
        }else{
            val blog= NicePlace(title, "ff")
            viewModel.add(blog)
            txtplce.text.clear()
            mainrecycler.adapter?.notifyDataSetChanged()
        }

    }
}
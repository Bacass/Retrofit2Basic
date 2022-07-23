package com.retrofit.exam

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val TAG = this.javaClass.name

    private var retrofit = RetrofitClient.client

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        val btnStart = findViewById<Button>(R.id.btnStart)
        btnStart.setOnClickListener {
            startRetrofit()
        }
    }

    private fun startRetrofit() {
        val postApi = retrofit?.create(ApiService::class.java)
        postApi!!.getPost().enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(call: Call<List<PostModel>>, response: Response<List<PostModel>>) {
                if (response.isSuccessful) {
                    response.body()?.forEach {
                        Log.d(TAG, "onResponse: ${it.userId}, ${it.title}")
                    }
                }
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
package com.example.mvvm.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.ui.main.adapter.RecyclerAdapter
import com.example.mvvm.ui.main.repo.Mocky
import com.example.mvvm.ui.main.repo.NetRepositoryImpl
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainFragment : Fragment() {

    private var adapter: RecyclerAdapter? = null

    private var viewModel: MainViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.main_fragment, container, false)
        adapter = RecyclerAdapter(ArrayList())
        root.findViewById<RecyclerView>(R.id.recycler).adapter = adapter
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        repoFactory(RepoType.NET)?.let {
            viewModel = MainViewModel.createMe(this, it)
            viewModel?.liveList?.observe(this, Observer { data ->
                adapter?.update(data)
            })
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}

enum class RepoType { Dummy, NET }

fun repoFactory(type: RepoType) = when (type) {
    RepoType.Dummy -> { null
         }
    RepoType.NET -> {
        val api = Retrofit.Builder().baseUrl("http://www.mocky.io")
            .client(OkHttpClient().newBuilder().build())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(Mocky::class.java)
        NetRepositoryImpl(api)
    }
}


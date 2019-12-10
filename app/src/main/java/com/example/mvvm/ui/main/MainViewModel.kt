package com.example.mvvm.ui.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm.ui.main.repo.Data
import com.example.mvvm.ui.main.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Suppress("UNCHECKED_CAST")
class MainViewModel(repo: Repository) : ViewModel() {

    val liveList: MutableLiveData<ArrayList<Data>> = MutableLiveData()
    val data: ArrayList<Data>? = arrayListOf()

    init {
        data?.clear()
        GlobalScope.launch(Dispatchers.IO) {
            data?.addAll(repo.getData().body()?.results as ArrayList)
            withContext(Dispatchers.Main){
                liveList.value = data
            }
        }
    }

    class ViewModelFactory(
        private val repo: Repository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repo) as T
        }
    }

    companion object{
        fun createMe(fragment: Fragment, repo: Repository) =
            ViewModelProviders.of(fragment, ViewModelFactory(repo)).get(MainViewModel::class.java)
    }
}

package com.benmohammad.gitd.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.benmohammad.gitd.R
import com.benmohammad.gitd.service.model.Project
import com.benmohammad.gitd.service.repository.ProjectRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ProjectListViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ProjectRepository.instance
    var projectListLiveData: MutableLiveData<List<Project>> = MutableLiveData()

    init {
        loadProjectList()
    }

    private fun loadProjectList() {

        viewModelScope.launch {
            try {
                val request =
                    repository.getProjectList(getApplication<Application>().getString(R.string.github_user_name))
                if (request.isSuccessful) {
                    //データを取得したら、LiveDataを更新
                    projectListLiveData.postValue(request.body())
                }
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }
}

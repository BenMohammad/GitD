package com.benmohammad.gitd.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.benmohammad.gitd.R
import com.benmohammad.gitd.service.model.Project
import com.benmohammad.gitd.service.repository.ProjectRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ProjectViewModel(
    private val myApplication: Application,
    private val mProjectID: String
): AndroidViewModel(myApplication) {


    private val repository = ProjectRepository.instance
    val projectLiveData: MutableLiveData<Project> = MutableLiveData()

    val project = ObservableField<Project>()

    init {
        loadProjects()
    }

    private fun loadProjects() {
        viewModelScope.launch {
            try {
                val project = repository.getProjectDetails(myApplication.getString(R.string.github_user_name), mProjectID)
                if(project.isSuccessful) {
                    projectLiveData.postValue(project.body())
                }
            } catch (e: Exception) {
                Log.e("loadProjects:Failed", e.stackTrace.toString())
            }
        }
    }

    fun setProject(project: Project) {
        this.project.set(project)
    }



    class Factory(private val application: Application, private val projectID: String): ViewModelProvider.NewInstanceFactory() {

        @SuppressLint("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ProjectViewModel(application, projectID) as T
        }
    }
}
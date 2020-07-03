package com.benmohammad.gitd.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.benmohammad.gitd.R
import com.benmohammad.gitd.databinding.FragmentProjectListBinding
import com.benmohammad.gitd.service.model.Project
import com.benmohammad.gitd.view.adapter.ProjectAdapter
import com.benmohammad.gitd.view.callback.ProjectClickCallback
import com.benmohammad.gitd.viewmodel.ProjectListViewModel

const val TAG_OF_PROJECT_LIST_FRAGMENT = "ProjectListFragment"

class ProjectListFragment: Fragment() {


    private val viewModel by lazy {ViewModelProviders.of(this).get(ProjectListViewModel::class.java)}

    private lateinit var binding: FragmentProjectListBinding
    private lateinit var projectAdapter: ProjectAdapter

    private val projectClickCallback = object : ProjectClickCallback {
        override fun onClick(project: Project) {
            if(lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && activity is MainActivity) {
                (activity as MainActivity).show(project)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false)
        projectAdapter = ProjectAdapter(projectClickCallback)

        binding.apply {
            projectList.adapter = projectAdapter
            isLoading = true
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectListViewModel) {
        viewModel.projectListLiveData.observe(viewLifecycleOwner, Observer {
            projects ->
            if(projects != null) {
                binding.isLoading = false
                projectAdapter.setProjectList(projects)
            }
        })
    }


}
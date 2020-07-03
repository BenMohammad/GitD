package com.benmohammad.gitd.view.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
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

        }
    }}
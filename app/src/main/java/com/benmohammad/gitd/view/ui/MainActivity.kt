package com.benmohammad.gitd.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.benmohammad.gitd.R
import com.benmohammad.gitd.service.model.Project

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            val fragment = ProjectListFragment()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment, TAG_OF_PROJECT_LIST_FRAGMENT)
                .commit()
        }
    }

    fun show(project: Project) {
        val projectFragment = ProjectFragment.forProject(project.name)

        supportFragmentManager
            .beginTransaction()
            .addToBackStack("project")
            .replace(R.id.fragment_container, projectFragment, null)
            .commit()
    }
}

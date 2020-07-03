package com.benmohammad.gitd.view.callback

import com.benmohammad.gitd.service.model.Project

interface ProjectClickCallback {

    fun onClick(project: Project)
}
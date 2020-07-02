package com.benmohammad.gitd.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ProjectViewModel(
    private val myApplication: Application,
    private val mProjectID: String
): AndroidViewModel(myApplication) {


}
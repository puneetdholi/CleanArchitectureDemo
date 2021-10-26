package com.example.architecturedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.architecturedemo.utils.showLightStatusBar
import dagger.android.support.DaggerAppCompatActivity

class DashboardActivity : DaggerAppCompatActivity(),ActivityIndicatorListener {
    lateinit var dashboardProgressBarContainer: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        showLightStatusBar()
        initViews()
    }

    private fun initViews(){
        dashboardProgressBarContainer = findViewById(R.id.layout_dashboard_progress_bar)
    }

    override fun showActivityIndicator() {
        dashboardProgressBarContainer.visibility = View.VISIBLE
    }

    override fun dismissActivityIndicator() {
        dashboardProgressBarContainer.visibility = View.GONE
    }
}

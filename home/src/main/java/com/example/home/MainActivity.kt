package com.example.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import com.example.base.presentation.BaseActivity
import com.example.base.presentation.BaseViewModel
import javax.inject.Inject

class MainActivity : BaseActivity<BaseViewModel>() {
    private lateinit var navHostFragment: NavHostFragment

    @Inject
    override lateinit var viewModel: BaseViewModel
    override val resourceLayout: Int?
        get() = R.layout.activity_home

    override fun onInitViews() {
        super.onInitViews()
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
    }

    override fun onBackPressed() {
        if (!navHostFragment.navController.popBackStack())
            finish()
        else
            navHostFragment.navController.navigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (!navHostFragment.navController.popBackStack())
                    finish()
                else
                    navHostFragment.navController.navigateUp()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}

package com.app.harho.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.app.harho.R
import com.app.harho.databinding.ActivityMainBinding
import com.app.harho.utils.gone
import com.app.harho.utils.visible


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navController: NavController
    private lateinit var findNavHost: NavHostFragment
    var flagDrawer = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.include.navDrawer.setOnClickListener {
            callSideDrawer()
        }
        navSetup()
    }

    fun goneIcon() {
        binding.include.navDrawer.gone()
    }

    fun visibleIcon() {
        binding.include.navDrawer.visible()
    }


    private fun navSetup() {
        findNavHost = supportFragmentManager.findFragmentById(R.id.flNavHome) as NavHostFragment
        navController = findNavHost.navController
    }


    private fun callSideDrawer() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.openDrawer(GravityCompat.START)
        actionBarDrawerToggle = object : ActionBarDrawerToggle(
            this, binding.drawerLayout, R.string.Nav_open, R.string.Nav_close
        ) {
            private val scaleFactor = 6f

            @SuppressLint("ObsoleteSdkInt")
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                val slideX = drawerView.width * slideOffset
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    binding.include.cardviewContent.translationX = slideX
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        binding.drawerLayout.layoutDirection = View.LAYOUT_DIRECTION_LTR
                    }
                }
                binding.include.cardviewContent.scaleX = 1 - slideOffset / scaleFactor
                binding.include.cardviewContent.scaleY = 1 - slideOffset / scaleFactor
                binding.include.cardviewContent.radius = 30f
                Log.e("qwerty", "onDrawerSlide")
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                binding.include.cardviewContent.radius = 0f
                flagDrawer = false
                Log.e("qwerty", "onDrawerClosed")
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                flagDrawer = true
                binding.include.cardviewContent.radius = 30f
                Log.e("qwerty", "onDrawerOpened")
            }

            override fun onDrawerStateChanged(newState: Int) {
                super.onDrawerStateChanged(newState)
                if (!flagDrawer) {
                    binding.include.cardviewContent.radius = 0f
                } else {
                    binding.include.cardviewContent.radius = 30f
                }
                Log.e("qwerty", "onDrawerStateChanged")
            }
        }

        if (supportActionBar != null) {
            supportActionBar!!.setHomeButtonEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        binding.drawerLayout.setScrimColor(Color.TRANSPARENT)
        binding.drawerLayout.drawerElevation = 0f
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle as ActionBarDrawerToggle)
        (actionBarDrawerToggle as ActionBarDrawerToggle).isDrawerSlideAnimationEnabled =
            true //disable "hamburger to arrow" drawable
        (actionBarDrawerToggle as ActionBarDrawerToggle).isDrawerIndicatorEnabled =
            true //disable "hamburger to arrow" drawable
        (actionBarDrawerToggle as ActionBarDrawerToggle).syncState()
    }

    override fun onClick(p0: View?) {


    }
}
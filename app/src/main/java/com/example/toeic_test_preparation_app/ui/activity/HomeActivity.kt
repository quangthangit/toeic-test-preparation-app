package com.example.toeic_test_preparation_app.ui.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.toeic_test_preparation_app.R
import com.example.toeic_test_preparation_app.ui.fragment.home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var navigationItems: List<NavigationItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        navigationItems = listOf(
            NavigationItem(
                findViewById(R.id.homeOption),
                findViewById(R.id.homeIcon),
                findViewById(R.id.homeText),
                HomeFragment()
            ),
            NavigationItem(
                findViewById(R.id.vocabularyOption),
                findViewById(R.id.vocabularyIcon),
                findViewById(R.id.vocabularyText),
                VocabularyFragment()
            ),
            NavigationItem(
                findViewById(R.id.examOption),
                findViewById(R.id.examIcon),
                findViewById(R.id.examText),
                AccountFragment()
            ),
            NavigationItem(
                findViewById(R.id.proggressOption),
                findViewById(R.id.proggressIcon),
                findViewById(R.id.proggressText),
                ProggressFragment()
            ),
            NavigationItem(
                findViewById(R.id.accountOption),
                findViewById(R.id.accountIcon),
                findViewById(R.id.accountText),
                AccountFragment()
            ),
        )

        navigationItems.forEach { item ->
            item.option.setOnClickListener {
                replaceFragment(item.fragment)
                resetAllIcons()
                setActiveIcon(item.icon, item.text)
            }
        }

        navigationItems.firstOrNull()?.let {
            replaceFragment(it.fragment)
            setActiveIcon(it.icon, it.text)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.homeFrameLayout, fragment)
            .commit()
    }

    private fun resetAllIcons() {
        val defaultColor = "#82898F".toColorInt()
        navigationItems.forEach {
            it.icon.setColorFilter(defaultColor)
            it.text.setTextColor(defaultColor)
        }
    }

    private fun setActiveIcon(icon: ImageView, text: TextView) {
        val activeColor = getColor(R.color.blue_sky)
        icon.setColorFilter(activeColor)
        text.setTextColor(activeColor)
    }

    data class NavigationItem(
        val option: LinearLayout,
        val icon: ImageView,
        val text: TextView,
        val fragment: Fragment
    )
}

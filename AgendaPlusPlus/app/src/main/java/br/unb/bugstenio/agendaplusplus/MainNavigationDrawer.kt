package br.unb.bugstenio.agendaplusplus

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main_navigation_drawer.*
import kotlinx.android.synthetic.main.app_bar_main_navigation_drawer.*
import kotlinx.android.synthetic.main.content_main_navigation_drawer.*

import br.unb.bugstenio.agendaplusplus.model.DAO.*
import br.unb.bugstenio.agendaplusplus.model.Object.*
import org.joda.time.*

class MainNavigationDrawer : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation_drawer)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        fragmentManager
                .beginTransaction()
                .replace(
                        fragment_content.id,
                        CalendarFragment())
                .commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_navigation_drawer, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_manage -> {
                val newUser: User = User(1, "@cubaum", "cubo@baum.com", "1234", DateTime(1998, 6, 15, 4,22))
                val DAO: UserDAO = UserDAO()
                DAO.createUser(newUser)
                replacePlaceholderFragment("Manage")
            }
            R.id.nav_calendar -> {
                fragmentManager
                        .beginTransaction()
                        .replace(
                                fragment_content.id,
                                CalendarFragment())
                        .commit()
            }
            R.id.nav_tasks -> {
                replacePlaceholderFragment("Tasks")
            }
            R.id.nav_events -> {
                replacePlaceholderFragment("Events")
            }
            R.id.nav_projects -> {
                replacePlaceholderFragment("Projects")
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun replacePlaceholderFragment(arg: String) {
        fragmentManager.beginTransaction()
                .replace(
                        fragment_content.id,
                        PlaceholderFragment().apply {
                            arguments = Bundle().apply {
                                putString("word", arg)
                            }
                        })
                .commit()
    }
}

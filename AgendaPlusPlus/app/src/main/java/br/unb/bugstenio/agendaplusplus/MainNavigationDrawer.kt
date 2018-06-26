package br.unb.bugstenio.agendaplusplus

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main_navigation_drawer.*
import kotlinx.android.synthetic.main.app_bar_main_navigation_drawer.*
import kotlinx.android.synthetic.main.content_main_navigation_drawer.*

import br.unb.bugstenio.agendaplusplus.model.DAO.*
import br.unb.bugstenio.agendaplusplus.model.Object.*
import org.joda.time.*
import kotlinx.android.synthetic.main.nav_header_main_navigation_drawer.*
import org.json.JSONArray
import org.json.JSONObject

class MainNavigationDrawer : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation_drawer)
        setSupportActionBar(toolbar)

        val toggle = object : ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            override fun onDrawerOpened(drawerView: View) {
                nav_header_email.text = Session.user?.email ?: "Erro"
                nav_header_username.text = Session.user?.username ?: "Erro"
                super.onDrawerOpened(drawerView)
            }
        }
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        fragmentManager
                .beginTransaction()
                .replace(
                        fragment_content.id,
                        CalendarFragment())
                .commit()

        startActivity(Intent(this, LoginActivity::class.java))
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
                serverConnectionTest() // TESTE
                replacePlaceholderFragment("Manage")
            }
            R.id.nav_calendar -> {
                fragmentManager
                        .beginTransaction()
                        .replace(
                                fragment_content.id,
                                CalendarFragment()
                        ).commit()
            }
            R.id.nav_tasks -> {
                fragmentManager
                        .beginTransaction()
                        .replace(
                                fragment_content.id,
                                TaskFragment()
                        ).commit()
            }
            R.id.nav_events -> {
                fragmentManager
                        .beginTransaction()
                        .replace(
                                fragment_content.id,
                                EventFragment()
                        ).commit()
            }
            R.id.nav_projects -> {
                fragmentManager
                        .beginTransaction()
                        .replace(
                                fragment_content.id,
                                ProjectFragment()
                        ).commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun replacePlaceholderFragment(arg: String) {
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

    var response: User? = null
    var response2: List<User>? = null

    fun serverConnectionTest(){
        val UserDAO = UserDAO()
        val GroupDAO = GroupDAO()
        val ProjectDAO = ProjectDAO()
        val ProjectEventDAO = ProjectEventDAO()
        val ProjectTaskDAO = ProjectTaskDAO()
        val UserEventDAO = UserEventDAO()
        val UserGroupDAO = UserGroupDAO()
        val UserTaskDAO = UserTaskDAO()

        val newUser = User(1, "@Moai", "Big@Moas.com", "123", DateTime(1998, 6, 15, 4,22))
        val newGroup = Group(1,"Grupao", "grupo do POST")
        val newProject = Project(1, "Projetao da Massa", "LADEIRA CORNO SAFADO", true, 1, 1 )
        val newProjectEvent = Event(1, "Evento Top", "Topzera", DateTime(2018, 7, 7, 4,2),
                DateTime(2018, 7, 7, 5, 10), 7)
        val newUserEvent = Event(1, "Evento UserTOP", "Topzera da humildade", DateTime(2018, 7, 7, 4,2),
                DateTime(2018, 7, 7, 5, 10), 5)
        val newProjectTask = Task(1, "Tarefinha fudida", "LADEIRA VIADAO", DateTime(2017, 2, 28, 2,1),
                null, 4)
        val newUserTask = Task(1, "Taskzinha do mano", "RUMO AO HEXA", DateTime(2017, 2, 28, 2,1),
                null, 1)

        UserDAO.createUser(newUser)
//        GroupDAO.createGroup(newGroup)
//        ProjectDAO.createProject(newProject)
//        ProjectEventDAO.createProjectEvent(newProjectEvent)
//        ProjectTaskDAO.createProjectTask(newProjectTask)
//        UserEventDAO.createUserEvent(newUserEvent)
//        UserTaskDAO.createUserTask(newUserTask)
//        UserGroupDAO.createUserGroup(newUser, newGroup, true)
        UserDAO.getUser(1) {
            response = it?.parseUser()
            Log.i("hahahah", response.toString())
        }


        UserDAO.getAllUsers{
            response2 = (it as JSONArray?)?.parseUsers()
            Log.i("hahahah", response2.toString())
        }

    }

}

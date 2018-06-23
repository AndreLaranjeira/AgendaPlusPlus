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
                serverConnectionTest() // TESTE
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

    fun serverConnectionTest(){
        val UserDAO = UserDAO()
        val GroupDAO = GroupDAO()
        val ProjectDAO = ProjectDAO()
        val ProjectEventDAO = ProjectEventDAO()
        val ProjectTaskDAO = ProjectTaskDAO()
        val UserEventDAO = UserEventDAO()
        val UserGroupDAO = UserGroupDAO()
        val UserTaskDAO = UserTaskDAO()

        val newUser = User(5, "@Moai", "Big@Moas.com", "123", DateTime(1998, 6, 15, 4,22))
        val newGroup = Group(1,"Grupao", "grupo do POST")
        val newProject = Project(0, "Projetao da Massa", "LADEIRA CORNO SAFADO", true, 1, 5)
        val newProjectEvent = Event(0, "Evento Top", "Topzera", DateTime(2018, 7, 7, 4,2),
                DateTime(2018, 7, 7, 5, 10), 4)
        val newUserEvent = Event(0, "Evento UserTOP", "Topzera da humildade", DateTime(2018, 7, 7, 4,2),
                DateTime(2018, 7, 7, 5, 10), 5)
        val newProjectTask = Task(0, "Tarefinha fudida", "LADEIRA VIADAO", DateTime(2017, 2, 28, 2,1),
                null, 4)
        val newUserTask = Task(0, "Taskzinha do mano", "RUMO AO HEXA", DateTime(2017, 2, 28, 2,1),
                null, 1)

        //UserDAO.createUser(newUser)
        //GroupDAO.createGroup(newGroup)
        //ProjectDAO.createProject(newProject)
        ProjectEventDAO.createProjectEvent(newProjectEvent)
        ProjectTaskDAO.createProjectTask(newProjectTask)
        //UserEventDAO.createUserEvent(newUserEvent)
        //UserTaskDAO.createUserTask(newUserTask)
        UserGroupDAO.createUserGroup(newUser, newGroup, true)
    }

}

package br.unb.bugstenio.agendaplusplus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import br.unb.bugstenio.agendaplusplus.model.Object.Project
import kotlinx.android.synthetic.main.activity_project_show.*

class ProjectShowActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener  {

    var projectId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_show)

        intent?.let {
            projectId = it.getLongExtra(ARG1, 0)
        }

        navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        fragmentManager
                .beginTransaction()
                .replace(
                        project_fragment.id,
                        ProjectDescriptionFragment.newProjectDescription(projectId!!)
                ).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_project -> {
                fragmentManager.beginTransaction()
                        .replace(
                                project_fragment.id,
                                ProjectDescriptionFragment.newProjectDescription(projectId!!)
                        ).commit()
                return true
            }
            R.id.navigation_tasks -> {
                fragmentManager.beginTransaction()
                        .replace(
                                project_fragment.id,
                                TaskFragment()
                        ).commit()
                return true
            }
            R.id.navigation_events -> {
                fragmentManager.beginTransaction()
                        .replace(
                                project_fragment.id,
                                EventFragment()
                        ).commit()
                return true
            }
            R.id.navigation_users -> {
                fragmentManager.beginTransaction()
                        .replace(
                                project_fragment.id,
                                UserListFragment.newUserList(projectId!!)
                        ).commit()
                return true
            }
        }
        return false
    }

    companion object {
        val ARG1 = "project"

        fun showProject(context: Context, project: Project){
            val intent = Intent(context, ProjectShowActivity::class.java).apply {
                putExtra(ARG1, project.id)
            }
            context.startActivity(intent)
        }
    }

}

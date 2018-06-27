package br.unb.bugstenio.agendaplusplus

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import br.unb.bugstenio.agendaplusplus.model.DAO.GroupDAO
import br.unb.bugstenio.agendaplusplus.model.DAO.ProjectDAO
import br.unb.bugstenio.agendaplusplus.model.DAO.UserGroupDAO
import br.unb.bugstenio.agendaplusplus.model.Object.Group
import br.unb.bugstenio.agendaplusplus.model.Object.Project
import br.unb.bugstenio.agendaplusplus.model.Object.parseGroup
import br.unb.bugstenio.agendaplusplus.model.Object.parseProject
import kotlinx.android.synthetic.main.activity_project_create_edit.*

class ProjectCreateEditActivity : AppCompatActivity() {

    var projectId: Long? = null
    var project: Project? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_create_edit)

        this.intent.let {
            projectId = it.getLongExtra(ARG1, 0)
        }

        Log.d("teste", "id dentro $projectId")

        if(projectId != 0.toLong()){
            ProjectDAO().getProject(projectId!!){
                project = it?.parseProject()
                if(project != null){
                    project_create_title.setText(project?.title, TextView.BufferType.EDITABLE)
                    project_create_description.setText(project?.description ?: "", TextView.BufferType.EDITABLE)
                    if(project!!.isActive && !project_create_active.isChecked ||
                            !project!!.isActive && project_create_active.isChecked){
                        project_create_active.toggle()
                    }
                }
            }
        }

        project_create_confirm_button.setOnClickListener {
            val title = project_create_title.text.toString()
            val description = project_create_description.text.toString()
            val isActive = project_create_active.isChecked
            val adminId = Session.user?.id ?: 0

            if(projectId == 0.toLong()){
                GroupDAO().createGroup(Group(0,"","")) {
                    val group = it?.parseGroup()
                    ProjectDAO().createProject(Project(
                            0,
                            title,
                            description,
                            isActive,
                            group!!.id,
                            adminId
                    ))
                    UserGroupDAO().createUserGroup(Session.user!!, group, true)
                }
            } else {
                val groupId = project?.groupId!!
                ProjectDAO().updateProject(
                        Project(
                                projectId!!,
                                title,
                                description,
                                isActive,
                                groupId,
                                adminId
                        )
                )
            }
            this.finish()
        }
    }

    companion object {

        val ARG1 = "project_id"

        fun createProject(context: Context) {
            val intent = Intent(context, ProjectCreateEditActivity::class.java).apply {
                putExtra(ARG1, 0)
            }
            context.startActivity(intent)
        }

        fun editProject(context: Context, projectId: Long) {
            val intent = Intent(context, ProjectCreateEditActivity::class.java).apply {
                putExtra(ARG1, projectId)
            }
            context.startActivity(intent)
        }
    }
}

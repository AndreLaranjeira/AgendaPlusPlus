package br.unb.bugstenio.agendaplusplus

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import br.unb.bugstenio.agendaplusplus.model.DAO.ProjectDAO
import br.unb.bugstenio.agendaplusplus.model.DAO.UserDAO
import br.unb.bugstenio.agendaplusplus.model.Object.Project
import br.unb.bugstenio.agendaplusplus.model.Object.parseProject
import br.unb.bugstenio.agendaplusplus.model.Object.parseUser
import kotlinx.android.synthetic.main.fragment_project_description.*

class ProjectDescriptionFragment : Fragment() {

    var projectId: Long? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_project_description, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let{
            projectId = it.getLong(ARG1)
        }

        project_description_update_button.setOnClickListener {
            ProjectCreateEditActivity.editProject(it.context, projectId!!)
        }

        project_description_delete_button.setOnClickListener {
            ProjectDAO().deleteProject(Project(projectId!!, "", "", true, 0, 0))
        }

        ProjectDAO().getProject(projectId!!){
            val project = it?.parseProject()
            project_description_title.text = project?.title ?: "Erro"
            project_description_description.text = project?.description ?: "Erro"
            if(project?.isActive ?: false){
                project_description_active.text = "Ativo"
            }
            UserDAO().getUser(project?.adminId!!){
                val user = it!!.parseUser()
                project_description_admin.text = user.username
            }
        }
    }

    companion object {

        val ARG1 = "project"

        fun newProjectDescription(projectId: Long) =
            ProjectDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG1, projectId)
                }
            }

    }

}
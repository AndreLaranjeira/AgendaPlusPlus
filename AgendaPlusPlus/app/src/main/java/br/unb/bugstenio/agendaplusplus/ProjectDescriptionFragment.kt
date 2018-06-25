package br.unb.bugstenio.agendaplusplus

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import br.unb.bugstenio.agendaplusplus.model.Object.Project
import kotlinx.android.synthetic.main.fragment_project_description.*

class ProjectDescriptionFragment : Fragment() {

    var projectId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            projectId = it.getLong(ARG1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_project_description, container, false)

        val updateButton = view.findViewById<Button>(R.id.project_description_update_button)
        val deleteButton = view.findViewById<Button>(R.id.project_description_delete_button)

        updateButton.setOnClickListener {
            Toast.makeText(it?.context, "Update $projectId", Toast.LENGTH_LONG).show()
        }

        deleteButton.setOnClickListener {
            Toast.makeText(it?.context, "Delete $projectId", Toast.LENGTH_LONG).show()
        }
        return view
    }

    companion object {

        val ARG1 = "project"

        fun newProjectDescription(project: Project) =
            ProjectDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG1, project.id)
                }
            }

    }

}
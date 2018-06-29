package br.unb.bugstenio.agendaplusplus

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import br.unb.bugstenio.agendaplusplus.model.DAO.ProjectTaskDAO
import br.unb.bugstenio.agendaplusplus.model.DAO.UserTaskDAO
import br.unb.bugstenio.agendaplusplus.model.Object.*
import kotlinx.android.synthetic.main.activity_task_show.*
import org.joda.time.DateTime
import java.util.*

class TaskShowActivity : Activity() {

    var taskId : Long = 0
    var task: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_show)

        this.intent.let {
            taskId = it.getLongExtra(ARG1, 0)
        }

        if(Session.project == null){
            UserTaskDAO().getUserTask(taskId) {
                task = it?.parseUserTask()!!

                task_show_title.text = task?.title ?: ""
                task_show_description.text = task?.description ?: ""
                task_show_limit_date.text = "Data Limite: " + (task?.limitDate ?: "Não há data limite")
                task_show_done.text = "Concluído: " + (task?.taskDone ?: "Não foi feito")
                task_show_project.text = "Projeto: Não pertence a um projeto"
            }
        } else {
            ProjectTaskDAO().getProjectTask(taskId) {
                task = it?.parseProjectTask()!!

                task_show_title.text = task?.title ?: ""
                task_show_description.text = task?.description ?: ""
                task_show_limit_date.text = "Data Limite: " + (task?.limitDate?.toString2() ?: "Não há data limite")
                task_show_done.text = "Concluído: " + (task?.taskDone?.toString2() ?: "Não foi feito")
                task_show_project.text = "Projeto: " + (Session.project?.title ?: "Não pertence a um projeto")
            }
        }

        task_show_update_button.setOnClickListener {
            TaskCreateEditActivity.editTask(it.context, taskId, Session.project != null, Session.project)
        }

        task_show_delete_button.setOnClickListener {
            if(Session.project == null){
                UserTaskDAO().deleteUserTask(Task(taskId, "", "", DateTime()))
            } else {
                ProjectTaskDAO().deleteProjectTask(Task(taskId, "", "", DateTime()))
            }
            this.finish()
        }
    }

    companion object {

        val ARG1 = "task"

        fun showTask(context: Context, task: Task) {
            val intent = Intent(context, TaskShowActivity::class.java).apply {
                putExtra(ARG1, task.id)
            }
            context.startActivity(intent)
        }

    }
}

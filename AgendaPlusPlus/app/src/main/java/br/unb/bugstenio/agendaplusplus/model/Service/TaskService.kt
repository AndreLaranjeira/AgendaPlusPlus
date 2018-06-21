package br.unb.bugstenio.agendaplusplus.model.Service

import br.unb.bugstenio.agendaplusplus.model.Object.*
import java.util.*

class TaskService {

    fun getUserTasks(user: User): List<Task> = throw NotImplementedError("Implemente pegar user tasks")
    fun getUserTasksByDate(user: User, date: Date): List<Task> = throw NotImplementedError("Implemente pegar user tasks por dia")
    fun getProjectTasks(project: Project): List<Task> = throw NotImplementedError("Implemente pegar project tasks")
    fun updateTask(task: Task): Boolean = throw NotImplementedError("Implemente editar task")
    fun deleteTask(task: Task): Boolean = throw NotImplementedError("Implemente deletar task")
}
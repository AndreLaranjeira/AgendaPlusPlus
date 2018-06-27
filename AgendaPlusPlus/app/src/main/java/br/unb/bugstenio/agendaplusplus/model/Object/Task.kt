package br.unb.bugstenio.agendaplusplus.model.Object

import android.util.Log
import org.joda.time.*
import org.json.JSONArray
import org.json.JSONObject

// Classe para tarefas simples a serem agendadas.

data class Task(
        val id: Long = 0,
        val title: String,
        val description: String,
        val limitDate: DateTime,
        val taskDone : DateTime? = null,
        val externalId: Long? = null
)

fun JSONObject.parseUserTask(): Task {
    try {
        return Task(
                (this["id_task"] as Int).toLong(),
                this["task_title"] as String,
                this["task_description"] as String,
                DateTime(this["task_limit"] as Long),
                DateTime(this["task_done"] as Long?),
                (this["fk_user"] as Int).toLong()
        )
    } catch (e: Exception) {
        Log.e("User Parse", "Não pode dar parse em task de user")
        return Task(
                (this["id_task"] as Int).toLong(),
                this["task_title"] as String,
                this["task_description"] as String,
                DateTime(this["task_limit"] as Long),
                null,
                (this["fk_user"] as Int).toLong()
        )
    }
}

fun JSONObject.parseProjectTask(): Task {
    try{
        return Task(
                (this["id_task"] as Int).toLong(),
                this["task_title"] as String,
                this["task_description"] as String,
                DateTime(this["task_limit"] as Long),
                DateTime(this["task_done"] as Long?),
                (this["fk_project"] as Int).toLong()
        )
    } catch (e: Exception) {
        Log.e("Project Parse", "Não pode dar parse em task de projeto")
        return Task(
                (this["id_task"] as Int).toLong(),
                this["task_title"] as String,
                this["task_description"] as String,
                DateTime(this["task_limit"] as Long),
                null,
                (this["fk_project"] as Int).toLong()
        )
    }

}

fun JSONArray.parseUserTasks(): List<Task> {
    val tasks: MutableList<Task> = mutableListOf()
    for(i in 0 until this.length()){
        try {
            tasks.add((this[i] as JSONObject).parseUserTask())
        } catch (e: Exception) {
            Log.e("Parse", "UserTasks JSONArray could not be parsed", e)
        }
    }
    return tasks
}

fun JSONArray.parseProjectTasks(): List<Task> {
    val tasks: MutableList<Task> = mutableListOf()
    for(i in 0 until this.length()){
        try {
            tasks.add((this[i] as JSONObject).parseProjectTask())
        } catch (e: Exception) {
            Log.e("Parse", "ProjectTasks JSONArray could not be parsed", e)
        }
    }
    return tasks
}

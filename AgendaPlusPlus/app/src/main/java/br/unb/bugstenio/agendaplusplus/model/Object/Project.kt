package br.unb.bugstenio.agendaplusplus.model.Object

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

// Classe de projetos (conjunto de tarefas e eventos que envolvam mais de uma pessoa).

data class Project(
        val id: Long = 0,
        val title: String,
        val description: String? = null,
        val isActive: Boolean,
        val groupId: Long,
        val adminId: Long
)

fun JSONObject.parseProject(): Project {
    val description = this["project_description"] as String
    return Project(
            (this["id_project"] as Int).toLong(),
            this["project_title"] as String,
            if(description != "null") description else "",
            this["is_active"] as Boolean,
            (this["fk_group"] as Int).toLong(),
            (this["fk_admin"] as Int).toLong()
    )
}

fun JSONArray.parseProjects(): List<Project> {
    val projects: MutableList<Project> = mutableListOf()
    for(i in 0 until this.length()){
        try {
            projects.add((this[i] as JSONObject).parseProject())
        } catch (e: Exception) {
            Log.e("Parse", "Projects JSONArray could not be parsed", e)
        }
    }
    return projects
}
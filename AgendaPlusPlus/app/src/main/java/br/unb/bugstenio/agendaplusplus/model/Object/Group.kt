package br.unb.bugstenio.agendaplusplus.model.Object

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

data class Group(
        val id: Long = 0,
        val title: String,
        val description: String? = null
)

fun JSONObject.parseGroup(): Group {
    return Group(
            (this["id_group"] as Int).toLong(),
            this["group_title"] as String,
            this["group_description"] as String
    )
}

fun JSONArray.parseGroups(): List<Group> {
    val groups: MutableList<Group> = mutableListOf()
    for(i in 0 until this.length()){
        try {
            groups.add((this[i] as JSONObject).parseGroup())
        } catch (e: Exception) {
            Log.e("Parse", "Groups JSONArray could not be parsed", e)
        }
    }
    return groups
}

data class UserGroup(val group: Long, val user: Long, val isAdmin: Boolean)

fun JSONObject.parseUserGroup(): UserGroup{
    return UserGroup(
            (this["fk_group"] as Int).toLong(),
            (this["fk_user"] as Int).toLong(),
            (this["is_admin"] as Boolean)
    )
}

fun JSONArray.parseUserGroups(): List<UserGroup> {
    val groups: MutableList<UserGroup> = mutableListOf()
    for(i in 0 until this.length()){
        try {
            groups.add((this[i] as JSONObject).parseUserGroup())
        } catch (e: Exception) {
            Log.e("Parse", "UserGroups JSONArray could not be parsed", e)
        }
    }
    return groups
}
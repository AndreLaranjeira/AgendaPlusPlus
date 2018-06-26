package br.unb.bugstenio.agendaplusplus.model.Object

import android.util.Log
import android.util.Range
import org.joda.time.*
import org.json.JSONArray
import org.json.JSONObject

// Classe para representar um usuário do aplicativo.

data class User(
        val id: Long = 0,
        val username: String,
        val email: String,
        val password: String,
        val birth_date: DateTime
)

fun JSONObject.parseUser(): User {
    return User(
            (this["id_user"] as Int).toLong(),
            this["username"] as String,
            this["email"] as String,
            this["password"] as String,
            DateTime(this["birth_date"] as Long)
    )
}

fun JSONArray.parseUsers(): List<User> {
    val users: MutableList<User> = mutableListOf()
    for(i in 0 until this.length()){
        try {
            users.add((this[i] as JSONObject).parseUser())
        } catch (e: Exception) {
            Log.e("Parse", "Não deu pra fazer parse de um JSONArray", e)
        }
    }
    return users
}
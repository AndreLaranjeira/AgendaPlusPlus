package br.unb.bugstenio.agendaplusplus.model.Object

import org.joda.time.*
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
            (this["id_user"] as Integer).toLong(),
            this["username"] as String,
            this["email"] as String,
            this["password"] as String,
            DateTime(this["birth_date"] as Long)
    )
}
package br.unb.bugstenio.agendaplusplus.model.Object

import org.joda.time.*

// Classe para representar um usuário do aplicativo.

data class User(
        val id: Long = 0,
        val username: String,
        val email: String,
        val password: String,
        val birth_date: DateTime
)
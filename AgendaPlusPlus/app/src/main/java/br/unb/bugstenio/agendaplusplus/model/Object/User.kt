package br.unb.bugstenio.agendaplusplus.model.Object

import java.util.*

// Classe para representar um usuário do aplicativo.

class User(username: String, email: String, password: String, birth_date: Date) {

    val agenda: MutableList<Agendable> = mutableListOf()

}

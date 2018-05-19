package br.unb.bugstenio.agendaplusplus.model

import java.util.*

// Classe para representar uma agenda.

class User(username: String, email: String, password: String, birth_date: Date) {

    val items: List<Agendable> = emptyList()

}
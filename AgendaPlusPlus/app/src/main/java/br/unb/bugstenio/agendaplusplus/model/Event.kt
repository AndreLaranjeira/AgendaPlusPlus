package br.unb.bugstenio.agendaplusplus.model

import java.util.*

// Classe para eventos a serem anotados na agenda.

class Event(name: String, description: String, date: Date) : Agendable(name, description) {

    val remainders: List<Date> = emptyList()

}
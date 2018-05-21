package br.unb.bugstenio.agendaplusplus.model

import java.util.*

// Classe para eventos a serem anotados na agenda.

class Event(title: String, description: String, eventDate: Date) : Agendable(title, description) {

    var notificationDate: Date? = null

}
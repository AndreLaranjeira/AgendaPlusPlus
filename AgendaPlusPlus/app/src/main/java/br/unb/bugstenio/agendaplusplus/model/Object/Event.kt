package br.unb.bugstenio.agendaplusplus.model.Object

import java.util.*

// Classe para eventos a serem anotados na agenda.

data class Event(
        val id: Long = 0,
        val title: String,
        val description: String,
        val eventDate: Date,
        val eventNotification: Date?,
        val projectId: Long? = null
)
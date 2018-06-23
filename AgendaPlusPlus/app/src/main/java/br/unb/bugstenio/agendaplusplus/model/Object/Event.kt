package br.unb.bugstenio.agendaplusplus.model.Object

import org.joda.time.*

// Classe para eventos a serem anotados na agenda.

data class Event(
        val id: Long = 0,
        val title: String,
        val description: String,
        val eventDate: DateTime,
        val eventNotification: DateTime? = null,
        val externalId: Long
)
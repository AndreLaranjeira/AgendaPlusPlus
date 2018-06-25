package br.unb.bugstenio.agendaplusplus.model.Object

import org.joda.time.*

// Classe para tarefas simples a serem agendadas.

data class Task(
        val id: Long = 0,
        val title: String,
        val description: String,
        val limitDate: DateTime,
        val taskDone : DateTime? = null,
        val externalId: Long
)
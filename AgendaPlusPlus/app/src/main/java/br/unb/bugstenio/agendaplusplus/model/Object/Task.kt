package br.unb.bugstenio.agendaplusplus.model.Object

import java.util.*

// Classe para tarefas simples a serem agendadas.

data class Task(
        val id: Long = 0,
        val title: String,
        val description: String,
        val limitDate: Date? = null,
        val taskDone : Date? = null,
        val projectId: Long? = null
)
package br.unb.bugstenio.agendaplusplus.model

import java.util.*

// Classe para tarefas simples a serem agendadas.

class Task(title: String, description: String, limitDate: Date) : Agendable(title, description) {

    var finishedOn: Date? = null

}
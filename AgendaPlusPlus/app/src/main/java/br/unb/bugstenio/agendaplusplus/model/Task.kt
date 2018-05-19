package br.unb.bugstenio.agendaplusplus.model

import java.util.*

// Classe para tarefas simples a serem agendadas.

class Task(name: String, description: String, date: Date) : Agendable(name, description) {

    var finished_on: Date? = null

}
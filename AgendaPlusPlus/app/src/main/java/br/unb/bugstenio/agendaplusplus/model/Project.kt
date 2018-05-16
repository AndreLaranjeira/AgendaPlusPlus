package br.unb.bugstenio.agendaplusplus.model

import java.util.*

// Classe de projetos (conjunto de tarefas e eventos que envolvam mais de uma pessoa).

class Project(name: String, group: Group) : Agendable(name) {

    val tasks : List<Task> = emptyList()
    val events : List<Event> = emptyList()

}
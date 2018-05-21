package br.unb.bugstenio.agendaplusplus.model

import java.util.*

// Classe de projetos (conjunto de tarefas e eventos que envolvam mais de uma pessoa).

class Project(title: String, description: String, group: Group, admin: User) : Agendable(title,
              description) {

    val tasks : MutableList<Task> = mutableListOf()
    val events : MutableList<Event> = mutableListOf()

}
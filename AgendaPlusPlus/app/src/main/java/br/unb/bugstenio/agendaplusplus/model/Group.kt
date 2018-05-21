package br.unb.bugstenio.agendaplusplus.model

class Group(title: String, description: String, admins: MutableList<User>) {

    val users: MutableList<User> = mutableListOf()

}
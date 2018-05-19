package br.unb.bugstenio.agendaplusplus.model

class Group(title: String, description: String, admin: User) {

    val users: List<User> = emptyList()

}
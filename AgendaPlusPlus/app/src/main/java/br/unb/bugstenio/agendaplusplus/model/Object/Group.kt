package br.unb.bugstenio.agendaplusplus.model.Object

data class Group(
        val id: Long = 0,
        val title: String,
        val description: String? = null
)
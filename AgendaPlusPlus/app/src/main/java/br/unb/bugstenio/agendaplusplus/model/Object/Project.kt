package br.unb.bugstenio.agendaplusplus.model.Object

// Classe de projetos (conjunto de tarefas e eventos que envolvam mais de uma pessoa).

data class Project(
        val id: Long = 0,
        val title: String,
        val description: String? = null,
        val isActive: Boolean,
        val groupId: Long,
        val adminId: Long
)
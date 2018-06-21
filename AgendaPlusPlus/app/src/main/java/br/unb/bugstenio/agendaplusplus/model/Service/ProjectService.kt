package br.unb.bugstenio.agendaplusplus.model.Service

import br.unb.bugstenio.agendaplusplus.model.Object.*

class ProjectService {

    fun getUserProjects(user: User): List<Project> = throw NotImplementedError("Implemente pegar user Projects")
    fun getProjectProject(project: Project): Project = throw NotImplementedError("Implemente pegar project Projects")
    fun updateProject(Project: Project): Boolean = throw NotImplementedError("Implemente editar Project")
    fun deleteProject(Project: Project): Boolean = throw NotImplementedError("Implemente deletar Project")

}
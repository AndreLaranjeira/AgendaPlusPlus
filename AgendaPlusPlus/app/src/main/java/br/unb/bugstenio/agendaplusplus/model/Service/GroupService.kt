package br.unb.bugstenio.agendaplusplus.model.Service

import br.unb.bugstenio.agendaplusplus.model.Object.*

class GroupService {

    fun getUserGroups(user: User): List<Group> = throw NotImplementedError("Implemente pegar user Groups")
    fun getProjectGroup(project: Project): Group = throw NotImplementedError("Implemente pegar project Groups")
    fun updateGroup(Group: Group): Boolean = throw NotImplementedError("Implemente editar Group")
    fun deleteGroup(Group: Group): Boolean = throw NotImplementedError("Implemente deletar Group")

}
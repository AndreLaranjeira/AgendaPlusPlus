package br.unb.bugstenio.agendaplusplus.model.Service

import br.unb.bugstenio.agendaplusplus.model.Object.*

class EventService {

    fun getUserEvents(user: User): List<Event> = throw NotImplementedError("Implemente pegar user Events")
    fun getProjectEvents(project: Project): List<Event> = throw NotImplementedError("Implemente pegar project Events")
    fun updateEvent(Event: Event): Boolean = throw NotImplementedError("Implemente editar event")
    fun deleteEvent(Event: Event): Boolean = throw NotImplementedError("Implemente deletar event")

}
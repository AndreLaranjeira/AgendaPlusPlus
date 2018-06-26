package br.unb.bugstenio.agendaplusplus.model.Util



open class NetworkHandler {
    protected val service = ServiceVolley()
    protected val apiController = APIController(service)
}
package br.unb.bugstenio.agendaplusplus.model.Util

import org.json.JSONObject

class APIController constructor(serviceInjection: ServiceInterface): ServiceInterface {
    private val service: ServiceInterface = serviceInjection

    override fun post(path: String?, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {
        service.post(path, params, completionHandler)
    }

    override fun update(path: String?, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {
        service.update(path, params, completionHandler)
    }

}
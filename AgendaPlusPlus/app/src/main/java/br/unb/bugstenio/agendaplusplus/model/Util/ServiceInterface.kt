package br.unb.bugstenio.agendaplusplus.model.Util

import org.json.JSONObject
import java.nio.channels.CompletionHandler

interface ServiceInterface {
    fun post(path: String?, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit)
}
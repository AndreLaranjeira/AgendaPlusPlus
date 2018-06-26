package br.unb.bugstenio.agendaplusplus.model.Util

import org.json.JSONArray
import org.json.JSONObject
import java.nio.channels.CompletionHandler

interface ServiceInterface {
    fun post(path: String?, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit)
    fun update(path: String?, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit)
    fun get(path: String?, completionHandler: (response: JSONObject?) -> Unit)
    fun getMany(path: String?, completionHandler: (response: JSONArray?) -> Unit)
}
package br.unb.bugstenio.agendaplusplus.model.Util

import android.util.Log
import br.unb.bugstenio.agendaplusplus.model.DAO.UserCallback
import com.android.volley.*
import com.android.volley.toolbox.*
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.*

class ServiceVolley : ServiceInterface {
    val TAG = ServiceVolley::class.java.simpleName
    val basePath = "http://7fccc794.ngrok.io"

    override fun post(path: String?, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {
        val jsonObjReq = object : JsonObjectRequest(Method.POST, basePath + path, params,
                Response.Listener<JSONObject> { response ->
                    Log.d(TAG, "/POST request OK! Response: $response")
                    completionHandler(response)
                },
                Response.ErrorListener { error ->
                    VolleyLog.e(TAG, "/POST request fail! Error: ${error.message}")
                    completionHandler(null)
                }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
            override fun deliverError(error: VolleyError?) {
                Log.e("SERVER ERROR: ", "POST Request failed. Could not connect to server")
            }
        }

        BackendVolley.instance?.addToRequestQueue(jsonObjReq, TAG)
    }

    override fun update(path: String?, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {
        val jsonObjReq = object : JsonObjectRequest(Method.PUT, basePath + path, params,
                Response.Listener<JSONObject> { response ->
                    Log.d(TAG, "/PUT request OK! Response: $response")
                    completionHandler(response)
                },
                Response.ErrorListener { error ->
                    VolleyLog.e(TAG, "/PUT request fail! Error: ${error.message}")
                    completionHandler(null)
                }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
            override fun deliverError(error: VolleyError?) {
                Log.e("SERVER ERROR: ", "PUT Request failed. Could not connect to server")
            }
        }

        BackendVolley.instance?.addToRequestQueue(jsonObjReq, TAG)
    }

    override fun delete(path: String?, completionHandler: (response: JSONObject?) -> Unit) {
        val ObjReq = object : JsonObjectRequest(Method.DELETE, basePath + path, null,
                Response.Listener<JSONObject> { response ->
                    Log.d(TAG, "/DELETE request OK! Response: $response")
                    completionHandler(response)
                },
                Response.ErrorListener { error ->
                    VolleyLog.e(TAG, "/DELETE request fail! Error: ${error.message}")
                    completionHandler(null)
                }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
            override fun deliverError(error: VolleyError?) {
                Log.e("SERVER ERROR: ", "DELETE Request failed. Could not connect to server")
            }
        }
        BackendVolley.instance?.addToRequestQueue(ObjReq, TAG)
    }


    override fun get(path: String?, completionHandler: (response: JSONObject?) -> Unit) {
        val jsonObjReq = object : JsonObjectRequest(Method.GET, basePath + path, null,
                Response.Listener<JSONObject> { response ->
                    Log.d(TAG, "/GET request OK! Response: $response")
                    completionHandler(response)
                },
                Response.ErrorListener { error ->
                    Log.e(TAG, "/GET request fail! Error: ${error.message}")
                    completionHandler(null)
                }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }
        BackendVolley.instance?.addToRequestQueue(jsonObjReq, TAG)
    }

    override fun getMany(path: String?, completionHandler: (response: JSONArray?) -> Unit) {
        val jsonObjReq = object : JsonArrayRequest(Method.GET, basePath + path, null,
                Response.Listener<JSONArray> { response ->
                    Log.d(TAG, "/GET request OK! Response: $response")
                    completionHandler(response)
                },
                Response.ErrorListener { error ->
                    VolleyLog.e(TAG, "/GET request fail! Error: ${error.message}")
                    completionHandler(null)
                }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }

            override fun deliverError(error: VolleyError?) {
                Log.e("SERVER ERROR: ", "GET Request failed. Could not connect to server")
            }
        }
        BackendVolley.instance?.addToRequestQueue(jsonObjReq, TAG)
    }
}
package br.unb.bugstenio.agendaplusplus

import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_placeholder.*


class PlaceholderFragment : Fragment() {

    private var word : String? = null
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            word = it.getString("word")
        }
        words.text = word
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_placeholder, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.i("Ue", "quem é meu pai? $context")
    }
}

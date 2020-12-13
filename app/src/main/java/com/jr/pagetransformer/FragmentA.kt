package com.jr.pagetransformer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentA : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getInt(POSITION)?.let { position ->
            val text = Content.values()[position].text
            view.tag = position
            val contentTextView = view.findViewById<TextView>(R.id.tvContent)
            contentTextView.text = text
        }
    }

    companion object {
        private const val POSITION = "FragmentA.POSITION"

        @JvmStatic
        fun newInstance(position: Int): FragmentA {
            val args = Bundle()
            args.putInt(POSITION, position)
            val f = FragmentA()
            f.arguments = args
            return f
        }
    }
}
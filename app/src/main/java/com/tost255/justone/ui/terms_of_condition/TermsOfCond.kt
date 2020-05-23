package com.tost255.justone.ui.terms_of_condition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tost255.justone.R

class TermsOfCond : Fragment() {

    private lateinit var termsOfCondViewModel: TermsOfCondViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        termsOfCondViewModel =
                ViewModelProviders.of(this).get(TermsOfCondViewModel::class.java)
        val root = inflater.inflate(R.layout.term_cond_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        termsOfCondViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}

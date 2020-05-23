package com.tost255.justone.ui.opinion

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tost255.justone.R

class Opinion : Fragment() {

    companion object {
        fun newInstance() = Opinion()
    }

    private lateinit var viewModel: OpinionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.opinion_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OpinionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

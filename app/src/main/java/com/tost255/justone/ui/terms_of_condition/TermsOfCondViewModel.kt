package com.tost255.justone.ui.terms_of_condition

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TermsOfCondViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Tutaj będą informacje prawne, regulaminy"
    }
    val text: LiveData<String> = _text
}
package com.misoca.textsimulator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val notoSansEnabled: MutableLiveData<Boolean> =  MutableLiveData(true)
    val includeFontPaddingEnabled: MutableLiveData<Boolean> =  MutableLiveData(true)
    val boldEnabled: MutableLiveData<Boolean> =  MutableLiveData(false)
    val sampleText: MutableLiveData<String> =  MutableLiveData("Sample Text 1234")
    val fontSize: MutableLiveData<String> =  MutableLiveData("24")
}

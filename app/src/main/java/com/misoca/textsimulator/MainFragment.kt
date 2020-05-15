package com.misoca.textsimulator

import android.graphics.Typeface
import android.graphics.fonts.FontFamily
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.misoca.textsimulator.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment(), LifecycleOwner {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding = MainFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = this@MainFragment.viewModel
        }
        viewModel.boldEnabled.observe(this, Observer{ setFont() })
        viewModel.includeFontPaddingEnabled.observe(this, Observer{ setFont() })
        viewModel.notoSansEnabled.observe(this, Observer{ setFont() })
        viewModel.fontSize.observe(this, Observer{ setFontSize(it) })
        viewModel.sampleText.observe(this, Observer {
            text.text = it
        })
        return binding.root
    }

    private fun setFont() {
        val font = if (viewModel.notoSansEnabled.value!!) {
            ResourcesCompat.getFont(requireContext(), R.font.app_font)
        } else {
            Typeface.DEFAULT
        }
        val style = if (viewModel.boldEnabled.value!!) Typeface.BOLD else Typeface.NORMAL
        text.setTypeface(font, style)
        text.includeFontPadding = viewModel.includeFontPaddingEnabled.value!!
    }

    private fun setFontSize(size: String) {
        val textSize = size.toFloatOrNull() ?: return
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
    }

}

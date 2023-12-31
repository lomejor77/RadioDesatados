package com.radiodesatados.radiodesatados.UI

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.radiodesatados.radiodesatados.R
import com.radiodesatados.radiodesatados.databinding.FragmentHomeBinding
import com.radiodesatados.radiodesatados.databinding.FragmentSplahBinding
import org.w3c.dom.Text

class SplahFragment : Fragment() {
    lateinit var binding: FragmentSplahBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splahFragment_to_homeFragment)
        }, 3000)
        binding = FragmentSplahBinding.inflate(layoutInflater, container, false)
        SplashView()


        return binding.root
    }

    private fun SplashView() {
        val animTop = AnimationUtils.loadAnimation(this.requireContext(),R.anim.from_top)
        val animBottom = AnimationUtils.loadAnimation(this.requireContext(),R.anim.from_bottom)

    }


}
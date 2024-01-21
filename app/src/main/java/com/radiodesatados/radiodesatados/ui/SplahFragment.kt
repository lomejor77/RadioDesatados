package com.radiodesatados.radiodesatados.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.radiodesatados.radiodesatados.R
import com.radiodesatados.radiodesatados.databinding.FragmentSplahBinding

class SplahFragment : Fragment() {
    lateinit var binding: FragmentSplahBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler(Looper.getMainLooper()).postDelayed({
            lifecycleScope.launchWhenResumed {
                findNavController().navigate(R.id.action_splahFragment_to_homeFragment)
            }
        }, 3500)
        binding = FragmentSplahBinding.inflate(layoutInflater, container, false)
       // SplashView()
       textAnimate()

        return binding.root
    }

    private fun textAnimate() {

        // Asigna tu TextView o cualquier vista que desees animar
        val titleTextView: View = binding.imageView
        // Crea una animación que desplaza la vista hacia arriba y hacia abajo
        val animator = ObjectAnimator.ofFloat(
            titleTextView,
            "translationY",
            50f,
            -50f
        )

        // Configura la duración de la animación (en milisegundos)
        animator.duration = 5000

        // Configura el interpolador para un movimiento uniforme
        animator.interpolator = LinearInterpolator()

        // Repite la animación indefinidamente
        animator.repeatCount = ObjectAnimator.INFINITE
        animator.repeatMode = ObjectAnimator.REVERSE

        // Inicia la animación
        animator.start()
    }

    /**private fun SplashView() {
        val animTop = AnimationUtils.loadAnimation(this.requireContext(),R.anim.from_top)
        val animBottom = AnimationUtils.loadAnimation(this.requireContext(),R.anim.from_bottom)

    }*/


}
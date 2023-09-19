package com.radiodesatados.radiodesatados.UI

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.radiodesatados.radiodesatados.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
   lateinit var binding: FragmentHomeBinding
   private lateinit var MPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        Radio()

        return binding.root
    }

    private fun Radio() {
        var url = "https://stream-150.zeno.fm/f0w58gzhrc9uv"
        MPlayer = MediaPlayer()
        MPlayer.setDataSource(url)
        MPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        MPlayer.prepareAsync()

        MPlayer.setOnPreparedListener {
            Play(this)
        }
    }

    private fun Play(context: HomeFragment) {

         binding.btnPlay.setOnClickListener {
             MPlayer.start()
             Toast.makeText(requireActivity(), "Escuchando...", Toast.LENGTH_SHORT).show()
         }

        binding.btnPause.setOnClickListener {
            MPlayer.pause()
            Toast.makeText(requireActivity(), "En Pausa", Toast.LENGTH_SHORT).show()
        }
    }


}
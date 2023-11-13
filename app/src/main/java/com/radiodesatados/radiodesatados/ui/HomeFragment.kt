package com.radiodesatados.radiodesatados.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.radiodesatados.radiodesatados.R
import com.radiodesatados.radiodesatados.databinding.FragmentHomeBinding



class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var MPlayer: MediaPlayer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentHomeBinding.inflate(layoutInflater,container,false)


        Radio()
        whatsapp()

        return binding.root



    }


    private fun Radio() {

        val url = "https://stream-150.zeno.fm/f0w58gzhrc9uv"
        MPlayer = MediaPlayer()
        MPlayer.setDataSource(url)
        MPlayer.setAudioAttributes(
            AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
        )
        MPlayer.setOnErrorListener { mediaPlayer, what, extra -> // logging stuff
            //Log.e("asdasd","asdsad")
            Toast.makeText(requireActivity(), "Revisa tu conexion a internet", Toast.LENGTH_SHORT)
                .show()
            false
        }

        //MPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        MPlayer.prepareAsync()

        MPlayer.setOnPreparedListener {
            play(this)
        }
    }

    private fun play(context: HomeFragment) {

        binding.btnPlay.setOnClickListener {

            if(MPlayer.isPlaying)
            {
                MPlayer.pause()
                Toast.makeText(requireActivity(), "En Pausa", Toast.LENGTH_SHORT).show()
                binding.btnPlay.setBackgroundResource(R.drawable.baseline_play_circle_filled_24)
            }else{
                MPlayer.start()
                Toast.makeText(requireActivity(), "Escuchando...", Toast.LENGTH_SHORT).show()
                binding.btnPlay.setBackgroundResource(R.drawable.baseline_pause_circle_outline_24)
            }
        }
    }

    private fun whatsapp() {
        binding.whatsapp.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.setPackage("com.whatsapp")
            intent.putExtra(Intent.EXTRA_TEXT, "Bienvenidos a Radio Desatados.")
            try {
                startActivity(intent)
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    requireActivity(),
                    "La aplicación Whastapp no se encuentra instalada en el dispositivo.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}
package com.radiodesatados.radiodesatados.ui

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.radiodesatados.radiodesatados.R
import com.radiodesatados.radiodesatados.databinding.FragmentHomeBinding



class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var MPlayer: MediaPlayer
    private lateinit var  audioManager: AudioManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        Radio()
        binding.volumeId.max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        binding.volumeId.progress = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        //whatsapp()

        return binding.root



    }

    private fun Radio() {
        this.audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val url = "https://stream-150.zeno.fm/f0w58gzhrc9uv"
        MPlayer = MediaPlayer()
        MPlayer.setDataSource(url)
        MPlayer.setAudioAttributes(AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
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
            play()
        }
    }

    private fun play() {

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
            binding.volumeId.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, flags: 0)

                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
        }
    }

    /*private fun whatsapp() {
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
    }*/

}
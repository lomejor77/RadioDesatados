package com.radiodesatados.radiodesatados.ui

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.radiodesatados.radiodesatados.R.drawable
import com.radiodesatados.radiodesatados.databinding.FragmentHomeBinding



class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var mPlayer: MediaPlayer
    private lateinit var  audioManager: AudioManager
    private var isMuted = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentHomeBinding.inflate(layoutInflater,container,false)


        radio()
        binding.volumeId.max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        binding.volumeId.progress = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

        // Botones para mutear/desmutear
        val muteButton = binding.mute
        val unmuteButton = binding.unMute
        muteButton.setOnClickListener {
            toggleMute()
        }

        unmuteButton.setOnClickListener {
            toggleMute()
        }
        //whatsapp()

        return binding.root
    }


    private fun radio() {
        this.audioManager = requireContext().getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val url = "https://stream-150.zeno.fm/f0w58gzhrc9uv"
        mPlayer = MediaPlayer()
        mPlayer.setDataSource(url)
        mPlayer.setOnInfoListener { _, what, _ ->
            if (what == MediaPlayer.MEDIA_INFO_METADATA_UPDATE) {
                val metadataRetriever = MediaMetadataRetriever()
                metadataRetriever.setDataSource(url)

                val title = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
                val artist = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)

                val metadataText = "Title: $title\nArtist: $artist"

                // Actualizar el TextView con la información de la metadata
                binding.metaText.text = metadataText
            }
            true
        }

        mPlayer.setAudioAttributes(AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        mPlayer.setOnErrorListener { _, what, _ -> // logging stuff
            //Log.e("asdasd","asdsad")
            Toast.makeText(requireActivity(), "Revisa tu conexion a internet", Toast.LENGTH_SHORT)
                .show()
            false
        }
        //MPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mPlayer.prepareAsync()

        mPlayer.setOnPreparedListener {
            play()
        }
    }

    private fun play() {

        binding.btnPlay.setOnClickListener {

            if(mPlayer.isPlaying)
            {
                mPlayer.pause()
                Toast.makeText(requireActivity(), "En Pausa", Toast.LENGTH_SHORT).show()
                binding.btnPlay.setBackgroundResource(drawable.baseline_play_circle_filled_24)
            }else{
                mPlayer.start()
                Toast.makeText(requireActivity(), "Estás escuchando Radio Desatados...", Toast.LENGTH_SHORT).show()
                binding.btnPlay.setBackgroundResource(drawable.baseline_pause_circle_outline_24)
            }
            binding.volumeId.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress,  0)

                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
        }
    }

    private fun toggleMute() {
        if (mPlayer != null) {
            if (isMuted) {
                // Habilita el sonido (establece el volumen a su valor original)
                mPlayer!!.setVolume(1f, 1f)
                Toast.makeText(
                    requireActivity(),
                    "Radio con volumen activado.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Mutea el sonido (establece el volumen a 0)
                mPlayer!!.setVolume(0f, 0f)
                Toast.makeText(
                    requireActivity(),
                    "Radio silenciada.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            isMuted = !isMuted
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
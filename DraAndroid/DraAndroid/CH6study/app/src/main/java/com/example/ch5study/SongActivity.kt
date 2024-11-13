import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.widget.ImageButton
import com.example.ch5study.R

class SongActivity : AppCompatActivity() {
    private val sharedMusicViewModel: SharedMusicViewModel by viewModels()

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        val playButton = findViewById<ImageButton>(R.id.song_miniplayer_iv)

        playButton.setOnClickListener {
            val isCurrentlyPlaying = sharedMusicViewModel.isPlaying.value ?: false
            sharedMusicViewModel.setPlaying(!isCurrentlyPlaying)
            if (!isCurrentlyPlaying) {
                restartTrack()
                playButton.setImageResource(R.drawable.btn_miniplay_mvpause) // 일시정지 이미지로 변경
            } else {
                playButton.setImageResource(R.drawable.btn_miniplayer_play) // 재생 이미지로 변경
            }
        }

        sharedMusicViewModel.isPlaying.observe(this) { isPlaying ->
            if (isPlaying) {
                restartTrack()
            }
        }
    }

    private fun restartTrack() {
        lifecycleScope.launch {
            while (sharedMusicViewModel.isPlaying.value == true) {
                delay(1000)
                val currentPosition = sharedMusicViewModel.currentTrackPosition.value ?: 0
                sharedMusicViewModel.updateTrackPosition(currentPosition + 1)
            }
        }
    }
}

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedMusicViewModel : ViewModel() {
    val currentTrackPosition = MutableLiveData<Int>()
    val isPlaying = MutableLiveData<Boolean>()

    init {
        currentTrackPosition.value = 0
        isPlaying.value = false
    }

    fun updateTrackPosition(position: Int) {
        currentTrackPosition.value = position
    }

    fun setPlaying(isPlaying: Boolean) {
        this.isPlaying.value = isPlaying
    }
}

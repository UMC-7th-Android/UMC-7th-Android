import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch5study.SongAdapter
import com.example.ch5study.databinding.FragmentLockerBinding

class LockerFragment : Fragment() {

    // ViewBinding 객체 선언
    private var _binding: FragmentLockerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // ViewBinding 초기화
        _binding = FragmentLockerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 더미 데이터 생성
        val dummySongs = listOf(
            SongAdapter.Song("Lilac", "아이유 (IU)"),
            SongAdapter.Song("Butter", "BTS"),
            SongAdapter.Song("Stay", "Justin Bieber"),
            SongAdapter.Song("Bad Habits", "Ed Sheeran"),
            SongAdapter.Song("Peaches", "Justin Bieber")
        )

        // RecyclerView 설정
        val songAdapter = SongAdapter(dummySongs)
        binding.lockerSavedSongRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.lockerSavedSongRecyclerView.adapter = songAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 메모리 누수를 방지하기 위해 바인딩 해제
    }
}

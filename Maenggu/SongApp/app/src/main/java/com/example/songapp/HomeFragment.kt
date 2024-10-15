package com.example.songapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.songapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment( ){
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.fragmentHomeVp.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,AlbumFragment())
                .commitAllowingStateLoss()
        }


        val bannerAPdapter = BannerVPAdapter(this)
        bannerAPdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAPdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        bannerAPdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        binding.fragmentHomeVp.adapter = bannerAPdapter
        binding.fragmentHomeVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        return binding.root
    }
}
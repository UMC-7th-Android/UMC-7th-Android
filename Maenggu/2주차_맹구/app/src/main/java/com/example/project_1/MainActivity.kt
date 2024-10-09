package com.example.project_1

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemReselectedListener{
    private lateinit var homeFragment: HomeFragment
    private lateinit var editFragment: EditFragment
    private lateinit var dayFragment: DayFragment
    private lateinit var userFragment: UserFragment

    companion object{
        const val TAG: String = "로그"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "MainActivity - onCreate() called")


        bottomnavi.setOnNavigationItemSelectedListerner(this)
    }
    private val onBottomNavigationView = BottomNavigationView.OnNavigationItemReselectedListener {
        when(item.itemId){
            R.id.menu_home ->{
                Log.d(TAG, "MainActivity -홈버튼 클릭")
                homeFragment = HomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, homeFragment).commit()
            }
            R.id.menu_wr ->{
                Log.d(TAG, "MainActivity -wr버튼 클릭")
                editFragment = EditFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, editFragment).commit()

            }
            R.id.menu_cal ->{
                Log.d(TAG, "MainActivity -wr버튼 클릭")
                dayFragment = DayFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, dayFragment).commit

            }
            R.id.menu_user ->{
                Log.d(Tag, "MainActivity -user버튼 ")
                userFragment = UserFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, userFragment).commit


            }
    }


        TODO("Not yet implemented")
    }
}
package uz.bnabiyev.simpletiktokapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import uz.bnabiyev.simpletiktokapp.adapters.ViewPagerAdapter
import uz.bnabiyev.simpletiktokapp.database.MyDbHelper
import uz.bnabiyev.simpletiktokapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        myDbHelper = MyDbHelper(this)
        viewPagerAdapter = ViewPagerAdapter(myDbHelper.getAllReels())
        binding.viewPager.adapter = viewPagerAdapter
    }
}
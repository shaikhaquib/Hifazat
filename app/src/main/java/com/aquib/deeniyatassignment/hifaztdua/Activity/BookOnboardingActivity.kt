package com.aquib.deeniyatassignment.hifaztdua.Activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.aquib.deeniyatassignment.hifaztdua.BookPageIntroFragment
import com.aquib.deeniyatassignment.hifaztdua.R
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer

class BookOnboardingActivity : AppCompatActivity() {

    private lateinit var mPager: ViewPager
    private var mPagerAdapter: PagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_onboarding)

        // Instantiate a ViewPager and a PagerAdapter.
        val sharedPreference = getSharedPreferences("Language", Context.MODE_PRIVATE)
        val x = sharedPreference.getString(SplashActivity.LANGUAGE_CODE, "en");
        mPager = findViewById(R.id.pager)
        mPagerAdapter = BookOnboardingPagerAdapter(
            supportFragmentManager,
            intent.getIntExtra("position", 0),
            intent.getIntExtra("type", 0),
            x
        )
        mPager.adapter = mPagerAdapter
        mPager.clipToPadding = true
        mPager.currentItem = intent.getIntExtra("position", 0)

        mPager.setPageTransformer(true, BookFlipPageTransformer())
    }

    class BookOnboardingPagerAdapter : FragmentPagerAdapter {
        var fragments = arrayListOf<BookPageIntroFragment>()

        constructor(fm: FragmentManager, position: Int, type: Int, x: String?) : super(fm) {
            if (type == 1)
                for (i in SplashActivity.duasforBimari.indices) {
                    var frag = BookPageIntroFragment.newInstance(
                        SplashActivity.duasforBimari.get(i).path + x + SplashActivity.duasforBimari.get(
                            i
                        ).imageName
                    )
                    fragments.add(frag)
                }
            else
                for (i in SplashActivity.duasforHifaazat.indices) {
                    var frag = BookPageIntroFragment.newInstance(
                        SplashActivity.duasforBimari.get(i).path + x + SplashActivity.duasforBimari.get(
                            i
                        ).imageName
                    )
                    fragments.add(frag)
                }
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }
    }
}

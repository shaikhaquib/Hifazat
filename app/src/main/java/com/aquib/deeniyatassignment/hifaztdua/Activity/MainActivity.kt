package com.aquib.deeniyatassignment.hifaztdua.Activity

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aquib.deeniyatassignment.hifaztdua.Adapter.DuasAdapterr
import com.aquib.deeniyatassignment.hifaztdua.Adapter.LanguageAdapter
import com.aquib.deeniyatassignment.hifaztdua.R
import com.aquib.deeniyatassignment.hifaztdua.SimpleDividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.dialoge_language_selection.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DuasAdapterr(this, intent.getIntExtra("type", 0))
        recyclerView.addItemDecoration(SimpleDividerItemDecoration(this))

        val menu: Menu = nav_view.getMenu()
        menu.findItem(R.id.nav_contact)
            .setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener {
                startActivity(Intent(applicationContext,FeedBack::class.java))
                drawer_layout.close()
                true
            })

        menu.findItem(R.id.nav_website)
            .setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener {
                val url = "https://www.deeniyat.com"
                val builder = CustomTabsIntent.Builder();
                val customTabsIntent = builder.build();
                customTabsIntent.launchUrl(this, Uri.parse(url));
                drawer_layout.close()
                true
            })
        menu.findItem(R.id.nav_lang)
            .setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener {
                loadLanguage()
                drawer_layout.close()
                true
            })



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        val sharedPreference = getSharedPreferences("Language", Context.MODE_PRIVATE)
        val x = sharedPreference.getString(SplashActivity.LANGUAGE_CODE, "en");
        val type = intent.getIntExtra("type", 1)
        when(x) {
            "hi" ->
                if (type == 1) {
                    setTitle("बिमारियो से हिफ़ाज़त और शिफ़ा की दुआएँ")
                    toolbar.setSubtitle("हदीस की रोषनी में")
                } else {
                    setTitle("हिफाज़त की दुआ")
                    toolbar.setSubtitle("क़ुरआन और हुज़ूर ﷺ की दुआओं के ज़ारीये")
                }
            "gu" ->
                if (type == 1) {
                    setTitle("બિમારિયો સે હિફ઼ાજ઼ત ઔર શિફ઼ા કી દુઆએઁ")
                    toolbar.setSubtitle("હદીસ કી રોષની મેં")
                } else {
                    setTitle("હિફાજ઼ત કી દુઆ")
                    toolbar.setSubtitle("ક઼ુરઆન ઔર હુજ઼ૂર ﷺ કી દુઆઓં કે જ઼ારીયે ")
                }
            "en" ->
                if (type == 1) {
                    setTitle("Beemariyon se Hifaazat aur Shifa ki Duaayein")
                    toolbar.setSubtitle("Quraan aur Huzoor ﷺ ki Duaaon ke zariye")
                } else {
                    setTitle("Hifaazat ki Dua")
                    toolbar.setSubtitle("Hadees ki roshni mein")
                }
            "ur" ->
                if (type == 1) {
                    toolbar.setTitle("بیماریو سے حفاظت اور شفا کی دعائیں")
                    toolbar.setSubtitle("حدیث کی روشنی میں")
                } else {
                    setTitle("حفاظت کی دعا")
                    toolbar.setSubtitle("قران اور حضور ﷺ کی دعا وں کیے ذرے")
                }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.language){
            loadLanguage()
        }else if (item.itemId == android.R.id.home){
            drawer_layout.open()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadLanguage() {
        val dialog  = Dialog(this)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setContentView(R.layout.dialoge_language_selection)


        val rvLang = dialog.rvLanguage
        rvLang.layoutManager = GridLayoutManager(this, 2)
        rvLang.setHasFixedSize(true)
        rvLang.adapter = LanguageAdapter(
            this, dialog, intent.getIntExtra(
                SplashActivity.LANGUAGE_CODE,
                0
            )
        )


        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)

        dialog.window!!.attributes = lp
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }
}
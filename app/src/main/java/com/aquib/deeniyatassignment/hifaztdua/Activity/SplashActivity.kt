package com.aquib.deeniyatassignment.hifaztdua.Activity

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.aquib.deeniyatassignment.hifaztdua.Models.DuaModel
import com.aquib.deeniyatassignment.hifaztdua.Models.LanguageList
import com.aquib.deeniyatassignment.hifaztdua.R
import java.util.*
import kotlin.collections.ArrayList

class SplashActivity : AppCompatActivity() {
    companion object{
        var languageList = ArrayList<LanguageList>()
        var duasforBimari = ArrayList<DuaModel>()
        var duasforHifaazat = ArrayList<DuaModel>()
        const val LANGUAGE_CODE = "LANGUAGE_CODE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            //doSomethingHere()
            startActivity(Intent(applicationContext, TypesOfDua::class.java))
            finish()
        }, 1000)
        var language  = arrayOf(
            "Transliteration",
            "हिंदी",
            "ગુજરાતી",
            "اردو"
        )
        val languageCode = arrayOf("en", "hi", "gu","ur")
        val bgColor = arrayOf(
            "#FF009688",
            "#E91E63",
            "#9C27B0",
            "#a7b53a",
            "#3F51B5",
            "#2196F3",
            "#009688",
            "#FF5722",
            "#607D8B",
            "#FF9800",
            "#4CAF50"
        )


        for (i in language.indices) {
            val languageModel = LanguageList()
            languageModel.displayName = language.get(i)
            languageModel.langeCode = languageCode[i]
            languageModel.backgroundColor = bgColor[i]
            languageList.add(languageModel)
        }


        setUpDuas()
        setUpDuasforHifaazat()


    }

    private fun setUpDuasforHifaazat() {

        if (duasforHifaazat.size!=0){
            duasforHifaazat.clear()
        }

        val hindi = arrayOf("१. शैतान से हिफ़ाज़त की दुआ", "२. किसी भी चीज़ के शर से हिफ़ाज़त की दुआ", "३. नज़र बद का ईलाज", "४. किसी से ख़तरे या ख़ौफ़ के वक़्त की दुआ", "५. ईमान पर साबित क़दमी की दुआ")
        val urdu = arrayOf("۱ شیطان سے حفاظت کی دعا", "۲کسی بھی چیز کے شر سے حفاظت کی دعا", "۳ نظر بد کا علاج", "۴ کسی سے خطرے یا خوف کے وقت کی دعا", "۵ ایمان پر ثابت قدمی کی دعا")
        val gujarati = arrayOf("૧ શૈતાન સે હિફાઝત કી દુઆ", "૨ કિસી ભી ચીઝ કે શર સે હિફાઝત કી દુઆ", "૩ નઝરે બદ કા ઇલાજ", "૪ કિસી સે ખતરે યા ખૌફ કે વકત કી દુઆ", "૫ ઇમાન પર સાબિત કદમી કી દુઆ")
        val trans = arrayOf("1. Shaitaan se hifaazat ki dua", "2. Kisi bhi cheez ke shar se hifaazat ki dua", "3. Nazare bad ka ilaaj", "4. Kisi se khatre ke khauf ke waqt ki dua", "5. Imaan par saabit qadmi ki dua")
        val path = arrayOf("Duas/hifaazat/", "Duas/hifaazat/", "Duas/hifaazat/", "Duas/hifaazat/", "Duas/hifaazat/")
        val imageName = arrayOf("/d1.png", "/d2.png", "/d3.png", "/d4.png", "/d5.png")

        for (i in hindi.indices) {
            val duaModel = DuaModel()
            duaModel.hindiTitle = hindi.get(i)
            duaModel.gujratiTitle = gujarati.get(i)
            duaModel.transTitle = trans.get(i)
            duaModel.urduTitle = urdu.get(i)
            duaModel.path = path.get(i)
            duaModel.imageName = imageName.get(i)



            duasforHifaazat.add(duaModel)
        }

    }

    private fun setUpDuas() {

        if (duasforBimari.size!=0){
            duasforBimari.clear()
        }

        val hindi = arrayOf("1-दुनिया व आख़िरत की आफियत के लिये सब से बेहतरीन दुआ", "2-बीमारी खत्म करने की दुआ", "3-बदन की सलामती की दुआ", "4-हर तरह की बीमारीयों से हिफाज़त की दुआ", "5-मुसीबतज़दा को देख कर पढने की दुआ")
        val urdu = arrayOf("۱ - دنیا و آخرت کی عافیت کے لیے سب سے بہترین دُعا", "۲ - بیماری سے مکمل شفا", "۳ - بدن کی سلامتی کی دُعا", "۴- ہرطرح کی بیماریوں سے حفاظت کی دعا", "۵ - مصیبت زدہ کو دیکھ کر پڑھنے کی دعا")
        val gujarati = arrayOf("1-દુનિયા વ આખિરત કી આફ઼િયત કે લિયે સબ સે બેહતરીન દુઆ", "2-બીમારી સે મુકમ્મલ શિફા", "3-બદન કી સલામતી કી દુઆ", "4-હર તરહ કી બીમારીયોં સે હિફાઝત કી દુઆ", "5-મુસીબત ઝદા કો દેખ કર પઢને કી દુઆ")
        val trans = arrayOf("1 - Duniya wa Aakhirat ki aafiyat ke liye sab se behtareen dua", "2 - Beemaari se mukammal shifa", "3 - Badan ki salaamati ki dua", "4 - Har tarah ki beemaariyon se hifaazat ki dua", "5 - Museebat zada ko dekh kar padhne ki dua")
        val path = arrayOf("Duas/bimari/", "Duas/bimari/", "Duas/bimari/", "Duas/bimari/", "Duas/bimari/")
        val imageName = arrayOf("/d1.png", "/d2.png", "/d3.png", "/d4.png", "/d5.png")

        for (i in hindi.indices) {
            val duaModel = DuaModel()
            duaModel.hindiTitle = hindi.get(i)
            duaModel.gujratiTitle = gujarati.get(i)
            duaModel.transTitle = trans.get(i)
            duaModel.urduTitle = urdu.get(i)
            duaModel.path = path.get(i)
            duaModel.imageName = imageName.get(i)



            duasforBimari.add(duaModel)
        }


    }
}
package nktori.hiitjump

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nktori.hiitjump.skip.Difficulty

lateinit var skipDifficulty: Difficulty

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}
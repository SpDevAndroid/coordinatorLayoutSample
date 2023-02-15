package saulmm.coordinatorexamples

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FragmentContainerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)


        val transaction = supportFragmentManager.beginTransaction()
        val fragment = CoordinatorWithPagerFragment()
        transaction.add(R.id.frame_container, fragment, "TEST_FRAG")
        transaction.commitAllowingStateLoss()
    }

    companion object {
        fun start(c: Context) {
            c.startActivity(Intent(c, FragmentContainerActivity::class.java))
        }
    }

}
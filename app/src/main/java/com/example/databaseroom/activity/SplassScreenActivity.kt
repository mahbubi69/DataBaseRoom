package com.example.databaseroom.activity

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Space
import com.example.databaseroom.R
import java.lang.ref.WeakReference

class SplassScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splass_screen)

        val task = MyAsyncTask(this)
        task.execute()
    }

    companion object {
        class MyAsyncTask internal constructor(context: SplassScreenActivity) :
            AsyncTask<Void, Void, Void>() {

            private val activityReference: WeakReference<SplassScreenActivity> =
                WeakReference(context)

            override fun onPreExecute() {
                val activity = activityReference.get()
                if (activity == null || activity.isFinishing) return
            }

            override fun onPostExecute(result: Void?) {
                val activity = activityReference.get()
                if (activity == null || activity.isFinishing) return
                if (activity.isFinished()) {
                    activity.startActivity(Intent(activity, MainActivity::class.java))
                    activity.finish()
                }
            }

            override fun doInBackground(vararg p0: Void?): Void? {
                try {
                    Thread.sleep(1000)
                } catch (e: Exception) {
                }
                return null
            }
        }
    }

    private fun isFinished(): Boolean {
        val pref = applicationContext.getSharedPreferences("UserData", MODE_PRIVATE)
        return pref.getBoolean("login", true)
    }
}
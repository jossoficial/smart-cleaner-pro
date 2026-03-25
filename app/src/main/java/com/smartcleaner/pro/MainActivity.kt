package com.smartcleaner.pro

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.webkit.WebView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = WebView(this)

        val btnClean = findViewById<Button>(R.id.btnClean)
        val btnSettings = findViewById<Button>(R.id.btnSettings)

        btnClean.setOnClickListener {
            val deleted = cleanCache(cacheDir)
            webView.clearCache(true)
            Toast.makeText(this, "Archivos limpiados: $deleted", Toast.LENGTH_SHORT).show()
        }

        btnSettings.setOnClickListener {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:com.android.chrome")
            startActivity(intent)
        }
    }

    private fun cleanCache(dir: File): Int {
        var count = 0
        dir.listFiles()?.forEach {
            if (it.isFile) {
                if (it.delete()) count++
            }
        }
        return count
    }
}

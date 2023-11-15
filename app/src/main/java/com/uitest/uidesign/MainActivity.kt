package com.uitest.uidesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.uitest.uidesign.ui.theme.UIDesignTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      UIDesignTheme {
        UIDesignApp()
      }
    }
  }
}
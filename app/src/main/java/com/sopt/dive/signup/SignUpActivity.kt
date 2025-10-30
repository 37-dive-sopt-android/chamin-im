package com.sopt.dive.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.component.text.DiveTitle
import com.sopt.dive.component.textfield.DiveTextField
import com.sopt.dive.ui.component.button.DiveButton
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.KeyStorage

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignUpScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        onSignUpSuccess = { id, pw, nickname, drink ->
                            val resultIntent = Intent().apply {
                                putExtra(KeyStorage.ID, id)
                                putExtra(KeyStorage.PW, pw)
                                putExtra(KeyStorage.NICKNAME, nickname)
                                putExtra(KeyStorage.DRINK, drink)
                            }
                            setResult(Activity.RESULT_OK, resultIntent)
                            finish()
                        }
                    )
                }
            }
        }
    }
}
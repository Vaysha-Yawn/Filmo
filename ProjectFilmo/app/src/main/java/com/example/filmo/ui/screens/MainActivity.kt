package com.example.filmo.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.filmo.MainAct
import com.example.filmo.testData.TestData
import com.example.filmo.ui.theme.FilmoTheme


val exampleData = TestData()

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FilmoTheme {
                MainAct(exampleData.map)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FilmoTheme {
        MainAct(exampleData.map)
    }
}
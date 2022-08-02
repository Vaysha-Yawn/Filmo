package com.example.filmo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.filmo.testData.TestData
import com.example.filmo.ui.LotOfFilms
import com.example.filmo.ui.theme.FilmoTheme


val exampleData = TestData()

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FilmoTheme {
                val textState = remember {
                    mutableStateOf("")
                }
                Column {
                    Search(textState)
                    if (textState.value==""){
                        MainAct(exampleData.map)
                    }else{
                        LotOfFilms(stringResource(id = R.string.answers)+" "+textState.value , exampleData.list)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FilmoTheme {
        LotOfFilms(exampleData.title , exampleData.list)
    }
}


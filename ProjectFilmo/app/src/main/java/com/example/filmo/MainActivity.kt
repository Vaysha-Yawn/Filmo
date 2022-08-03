package com.example.filmo

import android.os.Bundle
import android.telecom.Call
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.filmo.remote.dataClass.Screens
import com.example.filmo.testData.TestData
import com.example.filmo.ui.composableFunctions.Details
import com.example.filmo.ui.composableFunctions.LotOfFilms
import com.example.filmo.ui.composableFunctions.MainAct
import com.example.filmo.ui.composableFunctions.Search
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

                val navigationState = remember {
                    mutableStateOf(Screens.FirstScreen)
                }

                Column {
                    Search(textState)
                    if (textState.value==""){
                        MainAct(exampleData.map)
                    }else{
                        LotOfFilms(stringResource(id = R.string.answers)+" "+textState.value , exampleData.list)
                    }
                }

                Details(film = exampleData.filmMore)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FilmoTheme {
        Details(film = exampleData.filmMore)
    }
}


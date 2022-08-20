package com.example.filmo.ui


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.bundleOf
import com.example.filmo.model.testData.TestData
import com.example.filmo.navigation.DrawScreen
import com.example.filmo.navigation.Screens
import com.example.filmo.retrofit.viewModels.DetailVM
import com.example.filmo.retrofit.viewModels.MainVM
import com.example.filmo.retrofit.viewModels.SearchVM
import com.example.filmo.retrofit.viewModels.SelectionVM
import com.example.filmo.ui.composableFunctions.KeysAreOver
import com.example.filmo.ui.theme.FilmoTheme


val exampleData = TestData()

class MainActivity : ComponentActivity() {

    val mainVM: MainVM by viewModels()
    val selectionVM: SelectionVM by viewModels()
    val detailVM: DetailVM by viewModels()
    val searchVM: SearchVM by viewModels()

    lateinit var nav: MutableState<Screens>
    lateinit var keyIndex: MutableState<Int>
    lateinit var key: MutableState<String>

    var arguments = bundleOf()

    val keysArr = listOf("k_smi0n084", "k_lx2w9d56", "k_mi7e7614", "k_lb8xezv4")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FilmoTheme {
                nav = remember {
                    mutableStateOf(Screens.MainScreen)
                }
                keyIndex = remember {
                    mutableStateOf(0)
                }
                key = remember {
                    mutableStateOf(keysArr[keyIndex.value])
                }
                if (keyIndex.value < 4) {
                    DrawScreen(nav.value, arguments)
                } else {
                    KeysAreOver()
                }
            }
        }
    }

    fun navigate(nameScreen: Screens, arguments: Bundle) {
        this.arguments = arguments
        nav.value = nameScreen
    }

    fun nextKey() {
        keyIndex.value++
        if (keyIndex.value < 4) {
            key.value = keysArr[keyIndex.value]
        }
        Log.e("ddddddd","${keyIndex.value}")
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FilmoTheme {
    }
}




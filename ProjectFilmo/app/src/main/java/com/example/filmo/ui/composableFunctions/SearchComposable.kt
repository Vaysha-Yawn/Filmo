package com.example.filmo.ui.composableFunctions


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmo.R

// Виджет поиска
@Composable
fun Search(state: MutableState<String>) {
    TextField(
        value = state.value,
        onValueChange = { state.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        textStyle = TextStyle(
            fontSize = 20.sp
        ),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "search",
                Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != "") {
                IconButton(onClick = {
                    state.value = ""
                }) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "search",
                        Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )

                }
            }
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search), style = TextStyle(
                    fontSize = 20.sp,
                )
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(30.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.DarkGray,
            leadingIconColor = Color.Gray,
            trailingIconColor = Color.Gray,
            backgroundColor = Color(0xFFEBEBEB),
            focusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Gray,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            placeholderColor = Color.Gray,
        )
    )
}
package com.example.testing

import LoginPage
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.testing.ui.theme.TestingTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val context = LocalContext.current
            val coroutineScope = rememberCoroutineScope()
            val dataStore = StoreUserEmail(context)
            val userEmail = dataStore.getEmail.collectAsState(initial = "")
            //BottomSheetLayout()
            MainScreenView()
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetLayout() {
    val offset = remember { Animatable(Offset.Zero, Offset.VectorConverter) }

    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )

    var isSheetFullScreen by remember { mutableStateOf(false) }
    val roundedCornerRadius = if (isSheetFullScreen) 0.dp else 12.dp
    val modifier = if (isSheetFullScreen)
        Modifier
            .fillMaxSize()
    else
        Modifier.fillMaxWidth()

    BackHandler(modalSheetState.isVisible) {
        coroutineScope.launch { modalSheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
        sheetContent = {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        isSheetFullScreen = !isSheetFullScreen
                    }
                ) {
                    Text(text = "Toggle Sheet Fullscreen")
                }

                Button(
                    onClick = {
                        coroutineScope.launch { modalSheetState.hide() }
                    }
                ) {
                    Text(text = "Hide Sheet")
                }
            }
        }
    ) {
        Scaffold {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Button(
                    onClick = {

                        coroutineScope.launch {
                            if (modalSheetState.isVisible) {
                                modalSheetState.hide()
                            }
                            else {
                                Log.e("Show_","show_"+"show_")
                                modalSheetState.show()
                               // modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                            }
                        }
                    },
                ) {
                    Text(text = "Open Sheet")
                }
            }
        }
    }
}

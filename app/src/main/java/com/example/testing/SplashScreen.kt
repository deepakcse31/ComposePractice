package com.example.testing

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.testing.theme.background
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter",
    "SuspiciousIndentation"
)
@Composable
fun SplashScreen() {
    val context = LocalContext.current

    val dataStore = StoreUserEmail(context)
//    val modalSheetState = rememberModalBottomSheetState(
//        initialValue = ModalBottomSheetValue.Hidden,
//        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
//        skipHalfExpanded = true
//    )
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scope = rememberCoroutineScope()

    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = state,

        sheetContent = {
            // Content of the BottomSheet
                Spacer(modifier = Modifier.height(100.dp))
                Box() {
                    /* sheet content */
                }

            LazyColumn {
                items(50) {
                    ListItem(
                        text = { Text("Item $it") },
                        icon = {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .height(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Rest of the UI")
            Spacer(Modifier.height(20.dp))
            Button(onClick = { scope.launch { state.show() } }) {
                Text("Click to show sheet")
            }
        }
    }




        Scaffold {
            Box(
                //...
            ) {
                var openDialog by remember {
                    mutableStateOf(false) // Initially dialog is closed
                }
                Button(
                    onClick = {
                        Log.e("Clicked","Clicked"+"Clicked")
                       // openDialog = true
                        scope.launch {
                            state.show()
//                            if (state.isVisible)
//                                state.hide()
//                            else
//                                Log.e("Open_","Open_"+"Open_")
//                                state.show()
                               // modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                        }
                    },
                ) {

                    Text(text = "Open Sheet")
                }
            }
        }

//        {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text("Rest of the UI")
//            Spacer(Modifier.height(20.dp))
//            Button(onClick = { scope.launch { modalSheetState.show() } }) {
//                Text("Click to show sheet")
//            }
//        }
    }

    @Composable
    @OptIn(ExperimentalMaterialApi::class)
    fun ModalBottomSheetSample() {

    }


    @ExperimentalMaterialApi
    @Composable
    fun SheetLayout() {

        val sheetState = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
        )

        //Our flag variable
        val showModalSheet = rememberSaveable {
            mutableStateOf(false)
        }

        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = {
                // BottomSheetContent()
            }) {
            //Rest of the Scaffold
        }
    }

    @Composable
    fun BottomSheetContent() {
        Surface(
            modifier = Modifier.height(300.dp),
            color = Color(0xff7353ba)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Modal Bottom Sheet",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp),
                    color = Color.White
                )
                Divider(
                    modifier = Modifier.padding(5.dp),
                    color = Color.White
                )
                Text(
                    text = "MDSLFMDLSKMLKDSMFLSDLKFNLKSDNFLKSDNFNDSLNF,MSD FM DSKF ",
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
@Composable
fun DialogBox2FA(onDismiss: () -> Unit) {
    val contextForToast = LocalContext.current.applicationContext

    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth().clip(RoundedCornerShape(28.dp)),
            elevation = 4.dp,

        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(color = Color(0xFF35898f)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 16.dp),
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "2-Step Verification",
                        alignment = Alignment.Center
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                    text = "2-Step Verification",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                      //  fontFamily = FontFamily(Font(R.font, FontWeight.Bold)),
                        fontSize = 20.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                    text = "Setup 2-Step Verification to add additional layer of security to your account.",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                       // fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal)),
                        fontSize = 14.sp
                    )
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp, start = 36.dp, end = 36.dp, bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF35898f)),
                    onClick = {
                        onDismiss()
                        Toast.makeText(
                            contextForToast,
                            "Click: Setup Now",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                    Text(
                        text = "Setup Now",
                        color = Color.White,
                        style = TextStyle(
//                            fontFamily = FontFamily(
//                                Font(
//                                   // R.font.roboto_medium,
//                                    FontWeight.Medium
//                                )
//                            ),
                            fontSize = 16.sp
                        )
                    )
                }

                TextButton(
                    onClick = {
                        onDismiss()
                        Toast.makeText(
                            contextForToast,
                            "Click: I'll Do It Later",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                    Text(
                        text = "I'll Do It Later",
                        color = Color(0xFF35898f),
                        style = TextStyle(
//                            fontFamily = FontFamily(Font(
//                                   // R.font.roboto_regular,
//                                    FontWeight.Normal
//                                )
//                            ),
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }
    }
}


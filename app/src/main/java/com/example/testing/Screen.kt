package com.example.testing


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.testing.dataclass.Details
import com.example.testing.dataclass.EmployDetails
import kotlinx.coroutines.NonDisposableHandle
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(navController: NavController, string: String?) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val dataStore = StoreUserEmail(context)
    val userEmail = dataStore.getEmail.collectAsState(initial = "")


//    val windowInsets = LocalWindowInsets.current
//    val bottomNavigationHeight = with(LocalDensity.current) {
//        windowInsets.navigationBars.bottom.toDp()
//    }

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )
    val onClick = { /* handle click event */ }

    coroutineScope.launch {
        Log.e("Email","Email"+userEmail.value);
    }
        Log.e("String","String"+string)

    ConstraintLayout(   modifier = Modifier.fillMaxSize()){

        val (scroll, content,headingtv,submit) = createRefs()
        Text(
            text = "Deepak",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .wrapContentHeight()
                .clickable { navController.navigate("splash") }
                .constrainAs(headingtv) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

        Button(modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .constrainAs(submit) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                top.linkTo(parent.top)
            },onClick = {coroutineScope.launch {
            if (modalSheetState.isVisible)
                modalSheetState.hide()
            else
                modalSheetState.show()
            //
        }}) {

        }

        ModalBottomSheetLayout(
            sheetState = modalSheetState,
            sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
            modifier = Modifier.zIndex(10f) .navigationBarsPadding(),
            sheetElevation = 50.dp,

            sheetContent = {

                Column(

                    //...
                ) {
                    //...

                    Button(
                        onClick = {
                            coroutineScope.launch { modalSheetState.hide() }
                        }
                    ) {
                        Text(text = "Hide Sheet", modifier = Modifier.height(200.dp))
                    }
                }
            }
        ) {
//                Scaffold {
//                    Box(
//                        //...
//                    ) {
//                        Button(
//                            onClick = {
//                                coroutineScope.launch {
//                                    if (modalSheetState.isVisible)
//                                        modalSheetState.hide()
//                                    else
//                                        modalSheetState.show()
//                                 //       modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
//                                }
//                            },
//                        ) {
//                            Text(text = "Open Sheet")
//                        }
//                    }
//                }
        }
    }

}

@Composable
fun NetworkScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "My Network Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

@Composable
fun AddPostScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Add Post Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}


@Composable
fun NotificationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Notification Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}


@Composable
fun JobScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Jobs Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}
@Composable
fun DetailsContent() {
    var selectedIndex by remember{mutableStateOf(-1)}
    val employees = remember { Details.EmployDetailsList }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            employees
        ) {
            EmployeeCard(emp = it)
        }
    }
}

@Composable
fun EmployeeCard(emp: EmployDetails) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                Log.e("Data", "data" + emp.title)
            },
        elevation = 2.dp,
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {

        Row(modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.weight(1f),
                Arrangement.Center) {
                Text(
                    text = emp.title,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    text = "Age :- "+emp.age.toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )
                Text(
                    text = "Sex :- "+emp.sex,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )

                Text(
                    text = "Description :- "+emp.description,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )
            }
            Image(painter = painterResource(emp.ImageId), contentDescription = "Profile Image",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(8.dp)
                    .size(110.dp)
                    .clip((CircleShape)))
        }
    }
}

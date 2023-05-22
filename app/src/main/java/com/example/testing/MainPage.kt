package com.example.testing

import LoginPage
import android.annotation.SuppressLint
import android.graphics.ColorSpace
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



typealias SheetContent = @Composable ColumnScope.() -> Unit
@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

@Composable
fun MainScreenView(){
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

   // val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var bottomSheetContent: SheetContent? by remember { mutableStateOf(null) }

    val showBottomSheet: (SheetContent) -> Unit = { content: SheetContent ->
        bottomSheetContent = content
        CoroutineScope(Dispatchers.Default).launch { bottomSheetState.show() }
    }
    val hideBottomSheet: () -> Unit = {
        CoroutineScope(Dispatchers.Default).launch {
            bottomSheetState.hide()
            bottomSheetContent = null
        }
    }

    BackHandler(bottomSheetContent != null) {
        hideBottomSheet()
    }


    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    //val bottomSheetNavigator = rememberBottomSheetNavigator()
    val systemUiController = rememberSystemUiController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    //Log.e("Navcontroller","Navcontroller"+navController.currentDestination)

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val dataStore = StoreUserEmail(context)
    val userEmail = dataStore.getEmail.collectAsState(initial = "")

    val colors = MaterialTheme.colors




    var statusBarColor by remember { mutableStateOf(colors.error) }
//    coroutineScope.launch {
//        Log.e("Email","Email"+userEmail.value);
//    }

    if (userEmail.value?.equals("Deepak",false) == true)
    {
        Log.e("Working","Working"+"Working")

      //  navController.navigate("login")
    }
    else{

        Log.e("Working1","Working1"+"Working1")

    }
    val animatedStatusBarColor by animateColorAsState(
        targetValue = statusBarColor,
        animationSpec = tween()
    )

    navController.addOnDestinationChangedListener { controller, destination, arguments ->
       Log.e("Destination_id","Destination_id"+destination.id)
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        if (navController.currentBackStackEntry?.destination?.route.equals("login"))
        {
            Log.e("Not_WOrking","Not_WOrking"+"Not_WOrking")
            bottomBarState.value=true
        }
        else{
            bottomBarState.value=true
        }
        Log.e("Destination_id","Destination_id"+currentRoute)
    }


//    Scaffold(
//        bottomBar = { Navigaiton(navController = navController, bottomBarState) },
//            content = {
//
//
//
//                Box(
//                    Modifier.fillMaxSize()
//                        .pointerInput(Unit) {
//                            detectTapGestures(
//                                onPress = { /* Nothing to do here */ },
//                                onTap = { coroutineScope.launch { bottomSheetState.show() } }
//                            )
//                        }
//                )
//                {
//                    NavigationGraph(navController)
//                }
//
//
////                Box(modifier = Modifier.padding(it)) {
////                    (
////                            NavigationGraph(navController)
////                    )
////                }
//            }
//        )


    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            // Content of the BottomSheet
            Spacer(modifier = Modifier.height(100.dp))
            Box() {
                /* sheet content */
            }
        }
    ){
        Scaffold( bottomBar = { Navigaiton(navController = navController, bottomBarState) },
            content = {
                Box(
                    Modifier.fillMaxSize()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = { /* Nothing to do here */ },
                                onTap = { coroutineScope.launch {

                                //    bottomSheetState.show()
                                }
                                }
                            )
                        }
                )
                {
                    NavigationGraph(navController)
                }


//                Box(modifier = Modifier.padding(it)) {
//                    (
//                            NavigationGraph(navController)
//                    )
//                }
            }
        )
    }


        //{ NavigationGraph(navController) }

    LaunchedEffect(animatedStatusBarColor) {
        systemUiController.setStatusBarColor(animatedStatusBarColor)
    }
}

//val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
// val bottomSheetNavigator = rememberBottomSheetNavigator()


@Composable
fun Navigaiton(navController: NavController, bottomBarState: MutableState<Boolean>) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.MyNetwork,
        BottomNavItem.AddPost,
        BottomNavItem.Notification,
        BottomNavItem.Jobs
    )
    Log.e("Destination->","Destination"+navController.currentDestination)

    if (bottomBarState.value){
        BottomNavigation(backgroundColor = Color.White, elevation = 10.dp, modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)
            )){
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                    label = { Text(text = item.title,
                        fontSize = 9.sp) },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Black.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = currentRoute == item.screen_route,
                    onClick = {
                        navController.navigate(item.screen_route) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }


}

@Composable
fun NavigationGraph(navController: NavHostController) {
    Log.e("Navcontroller","Navcontroller"+navController.currentDestination)
//    val bottomSheetNavigator = rememberBottomSheetNavigator()
//    ModalBottomSheetLayout(bottomSheetNavigator){
//
//    }
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route+"/{userId}") {
        composable(BottomNavItem.Home.screen_route+"/{userId}") {
            HomeScreen(navController,it.arguments?.getString("userId"))
        }
        composable(BottomNavItem.MyNetwork.screen_route) {
            DetailsContent()
        // NetworkScreen()
        }
        composable(BottomNavItem.AddPost.screen_route) {
            AddPostScreen()
        }
        composable(BottomNavItem.Notification.screen_route) {
            NotificationScreen()
        }
        composable(BottomNavItem.Jobs.screen_route) {
            JobScreen()
        }
        composable(route="splash")
        {

            SplashScreen()

        }
        composable(route="login")
        {backStackEntry ->
            LoginPage(navController = navController)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    MainScreenView()
}
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.testing.LoginViewModel
import com.example.testing.MainViewModel
import com.example.testing.theme.DisneyComposeTheme
import com.example.testing.ui.theme.Purple700
import com.google.gson.JsonObject
import com.taskmo.supermanager.data.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginPage(navController: NavHostController?) {
    var mainViewModel : MainViewModel=MainViewModel()
    val viewModel : LoginViewModel = hiltViewModel()

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Expanded,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )


    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )


    val scope = rememberCoroutineScope()

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
   // mainViewModel.deepak("Deepak")

    mainViewModel._upidata.observeForever {
        Log.e("it","it"+it)
    }

    viewModel.apply {
        lifecycleOwner.lifecycleScope.launch {
            myUiState.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED).collectLatest{
                when (it) {
                    is Resource.Success -> {
                        try {
                            if (!it.value.error) {
                                Log.e("data--->","data--->"+it)
                                //val action = LoginFragmentDirections.actionLoginFragmentToVerifyOtpFragment(number)
                                //superNavigate(action)
                            } else {
                               // showtoast(it.value.message)
                            }
                        } catch (e: Exception) {
                            //showtoast("oops..! Something went wrong.")
                        }
                    }
                    is Resource.Failure ->{

                        Log.e("data1","data1"+"data1")
                    }
                    is Resource.Loading ->{
                       // showprogress()

                        Log.e("data2","data2"+"data2")
                    }
                    else -> {


                        Log.e("data3","data3"+"data3")
                    }
                }
            }
        }
    }


    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetContent = {
            Column(
                //...
            ) {
                Button(
                    onClick = {
                       // coroutineScope.launch { modalSheetState.hide() }
                    }
                ) {
                    Text(text = "Hide Sheet")
                }
            }
        }
    ) {
            Box(
                modifier = Modifier
                    .fillMaxSize().height(330.dp
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Button(
                    onClick = {
//                        coroutineScope.launch {
//                            if (modalSheetState.isVisible)
//                                modalSheetState.hide()
//                            else
//                                modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
//                        }
                    },
                ) {
                    Text(text = "Open Sheet")
                }
            }
    }
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent =  {
            Box(Modifier.fillMaxWidth().height(200.dp).background(Color(0XFF0F9D58))) {
                Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Hello Geek!", fontSize = 20.sp, color = Color.White)
                }
            }
        },
        sheetPeekHeight = 100.dp
    ){}

//    BottomSheetScaffold(
//        scaffoldState = bottomSheetScaffoldState,
//        sheetContent = {
//            Box(
//                Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//            ) {
//                Text(text = "Hello from sheet")
//            }
//        }, sheetPeekHeight = 0.dp
//    ){}
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray, RoundedCornerShape(0.dp))) {
        ClickableText(
            text = AnnotatedString("Sign up here"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            onClick = {
//                scope.launch {
//                    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
//                        bottomSheetScaffoldState.bottomSheetState.expand()
//                    } else {
//                        bottomSheetScaffoldState.bottomSheetState.collapse()
//                    }
//                }
             },
            style = TextStyle(
                fontSize = 14.sp,
               // fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple700
            )
        )
    }
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        Text(text = "Login", style = TextStyle(fontSize = 40.sp))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    Log.e("Button_clicked","Button_cliced"+"button_clicked")
                    scope.launch {

                        Log.e("Button_clicked-1","Button_cliced-1"+"button_clicked-1")
                        modalSheetState.show()
                        bottomSheetScaffoldState.bottomSheetState.expand()
                      /*  if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }

                       */
                    }
                    //BottomSheetLayout(modalSheetState, scope)
                   // Log.e("Usename","username"+username.value.text.toString())
                     //   Apicall(username.value.text.toString(),viewModel)
                           // mainViewModel.deepak("Deepak_Kumar_poddae")
                          //navController.navigate("home/1234")
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("Forgot password?"),
            onClick = { },
            style = TextStyle(
                fontSize = 14.sp,
               // fontFamily = FontFamily.Default
            )
        )
    }

}

private fun Apicall(number: String, viewModel: LoginViewModel) {
    val payerReg = JsonObject()
    payerReg.addProperty("mobile_number", number)
    viewModel.login(payerReg)

}

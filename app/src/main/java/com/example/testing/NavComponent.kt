package com.example.testing

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", R.drawable.baseline_home_24,"home")
    object MyNetwork: BottomNavItem("My Network",R.drawable.baseline_home_24,"my_network")
    object AddPost: BottomNavItem("Post",R.drawable.baseline_home_24,"add_post")
    object Notification: BottomNavItem("Notification",R.drawable.baseline_home_24,"notification")
    object Jobs: BottomNavItem("Jobs",R.drawable.baseline_home_24,"jobs")
}
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = Routes.Login) {
        composable(Routes.Login) { LoginScreen(navController) }
        composable(Routes.Register) { RegisterScreen(navController) }
        composable(Routes.Home) { HomeScreen(navController) }
    }
}

object Routes {
    const val Login = "login"
    const val Register = "register"
    const val Home = "home"
}
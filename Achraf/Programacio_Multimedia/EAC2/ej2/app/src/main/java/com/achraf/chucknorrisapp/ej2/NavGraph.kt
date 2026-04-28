package com.achraf.chucknorrisapp.ej2

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.achraf.chucknorrisapp.ej2.ui.screens.CategoryScreen
import com.achraf.chucknorrisapp.ej2.ui.screens.MainScreen
import com.achraf.chucknorrisapp.ej2.ui.screens.CategoryViewModel
import com.achraf.chucknorrisapp.ej2.ui.screens.MainViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            val mainViewModel: MainViewModel = viewModel()
            MainScreen(
                viewModel = mainViewModel,
                onCategoryClick = { category ->
                    navController.navigate("category/$category")
                }
            )
        }
        composable(
            "category/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            val categoryViewModel: CategoryViewModel = viewModel()
            CategoryScreen(
                category = category,
                viewModel = categoryViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

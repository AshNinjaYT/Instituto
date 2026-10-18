package com.achraf.chucknorrisapp.ej3

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.achraf.chucknorrisapp.ej3.ui.screens.*

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = "main") {

        // Pantalla principal
        composable("main") {
            val mainViewModel: MainViewModel = viewModel(
                factory = MainViewModel.factory(context)
            )
            MainScreen(
                viewModel = mainViewModel,
                onCategoryClick = { category ->
                    navController.navigate("category/$category")
                },
                onFavoritesClick = {
                    navController.navigate("favorites")
                }
            )
        }

        // Pantalla de categoría
        composable(
            "category/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            val categoryViewModel: CategoryViewModel = viewModel(
                factory = CategoryViewModel.factory(context)
            )
            CategoryScreen(
                category = category,
                viewModel = categoryViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        // Pantalla de favoritos (nueva)
        composable("favorites") {
            val favoritesViewModel: FavoritesViewModel = viewModel(
                factory = FavoritesViewModel.factory(context)
            )
            FavoritesScreen(
                viewModel = favoritesViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

package com.example.pokedexapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedexapp.presentation.pokemon_list.PokemonListScreen
import com.example.pokedexapp.presentation.ui.theme.PokedexAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PokemonListScreen.route
                    ) {
                        composable(route = Screen.PokemonListScreen.route){
                            PokemonListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.PokemonDetailScreen.route + "/{dominantColor}/{name}"
//                            ,
//                            arguments = listOf(
//                                navArgument("dominantColor") {
//                                    type = NavType.IntType
//                                },
//                                navArgument("pokemonName") {
//                                    type = NavType.StringType
//                                }
//                            )
                        ) {
//                            val dominantColor = remember {
//                                val color = it.arguments?.getInt("dominantColor")
//                                color?.let { Color(it) } ?: Color.White
//                            }
//                            val pokemonName = remember {
//                                it.arguments?.getString("pokemonName")
//                            }

                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokedexAppTheme {

    }
}
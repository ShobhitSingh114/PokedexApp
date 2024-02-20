package com.example.pokedexapp.presentation.pokemon_list

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.example.pokedexapp.domain.model.PokedexListEntry
import com.example.pokedexapp.domain.use_case.get_all_pokemons.GetAllPokemonUseCase
import com.example.pokedexapp.util.Constants.PAGE_SIZE
import com.example.pokedexapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    val getAllPokemonUseCase: GetAllPokemonUseCase
) : ViewModel() {

    private var curPage = 0

    var pokemonList = mutableStateOf<List<PokedexListEntry>>(listOf())
//    var loadError = mutableStateOf("")
//    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    private var cachedPokemonList = listOf<PokedexListEntry>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)


    private val _state = mutableStateOf(PokemonListState())
    val state: State<PokemonListState> = _state

    init {
        loadPokemonPaginated()
    }

    fun searchPokemonList(query: String) {
        val listToSearch = if(isSearchStarting) {
//            pokemonList.value
            _state.value.pokemonList
        } else {
            cachedPokemonList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
                pokemonList.value = cachedPokemonList
//                _state.value.pokemonList = cachedPokemonList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.pokemonName.contains(query.trim(), ignoreCase = true) ||
                        it.number.toString() == query.trim()
            }
            if(isSearchStarting) {
                cachedPokemonList = pokemonList.value
//                cachedPokemonList = _state.value.pokemonList
                isSearchStarting = false
            }
            pokemonList.value = results
//            _state.value.pokemonList = results
            isSearching.value = true
        }
    }

    fun loadPokemonPaginated() {
//        getAllPokemonUseCase(PAGE_SIZE, curPage * PAGE_SIZE).onEach { result ->
        getAllPokemonUseCase(offset = curPage * PAGE_SIZE, limit = PAGE_SIZE ).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    // meaning is currently loading more entries then result data actually contains
                    // .count = how many pokemon data is there in total
                    endReached.value = curPage * PAGE_SIZE >= result.data!!.count

                    val pokedexEntries = result.data.results.mapIndexed { index, entry ->
                        val number = if(entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"

                        PokedexListEntry(entry.name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        }, url, number.toInt())
                    }
                    curPage++
                    pokemonList.value += pokedexEntries
                    _state.value = PokemonListState(
//                        pokemonList = _state.value.pokemonList + pokedexEntries
//                        pokemonList = pokemonList.value + pokedexEntries
                        pokemonList = pokemonList.value
                    )
                }
                is Resource.Error -> {
                    _state.value = PokemonListState(
                        error = result.message ?: "An Unexpected Error Occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PokemonListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }



        fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }

}
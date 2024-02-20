package com.example.pokedexapp.presentation.pokemon_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.remote.dto.PokemonDetailDto
import com.example.pokedexapp.domain.repository.PokemonRepository
import com.example.pokedexapp.domain.use_case.get_pokemon_detail.GetPokemonDetailUseCase
import com.example.pokedexapp.util.Constants
import com.example.pokedexapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(PokemonDetailState())
    val state: State<PokemonDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_POKEMON_NAME)?.let { pokemonName ->
            getPokemon(pokemonName)
        }
    }
    private fun getPokemon(pokemonName: String) {
        getPokemonDetailUseCase(pokemonName).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = PokemonDetailState(pokemonDetail = result.data)
                }
                is Resource.Error -> {
                    _state.value = PokemonDetailState(
                        error = result.message ?: "An Unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PokemonDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}
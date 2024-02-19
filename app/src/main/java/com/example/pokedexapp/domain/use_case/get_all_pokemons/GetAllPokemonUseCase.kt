package com.example.pokedexapp.domain.use_case.get_all_pokemons

import com.example.pokedexapp.data.remote.dto.toPokemonList
import com.example.pokedexapp.domain.model.PokemonList
import com.example.pokedexapp.domain.repository.PokemonRepository
import com.example.pokedexapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(offset: Int, limit: Int): Flow<Resource<PokemonList>> = flow {

        try {

            emit(Resource.Loading())
            val allPokemon = repository.getPokemonList(offset, limit).toPokemonList()
            emit(Resource.Success(allPokemon))

        }
        catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An Unexpected Error Occurred"))
        }
        catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your Internet Connection"))
        }

    }
}
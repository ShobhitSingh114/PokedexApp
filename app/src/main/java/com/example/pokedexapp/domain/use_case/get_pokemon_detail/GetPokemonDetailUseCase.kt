package com.example.pokedexapp.domain.use_case.get_pokemon_detail

import com.example.pokedexapp.data.remote.dto.toPokemonList
import com.example.pokedexapp.data.remote.dto.toPokmonDetail
import com.example.pokedexapp.domain.model.PokemonDetail
import com.example.pokedexapp.domain.model.PokemonList
import com.example.pokedexapp.domain.repository.PokemonRepository
import com.example.pokedexapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(name: String): Flow<Resource<PokemonDetail>> = flow {

        try {

            emit(Resource.Loading())
            val pokemonDetail = repository.getPokemonInfoByName(name).toPokmonDetail()
            emit(Resource.Success(pokemonDetail))

        }
        catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An Unexpected Error Occurred"))
        }
        catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your Internet Connection"))
        }

    }
}
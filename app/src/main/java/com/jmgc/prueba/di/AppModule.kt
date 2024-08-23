package com.jmgc.prueba.di

import com.jmgc.prueba.domain.use_case.GetPokemonsUseCase
import com.jmgc.prueba.data.remote.PokeApiService
import com.jmgc.prueba.data.repository.PokemonRepositoryImpl
import com.jmgc.prueba.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun providePokeApiService(): PokeApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PokeApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonRepository(
        pokeApiService: PokeApiService
    ): PokemonRepository {
        return PokemonRepositoryImpl(pokeApiService)
    }

    @Provides
    @Singleton
    fun provideGetPokemonsUseCase(
        pokemonRepository: PokemonRepository
    ): GetPokemonsUseCase {
        return GetPokemonsUseCase(pokemonRepository)
    }


}

package com.jmgc.prueba.di


import com.jmgc.prueba.data.local.PokemonDao
import com.jmgc.prueba.data.remote.api.PokeApiService
import com.jmgc.prueba.data.repository.PokemonRepository1
import com.jmgc.prueba.data.repository.PokemonRepositoryImpl
import com.jmgc.prueba.domain.repository.PokemonRepository
import com.jmgc.prueba.domain.use_case.FetchPokemonsUseCase
import com.jmgc.prueba.domain.use_case.SavePokemonsUseCase
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
    fun providePokemonRepository1(
        api: PokeApiService
    ) = PokemonRepository1(api)


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
        pokemonRepository: PokemonRepository,
        pokemonDao: PokemonDao
    ): FetchPokemonsUseCase {
        return FetchPokemonsUseCase(pokemonRepository,pokemonDao)
    }

    @Provides
    @Singleton
    fun provideSavePokemonsUseCase(
        pokemonRepository: PokemonRepository,
        pokemonDao: PokemonDao
    ): SavePokemonsUseCase {
        return SavePokemonsUseCase(pokemonRepository,pokemonDao)
    }


}

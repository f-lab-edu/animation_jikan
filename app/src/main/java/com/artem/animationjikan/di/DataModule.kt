package com.artem.animationjikan.di

import com.artem.animationjikan.data.repository.AnimationRepository
import com.artem.animationjikan.data.repository.AnimationRepositoryImpl
import com.artem.animationjikan.data.repository.CharacterRepository
import com.artem.animationjikan.data.repository.CharacterRepositoryImpl
import com.artem.animationjikan.data.repository.MangaRepository
import com.artem.animationjikan.data.repository.MangaRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindAnimationRepository(animationRepository: AnimationRepositoryImpl): AnimationRepository

    @Binds
    @Singleton
    fun bindMangaRepository(mangaRepository: MangaRepositoryImpl): MangaRepository

    @Binds
    @Singleton
    fun bindCharacterRepository(characterRepository: CharacterRepositoryImpl): CharacterRepository
}
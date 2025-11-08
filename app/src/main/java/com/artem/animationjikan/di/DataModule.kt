package com.artem.animationjikan.di

import com.artem.animationjikan.data.repository.AnimationRepositoryImpl
import com.artem.animationjikan.data.repository.CharacterRepositoryImpl
import com.artem.animationjikan.data.repository.LikeRepositoryImpl
import com.artem.animationjikan.data.repository.MangaRepositoryImpl
import com.artem.animationjikan.domain.repository.AnimationRepository
import com.artem.animationjikan.domain.repository.CharacterRepository
import com.artem.animationjikan.domain.repository.LikeRepository
import com.artem.animationjikan.domain.repository.MangaRepository
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

    @Binds
    @Singleton
    abstract fun bindLikeRepository(likeRepositoryImpl: LikeRepositoryImpl): LikeRepository
}
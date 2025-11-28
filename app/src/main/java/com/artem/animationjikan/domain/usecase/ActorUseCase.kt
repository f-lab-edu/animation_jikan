package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.data.mapper.toCharacterVoiceActorEntity
import com.artem.animationjikan.domain.entities.CharacterVoiceActorEntity
import com.artem.animationjikan.domain.repository.VoiceActorRepository
import javax.inject.Inject

class ActorUseCase @Inject constructor(
    private val voiceActorRepository: VoiceActorRepository,
) {
    companion object {
        val TAG: String? = ActorUseCase::class.java.simpleName
    }

    suspend fun execute(id: Int): Result<List<CharacterVoiceActorEntity>> {
        return voiceActorRepository.fetchCharacterVoiceActors(id = id).mapCatching { list ->
            list.map { dto -> dto.toCharacterVoiceActorEntity() }
        }
    }

}
package com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.character.tabs.actor

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.domain.entities.CharacterVoiceActorEntity
import com.artem.animationjikan.domain.usecase.ActorUseCase
import com.artem.animationjikan.domain.usecase.LikeUseCase
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.TabBaseViewModel
import com.artem.animationjikan.util.enums.DetailTabs
import com.artem.animationjikan.util.enums.FilterType
import com.artem.animationjikan.util.enums.ViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorViewModel @Inject constructor(
    private val actorUseCase: ActorUseCase,
    likeUseCase: LikeUseCase,
) : TabBaseViewModel<CharacterVoiceActorEntity>() {

    private var likeIds: Set<Int> = emptySet()
    override val type: DetailTabs = DetailTabs.FIRST

    companion object {
        val TAG: String? = ActorViewModel::class.simpleName
    }

    init {
        likeUseCase.execute(mediaType = FilterType.VOICE_ACTOR.name)
            .onEach { result ->
                likeIds = result.map { entity -> entity.mediaId }.toSet()
            }.launchIn(viewModelScope)
    }

    override fun execute(malId: Int) {
        _state.value = ViewModelState.Loading

        viewModelScope.launch {
            actorUseCase.execute(id = malId).onSuccess {
                _list.value = it
                _state.value = ViewModelState.Success
            }.onFailure {
                Log.e(TAG, it.message.toString())
                _state.value = ViewModelState.Error
            }
        }
    }

}
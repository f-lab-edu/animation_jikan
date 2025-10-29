package com.artem.animationjikan.domain.usecase

import android.util.Log
import com.artem.animationjikan.data.dto.LikeData
import com.artem.animationjikan.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LikeUsecase @Inject constructor(
    private val likeRepository: LikeRepository
) {
    //TODO 참고용 나중에 비슷하게 사용할 가능성 있음
    /*fun getLikeOfType(mediaType: String): Flow<Result<List<Int>>> {
        val response = likeRepository.getAllLikeOfType(mediaType)
        return response
    }*/

    fun execute(mediaType: String): Flow<Result<List<LikeData>>> {
        Log.e("LikeUsecase", "execute")
        return likeRepository.getAllLikeOfType(mediaType = mediaType)
    }
}
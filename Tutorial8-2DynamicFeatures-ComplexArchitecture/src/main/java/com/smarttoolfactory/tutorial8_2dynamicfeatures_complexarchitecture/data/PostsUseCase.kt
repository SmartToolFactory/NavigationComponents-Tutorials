package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.data


import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.api.DataResult
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.api.Post

class PostsUseCase(private val postsRepository: PostsRepository) {

    suspend fun getPosts(): DataResult<List<Post>> {
        return postsRepository.getPostResult()
    }
}
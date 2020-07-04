package com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.data


import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.api.DataResult
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.api.Post

class PostsUseCase(private val postsRepository: PostsRepository) {

    suspend fun getPosts(): DataResult<List<Post>> {
        return postsRepository.getPostResult()
    }
}
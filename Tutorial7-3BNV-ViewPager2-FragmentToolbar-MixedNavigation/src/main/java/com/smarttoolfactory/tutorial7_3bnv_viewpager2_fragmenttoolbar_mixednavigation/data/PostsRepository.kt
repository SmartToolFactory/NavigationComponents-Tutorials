package com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.data


import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.api.DataResult
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.api.Post
import com.smarttoolfactory.tutorial7_3bnv_viewpager2_fragmenttoolbar_mixednavigation.api.PostApi

class PostsRepository(private val postApi: PostApi) {

    suspend fun getPostResult(): DataResult<List<Post>> {

        // Using List<Post>
        return try {
            DataResult.Success(postApi.getPosts())
        } catch (error: Exception) {
            DataResult.Error(error)
        }
    }

}

package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.data


import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.api.DataResult
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.api.Post
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.api.PostApi

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

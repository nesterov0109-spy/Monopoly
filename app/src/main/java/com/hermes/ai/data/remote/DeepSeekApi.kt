package com.hermes.ai.data.remote

import com.hermes.ai.data.model.ChatRequest
import com.hermes.ai.data.model.ChatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DeepSeekApi {
    @POST("v1/chat/completions")
    suspend fun sendChatMessage(@Body request: ChatRequest): Response<ChatResponse>
}

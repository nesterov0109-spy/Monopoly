package com.hermes.ai.data.repository

import com.hermes.ai.data.model.ChatMessage
import com.hermes.ai.data.model.ChatRequest
import com.hermes.ai.data.model.ErrorResponse
import com.hermes.ai.data.model.UiState
import com.hermes.ai.data.remote.RetrofitClient
import com.google.gson.Gson

class ChatRepository {
    private val api = RetrofitClient.deepSeekApi

    suspend fun sendMessage(
        messages: List<ChatMessage>,
        onStateChange: (UiState) -> Unit
    ) {
        onStateChange(UiState.Loading)
        try {
            val request = ChatRequest(messages = messages)
            val response = api.sendChatMessage(request)

            if (response.isSuccessful) {
                val body = response.body()
                val reply = body?.choices?.firstOrNull()?.message?.content
                    ?: "No response from AI"
                onStateChange(UiState.Success(reply))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMsg = try {
                    Gson().fromJson(errorBody, ErrorResponse::class.java)
                        ?.error?.message ?: "Unknown error"
                } catch (e: Exception) {
                    "Error ${response.code()}: ${response.message()}"
                }
                onStateChange(UiState.Error(errorMsg))
            }
        } catch (e: Exception) {
            onStateChange(UiState.Error(e.localizedMessage ?: "Network error occurred"))
        }
    }
}

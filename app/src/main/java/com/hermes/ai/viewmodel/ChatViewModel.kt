package com.hermes.ai.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermes.ai.data.model.ChatMessage
import com.hermes.ai.data.model.UiState
import com.hermes.ai.data.repository.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ChatUiState(
    val messages: List<ChatMessage> = emptyList(),
    val inputText: String = "",
    val uiState: UiState = UiState.Idle,
    val isLoading: Boolean = false
)

class ChatViewModel : ViewModel() {
    private val repository = ChatRepository()

    private val _chatState = MutableStateFlow(ChatUiState())
    val chatState: StateFlow<ChatUiState> = _chatState.asStateFlow()

    fun sendMessage(text: String) {
        if (text.isBlank()) return

        val userMessage = ChatMessage(role = "user", content = text)
        val updatedMessages = _chatState.value.messages + userMessage

        _chatState.value = _chatState.value.copy(
            messages = updatedMessages,
            inputText = "",
            isLoading = true
        )

        viewModelScope.launch {
            repository.sendMessage(updatedMessages) { state ->
                _chatState.value = when (state) {
                    is UiState.Success -> {
                        val aiMessage = ChatMessage(role = "assistant", content = state.response)
                        _chatState.value.copy(
                            messages = _chatState.value.messages + aiMessage,
                            isLoading = false,
                            uiState = UiState.Idle
                        )
                    }
                    is UiState.Error -> {
                        _chatState.value.copy(
                            isLoading = false,
                            uiState = state
                        )
                    }
                    is UiState.Loading -> {
                        _chatState.value.copy(isLoading = true)
                    }
                    is UiState.Idle -> _chatState.value
                }
            }
        }
    }

    fun updateInputText(text: String) {
        _chatState.value = _chatState.value.copy(inputText = text)
    }

    fun clearChat() {
        _chatState.value = ChatUiState()
    }
}

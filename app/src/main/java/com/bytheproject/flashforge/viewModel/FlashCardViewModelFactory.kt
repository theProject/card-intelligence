// src/main/java/com/example/flashintelligence/viewmodel/FlashCardViewModelFactory.kt
// For a viewModel factory - need to update notes here - maybe an assembler?

package com.bytheproject.flashforge.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bytheproject.flashforge.repository.FlashCardRepository

class FlashCardViewModelFactory(
    private val repository: FlashCardRepository,
    private val deckId: Long
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlashCardViewModel::class.java)) {
            return FlashCardViewModel(repository, deckId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
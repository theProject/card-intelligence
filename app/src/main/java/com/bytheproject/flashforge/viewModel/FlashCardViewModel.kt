// src/main/java/com/example/flashintelligence/viewmodel/FlashCardViewModel.kt
// Kotlin class that handles UI logic, here for the Flash Card itself

package com.bytheproject.flashforge.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bytheproject.flashforge.model.Deck
import com.bytheproject.flashforge.model.FlashCard
import com.bytheproject.flashforge.repository.FlashCardRepository

class FlashCardViewModel(
    private val repository: FlashCardRepository,
    private val deckId: Long
) : ViewModel() {

    private val _deck = MutableLiveData<Deck>()
    val deck: LiveData<Deck> = _deck

    private val _flashCards = MutableLiveData<List<FlashCard>>()
    val flashCards: LiveData<List<FlashCard>> = _flashCards

    private val _currentCardIndex = MutableLiveData<Int>()
    val currentCardIndex: LiveData<Int> = _currentCardIndex

    private val _isShowingAnswer = MutableLiveData<Boolean>()
    val isShowingAnswer: LiveData<Boolean> = _isShowingAnswer

    init {
        loadDeck()
        loadFlashCards()
        _currentCardIndex.value = 0
        _isShowingAnswer.value = false
    }

    private fun loadDeck() {
        _deck.value = repository.getDeckById(deckId)
    }

    private fun loadFlashCards() {
        _flashCards.value = repository.getFlashCardsForDeck(deckId)
    }

    fun addFlashCard(question: String, answer: String) {
        repository.addFlashCard(question, answer, deckId)
        loadFlashCards()
    }

    fun flipCard() {
        _isShowingAnswer.value = !(_isShowingAnswer.value ?: false)
    }

    fun nextCard() {
        val currentIdx = _currentCardIndex.value ?: 0
        val cardCount = _flashCards.value?.size ?: 0
        if (cardCount > 0) {
            _currentCardIndex.value = (currentIdx + 1) % cardCount
            _isShowingAnswer.value = false
        }
    }

    fun previousCard() {
        val currentIdx = _currentCardIndex.value ?: 0
        val cardCount = _flashCards.value?.size ?: 0
        if (cardCount > 0) {
            _currentCardIndex.value = (currentIdx - 1 + cardCount) % cardCount
            _isShowingAnswer.value = false
        }
    }

    fun getCurrentCard(): FlashCard? {
        val cards = _flashCards.value
        val currentIdx = _currentCardIndex.value
        return if (cards != null && currentIdx != null && cards.isNotEmpty()) {
            cards[currentIdx]
        } else {
            null
        }
    }
}
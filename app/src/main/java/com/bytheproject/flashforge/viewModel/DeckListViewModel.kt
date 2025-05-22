// src/main/java/com/example/flashintelligence/viewmodel/DeckListViewModel.kt
// This is a class (ViewModels) that handles the UI Logic for each component

package com.bytheproject.flashforge.viewModel

    import androidx.lifecycle.LiveData
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import com.bytheproject.flashforge.model.Deck
    import com.bytheproject.flashforge.repository.FlashCardRepository

    class DeckListViewModel : ViewModel() {
        private val repository = FlashCardRepository()

        private val _decks = MutableLiveData<List<Deck>>()
        val decks: LiveData<List<Deck>> = _decks

        init {
            loadDecks()
        }

        private fun loadDecks() {
            _decks.value = repository.getAllDecks()
        }

        fun addDeck(name: String, description: String = "") {
            repository.addDeck(name, description)
            loadDecks()
        }

        fun getRepository(): FlashCardRepository = repository
    }

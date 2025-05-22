// src/main/java/com/example/flashintelligence/repository/FlashCardRepository.kt

// This will be a temporary and in-memory repository for our flash cards - and is an example
// of using Kotlin * New* Class* as its performing functions/calculations versus just holding data
// Think utility classes, controllers, ViewModels, services, repositories, or any class where the
// identity or behavior is more important than just the raw data it might hold.

package com.bytheproject.flashforge.repository

import com.bytheproject.flashforge.model.Deck
import com.bytheproject.flashforge.model.FlashCard

class FlashCardRepository {
    private val decks = mutableListOf<Deck>()
    private val flashCards = mutableListOf<FlashCard>()
    private var nextDeckId = 1L
    private var nextCardId = 1L

    init {
        // Add some sample data
        val sampleDeck = Deck(nextDeckId++, "Sample Deck", "A sample deck of flash cards")
        decks.add(sampleDeck)

        flashCards.add(FlashCard(nextCardId++, "What is Kotlin?", "Kotlin is a modern programming language that runs on the JVM.", sampleDeck.id))
        flashCards.add(FlashCard(nextCardId++, "What is Android?", "Android is a mobile operating system developed by Google.", sampleDeck.id))
        flashCards.add(FlashCard(nextCardId++, "What is Material Design?", "Material Design is a design system created by Google that helps teams build high-quality digital experiences.", sampleDeck.id))
    }

    fun getAllDecks(): List<Deck> = decks.toList()

    fun getDeckById(deckId: Long): Deck? = decks.find { it.id == deckId }

    fun getFlashCardsForDeck(deckId: Long): List<FlashCard> = flashCards.filter { it.deckId == deckId }

    fun addDeck(name: String, description: String = ""): Deck {
        val newDeck = Deck(nextDeckId++, name, description)
        decks.add(newDeck)
        return newDeck
    }

    fun addFlashCard(question: String, answer: String, deckId: Long): FlashCard {
        val newCard = FlashCard(nextCardId++, question, answer, deckId)
        flashCards.add(newCard)
        return newCard
    }
}

// src/main/java/com/example/flashintelligence/model/FlashCard.kt - when adding, we create the
//model folder by right clicking the parent (flashintellingence) and selecting a new package, and
//name in model.  Then, we right click our model package and create a new Kotlin Class/File, then
//select then in the helpful dialog, use Data Class - it just starts as out wit that we need.

package com.bytheproject.flashforge.model

data class FlashCard(
    val id: Long = 0,
    val question: String,
    val answer: String,
    val deckId: Long = 0
)
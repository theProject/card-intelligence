package com.bytheproject.flashforge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bytheproject.flashforge.R
import com.bytheproject.flashforge.databinding.FragmentFlashCardBinding
import com.bytheproject.flashforge.viewModel.DeckListViewModel
import com.bytheproject.flashforge.viewModel.FlashCardViewModel
import com.bytheproject.flashforge.viewModel.FlashCardViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FlashCardFragment : Fragment() {

    private var _binding: FragmentFlashCardBinding? = null
    private val binding get() = _binding!!

    private val args: FlashCardFragmentArgs by navArgs()
    private val deckListViewModel: DeckListViewModel by viewModels()
    private lateinit var viewModel: FlashCardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlashCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupToolbar()
        setupButtons()
        setupFab()
        observeViewModel()
    }

    private fun setupViewModel() {
        val repository = deckListViewModel.getRepository()
        val factory = FlashCardViewModelFactory(repository, args.deckId)
        viewModel = viewModels<FlashCardViewModel> { factory }.value
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupButtons() {
        binding.buttonFlip.setOnClickListener {
            viewModel.flipCard()
        }

        binding.buttonNext.setOnClickListener {
            viewModel.nextCard()
        }

        binding.buttonPrevious.setOnClickListener {
            viewModel.previousCard()
        }
    }

    private fun setupFab() {
        binding.fabAddCard.setOnClickListener {
            showAddCardDialog()
        }
    }

    private fun observeViewModel() {
        viewModel.deck.observe(viewLifecycleOwner) { deck ->
            binding.toolbar.title = deck?.name
        }

        viewModel.flashCards.observe(viewLifecycleOwner) { _ ->
            updateCardCounter()
            updateCardContent()
        }

        viewModel.currentCardIndex.observe(viewLifecycleOwner) { _ ->
            updateCardCounter()
            updateCardContent()
        }

        viewModel.isShowingAnswer.observe(viewLifecycleOwner) { _ ->
            updateCardContent()
        }
    }

    private fun updateCardCounter() {
        val currentIndex = viewModel.currentCardIndex.value ?: 0
        val cardCount = viewModel.flashCards.value?.size ?: 0

        if (cardCount > 0) {
            binding.textCardCounter.text = getString(R.string.card_counter, currentIndex + 1, cardCount)
        } else {
            binding.textCardCounter.text = ""
        }
    }

    private fun updateCardContent() {
        val currentCard = viewModel.getCurrentCard()
        val isShowingAnswer = viewModel.isShowingAnswer.value ?: false

        if (currentCard != null) {
            binding.textCardContent.text = if (isShowingAnswer) {
                currentCard.answer
            } else {
                currentCard.question
            }

            binding.buttonFlip.text = if (isShowingAnswer) {
                getString(R.string.flip)
            } else {
                getString(R.string.flip)
            }
        } else {
            binding.textCardContent.text = ""
        }
    }

    private fun showAddCardDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_card, null)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.add_card)
            .setView(dialogView)
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(R.string.create) { dialog, _ ->
                val questionEditText = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.editTextQuestion)
                val answerEditText = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.editTextAnswer)

                val question = questionEditText.text.toString().trim()
                val answer = answerEditText.text.toString().trim()

                if (question.isNotEmpty() && answer.isNotEmpty()) {
                    viewModel.addFlashCard(question, answer)
                }

                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
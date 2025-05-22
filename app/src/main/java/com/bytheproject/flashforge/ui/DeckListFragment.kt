// src/main/java/com/example/flash/ui/DeckListFragment.kt
// Building out the actual UI components

package com.bytheproject.flashforge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bytheproject.flashforge.R
import com.bytheproject.flashforge.databinding.FragmentDeckListBinding
import com.bytheproject.flashforge.model.Deck
import com.bytheproject.flashforge.viewModel.DeckListViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DeckListFragment : Fragment() {

    private var _binding: FragmentDeckListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DeckListViewModel by viewModels()
    private lateinit var adapter: DeckAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeckListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupFab()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = DeckAdapter { deck ->
            navigateToFlashCards(deck)
        }

        binding.deckRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@DeckListFragment.adapter
        }
    }

    private fun setupFab() {
        binding.fabAddDeck.setOnClickListener {
            showAddDeckDialog()
        }
    }

    private fun observeViewModel() {
        viewModel.decks.observe(viewLifecycleOwner) { decks ->
            adapter.submitList(decks)
        }
    }

    private fun navigateToFlashCards(deck: Deck) {
        val action = DeckListFragmentDirections.actionDeckListFragmentToFlashCardFragment(deck.id)
        findNavController().navigate(action)
    }

    private fun showAddDeckDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_deck, null)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.add_deck)
            .setView(dialogView)
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(R.string.create) { dialog, _ ->
                val nameEditText = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.editTextDeckName)
                val descriptionEditText = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.editTextDeckDescription)

                val name = nameEditText.text.toString().trim()
                val description = descriptionEditText.text.toString().trim()

                if (name.isNotEmpty()) {
                    viewModel.addDeck(name, description)
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
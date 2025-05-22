// src/main/java/com/example/flashintelligence/MainActivity.kt
package com.bytheproject.flashforge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController // It's good practice to import NavController directly
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.bytheproject.flashforge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController // Declare NavController at class level

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Set your MaterialToolbar (with id 'topAppBar') as the ActionBar
        // This must be done AFTER setContentView and BEFORE setupActionBarWithNavController
        setSupportActionBar(binding.topAppBar) // 'binding.topAppBar' assumes your Toolbar's ID is 'topAppBar'

        setupNavigation()
    }

    private fun setupNavigation() {
        // Find the NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Get the NavController from the NavHostFragment
        navController = navHostFragment.navController // Assign to class-level variable

        // 2. Set up the ActionBar to work with the NavController
        // This will automatically update the ActionBar title and handle the Up button
        setupActionBarWithNavController(navController)
    }

    // 3. Handle the "Up" button action in the ActionBar
    // This is called when the user presses the Up button (back arrow) in the ActionBar
    override fun onSupportNavigateUp(): Boolean {
        // Let the NavController handle the Up navigation.
        // If navController.navigateUp() returns false (meaning it's at the start destination),
        // then fall back to the default super.onSupportNavigateUp() behavior.
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
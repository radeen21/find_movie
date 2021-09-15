package com.example.base.navigation

import androidx.navigation.NavController

interface CommonNavigation {
    fun navigateToDetail(findNavController: NavController, data: Any)
}
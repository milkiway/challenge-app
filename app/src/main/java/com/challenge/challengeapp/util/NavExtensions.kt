package com.challenge.challengeapp.util

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateAndPopUpTo(resId: Int) = this.navigate(resId, null, getNavOptionsPopUpTo(resId))

private fun getNavOptionsPopUpTo(fragmentId: Int): NavOptions = NavOptions.Builder().setPopUpTo(fragmentId, true).build()

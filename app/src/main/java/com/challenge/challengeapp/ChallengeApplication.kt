package com.challenge.challengeapp

import android.app.Application
import com.challenge.challengeapp.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ChallengeApplication : Application() {}
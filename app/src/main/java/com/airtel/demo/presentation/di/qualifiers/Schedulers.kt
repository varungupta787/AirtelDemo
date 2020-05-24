package com.airtel.demo.presentation.di.qualifiers

import javax.inject.Qualifier

@Qualifier
annotation class UiThreadScheduler

@Qualifier
annotation class IOScheduler

@Qualifier
annotation class TrampolineScheduler
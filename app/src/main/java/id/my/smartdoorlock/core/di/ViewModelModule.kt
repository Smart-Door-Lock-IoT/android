package id.my.smartdoorlock.core.di

import id.my.smartdoorlock.presentation.pages.changepin.ChangePinViewModel
import id.my.smartdoorlock.presentation.pages.home.HomeViewModel
import id.my.smartdoorlock.presentation.pages.logactivities.LogActivitiesViewModel
import id.my.smartdoorlock.presentation.pages.pin.PinViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::ChangePinViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::LogActivitiesViewModel)
    viewModelOf(::PinViewModel)
}
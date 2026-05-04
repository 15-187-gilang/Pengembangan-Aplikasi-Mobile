package com.example.tugas3.di

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.tugas3.data.NoteRepository
import com.example.tugas3.data.SettingsManager
import com.example.tugas3.database.NoteDatabase
import com.example.tugas3.platform.AndroidDeviceInfo
import com.example.tugas3.platform.AndroidNetworkMonitor
import com.example.tugas3.platform.DeviceInfo
import com.example.tugas3.platform.NetworkMonitor
import com.example.tugas3.viewmodel.NoteViewModel
import com.example.tugas3.viewmodel.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        val driver = AndroidSqliteDriver(NoteDatabase.Schema, androidContext(), "notes.db")
        NoteDatabase(driver)
    }
    single { NoteRepository(get()) }
    single { SettingsManager(androidContext()) }
    
    single<DeviceInfo> { AndroidDeviceInfo() }
    single<NetworkMonitor> { AndroidNetworkMonitor(androidContext()) }

    viewModel { NoteViewModel(get(), get(), get()) }
    viewModel { ProfileViewModel() }
}

package com.example.navigationsafeargs.di

import android.app.Application
import androidx.lifecycle.ViewModel
import org.koin.dsl.module
import com.example.navigationsafeargs.data.db.TaskDatabase
import com.example.navigationsafeargs.data.repository.TaskRepository
import com.example.navigationsafeargs.viewmodels.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {

    single { get<Application>() }

    single { TaskDatabase.buildDatabase(get()).taskDao() }
    single { TaskDatabase.buildDatabase(get()).deletedTaskDao() }

    single { TaskRepository(get(), get()) }

    viewModel { NoteViewModel(get()) }

}



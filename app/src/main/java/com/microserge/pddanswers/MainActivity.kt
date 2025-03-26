package com.microserge.pddanswers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.microserge.pddanswers.ui.presentation.question_list.QuestionListScreenRoot
import com.microserge.pddanswers.ui.presentation.question_list.SearchViewModel
import com.microserge.pddanswers.ui.theme.PddAnswersTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PddAnswersTheme {
                val viewModel = koinViewModel<SearchViewModel>()
                QuestionListScreenRoot(viewModel)
            }
        }
    }
}


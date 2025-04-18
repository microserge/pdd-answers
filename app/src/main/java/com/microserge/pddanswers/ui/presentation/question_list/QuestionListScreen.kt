package com.microserge.pddanswers.ui.presentation.question_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.microserge.pddanswers.R
import com.microserge.pddanswers.core.presentation.Background
import com.microserge.pddanswers.ui.presentation.question_list.components.QuestionList
import com.microserge.pddanswers.ui.presentation.question_list.components.QuestionSearchBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuestionListScreenRoot(viewModel: SearchViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    QuestionListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is QuestionListAction.OnQuestionClick -> Unit
                is QuestionListAction.OnSearchQueryChange -> Unit
                QuestionListAction.LoadMore -> Unit
            }
            viewModel.onAction(action)
        },
    )
}

@Composable
fun QuestionListScreen(
    state: QuestionListState,
    onAction: (QuestionListAction) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val searchResultListState = rememberLazyListState()

    LaunchedEffect(state.searchQuery) {
        searchResultListState.animateScrollToItem(0)
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Background)
                .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        QuestionSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = {
                onAction(QuestionListAction.OnSearchQueryChange(it))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier =
                Modifier
                    .widthIn(max = 400.dp)
                    .fillMaxWidth()
                    .padding(16.dp),
        )

        HorizontalDivider(
            thickness = 1.dp,
            color = com.microserge.pddanswers.core.presentation.CardBorder,
        )

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (state.isLoading && state.searchResult.isEmpty()) {
                CircularProgressIndicator()
            } else {
                when {
                    state.errorMessage != null -> {
                        Text(
                            text = state.errorMessage.asString(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                            color = com.microserge.pddanswers.core.presentation.Text,
                        )
                    }

                    state.searchResult.isEmpty() -> {
                        Text(
                            text = stringResource(R.string.empty),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                            color = com.microserge.pddanswers.core.presentation.Text,
                        )
                    }

                    else -> {
                        QuestionList(
                            scrollState = searchResultListState,
                            questions = state.searchResult,
                            onQuestionClick = {
                                onAction(QuestionListAction.OnQuestionClick(it))
                            },
                            onLoadMore = {
                                onAction(QuestionListAction.LoadMore)
                            },
                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .padding(vertical = 16.dp),
                        )
                        if (state.isLoading) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

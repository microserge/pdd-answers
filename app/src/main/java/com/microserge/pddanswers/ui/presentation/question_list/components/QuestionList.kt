package com.microserge.pddanswers.ui.presentation.question_list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.microserge.pddanswers.core.presentation.components.EndlessLazyColumn
import com.microserge.pddanswers.question.domain.Question

@Composable
fun QuestionList(
    questions: List<Question>,
    onQuestionClick: (Question) -> Unit,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState(),
) {
    EndlessLazyColumn(
        modifier = modifier,
        listState = scrollState,
        items = questions,
        itemKey = { it.id },
        itemContent = { question: Question ->
            QuestionListItem(
                question = question,
                modifier =
                    Modifier
                        .widthIn(max = 700.dp)
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                onClick = { onQuestionClick(question) },
            )
        },
        loadMore = onLoadMore,
    )
}

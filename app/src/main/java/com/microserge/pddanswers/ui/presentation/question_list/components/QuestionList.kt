package com.microserge.pddanswers.ui.presentation.question_list.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.microserge.pddanswers.question.domain.Question
import com.microserge.pddanswers.ui.presentation.question_list.QuestionListAction

@Composable
fun QuestionList(
    questions: List<Question>,
    onQuestionClick: (Question) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        modifier = modifier,
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = questions,
            key = { it.id }
        ) { question ->
            QuestionListItem(
                question = question,
                modifier = Modifier
                    .widthIn(max = 700.dp)
                    .fillParentMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = {
                    onQuestionClick(question)
                }
            )
        }
    }
}
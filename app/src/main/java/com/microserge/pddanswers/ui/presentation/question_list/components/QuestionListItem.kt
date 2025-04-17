package com.microserge.pddanswers.ui.presentation.question_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.microserge.pddanswers.core.presentation.DesertWhite1
import com.microserge.pddanswers.question.domain.Question

@Composable
fun QuestionListItem(
    question: Question,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .clickable(onClick = onClick),
        color = DesertWhite1
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
        ) {
            Text(
                question.title,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.7f),
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodySmall,
                softWrap = true
            )

            ImageWithSkeleton(question.image)
        }
    }
}
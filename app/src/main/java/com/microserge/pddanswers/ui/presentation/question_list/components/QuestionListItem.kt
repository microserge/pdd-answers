package com.microserge.pddanswers.ui.presentation.question_list.components

import androidx.compose.foundation.BorderStroke
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
import com.microserge.pddanswers.core.presentation.Background
import com.microserge.pddanswers.question.domain.Question

@Composable
fun QuestionListItem(
    question: Question,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        modifier =
            modifier
                .clickable(onClick = onClick),
        color = Background,
        border = BorderStroke(1.dp, com.microserge.pddanswers.core.presentation.CardBorder),
    ) {
        Row(
            modifier =
                Modifier
                    .padding(12.dp),
        ) {
            Text(
                question.title,
                modifier =
                    Modifier
                        .padding(16.dp)
                        .fillMaxWidth(0.7f),
                color = com.microserge.pddanswers.core.presentation.Text,
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodySmall,
                softWrap = true,
            )

            ImageWithSkeleton(question.image)
        }
    }
}

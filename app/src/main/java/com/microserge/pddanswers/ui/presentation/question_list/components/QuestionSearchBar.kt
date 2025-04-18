package com.microserge.pddanswers.ui.presentation.question_list.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.microserge.pddanswers.R
import com.microserge.pddanswers.core.presentation.Background
import com.microserge.pddanswers.core.presentation.DarkBlue
import com.microserge.pddanswers.core.presentation.SandYellow
import com.microserge.pddanswers.core.presentation.SearchBackground
import com.microserge.pddanswers.core.presentation.Text

@Composable
fun QuestionSearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onImeSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        LocalTextSelectionColors provides
            TextSelectionColors(
                handleColor = SandYellow,
                backgroundColor = SearchBackground,
            ),
    ) {
        OutlinedTextField(
            colors =
                OutlinedTextFieldDefaults.colors(
                    cursorColor = DarkBlue,
                    focusedBorderColor = Background,
                    focusedTextColor = Text,
                ),
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            shape = RoundedCornerShape(10),
            placeholder = {
                Text(
                    text = stringResource(R.string.search_hint),
                )
            },
            singleLine = true,
            keyboardActions =
                KeyboardActions(
                    onSearch = {
                        onImeSearch()
                    },
                ),
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search,
                ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Text.copy(alpha = 0.66f),
                )
            },
            trailingIcon = {
                AnimatedVisibility(
                    visible = searchQuery.isNotBlank(),
                ) {
                    IconButton(
                        onClick = {
                            onSearchQueryChange("")
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.close_hint),
                            tint = Text.copy(alpha = 0.66f),
                        )
                    }
                }
            },
            modifier =
                modifier
                    .background(
                        shape = RoundedCornerShape(10),
                        color = SearchBackground,
                    ).minimumInteractiveComponentSize(),
        )
    }
}

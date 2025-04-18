package com.dkproject.regularcustomermanagement.presentation.ui.createCustomer.Memo

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dkproject.regularcustomermanagement.R
import com.dkproject.regularcustomermanagement.presentation.Component.Button.BasicButton
import com.dkproject.regularcustomermanagement.presentation.Component.TexetField.DefaultTextField
import com.dkproject.regularcustomermanagement.presentation.Component.Text.MemoTextBox
import com.dkproject.regularcustomermanagement.presentation.theme.RegularCustomerManagementTheme

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MemoScreen(
    viewModel: MemoViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    updateStarAndMemo: (Boolean, List<String>) -> Unit = {_, _ ->}
) {

    var memoString by rememberSaveable { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val maxHeight = maxHeight
        Column {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_small)))
                    .heightIn(min = 0.dp, max = maxHeight * 0.75f)
                    .background(MaterialTheme.colorScheme.onSecondary)
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .verticalScroll(rememberScrollState())
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(R.string.favorites), fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(
                        checked = uiState.isStar,
                        onCheckedChange = viewModel::updateIsStar
                    )
                }
                Text(
                    text = stringResource(R.string.memo), fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
                )

                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small))
                ) {
                    DefaultTextField(
                        value = memoString,
                        onValueChange = { memoString = it },
                        modifier = Modifier
                            .weight(3f)
                            .heightIn(
                                min = dimensionResource(R.dimen.textfield_min),
                                max = dimensionResource(R.dimen.textfield_max)
                            ),
                        interactionSource = interactionSource,
                        placeholder = stringResource(R.string.memoplaceholder)
                    )
                    Spacer(modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_six)))
                    BasicButton(
                        modifier = Modifier.weight(1f),
                        title = stringResource(R.string.add),
                        onClick = {
                            viewModel.pluseMemo(memoString)
                            memoString = ""
                        },
                    )
                }
                AnimatedVisibility(uiState.memoList.isNotEmpty()) {
                    Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_six))) {
                        uiState.memoList.forEach { memo ->
                            MemoTextBox(
                                modifier = Modifier.fillMaxWidth(),
                                value = memo,
                                isDelete = true,
                                onDeleteClick = { viewModel.removeMemo(memo) }
                            )
                        }
                    }
                }
                if (uiState.memoList.isEmpty()) {
                    Text(
                        text = "메모 사항이 존재하지 않습니다!",
                        modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_four)),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
            BasicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.padding_medium)),
                title = stringResource(R.string.complete),
                enabled = !loading,
                loading = loading,
                onClick = {
                    updateStarAndMemo(uiState.isStar, uiState.memoList)
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun MemoScreenPreview() {
    RegularCustomerManagementTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            MemoScreen()
        }
    }
}
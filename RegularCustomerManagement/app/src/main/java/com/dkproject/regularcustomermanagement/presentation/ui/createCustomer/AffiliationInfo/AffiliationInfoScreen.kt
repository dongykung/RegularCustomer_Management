package com.dkproject.regularcustomermanagement.presentation.ui.createCustomer.AffiliationInfo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dkproject.regularcustomermanagement.R
import com.dkproject.regularcustomermanagement.presentation.Component.Button.BasicButton
import com.dkproject.regularcustomermanagement.presentation.Component.Button.TagChip
import com.dkproject.regularcustomermanagement.presentation.Component.TexetField.InputTextField
import com.dkproject.regularcustomermanagement.presentation.theme.RegularCustomerManagementTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AffiliationInfoScreen(
    viewModel: AffiliationViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onNextStep: (String, List<String>) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var tagString by rememberSaveable { mutableStateOf("") }
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_small)))
                .background(MaterialTheme.colorScheme.onSecondary)
                .padding(dimensionResource(R.dimen.padding_medium))
                .verticalScroll(rememberScrollState())
        ) {
            // Affiliations Section
            Text(
                modifier = modifier,
                text = stringResource(R.string.affiliationtag),
                fontWeight = FontWeight.Bold
            )
            InputTextField(
                value = uiState.affiliationName,
                onValueChange = viewModel::updateAffiliationName,
                placeholder = stringResource(R.string.affiliationtag),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth().padding(vertical = dimensionResource(R.dimen.padding_small))
            )
            // Tag Section
            Text(
                modifier = modifier,
                text = stringResource(R.string.tag),
                fontWeight = FontWeight.Bold
            )
            AnimatedVisibility(uiState.tags.isNotEmpty()) {
                FlowRow(
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small)),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_four))
                ) {
                    uiState.tags.forEach { tag ->
                        TagChip(tag = tag, isDelete = true) {
                            viewModel.removeTag(tag)
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                InputTextField(
                    value = tagString,
                    onValueChange = { tagString = it },
                    placeholder = stringResource(R.string.tag),
                    modifier = Modifier
                        .weight(3f)
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_medium)))
                // add Tag
                BasicButton(
                    title = stringResource(R.string.add),
                    onClick = {
                        viewModel.addTag(tagString)
                        tagString = ""
                    },
                    modifier = Modifier.weight(1f),
                    enabled = tagString.isNotEmpty() && !uiState.tags.contains(tagString) && uiState.tags.count() < 8
                )
            }
            AnimatedVisibility(uiState.tags.count() >= 8) {
                Text(
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_six)).fillMaxWidth(),
                    text = stringResource(R.string.maxtag),
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.End
                )
            }
        }
        // Next Button
        BasicButton(
            title = stringResource(R.string.next),
            onClick = {
                onNextStep(uiState.affiliationName, uiState.tags)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun AffiliationInfoPreview() {
    RegularCustomerManagementTheme {
        AffiliationInfoScreen(onNextStep = {_, _ ->})
    }
}
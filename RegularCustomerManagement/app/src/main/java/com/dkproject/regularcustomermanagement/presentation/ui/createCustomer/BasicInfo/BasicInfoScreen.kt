package com.dkproject.regularcustomermanagement.presentation.ui.createCustomer.BasicInfo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dkproject.regularcustomermanagement.R
import com.dkproject.regularcustomermanagement.presentation.Component.Button.BasicButton
import com.dkproject.regularcustomermanagement.presentation.Component.TexetField.InputTextField
import com.dkproject.regularcustomermanagement.presentation.model.BasicInfo
import com.dkproject.regularcustomermanagement.presentation.theme.RegularCustomerManagementTheme
import com.dkproject.regularcustomermanagement.presentation.utils.Constants.MAX_CUSTOMER_NAME_LENGTH

@Composable
fun BasicInfoScreen(
    viewModel: BasicInfoViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onNextStep: (BasicInfo) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_small)))
            .background(MaterialTheme.colorScheme.onSecondary)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            // Name Section
            InputSection(
                value = uiState.name,
                title = stringResource(R.string.name),
                placeholder = stringResource(R.string.customername),
                onValueChange = viewModel::updateUserName,
                maxLine = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                isError = uiState.name.length > MAX_CUSTOMER_NAME_LENGTH
            )
            AnimatedVisibility(uiState.name.length > MAX_CUSTOMER_NAME_LENGTH) {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp),
                    text = stringResource(R.string.maxnamelength, MAX_CUSTOMER_NAME_LENGTH),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.End
                )
            }
            // Nickname Section
            InputSection(
                value = uiState.nickName,
                title = stringResource(R.string.nickname),
                placeholder = stringResource(R.string.nickname),
                onValueChange = viewModel::updateNickname,
                maxLine = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium))
            )
            // PhoneNumber Section
            InputSection(
                value = uiState.phoneNumber,
                title = stringResource(R.string.phonenumber),
                placeholder = stringResource(R.string.phoneplaceholder),
                onValueChange = viewModel::updatePhoneNumber,
                maxLine = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number),
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium))
            )
            // carNumber Section
            InputSection(
                value = uiState.carNumber,
                title = stringResource(R.string.carnumber),
                placeholder = stringResource(R.string.carnumber),
                onValueChange = viewModel::updateCarNumber,
                maxLine = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium))
            )
            BasicButton(
                title = stringResource(R.string.next),
                onClick = {
                    onNextStep(
                        BasicInfo(
                            name = uiState.name,
                            phoneNumber = uiState.phoneNumber,
                            nickName = uiState.nickName,
                            carNumber = uiState.carNumber
                        )
                    )
                },
                enabled = (uiState.name.isNotEmpty() && uiState.name.length <= MAX_CUSTOMER_NAME_LENGTH),
                modifier = Modifier.fillMaxWidth().padding(vertical = dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}

@Composable
private fun InputSection(
    value: String?,
    title: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    maxLine: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError : Boolean = false,
    modifier: Modifier = Modifier
) {
    Text(modifier = modifier,text = title, fontWeight = FontWeight.Bold)
    InputTextField(
        value = value ?: "",
        onValueChange = onValueChange,
        placeholder = placeholder,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
        keyboardOptions = keyboardOptions,
        maxLines = maxLine,
        isError = isError
    )
}


@Composable
@Preview(showBackground = true)
private fun BasicInfoPreview() {
    RegularCustomerManagementTheme {
        BasicInfoScreen(onNextStep = { })
    }
}
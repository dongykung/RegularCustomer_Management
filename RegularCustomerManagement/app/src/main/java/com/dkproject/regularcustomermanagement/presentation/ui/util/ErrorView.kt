package com.dkproject.regularcustomermanagement.presentation.ui.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dkproject.regularcustomermanagement.R
import com.dkproject.regularcustomermanagement.presentation.Component.Button.BasicButton
import com.dkproject.regularcustomermanagement.presentation.theme.RegularCustomerManagementTheme

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    message: String,
    onRetry: () -> Unit = {},
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = message, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_six)))
            BasicButton(
                title = stringResource(R.string.retry),
                onClick = onRetry,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ErrorViewPreview() {
    RegularCustomerManagementTheme {
        ErrorView(
            message = stringResource(R.string.failgetcustomer),
            modifier = Modifier.fillMaxSize()
        )
    }
}
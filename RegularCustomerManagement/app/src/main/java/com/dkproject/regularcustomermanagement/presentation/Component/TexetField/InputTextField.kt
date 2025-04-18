package com.dkproject.regularcustomermanagement.presentation.Component.TexetField

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dkproject.regularcustomermanagement.R

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    maxLines: Int = 1,
    placeholder: String,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: RoundedCornerShape = RoundedCornerShape(dimensionResource(R.dimen.padding_small))
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        maxLines = maxLines,
        singleLine = maxLines == 1,
        placeholder = { Text(text = placeholder) },
        shape = shape,
        isError = isError,
        keyboardOptions = keyboardOptions,
    )
}

@Composable
@Preview(showBackground = true)
private fun InputTextFieldPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        InputTextField(value = "", onValueChange = {}, placeholder = "테스트", modifier = Modifier.padding(24.dp).height(300.dp))
    }
}
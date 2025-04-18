package com.dkproject.regularcustomermanagement.presentation.Component.TexetField

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dkproject.regularcustomermanagement.R
import com.dkproject.regularcustomermanagement.presentation.theme.RegularCustomerManagementTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = stringResource(R.string.searchcustomer),
    onValueChange: (String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = 1,
    enabled: Boolean = true,
    textStyle: TextStyle = TextStyle.Default,
    interactionSource: InteractionSource,
    onSubmit: () -> Unit = {},
    shape: Shape = RoundedCornerShape(dimensionResource(R.dimen.padding_small))
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions {
            onSubmit()
            keyboardController?.hide()
        },
        maxLines = maxLines,
        singleLine = maxLines == 1,
        textStyle = textStyle
    ) { innerTextField ->
        TextFieldDefaults.DecorationBox(
            value = value,
            innerTextField = innerTextField,
            enabled = enabled,
            singleLine = maxLines == 1,
            shape = shape,
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (value.isNotEmpty()) {
                    IconButton(onClick = { onValueChange("") }) {
                        Icon(Icons.Default.Close, null)
                    }
                }
            },
            colors = TextFieldDefaults.colors(unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent),
            placeholder = { Text(text = placeholder) },
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun SearchTextFieldPreview() {
    RegularCustomerManagementTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            var text by remember { mutableStateOf("") }
            SearchTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                interactionSource = remember {  MutableInteractionSource() }
            )
        }
    }
}
package com.dkproject.regularcustomermanagement.presentation.Component.Text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.dkproject.regularcustomermanagement.R
import com.dkproject.regularcustomermanagement.presentation.theme.RegularCustomerManagementTheme

@Composable
fun MemoTextBox(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(dimensionResource(R.dimen.padding_small)),
    value: String,
    isDelete: Boolean = false,
    onDeleteClick: () -> Unit = {},
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = MaterialTheme.colorScheme.background,
    ) {
        Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.Top) {
            Text(text = value, modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(dimensionResource(R.dimen.padding_small)))
            if(isDelete) {
                IconButton(onDeleteClick, modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_small))) {
                    Icon(Icons.Default.Close, null, tint = Color.Black)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun MemoTextBoxPreview() {
    RegularCustomerManagementTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            MemoTextBox(
                value = "이사람 개쩜",
                isDelete = true
            )
        }
    }
}
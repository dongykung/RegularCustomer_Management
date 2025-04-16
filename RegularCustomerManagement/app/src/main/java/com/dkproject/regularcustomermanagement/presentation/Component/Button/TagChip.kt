package com.dkproject.regularcustomermanagement.presentation.Component.Button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dkproject.regularcustomermanagement.R
import com.dkproject.regularcustomermanagement.presentation.theme.RegularCustomerManagementTheme

@Composable
fun TagChip(
    tag: String,
    modifier: Modifier = Modifier,
    isDelete: Boolean = false,
    shape: Shape = RoundedCornerShape(dimensionResource(R.dimen.padding_medium)),
    onDelete: () -> Unit = {},
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = shape,
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = dimensionResource(R.dimen.padding_small),
                vertical = dimensionResource(R.dimen.padding_six)
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = tag,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            if (isDelete)
                IconButton(onDelete, modifier = Modifier.size(InputChipDefaults.AvatarSize)) {
                    Icon(Icons.Default.Close, null)
                }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun TagChipPreview() {
    RegularCustomerManagementTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            TagChip("Tag", isDelete = true)
        }
    }
}
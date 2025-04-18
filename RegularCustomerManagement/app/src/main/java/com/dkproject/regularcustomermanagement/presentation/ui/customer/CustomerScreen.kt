package com.dkproject.regularcustomermanagement.presentation.ui.customer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dkproject.regularcustomermanagement.R
import com.dkproject.regularcustomermanagement.domain.model.Customer
import com.dkproject.regularcustomermanagement.presentation.Component.TexetField.SearchTextField
import com.dkproject.regularcustomermanagement.presentation.model.UiState
import com.dkproject.regularcustomermanagement.presentation.theme.RegularCustomerManagementTheme
import com.dkproject.regularcustomermanagement.presentation.ui.util.ErrorView
import com.dkproject.regularcustomermanagement.presentation.ui.util.LoadingScreen
import com.dkproject.regularcustomermanagement.presentation.utils.Constants
import com.dkproject.regularcustomermanagement.presentation.utils.Constants.CUSTOMER_MOCKS

@Composable
fun CustomerScreen(
    modifier: Modifier = Modifier,
    uiState: UiState<List<Customer>>,
    searchResults: List<Customer>,
    searchQuery: String,
    onQueryChanged: (String) -> Unit
) {
    when (uiState) {
        is UiState.Error -> ErrorView(message = uiState.message, modifier = modifier.fillMaxSize())
        UiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is UiState.Success -> {
            CustomerListView(
                uiState = uiState.data,
                modifier = modifier,
                searchQuery = searchQuery,
                searchResults = searchResults,
                onQueryChanged = onQueryChanged
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomerListView(
    modifier: Modifier = Modifier,
    uiState: List<Customer>,
    searchResults: List<Customer>,
    searchQuery: String,
    onQueryChanged: (String) -> Unit = {}
) {
    val grouped: Map<String, List<Customer>> = uiState.groupBy { customer ->
        val affiliation = customer.affiliation
        if (affiliation.isNullOrEmpty()) stringResource(R.string.independent) else affiliation
    }
    val searchGrouped: Map<String, List<Customer>> = searchResults.groupBy { customer ->
        val affiliation = customer.affiliation
        if (affiliation.isNullOrEmpty()) stringResource(R.string.independent) else affiliation
    }
    val showList = if (searchQuery.isEmpty()) grouped else searchGrouped
    if (uiState.isEmpty()) {
        Box(modifier = modifier.fillMaxSize()) {
            Text(
                text = stringResource(R.string.nonexistcustomer),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    } else {
        Column(modifier = modifier) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                SearchTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                    value = searchQuery,
                    onValueChange = onQueryChanged,
                    interactionSource = remember { MutableInteractionSource() },

                    )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = dimensionResource(R.dimen.padding_medium))
                    .padding(top = dimensionResource(R.dimen.padding_eight)),
            ) {
                showList.forEach { (affiliation, customers) ->
                    stickyHeader {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            Text(
                                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small)),
                                text = affiliation,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    items(customers, key = { it.id }) { customer ->
                        CustomerListItem(customer = customer)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomerListItem(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(dimensionResource(R.dimen.padding_eight)),
    colors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
    ),
    customer: Customer
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.padding_eight)),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        shape = shape,
        colors = colors,
        onClick = {}
    ) {
        Box() {
            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = customer.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.scrim
                )
                if (!customer.phoneNumber.isNullOrEmpty())
                    Text(text = Constants.formatPhoneNumber(customer.phoneNumber))
                if (!customer.carNumber.isNullOrEmpty())
                    Text(text = "${stringResource(R.string.car)}: ${customer.carNumber}")

                if (customer.tags.isNotEmpty())
                    Row(
                        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_six)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Bookmark, null)
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_four)),
                            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_four))
                        ) {
                            customer.tags.forEach { memo ->
                                Text(text = memo)
                            }
                        }
                    }
            }
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.TopEnd)) {
                Icon(Icons.Default.MoreVert, null)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun CustomerListPreview() {
    RegularCustomerManagementTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            CustomerListView(
                modifier = Modifier.fillMaxSize(),
                uiState = emptyList(),
                searchQuery = "",
                searchResults = emptyList()
            )
        }
    }
}
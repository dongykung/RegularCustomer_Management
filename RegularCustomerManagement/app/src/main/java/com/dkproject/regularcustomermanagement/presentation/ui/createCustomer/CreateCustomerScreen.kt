package com.dkproject.regularcustomermanagement.presentation.ui.createCustomer

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dkproject.regularcustomermanagement.R
import com.dkproject.regularcustomermanagement.presentation.Component.Text.AnimatedStepText
import com.dkproject.regularcustomermanagement.presentation.model.BasicInfo
import com.dkproject.regularcustomermanagement.presentation.model.CreateCustomerStep
import com.dkproject.regularcustomermanagement.presentation.theme.RegularCustomerManagementTheme
import com.dkproject.regularcustomermanagement.presentation.ui.createCustomer.AffiliationInfo.AffiliationInfoScreen
import com.dkproject.regularcustomermanagement.presentation.ui.createCustomer.BasicInfo.BasicInfoScreen
import com.dkproject.regularcustomermanagement.presentation.ui.createCustomer.Memo.MemoScreen
import com.dkproject.regularcustomermanagement.presentation.utils.Constants.CREATECUSTOMERSTEPS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCustomerScreen(
    uiState: CreateCustomerUiState,
    onMoveStep: (CreateCustomerStep) -> Unit = {},
    popBackStack: () -> Unit = {},
    updateBasicInfo: (BasicInfo) -> Unit = {},
    updateAffiliationAndTags: (String, List<String>) -> Unit,
    updateStarAndMemo: (Boolean, List<String>) -> Unit = { _, _ -> },
    onCompleted: () -> Unit = {}
) {
    val currentStep = uiState.currentStep
    val progressValue by animateFloatAsState(
        targetValue = uiState.currentStep.progress,
        label = "progress"
    )
    BackHandler {
        when (currentStep) {
            CreateCustomerStep.BASIC -> popBackStack()
            CreateCustomerStep.AFFILIATION -> onMoveStep(CreateCustomerStep.BASIC)
            CreateCustomerStep.MEMO -> onMoveStep(CreateCustomerStep.AFFILIATION)
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(title = {
            Text(
                stringResource(R.string.addcustomer),
                fontWeight = FontWeight.Bold
            )
        },
            navigationIcon = {
                when (currentStep) {
                    CreateCustomerStep.BASIC -> {
                        IconButton(onClick = popBackStack) {
                            Icon(Icons.Default.Close, null)
                        }
                    }
                    else -> {
                        IconButton(onClick = {
                            if (currentStep == CreateCustomerStep.AFFILIATION) onMoveStep(CreateCustomerStep.BASIC)
                            else onMoveStep(CreateCustomerStep.AFFILIATION)
                        },
                            enabled = !uiState.loading) {
                            Icon(Icons.AutoMirrored.Default.ArrowBack, null)
                        }
                    }
                }
            },
            actions = {
                AnimatedVisibility(currentStep == CreateCustomerStep.MEMO) {
                    TextButton(onCompleted) {
                        Text(stringResource(R.string.complete))
                    }
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Row {
                CREATECUSTOMERSTEPS.forEachIndexed { index, step ->
                    AnimatedStepText(
                        text = stringResource(step.label),
                        isSelected = currentStep == step,
                    )
                    if (index != CREATECUSTOMERSTEPS.lastIndex)
                        Spacer(modifier = Modifier.weight(1f))
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                LinearProgressIndicator(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
                    progress = { progressValue })
                Row(verticalAlignment = Alignment.Top) {
                    CREATECUSTOMERSTEPS.forEachIndexed { index, step ->
                        val iconRes = when {
                            index < CREATECUSTOMERSTEPS.indexOf(currentStep) -> R.drawable.checkcircle       // 완료
                            index == CREATECUSTOMERSTEPS.indexOf(currentStep) -> R.drawable.ingcircle         // 현재 진행 중
                            else -> R.drawable.noncheckcircle                     // 아직 도달 X
                        }
                        Crossfade(targetState = iconRes, label = "StepIconCrossfade") { icon ->
                            Image(painter = painterResource(id = icon), contentDescription = null)
                        }
                        if (index != CREATECUSTOMERSTEPS.lastIndex)
                            Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            AnimatedContent(targetState = currentStep, label = "") { step ->
                when (step) {
                    CreateCustomerStep.BASIC -> {
                        BasicInfoScreen(modifier = Modifier, onNextStep = { basicInfo ->
                            updateBasicInfo(basicInfo)
                            onMoveStep(CreateCustomerStep.AFFILIATION)
                        })
                    }
                    CreateCustomerStep.AFFILIATION -> {
                        AffiliationInfoScreen(onNextStep = { affiliationName, tags ->
                            updateAffiliationAndTags(affiliationName,tags)
                            onMoveStep(CreateCustomerStep.MEMO)
                        })
                    }
                    CreateCustomerStep.MEMO -> {
                        MemoScreen(updateStarAndMemo = { star, memo ->
                            updateStarAndMemo(star, memo)
                            onCompleted()
                        })
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun CreateCustomerSreenPreview() {
    RegularCustomerManagementTheme {
        CreateCustomerScreen(uiState = CreateCustomerUiState(), updateAffiliationAndTags = {_, _ ->})
    }
}
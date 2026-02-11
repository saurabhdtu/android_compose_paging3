package com.sample.compose.ui.common

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TextInput(
    key: String,
    label: String = "",
    placeholder: String = "",
    value: String = "",
    leadingIconRes: Int?,
    trailingIconRes: Int?,

    keyboardType: KeyboardType?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val textStyle = MaterialTheme.typography.body1

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = false,
        textStyle = textStyle,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType?:KeyboardType.Text),
        keyboardActions = KeyboardActions.Default,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = enabled,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                placeholder = if (placeholder.isNotBlank()) {
                    { Text(text = placeholder) }
                } else {
                    null
                },
                label = if (label.isNotBlank()) {
                    { Text(text = label) }
                } else {
                    null
                },
                leadingIcon = leadingIconRes?.let { resId ->
                    { Icon(
                        painter = painterResource(id = resId),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    ) }
                },
                trailingIcon = trailingIconRes?.let { resId ->
                    { Icon(
                        painter = painterResource(id = resId),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    ) }
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    )
}
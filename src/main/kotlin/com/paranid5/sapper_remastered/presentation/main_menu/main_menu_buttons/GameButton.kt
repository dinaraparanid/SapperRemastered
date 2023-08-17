package com.paranid5.sapper_remastered.presentation.main_menu.main_menu_buttons

import arrow.core.None
import arrow.core.Some
import com.paranid5.sapper_remastered.data.player.PlayerRepository
import com.paranid5.sapper_remastered.presentation.game.ui.showGameTypeDialog
import com.paranid5.sapper_remastered.presentation.game.ui.launchSelectedGame
import com.paranid5.sapper_remastered.presentation.utils.passwordText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import java.awt.Color
import java.awt.Font
import javax.swing.*

fun GameButton(isPlayingState: MutableStateFlow<Boolean>): JButton {
    val coroutineScope = CoroutineScope(Dispatchers.Swing)

    return JButton("GAME").apply {
        initView()

        addActionListener {
            if (isPlayingState.value)
                return@addActionListener

            coroutineScope.launch { loginAndStartGame(isPlayingState) }
        }
    }
}

private fun JButton.initView() {
    horizontalAlignment = SwingConstants.CENTER
    verticalAlignment = SwingConstants.CENTER
    verticalTextPosition = SwingConstants.CENTER

    add(Box.createVerticalStrut(30))

    background = Color.DARK_GRAY
    font = Font(Font.SANS_SERIF, Font.BOLD, 25)
    foreground = Color.YELLOW
}

private suspend inline fun loginAndStartGame(isPlayingState: MutableStateFlow<Boolean>) {
    val (name, password, loginResult) = showLoginDialog()

    if (loginResult == JOptionPane.OK_OPTION)
        when (val player = PlayerRepository.getByNameAsync(name).await()) {
            None -> launchSelectedGame(
                player = PlayerRepository.addAsync(name, password).await(),
                gameTypeResult = showGameTypeDialog(),
                isPlayingState = isPlayingState
            )

            is Some -> when (player.value.password) {
                password -> launchSelectedGame(
                    player = player.value,
                    gameTypeResult = showGameTypeDialog(),
                    isPlayingState = isPlayingState
                )

                else -> showLoginErrorMessage()
            }
        }
}

private fun showLoginDialog(): Triple<String, String, Int> {
    val nameTextField = JTextField(15)
    val passwordTextField = JPasswordField(15)

    val loginResult = JOptionPane.showConfirmDialog(
        null,
        arrayOf(
            JLabel("Name"),
            nameTextField,
            JLabel("Login"),
            passwordTextField
        ),
        "Sing In",
        JOptionPane.OK_CANCEL_OPTION
    )

    println("Password: ${passwordTextField.passwordText}")
    return Triple(nameTextField.text, passwordTextField.passwordText, loginResult)
}

private fun showLoginErrorMessage() = JOptionPane.showMessageDialog(
    null,
    "Wrong Login or Password",
    "Authorization failed",
    JOptionPane.ERROR_MESSAGE
)

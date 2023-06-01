package com.paranid5.presentation

import com.paranid5.data.player.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.swing.Swing
import java.io.File
import javax.sound.sampled.AudioSystem
import javax.swing.JFrame
import kotlin.time.Duration.Companion.microseconds

/**
 * Main part of program
 * (all widgets and actions)
 */

fun startApplication() = runBlocking {
    PlayerRepository.initDatabaseAsync()

    MainFrame().let { mainFrame ->
        mainFrame.isVisible = true
        launch(Dispatchers.Swing) { launchAudioPlayback(mainFrame) }
        Unit
    }
}

private suspend fun launchAudioPlayback(mainFrame: JFrame) {
    while (mainFrame.isVisible) {
        val clip = AudioSystem.getClip().apply {
            open(
                AudioSystem.getAudioInputStream(
                    File("src/main/resources/utils/Alexei Zakharov - Transcendental (Bonus Track).wav")
                        .absoluteFile
                )
            )
        }

        clip.start()
        delay(clip.microsecondLength.microseconds)
    }
}
package org.openpin.appframework.sensors.microphone

import android.media.MediaRecorder

/**
 * Configuration for audio capture.
 *
 * @param audioSource The source of audio input & recording configuration.
 * @param outputFormat The format of the output file (e.g., MPEG_4, THREE_GPP).
 * @param audioEncoder The encoder to use for audio data (e.g., AAC, AMR_NB).
 * @param bitRate The audio encoding bit rate in bits per second.
 * @param sampleRate The audio sampling rate in Hz.
 */
data class AudioCaptureConfig(
    val audioSource: Int = MediaRecorder.AudioSource.VOICE_RECOGNITION,
    val outputFormat: Int = MediaRecorder.OutputFormat.OGG,
    val audioEncoder: Int = MediaRecorder.AudioEncoder.OPUS,
    val bitRate: Int = 24_000,
    val sampleRate: Int = 16_000
)

/**
 * Top-level camera configuration.
 *
 * @param defaultAudioCaptureConfig Default config for audio capture.
 */
data class MicrophoneConfig(
    val defaultAudioCaptureConfig: AudioCaptureConfig = AudioCaptureConfig(),
)
package com.mia_princz.tetris;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * The AudioPlayer class handles playing audio clips for various Tetris game events.
 * It provides methods for initializing sounds, playing specific sounds, controlling volume, and muting/unmuting the game.
 */
public class AudioPlayer {
    private static Clip BUTTON_SOUND;

    private static Clip MOVE_BLOCK_SOUND;

    private static Clip ROTATE_BLOCK_SOUND;

    private static Clip BLOCK_LANDED_SOUND;

    private static Clip BLOCK_HARD_DROP_SOUND;

    private static Clip LINE_CLEARED_SOUND;

    private static Clip FOUR_LINE_CLEARED_SOUND;

    private static Clip LEVEL_UP_SOUND;

    private static Clip GAME_OVER_SOUND;

    private static Clip GAME_THEME_SONG;


    // The current volume level for music.
    private static float CURRENT_MUSIC_VOLUME = -17;

    // The current volume level for sound effects.
    private static float CURRENT_SFX_VOLUME = -17;

    private static float PREVIOUS_MUSIC_VOLUME;

    private static float PREVIOUS_SFX_VOLUME;

    private static boolean isMuted = false;

    /**
     * Initializes the audio clips by loading the corresponding sound files.
     * This method should be called once during the initialization of the game.
     * It handles potential exceptions that may occur during audio clip loading.
     */
    public static void initSounds() {
        // Exception handling block for loading audio clips
        try {
            BUTTON_SOUND = AudioSystem.getClip();
            BUTTON_SOUND.open(AudioSystem.getAudioInputStream(new File("game_sounds" + File.separator + "Tetris-menu_sound.wav").getAbsoluteFile()));

            MOVE_BLOCK_SOUND = AudioSystem.getClip();
            MOVE_BLOCK_SOUND.open(AudioSystem.getAudioInputStream(new File("game_sounds" + File.separator + "Tetris-move_piece.wav").getAbsoluteFile()));

            ROTATE_BLOCK_SOUND = AudioSystem.getClip();
            ROTATE_BLOCK_SOUND.open(AudioSystem.getAudioInputStream(new File("game_sounds" + File.separator + "Tetris-rotate_piece.wav").getAbsoluteFile()));

            BLOCK_LANDED_SOUND = AudioSystem.getClip();
            BLOCK_LANDED_SOUND.open(AudioSystem.getAudioInputStream(new File("game_sounds" + File.separator + "Tetris-piece_landed.wav").getAbsoluteFile()));

            BLOCK_HARD_DROP_SOUND = AudioSystem.getClip();
            BLOCK_HARD_DROP_SOUND.open(AudioSystem.getAudioInputStream(new File("game_sounds" + File.separator + "Tetris-piece_hard-drop.wav").getAbsoluteFile()));

            LINE_CLEARED_SOUND = AudioSystem.getClip();
            LINE_CLEARED_SOUND.open(AudioSystem.getAudioInputStream(new File("game_sounds" + File.separator + "Tetris-line_clear.wav").getAbsoluteFile()));

            FOUR_LINE_CLEARED_SOUND = AudioSystem.getClip();
            FOUR_LINE_CLEARED_SOUND.open(AudioSystem.getAudioInputStream(new File("game_sounds" + File.separator + "Tetris-4_lines_clear.wav").getAbsoluteFile()));

            LEVEL_UP_SOUND = AudioSystem.getClip();
            LEVEL_UP_SOUND.open(AudioSystem.getAudioInputStream(new File("game_sounds" + File.separator + "Tetris-level_up_jingle.wav").getAbsoluteFile()));

            GAME_OVER_SOUND = AudioSystem.getClip();
            GAME_OVER_SOUND.open(AudioSystem.getAudioInputStream(new File("game_sounds" + File.separator + "Tetris-game_over.wav").getAbsoluteFile()));

            GAME_THEME_SONG = AudioSystem.getClip();
            GAME_THEME_SONG.open(AudioSystem.getAudioInputStream(new File("game_sounds" + File.separator + "Tetris-main_theme_song.wav").getAbsoluteFile()));

        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException lineUnavailableException) {
            // RunTimeException enough. AudioPlayer is not a vital class.
            throw new RuntimeException(lineUnavailableException);
        }
    }

    /**
     * Plays the sound for a button press event.
     */
    public static void playButtonSound() {
        BUTTON_SOUND.setFramePosition(0);
        BUTTON_SOUND.start();
    }

    /**
     * Plays the sound for moving a block.
     */
    public static void playMoveBlockSound() {
        MOVE_BLOCK_SOUND.setFramePosition(0);
        MOVE_BLOCK_SOUND.start();
    }

    /**
     * Plays the sound for rotating a block.
     */
    public static void playRotateBlockSound() {
        ROTATE_BLOCK_SOUND.setFramePosition(0);
        ROTATE_BLOCK_SOUND.start();
    }

    /**
     * Plays the sound if a block lands.
     */
    public static void playBlockLandedSound() {
        BLOCK_LANDED_SOUND.setFramePosition(0);
        BLOCK_LANDED_SOUND.start();
    }

    /**
     * Plays the sound for hard dropping a block.
     */
    public static void playBlockHardDropSound() {
        BLOCK_HARD_DROP_SOUND.setFramePosition(0);
        BLOCK_HARD_DROP_SOUND.start();
    }

    /**
     * Plays the sound if a line was cleared.
     */
    public static void playLineClearedSound() {
        LINE_CLEARED_SOUND.setFramePosition(0);
        LINE_CLEARED_SOUND.start();
    }

    /**
     * Plays the sound if four lines were cleared.
     */
    public static void playFourLineClearedSound() {
        FOUR_LINE_CLEARED_SOUND.setFramePosition(0);
        FOUR_LINE_CLEARED_SOUND.start();
    }

    /**
     * Plays the sound if leveled up.
     */
    public static void playLevelUpSound() {
        LEVEL_UP_SOUND.setFramePosition(0);
        LEVEL_UP_SOUND.start();
    }

    /**
     * Plays the sound if the game is over.
     */
    public static void playGameOverSound() {
        GAME_OVER_SOUND.setFramePosition(0);
        GAME_OVER_SOUND.start();
    }

    /**
     * Plays the sound of the theme song.
     */
    public static void playGameThemeSong() {
        GAME_THEME_SONG.setFramePosition(0);
        GAME_THEME_SONG.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Retrieves the current music volume level.
     * @return The current music volume level.
     */
    public static float getCurrentMusicVolume() {
        return CURRENT_MUSIC_VOLUME;
    }

    /**
     * Sets the current music volume level.
     * @param value The new value for the music volume.
     */
    public static void setCurrentMusicVolume(float value) {
        CURRENT_MUSIC_VOLUME = value;
    }

    /**
     * Retrieves the current SFX volume level.
     * @return The current SFX volume level.
     */
    public static float getCurrentSfxVolume() {
        return CURRENT_SFX_VOLUME;
    }

    /**
     * Sets the current SFX volume level.
     * @param value The new SFX for the music volume.
     */
    public static void setCurrentSfxVolume(float value) {
        CURRENT_SFX_VOLUME = value;
    }

    public static FloatControl getFloatControlMusic() {
        return (FloatControl) GAME_THEME_SONG.getControl(FloatControl.Type.MASTER_GAIN);
    }

    public static FloatControl[] getFloatControlSfx() {
        return new FloatControl[] {
                (FloatControl) BUTTON_SOUND.getControl(FloatControl.Type.MASTER_GAIN),
                (FloatControl) MOVE_BLOCK_SOUND.getControl(FloatControl.Type.MASTER_GAIN),
                (FloatControl) ROTATE_BLOCK_SOUND.getControl(FloatControl.Type.MASTER_GAIN),
                (FloatControl) BLOCK_LANDED_SOUND.getControl(FloatControl.Type.MASTER_GAIN),
                (FloatControl) BLOCK_HARD_DROP_SOUND.getControl(FloatControl.Type.MASTER_GAIN),
                (FloatControl) LINE_CLEARED_SOUND.getControl(FloatControl.Type.MASTER_GAIN),
                (FloatControl) FOUR_LINE_CLEARED_SOUND.getControl(FloatControl.Type.MASTER_GAIN),
                (FloatControl) LEVEL_UP_SOUND.getControl(FloatControl.Type.MASTER_GAIN),
                (FloatControl) GAME_OVER_SOUND.getControl(FloatControl.Type.MASTER_GAIN),
        };
    }

    /**
     * Mutes or unmutes the game by adjusting the volume levels of music and sound effects.
     * If the game is currently muted, it will be unmuted with the previous volume levels.
     * If the game is not muted, it will be muted, and the volume levels will be set to -80.
     */
    public static void muteOrUnmuteGame() {
        if (!isMuted) {
            PREVIOUS_MUSIC_VOLUME = CURRENT_MUSIC_VOLUME;
            PREVIOUS_SFX_VOLUME = CURRENT_SFX_VOLUME;
            AudioPlayer.setCurrentSfxVolume(-80);
            AudioPlayer.setCurrentMusicVolume(-80);
            isMuted = true;
        } else {
            AudioPlayer.setCurrentSfxVolume(PREVIOUS_MUSIC_VOLUME);
            AudioPlayer.setCurrentMusicVolume(PREVIOUS_SFX_VOLUME);
            isMuted = false;
        }

        AudioPlayer.getFloatControlMusic().setValue(AudioPlayer.getCurrentMusicVolume());

        FloatControl[] controls = AudioPlayer.getFloatControlSfx();
        for (FloatControl control : controls) {
            control.setValue(AudioPlayer.getCurrentSfxVolume());
        }
    }

    /**
     * Checks if the game is currently muted.
     * @return true if the game is muted, false otherwise.
     */
    public static boolean getIsMuted() {
        return isMuted;
    }
}

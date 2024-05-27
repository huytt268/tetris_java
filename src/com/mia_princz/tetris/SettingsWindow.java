package com.mia_princz.tetris;

import javax.imageio.ImageIO;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The settings window of the Tetris game.
 * This class extends JFrame and implements ActionListener.
 */
public class SettingsWindow extends JFrame implements ActionListener {

    // Background animation thread
    private final BackgroundAnimationThread BACKGROUND_ANIMATION_THREAD;

    // Music volume slider
    private JSlider sliderMusicVolume;

    // Sound effects volume slider
    private JSlider sliderSFXVolume;

    /**
     * Constructs a SettingsWindow object.
     *
     * @param menuBackgroundPanel       The menu background panel.
     * @param backgroundAnimationThread The background animation thread.
     */
    public SettingsWindow(MenuBackgroundPanel menuBackgroundPanel, BackgroundAnimationThread backgroundAnimationThread) {
        // Initialization methods
        initWindow();
        initButtons();
        initSliders();
        initImages();

        BACKGROUND_ANIMATION_THREAD = backgroundAnimationThread;
        add(menuBackgroundPanel);
    }
    private void initWindow() {
        setTitle("Tetris ~ Settings");
        setLayout(new BorderLayout());
        setSize(720, 900);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initButtons() {
        JButton backToMenuButton = new JButton();
        backToMenuButton.setBounds(190, 700, 335, 70);
        backToMenuButton.setActionCommand("back");
        backToMenuButton.addActionListener(this);
        backToMenuButton.setOpaque(false);
        backToMenuButton.setContentAreaFilled(false);
        backToMenuButton.setBorderPainted(false);
        backToMenuButton.setFocusPainted(false);
        backToMenuButton.setIcon(new ImageIcon("game_assets\\button_return-to-menu.png"));
        backToMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backToMenuButton.setIcon(new ImageIcon("game_assets\\button_return-to-menu (1).png"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backToMenuButton.setIcon(new ImageIcon("game_assets\\button_return-to-menu.png"));
            }
        });

        add(backToMenuButton);
    }

    private void initSliders() {
        sliderMusicVolume = new JSlider(-40, 6);
        sliderMusicVolume.setOpaque(false);
        sliderMusicVolume.setBounds(320,100,300,60);
        sliderMusicVolume.setValue((int) AudioPlayer.getCurrentMusicVolume());
        sliderMusicVolume.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                AudioPlayer.setCurrentMusicVolume(sliderMusicVolume.getValue());

                if (AudioPlayer.getCurrentMusicVolume() == -40) {
                    AudioPlayer.setCurrentMusicVolume(-80);
                }

                AudioPlayer.getFloatControlMusic().setValue(AudioPlayer.getCurrentMusicVolume());
            }
        });

        sliderSFXVolume = new JSlider(-40, 6);
        sliderSFXVolume.setOpaque(false);
        sliderSFXVolume.setBounds(320,200,300,60);
        sliderSFXVolume.setValue((int) AudioPlayer.getCurrentSfxVolume());
        sliderSFXVolume.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                AudioPlayer.setCurrentSfxVolume(sliderSFXVolume.getValue());

                if (AudioPlayer.getCurrentSfxVolume() == -40) {
                    AudioPlayer.setCurrentSfxVolume(-80);
                }

                FloatControl[] controls = AudioPlayer.getFloatControlSfx();
                for (FloatControl control : controls) {
                    control.setValue(AudioPlayer.getCurrentSfxVolume());
                }
            }
        });

        add(sliderMusicVolume);
        add(sliderSFXVolume);
    }

    private void initImages() {
        BufferedImage img;
        try {
            img = ImageIO.read(new File("game_assets\\label_music-volume.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image scaled = img.getScaledInstance(210, 60, Image.SCALE_SMOOTH);

        JLabel musicVolumeLalel = new JLabel();
        musicVolumeLalel.setIcon(new ImageIcon(scaled));
        musicVolumeLalel.setBounds(100, 100, 210, 60);

        try {
            img = ImageIO.read(new File("game_assets\\label_sfx-volume.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scaled = img.getScaledInstance(210, 60, Image.SCALE_SMOOTH);
        JLabel sfxVolumeLabel = new JLabel();
        sfxVolumeLabel.setIcon(new ImageIcon(scaled));
        sfxVolumeLabel.setBounds(100, 200, 210, 60);



        add(musicVolumeLalel);
        add(sfxVolumeLabel);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("back")) {
            AudioPlayer.playButtonSound();

            BACKGROUND_ANIMATION_THREAD.stopThread();

            setVisible(false);

            try {
                Tetris.showMainMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

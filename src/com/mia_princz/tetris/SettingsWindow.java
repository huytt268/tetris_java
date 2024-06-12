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
        setLayout(null);
        setSize(560, 700);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initButtons() {
        JButton backToMenuButton = new JButton();
        backToMenuButton.setBounds(186, 550, 188, 80); // Adjusted for new button size
        backToMenuButton.setActionCommand("back");
        backToMenuButton.addActionListener(this);
        backToMenuButton.setOpaque(false);
        backToMenuButton.setContentAreaFilled(false);
        backToMenuButton.setBorderPainted(false);
        backToMenuButton.setFocusPainted(false);
        backToMenuButton.setIcon(new ImageIcon(new ImageIcon("game_assets\\button_return-to-menu.png").getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
        backToMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backToMenuButton.setIcon(new ImageIcon(new ImageIcon("game_assets\\button_return-to-menu (1).png").getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backToMenuButton.setIcon(new ImageIcon(new ImageIcon("game_assets\\button_return-to-menu.png").getImage().getScaledInstance(188, 80, Image.SCALE_SMOOTH)));
            }
        });

        add(backToMenuButton);
    }

    private void initSliders() {
        sliderMusicVolume = new JSlider(-40, 6);
        sliderMusicVolume.setOpaque(false);
        sliderMusicVolume.setBounds(250, 100, 250, 50); // Adjusted for new window size
        sliderMusicVolume.setValue(6); // Default value set to 100%
        sliderMusicVolume.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newVolume = sliderMusicVolume.getValue();
                AudioPlayer.setCurrentMusicVolume(newVolume);

                if (newVolume == -40) {
                    newVolume = -80;
                }

                AudioPlayer.getFloatControlMusic().setValue(newVolume);
            }
        });

        sliderSFXVolume = new JSlider(-40, 6);
        sliderSFXVolume.setOpaque(false);
        sliderSFXVolume.setBounds(250, 180, 250, 50); // Adjusted for new window size
        sliderSFXVolume.setValue(6); // Default value set to 100%
        sliderSFXVolume.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newVolume = sliderSFXVolume.getValue();
                AudioPlayer.setCurrentSfxVolume(newVolume);

                if (newVolume == -40) {
                    newVolume = -80;
                }

                FloatControl[] controls = AudioPlayer.getFloatControlSfx();
                for (FloatControl control : controls) {
                    control.setValue(newVolume);
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
        Image scaled = img.getScaledInstance(188, 80, Image.SCALE_SMOOTH); // Adjusted for new window size

        JLabel musicVolumeLalel = new JLabel();
        musicVolumeLalel.setIcon(new ImageIcon(scaled));
        musicVolumeLalel.setBounds(50, 80, 188, 80); // Adjusted for new window size

        try {
            img = ImageIO.read(new File("game_assets\\label_sfx-volume.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scaled = img.getScaledInstance(188, 80, Image.SCALE_SMOOTH); // Adjusted for new window size
        JLabel sfxVolumeLabel = new JLabel();
        sfxVolumeLabel.setIcon(new ImageIcon(scaled));
        sfxVolumeLabel.setBounds(50, 160, 188, 80); // Adjusted for new window size

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

package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    Clip clip;
    URL soundURL[]=new URL[30];
    FloatControl gainControl;

    public Sound() {
        soundURL[0]=getClass().getResource("/sound/backgroundMusic.wav");
        soundURL[1]=getClass().getResource("/sound/dash.wav");
        soundURL[2]=getClass().getResource("/sound/creditMusic.wav");
    }

    public void setFile(int i, float volume){
        try{
            if(i!=1){
                stop();
            }

            if (soundURL[i] == null) {
                throw new IllegalArgumentException("Sound URL at index " + i + " is null. Check resource path.");
            }
            AudioInputStream ais= AudioSystem.getAudioInputStream(soundURL[i]);
            clip=AudioSystem.getClip();
            clip.open(ais);

            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolume(volume);
            } else {
                System.err.println("MASTER_GAIN control not supported for this clip.");
            }

        } catch (Exception e){
            System.err.println("Error loading sound at index " + i + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void setVolume(float decibels) {
        if (gainControl != null) {
            // Ensure the value is within the valid range
            float min = gainControl.getMinimum();
            float max = gainControl.getMaximum();
            if (decibels < min) {
                decibels = min;
            } else if (decibels > max) {
                decibels = max;
            }
            gainControl.setValue(decibels);
            System.out.println("Volume set to " + decibels + " dB");
        } else {
            System.err.println("Cannot set volume: Gain control is null");
        }
    }
    public void play(){
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
            System.out.println("Playing sound: " + (clip.isRunning() ? "started" : "failed to start"));
        } else {
            System.out.println("Cannot play: Clip is null");
        }
    }

    public void loop(){
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            System.out.println("Looping sound");
        } else {
            System.out.println("Cannot loop: Clip is null");
        }
    }

    public void stop(){
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
                System.out.println("Stopped sound");
            } else {
                System.out.println("Sound was not running");
            }
            clip.flush();
            clip.close();
            clip = null;
            System.out.println("Closed sound clip");
        } else {
            System.out.println("No sound to stop: Clip is null");
        }
    }
}

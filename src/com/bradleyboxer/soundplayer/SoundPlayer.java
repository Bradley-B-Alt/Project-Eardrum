package com.bradleyboxer.soundplayer;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SoundPlayer extends JFrame {

	private static final long serialVersionUID = 1L;
	static JPanel buttonPanel = new JPanel();
	static JPanel buttonPanel2 = new JPanel();
	static JPanel buttonPanel3 = new JPanel();
	static JButton stopPlaying = new JButton();
	static Color buttonColor = Color.decode("#66CCCC");
	/*to add sounds, update these lines*/
	static AudioClip[] clickArray = {simp(3), simp(1), simp(2), simp(0), simp(4), simp(5), simp(6), simp(7)};
	static JButton[] buttonArray = {new JButton(), new JButton(), new JButton(), new JButton(), new JButton(), new JButton(), new JButton(), new JButton()};
	static String[] buttonNames = {"Heyeyeyey", "Rick Astley", "Sandstorm", "Explosion", "Bork", "FIRST Priority", "Mnuah!", "Airhorn"};
	/*to add sounds, update these lines*/
	
	static int soundTally = clickArray.length;
	
	public static AudioClip simp(int resourceNumber) {
		return Applet.newAudioClip((URL) SoundPlayer.class.getResource("/resources/sound" + String.valueOf(resourceNumber) + ".wav"));
	}
	
	public SoundPlayer() {
		
		this.setSize(350, 155);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Zach's Amazing Sound Player");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setLayout(new FlowLayout());
		this.add(buttonPanel);
		this.add(buttonPanel2);
		this.add(buttonPanel3);
		this.getContentPane().setBackground(Color.decode("#5F9F9F"));
		this.setResizable(false);
		this.setIconImage(new ImageIcon(SoundPlayer.class.getResource("/resources/icon.png")).getImage());
		stopPlaying.setText("Stop Playback");
		stopPlaying.addActionListener(new stopPlayback());
		stopPlaying.setBackground(buttonColor);
		buttonPanel.setBackground(Color.decode("#2F4F4F"));
		buttonPanel2.setBackground(Color.decode("#2F4F4F"));
		buttonPanel3.setBackground(Color.decode("#2F4F4F"));
		
		for(int i = 0;i<soundTally;i++) { //add every JButton to one of three panels split 3 per
			if(i<3) {buttonPanel.add(buttonArray[i]);} 
			if(i<6 && i>=3) {buttonPanel2.add(buttonArray[i]);} 
			if(i<9 && i>=6) {buttonPanel3.add(buttonArray[i]);} 
			buttonArray[i].setText(buttonNames[i]); //set text of buttons
			buttonArray[i].addActionListener(new playSound()); //listen for button presses
			buttonArray[i].setBackground(buttonColor);
		}
		buttonPanel3.add(stopPlaying);
	}
	
	public static void main(String[] args) {
		new SoundPlayer(); //main program
	}
	
	public void stopAllSounds() {
		for(int i = 0;i<soundTally;i++) {
			clickArray[i].stop();
		}
	}
	
	private class playSound implements ActionListener {	
		public void actionPerformed(ActionEvent e) {
			for(int i = 0;i<soundTally;i++) { //search through buttonArrays and stop/start the clickArray corresponding to it
				if(e.getSource()==buttonArray[i]) {
					clickArray[i].stop();
					clickArray[i].play();
				}
			}
		}
	}
	
	private class stopPlayback implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==stopPlaying) {
				stopAllSounds();
			}
				
		}
	}
}

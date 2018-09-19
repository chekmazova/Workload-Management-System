package gui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class Calendar extends JFrame{
	
	public Calendar() {
		JLabel calendarImg = new JLabel();
		Image img = new ImageIcon(this.getClass().getResource("/calendar.png")).getImage().getScaledInstance(800, 500, Image.SCALE_DEFAULT);
		calendarImg.setIcon(new ImageIcon(img));
		calendarImg.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(calendarImg);
	}

}

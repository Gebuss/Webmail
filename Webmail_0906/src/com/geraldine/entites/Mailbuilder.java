package com.geraldine.entites;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Point;
import javax.swing.SwingConstants;

public class Mailbuilder extends JPanel {
	private final JScrollBar scrollBar_1 = new JScrollBar();

	/**
	 * Create the panel.
	 */
	public Mailbuilder() {
		setLayout(new BorderLayout(0, 0));
		
		JTextArea  textMessage = new JTextArea();
		 textMessage.setBorder(new EtchedBorder(EtchedBorder.LOWERED, SystemColor.windowBorder, null));
		 textMessage.setDragEnabled(true);
		 textMessage.setToolTipText("Enter your text here");
		 textMessage.setLineWrap(true);
		 textMessage.setWrapStyleWord(true);
		 textMessage.setColumns(20);
		add( textMessage);
		scrollBar_1.setAutoscrolls(true);
		add(scrollBar_1, BorderLayout.EAST);
		
//		JButton btnEnvoyer = new JButton("Envoyer");
//		btnEnvoyer.setMaximumSize(new Dimension(13, 13));
//		btnEnvoyer.setHorizontalTextPosition(SwingConstants.LEFT);
//		btnEnvoyer.setHorizontalAlignment(SwingConstants.LEFT);
//		btnEnvoyer.setLocation(new Point(33, 0));
//		btnEnvoyer.setSelected(true);
//		btnEnvoyer.setMinimumSize(new Dimension(11, 10));
//		btnEnvoyer.addMouseMotionListener(new MouseMotionAdapter() {
//			@Override
//			public void mouseDragged(MouseEvent arg0) {
//			}
//		});
//		btnEnvoyer.setPreferredSize(new Dimension(14, 14));
//		add(btnEnvoyer, BorderLayout.SOUTH);
//
	}

}

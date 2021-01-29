# POS Machine in Cafeteria
> Develop an application that allows coffee businesses to manage their store through the automatic selling system.
This application is targeted towards small cafes that don't usually have enough employees.

## Table of contents
* [General information](#general-information)
* [Screenshots](#screenshots)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Inspiration](#inspiration)
* [Contact](#contact)

## General information
This project is held by KG-ITBANK which is an employment helper in Korea.
The purpose of project is to improve my Java coding skills, and also helps me to understand logical structure of Java coding.
This project has motivated me to become a web developer.

## Screenshots
![main display](./img/screenshot.png)

## Technologies
- eclipse
- Oracle Database - version 18c Express Edition
- sqldeveloper - version 19.2.1.247

## Setup
- Describe how to install:
- setup your local environement:
- add link to demo version:

## Code Examples
examples of main display:   
`package project.five.pos.device;

import java.awt.*;

import javax.swing.*;

import project.five.pos.SwingTools;
import project.five.pos.device.comp.DevicePanel;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.btn.action.*;

public class MainDisplay extends JFrame {

	static String[] image_path = {
			"assets/images/device/14.png",
			"assets/images/device/setting.png",
			"assets/images/device/coin.png",
	};
	// 관리자 모드, 판매모드
	JButton manage_btn, sale_btn, managerSign_btn;
	JPanel center_p, south_p;
	JLabel deviceId_lab;
	
	public MainDisplay(String device_id) {
		setLayout(new BorderLayout());
		setResizable(false);	
		
		center_p = new DevicePanel(image_path[0], 890, 789, 
									new FlowLayout(FlowLayout.CENTER, 100, 250));
		south_p = new DevicePanel(image_path[0], 890, 789, new BorderLayout());
		
		deviceId_lab = new JLabel("DEVICE_ID : " + device_id);
		
		manage_btn = new DeviceBtn("관리자", image_path[1], 180, new ChangeFrameAction(this, device_id));

		sale_btn = new DeviceBtn("판매", image_path[2], 180, new ChangeFrameAction(this, device_id));

		managerSign_btn = new DeviceBtn("매니저 등록", 100, 30, new ChangeFrameAction(this));
		
		center_p.add(manage_btn);
		center_p.add(sale_btn);
		south_p.add(deviceId_lab, BorderLayout.WEST);
		south_p.add(managerSign_btn, BorderLayout.EAST);
		
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);
		SwingTools.initTestFrame(this, "main", true);
	}}`



## Features
Awesome features:
* Able to arrange all the tables related to store management in administer mode.
* Encourage the customer to use mileage system.
* Able to respect customer's privacy by securely saving personal information in database .

To-do list:
* Change the setting of Hikari DB to accept more information from application.
* Use 'JSON' to store all the information even if the application is shut down.

## Status
This project is finished in 2020, 24th of December. 

## Inspiration
This project inspired by COFFE COURT, the coffee shop in front of buiding where I study in.

## Contact
Created by [@teamFive-KGITBANK](https://www.teamFive.com/) - feel free to contact our team!

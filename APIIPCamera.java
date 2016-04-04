package com.munichtrading.tracking.camera;

/**
 * Java API for iPCamera App
 * https://itunes.apple.com/de/app/id570912928
 * 
 * @author Linus Kohl
 * @version 0.1
 *
 * Copyright (C) 2016 Linus Kohl, kohl@munichtrading.com
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD license.  See the LICENSE file for details.
 */

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.InetAddress;

public class APIIPCamera {
	private static Logger logger = Logger.getRootLogger();
    private Settings settings;
    private InetAddress device;
	private final String USER_AGENT = "Mozilla/5.0";
	
	public APIIPCamera(String _device) throws Exception {
		SimpleLayout layout = new SimpleLayout();
	    ConsoleAppender consoleAppender = new ConsoleAppender( layout );
	    logger.addAppender( consoleAppender );
	    logger.setLevel( Level.INFO);
		this.device = InetAddress.getByName(_device);
		this.getSettings();
	}
	
	public boolean setCamera(int val) {
		logger.info("setCamera: " + val);
		this.sendGet("camera", val);
		return this.settings.camera == val;
	}
	
	public int getCamera() {
		return this.settings.camera;
	}
	
	public boolean setFps(int val) {
		logger.info("setFps: " + val);
		this.sendGet("fps", val);
		return this.settings.fps == val;
	}
	
	public int getFps() {
		return this.settings.fps;
	}
	
	public boolean setQuality(double val) {
		logger.info("setFps: " + val);
		this.sendGet("quality", val);
		return this.settings.quality == val;
	}
	
	public double getQuality() {
		return this.settings.quality;
	}
	
	public boolean setResolution(int val) {
		logger.info("setResolution: " + val);
		this.sendGet("resolution", val);
		return this.settings.resolution == val;
	}
	
	public int getResolution() {
		return this.settings.resolution;
	}
	
	public boolean setFlip(int val) {
		logger.info("setFlip: " + val);
		this.sendGet("flip", val);
		return this.settings.flip == val;
	}
	
	public int getFlip() {
		return this.settings.flip;
	}
	
	public boolean setRotation(int val) {
		logger.info("setRotation: " + val);
		this.sendGet("rotation", val);
		return this.settings.rotation == val;
	}
	
	public int getRotation() {
		return this.settings.rotation;
	}
	
	public boolean setWb(boolean val) {
		logger.info("setWb: " + val);
		this.sendGet("wb", val);
		return this.settings.wb == val;
	}
	
	public boolean getWb() {
		return this.settings.wb;
	}
	
	public boolean setExposure(boolean val) {
		logger.info("setExposure: " + val);
		this.sendGet("exposure", val);
		return this.settings.exposure == val;
	}
	
	public boolean getExposure() {
		return this.settings.exposure;
	}
	
	public boolean setFocus(boolean val) {
		logger.info("setFocus: " + val);
		this.sendGet("focus", val);
		return this.settings.focus == val;
	}
	
	public boolean getFocus() {
		return this.settings.focus;
	}
	
	public boolean setTorch(boolean val) {
		logger.info("setTorch: " + val);
		this.sendGet("torch", val);
		return this.settings.torch == val;
	}
	
	public boolean getTorch() {
		return this.settings.torch;
	}
	
	private void sendGet(String parameter, boolean value) {
		String string_val = value ? "1" : "0";
		this.sendGet(parameter, string_val);
	}
	
	private void sendGet(String parameter, int value) {
		String string_val = Integer.toString(value);
		this.sendGet(parameter, string_val);
	}
	
	private void sendGet(String parameter, double value) {
		String string_val = Double.toString(value);
		this.sendGet(parameter, string_val);
	}
	
	private boolean getSettings() {
		try {
			URIBuilder builder = new URIBuilder().setScheme("http")
					.setHost(this.device.getHostName())
					.setPath("/parameters");
			HttpClient client = HttpClients.createDefault();
			HttpGet request = new HttpGet(builder.build());
			request.addHeader("User-Agent", USER_AGENT);
			HttpResponse response = client.execute(request);
			logger.info("Sending request: " + builder.toString());
			logger.info("Response Code  : " + response.getStatusLine().getStatusCode());
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			this.updateSettings(result.toString());
		} catch (Exception e) {
		   return false;
		}
		return true;
	}
	
	private void sendGet(String parameter, String value) {
		try {
			URIBuilder builder = new URIBuilder().setScheme("http")
					.setHost(this.device.getHostName()).setPath("/parameters")
					.addParameter(parameter, value);

			HttpClient client = HttpClients.createDefault();
			HttpGet request = new HttpGet(builder.build());
			request.addHeader("User-Agent", USER_AGENT);
			HttpResponse response = client.execute(request);

			logger.info("Sending request: " + builder.toString());
			logger.info("Response Code  : " + response.getStatusLine().getStatusCode());

			if(response.getStatusLine().getStatusCode() == 200) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));
				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				this.updateSettings(result.toString());
			} else {
				throw new Exception("Request unsuccessfull");
			}
		} catch (HttpHostConnectException e) {
			logger.error("Could not connect to device");
		} catch (Exception e) {
			logger.error("Something went wrong");
		}
	}
	
	// update settings from device
    private void updateSettings(String result) throws Exception {
    	GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Settings.class, new SettingsDeserializer());
		Gson gson = gsonBuilder.create();
        this.settings = gson.fromJson( result, Settings.class );
    }
	
}

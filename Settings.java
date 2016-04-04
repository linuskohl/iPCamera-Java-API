package com.munichtrading.tracking.camera;

/**
 * Settings Class for iPCamera App
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

public class Settings {
	// possible values to set
	// iphone front or back camera
	public static final int    BACK_CAMERA   =   0;
	public static final int    FRONT_CAMERA  =   1;
	// resolution settings
	public static final int    RES_192x144   =   0;
	public static final int    RES_352x288   =   1;
	public static final int    RES_480x360   =   2;
	public static final int    RES_640x480   =   3;
	public static final int    RES_1280x720  =   4;
	// quality settings
	public static final double QUALITY_0     = 0.0;
	public static final double QUALITY_1     = 0.1;
	public static final double QUALITY_2     = 0.2;
	public static final double QUALITY_3     = 0.3;
	public static final double QUALITY_4     = 0.4;
	public static final double QUALITY_5     = 0.5;
	public static final double QUALITY_6     = 0.6;
	public static final double QUALITY_7     = 0.7;
	public static final double QUALITY_8     = 0.8;
	public static final double QUALITY_9     = 0.9;
	public static final double QUALITY_10    = 1.0;
	// frames per second
	public static final int    FPS_1         =   1;
	public static final int    FPS_2         =   2;
	public static final int    FPS_5         =   5;
	public static final int    FPS_10        =  10;
	public static final int    FPS_MAX       =   0;
	// flip image
	public static final int    FLIP_OFF      =   0;
	public static final int    FLIP_H        =   1;
	public static final int    FLIP_V        =   2;
	// orientation settings
	public static final int    ORI_AUTO      =   0;
	public static final int    ORI_L_LEFT    =   1;
	public static final int    ORI_L_RIGHT   =   3;
	public static final int    ORI_P         =   4;
	public static final int    ORI_P_FLIP    =   2;
	
	// settings 
	public int     camera;
	public int     fps;
	public double  quality;
	public int     resolution;
	public int     flip;
	public int     battery;
	public int     rotation;
	public boolean wb;
	public boolean exposure;
	public boolean torch;
	public boolean focus;
	public boolean stats;
		
	
	// toString functions for settings
	public String getCameraString() {
		String res = "Camera:\t\t";
		switch(this.camera) {
		  case BACK_CAMERA: res += "Back"; break;
		  case FRONT_CAMERA: res += "Front"; break;
		}
		return res;
	}
	
	public String getFPSString() {
		String res = "FPS:\t\t";
		switch(this.fps) {
			case FPS_1:   res += "1"; break;
			case FPS_2:   res += "2"; break;
			case FPS_5:   res += "5"; break;
			case FPS_10:  res += "10"; break;
			case FPS_MAX: res += "Max"; break;
		}
		return res;
	}
	
	public String getQualityString() {
		return "Quality:\t"+this.quality;
	}
	
	public String getResolutionString() {
		String res = "Resolution: \t";
		switch(this.resolution) {
			case RES_192x144:  res += "192x144"; break;
			case RES_352x288:  res += "352x288"; break;
			case RES_480x360:  res += "480x360"; break;
			case RES_640x480:  res += "640x480"; break;
			case RES_1280x720: res += "1280x720"; break;
		}
		return res;
	}
	
	public String getFlipString() {
		String res = "Flip: \t\t";
		switch(this.flip) {
			case FLIP_OFF: res += "Off"; break;
			case FLIP_H:   res += "Horizontal"; break;
			case FLIP_V:   res += "Vertical"; break;
		}
		return res;
	}
	
	public String getRotationString() {
		String res = "Rotation: \t";
		switch(this.rotation) {
			case ORI_AUTO:    res += "Auto"; break;
			case ORI_L_LEFT:  res += "Landscape Left"; break;
			case ORI_L_RIGHT: res += "Landscape Right"; break;
			case ORI_P:       res += "Portrait"; break;
			case ORI_P_FLIP:  res += "Portrait flip"; break;
		}
		return res;
	}
	
	public String getExposureString() {
		return "Exposure Lock: \t"+this.exposure;
	}
	
	public String getTorchString() {
		return "Torch: \t\t"+this.torch;
	}
	
	public String getFocusString() {
		return "Focus Lock: \t"+this.focus;
	}
	
	public String getStatsString() {
		return "Statistics: \t"+this.stats;
	}

	public String getBatteryString() {
		return "Battery: \t" + this.battery + "%";
	}
	
	public String toString() {
		return "\n__Current Settings__\n"
		      +this.getCameraString()+"\n"
	          +this.getFPSString()+"\n"
			  +this.getResolutionString()+"\n"
	          +this.getQualityString()+"\n"
	          +this.getFlipString()+"\n"
	          +this.getRotationString()+"\n"
	          +this.getExposureString()+"\n"
	          +this.getFocusString()+"\n"
	          +this.getStatsString()+"\n"
	          +this.getTorchString()+"\n"
	          +this.getBatteryString();
	}
	
}

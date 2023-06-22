package com.shopping.cart.RSocket;

public class ClinicalData {
	
	private int heartRate;
	private int bp;
	public int getHeartRate() {
		return heartRate;
	}
	public ClinicalData(int heartRate, int bp) {
		this.heartRate = heartRate;
		this.bp = bp;
	}
	@Override
	public String toString() {
		return "ClinicalData [heartRate=" + heartRate + ", bp=" + bp + "]";
	}
	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}
	public int getBp() {
		return bp;
	}
	public void setBp(int bp) {
		this.bp = bp;
	}

}

package model;

public class FileStorage {

	public double bytes;

	public void addBytes(long amount) {
		this.bytes += amount;
	}

	public double getBytes() {
		return this.bytes;
	}

}

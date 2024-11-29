package com.example.minhasreceitas;

public class Contact {

	// private variables
	int _id;
	byte[] _image;

	// Empty constructor
	public Contact() {

	}

	// constructor
	public Contact(int keyId,byte[] image) {
		this._id = keyId;
		this._image = image;

	}
	public Contact( byte[] image) {
		this._image = image;

	}
	public Contact(int keyId) {
		this._id = keyId;

	}

	// getting ID
	public int getID() {
		return this._id;
	}

	// setting id
	public void setID(int keyId) {
		this._id = keyId;
	}


	// getting phone number
	public byte[] getImage() {
		return this._image;
	}

	// setting phone number
	public void setImage(byte[] image) {
		this._image = image;
	}
}

package model;

public class Room {
	private int roomId;
	private String roomName;
	private String roomHost;
	
	public Room(int roomId,String roomName,String roomHost) {
		this.setRoomId(roomId);
		this.setRoomName(roomName);
		this.setRoomHost(roomHost);
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomHost() {
		return roomHost;
	}

	public void setRoomHost(String roomHost) {
		this.roomHost = roomHost;
	}
}

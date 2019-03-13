package com.rvlstudio;

import java.util.UUID;

public class Tag {
	private UUID uuid;
	private String name;
	
	public Tag() {}
	
	public Tag(UUID uuid, String name) {
		this.uuid = uuid;
		this.name = name;
	}
	
	public UUID getUUID() {
		return uuid;
	}
	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

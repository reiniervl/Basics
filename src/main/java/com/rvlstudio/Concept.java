package com.rvlstudio;

import java.util.UUID;

public class Concept {
	private UUID uuid;
	private String description;
	private String examples;
	
	public Concept() {
		this.uuid = UUID.randomUUID();
	}
	
	public Concept(UUID uuid, String description, String examples) {
		this.uuid = uuid;
		this.description = description;
		this.examples = examples;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExamples() {
		return examples;
	}
	public void setExamples(String examples) {
		this.examples = examples;
	}

	@Override
	public String toString() {
		return String.format("[%s, %s, %s]", uuid.toString(), description, examples);
	}
}

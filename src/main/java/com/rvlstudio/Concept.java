package com.rvlstudio;

import java.util.UUID;
import java.util.List;

public class Concept {
	private UUID uuid;
	private String description;
	private String examples;
	private List<Tag> tags;
	
	public Concept() {}
	
	public Concept(UUID uuid, String description, String examples, List<Tag> tags) {
		this.uuid = uuid;
		this.description = description;
		this.examples = examples;
		this.tags = tags;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
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
	public List<Tag> getTags() {
		return tags;
	}
	public String getTagsCSV() {
		StringBuilder sb = new StringBuilder();
		for(Tag t : tags) sb.append(t.getName() + ",");
		if(sb.length() > 1) sb.deleteCharAt(sb.length() -1);
		return sb.toString();
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}

package com.rvlstudio;

import java.util.UUID;
import java.util.List;

public interface ConceptDAO {
	void addConcept(Concept concept);
	void deleteConcept(Concept concept);
	void updateConcept(Concept concept);
	Concept getConcept(UUID uuid);
	List<Concept> getByTags(Tag... tags);
	
	void addTag(Tag tag);
	void addTags(Tag... tag);
	void deleteTag(Tag tag);
	Tag getTag(UUID uuid);
	Tag getTag(String name);
}

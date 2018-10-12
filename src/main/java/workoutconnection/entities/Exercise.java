package workoutconnection.entities;

import workoutconnection.models.DifficultyLevel;

public class Exercise {
	private long id;
	private String Type;
	private DifficultyLevel difficultyLevel;
	private String name;
	private String description;
	
	public Exercise(long id, String Type, DifficultyLevel difficultyLevel, String name, String description) {
		this.id = id;
		this.Type = Type;
		this.difficultyLevel = difficultyLevel;
		this.name = name;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public String getType() {
		return Type;
	}

	public DifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}

	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}

}

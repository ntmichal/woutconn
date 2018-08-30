package workoutconnection.entities;

public class MealSum {
	int carbs;
	int fats;
	int kcal;
	int volume;
	int proteins;
	
	public MealSum(int carbs, int fats, int proteins) {

		this.carbs = carbs;
		this.fats = fats;
		this.proteins = proteins;
	}
	
	public int getCarbs() {
		return carbs;
	}

	public void setCarbs(int carbs) {
		this.carbs = carbs;
	}
	public int getFats() {
		return fats;
	}
	public void setFats(int fats) {
		this.fats = fats;
	}
	public int getKcal() {
		return kcal;
	}
	public void setKcal(int kcal) {
		this.kcal = kcal;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getProteins() {
		return proteins;
	}
	public void setProteins(int proteins) {
		this.proteins = proteins;
	}
	
}

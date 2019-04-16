package workoutconnection.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users_measurement")
public class Measurement {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	@Column(name="measurementDate")
	private LocalDate measurementDate;

	@Column(name="neck")
	private int neck;

	@Column(name="arm")
	private int arm;

	@Column(name="forearm")
	private int forearm;

	@Column(name="wrist")
	private int wrist;

	@Column(name="chest")
	private int chest;

	@Column(name="waist")
	private int waist;

	@Column(name="hips")
	private int hips;

	@Column(name="thigh")
	private int thigh;

	@Column(name="calf")
	private int calf;

	public LocalDate getMeasurementDate() {
		return measurementDate;
	}
	public void setMeasurementDate(LocalDate measurementDate) {
		this.measurementDate = measurementDate;
	}
	public int getNeck() {
		return neck;
	}
	public void setNeck(int neck) {
		this.neck = neck;
	}
	public int getArm() {
		return arm;
	}
	public void setArm(int arm) {
		this.arm = arm;
	}
	public int getForearm() {
		return forearm;
	}
	public void setForearm(int forearm) {
		this.forearm = forearm;
	}
	public int getWrist() {
		return wrist;
	}
	public void setWrist(int wrist) {
		this.wrist = wrist;
	}
	public int getChest() {
		return chest;
	}
	public void setChest(int chest) {
		this.chest = chest;
	}
	public int getWaist() {
		return waist;
	}
	public void setWaist(int waist) {
		this.waist = waist;
	}
	public int getHips() {
		return hips;
	}
	public void setHips(int hips) {
		this.hips = hips;
	}
	public int getThigh() {
		return thigh;
	}
	public void setThigh(int thigh) {
		this.thigh = thigh;
	}
	public int getCalf() {
		return calf;
	}
	public void setCalf(int calf) {
		this.calf = calf;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public static MeasurementBuilder builder(){
		return new MeasurementBuilder();
	}
	public static final class MeasurementBuilder{
		private int id;
		private User user;
		private LocalDate measurementDate;
		private int neck;
		private int arm;
		private int forearm;
		private int wrist;
		private int chest;
		private int waist;
		private int hips;
		private int thigh;
		private int calf;

		public MeasurementBuilder setId(int id) {
			this.id = id;
			return  this;
		}
		public MeasurementBuilder setUser(User user) {
			this.user = user;
			return  this;
		}
		public MeasurementBuilder setMeasurementDate(LocalDate measurementDate) {
			this.measurementDate = measurementDate;
			return  this;
		}
		public MeasurementBuilder setNeck(int neck) {
			this.neck = neck;
			return  this;
		}
		public MeasurementBuilder setArm(int arm) {
			this.arm = arm;
			return  this;
		}
		public MeasurementBuilder setForearm(int forearm) {
			this.forearm = forearm;
			return  this;
		}
		public MeasurementBuilder setWrist(int wrist) {
			this.wrist = wrist;
			return  this;
		}
		public MeasurementBuilder setChest(int chest) {
			this.chest = chest;
			return  this;
		}
		public MeasurementBuilder setWaist(int waist) {
			this.waist = waist;
			return  this;
		}
		public MeasurementBuilder setHips(int hips) {
			this.hips = hips;
			return  this;
		}
		public MeasurementBuilder setThigh(int thigh) {
			this.thigh = thigh;
			return  this;
		}
		public MeasurementBuilder setCalf(int calf) {
			this.calf = calf;
			return  this;
		}

		public Measurement build(){
			Measurement measurement = new Measurement();
			measurement.setId(this.id);
			measurement.setUser(this.user);
			measurement.setMeasurementDate(this.measurementDate);
			measurement.setNeck(this.neck);
			measurement.setArm(this.arm);
			measurement.setForearm(this.forearm);
			measurement.setWrist(this.wrist);
			measurement.setChest(this.chest);
			measurement.setWaist(this.waist);
			measurement.setHips(this.hips);
			measurement.setThigh(this.thigh);
			measurement.setCalf(this.calf);

			return measurement;
		}
	}
}
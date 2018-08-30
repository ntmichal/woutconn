package workoutconnection;

public class YearMonthDay {
		Integer Year;
		Integer Month;
		Integer WeekNumber;
		
		public YearMonthDay(Integer Year, Integer Month, Integer WeekNumber) {
			this.Year = Year;
			this.Month = Month;
			this.WeekNumber = WeekNumber;
		}
		public Integer getYear() {
			return Year;
		}
		public void setYear(Integer year) {
			Year = year;
		}
		public Integer getMonth() {
			return Month;
		}
		public void setMonth(Integer month) {
			Month = month;
		}
		
		public Integer getDay() {
			return WeekNumber;
		}
		public void setDay(Integer WeekNumber) {
			this.WeekNumber = WeekNumber;
		}
		@Override
		public int hashCode() {
			return Year + Month + WeekNumber;
		}
		@Override
		public String toString() {
			return "YearMonthDay [Year=" + Year + ", Month=" + Month + ", WeekNumber=" + WeekNumber + "]";
		}

		
		
		
}

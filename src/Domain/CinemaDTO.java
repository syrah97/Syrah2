package Domain;

public class CinemaDTO extends DTO{
	private int cinemaNum;
	private String cinemaName;

	public CinemaDTO() {}
	public CinemaDTO(String cinemaName) {this.cinemaName=cinemaName;}
	public CinemaDTO(int cinemaNum, String cinemaName) {this.cinemaNum = cinemaNum; this.cinemaName = cinemaName;}

	public int getCinemaNum() {return cinemaNum;}
	public void setCinemaNum(int cinemaNum) {this.cinemaNum = cinemaNum;}
	public String getCinemaName() {return cinemaName;}
	public void setCinemaName(String cinemaName) {this.cinemaName = cinemaName;}

	@Override
	public String toString() {return "\n영화관 번호: " + cinemaNum + " 영화관 이름: " + cinemaName;}

}

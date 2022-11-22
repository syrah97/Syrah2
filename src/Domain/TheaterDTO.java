package Domain;

public class TheaterDTO extends DTO {

	private int TheaterNum; // 상영관 번호
	private int TheaterSeat; // 상영관 좌석 번호
	private String TheaterName; // 상영관 이름
	private int CinemaNum; // 영화관 번호 (상속)
	private String CinemaName; // 영화관 이름 (상속)
	private String MovieName;
	
	public TheaterDTO() {}
	
	public TheaterDTO(String CinemaName, String MovieName, String TheaterName) {
		this.CinemaName = CinemaName; this.TheaterName = TheaterName; this.MovieName = MovieName;
	}

	public TheaterDTO(int theaterNum, int theaterSeat, String theaterName) {
		TheaterNum = theaterNum; TheaterSeat = theaterSeat; TheaterName = theaterName;}

	public TheaterDTO(int theaterNum, int theaterSeat, String theaterName, int CinemaNum) {
		this.TheaterNum = theaterNum; this.TheaterSeat = theaterSeat;
		this.TheaterName = theaterName; this.CinemaNum = CinemaNum;}
	
	public int getTheaterNum() {return TheaterNum;}
	public void setTheaterNum(int theaterNum) {TheaterNum = theaterNum;}
	public int getTheaterSeat() {return TheaterSeat;}
	public void setTheaterSeat(int theaterSeat) {TheaterSeat = theaterSeat;}
	public String getTheaterName() {return TheaterName;}
	public void setTheaterName(String theaterName) {TheaterName = theaterName;}
	public int getCinemaNum() {return CinemaNum;}
	public void setCinemaNum(int cinemaNum) {CinemaNum = cinemaNum;}
	public String getCinemaName() {return CinemaName;}
	public void setCinemaName(String cinemaName) {CinemaName = cinemaName;}
	public String getMovieName() {return MovieName;}
	public void setMovieName(String movieName) {MovieName = movieName;}

	@Override
	public String toString() {return TheaterNum + TheaterSeat + TheaterName + CinemaNum + CinemaName + MovieName;}
	
//	@Override
//	public String toString() {return "\n상영관 번호: " + TheaterNum + " 상영관 좌석: " + TheaterSeat + " 상영관 이름: " + TheaterName;}

//	@Override
//	public String toString() {return "\n상영관 번호: " + TheaterNum + " 상영관 좌석: " + TheaterSeat + " 상영관 이름: " + TheaterName + " 영화관 번호: " + CinemaNum;}

}

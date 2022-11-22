package Domain;

public class MovieDTO extends DTO{
	private int movieCode;
	private String movieName;
	private String movieStartDate;
	private String movieEndDate;
	private String movieTime;
	private int cinemaNum;

	public MovieDTO() {}
	public MovieDTO(int movieCode, String movieName, String movieStartDate, String movieEndDate, String movieTime, int cinemaNum) {
		this.movieCode = movieCode; this.movieName = movieName;	this.movieStartDate = movieStartDate;
		this.movieEndDate = movieEndDate;	this.movieTime = movieTime; this.cinemaNum=cinemaNum;}

	public int getMovieCode() {return movieCode;}
	public void setMovieCode(int movieCode) {this.movieCode = movieCode;}
	public String getMovieName() {return movieName;}
	public void setMovieName(String movieName) {this.movieName = movieName;}
	public String getMovieStartDate() {return movieStartDate;}
	public void setMovieStartDate(String movieStartDate) {this.movieStartDate = movieStartDate;}
	public String getMovieEndDate() {return movieEndDate;}
	public void setMovieEndDate(String movieEndDate) {this.movieEndDate = movieEndDate;}
	public String getMovieTime() {return movieTime;}
	public void setMovieTime(String movieTime) {this.movieTime = movieTime;}
	public int getCinemaNum() {return cinemaNum;}
	public void setCinemaNum(int cinemaNum) {this.cinemaNum = cinemaNum;}

	@Override
	public String toString() {return  movieCode + movieName + movieStartDate + movieEndDate + movieTime + cinemaNum;}
}

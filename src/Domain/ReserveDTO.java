package Domain;

public class ReserveDTO {
	private int ticketNum;
	private String ticketDate;
	private String mId;
	private int movieCode;
	private int theaterNum;
	private int cinemaNum;
	private int theaterSeat;
	private String movieName;

	public ReserveDTO() {}
	
	public ReserveDTO(String mId, String ticketDate, int tickectNum, String movieName, int theaterNum, int theaterSeat)
	{	this.mId = mId; this.ticketDate = ticketDate; this.ticketNum = tickectNum;
		this.movieName = movieName;	this.theaterNum = theaterNum;}

	public ReserveDTO(int ticketNum, String ticketDate, String mId, int movieCode, int theaterNum, int cinemaNum, int theaterSeat)
	{	this.ticketNum = ticketNum; this.ticketDate = ticketDate; this.mId = mId; this.movieCode = movieCode;
		this.theaterNum = theaterNum; this.cinemaNum = cinemaNum; this.theaterSeat = theaterSeat;}

	public int getTicketNum() {return ticketNum;}
	public void setTicketNum(int ticketNum) {this.ticketNum = ticketNum;}
	public String getTicketDate() {return ticketDate;}
	public void setTicketDate(String ticketDate) {this.ticketDate = ticketDate;}
	public String getmId() {return mId;}
	public void setmId(String mId) {this.mId = mId;}
	public int getMovieCode() {return movieCode;}
	public void setMovieCode(int movieCode) {this.movieCode = movieCode;}
	public int getTheaterNum() {return theaterNum;}
	public void setTheaterNum(int theaterNum) {this.theaterNum = theaterNum;}
	public int getCinemaNum() {return cinemaNum;}
	public void setCinemaNum(int cinemaNum) {this.cinemaNum = cinemaNum;}
	public int getTheaterSeat() {return theaterSeat;}
	public void setTheaterSeat(int theaterSeat) {this.theaterSeat = theaterSeat;}
	public String getMovieName() {return movieName;}
	public void setMovieName(String movieName) {this.movieName = movieName;}

	@Override
	public String toString() {return ticketNum + ticketDate + mId + movieCode + theaterNum + cinemaNum + theaterSeat;}}

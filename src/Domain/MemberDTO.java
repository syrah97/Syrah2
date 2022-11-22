package Domain;

public class MemberDTO extends DTO{
	private String mId;
	private String mPw;
	private String mEmail;
	private int mBirth;
	private int mRole;

	public MemberDTO() {}
	public MemberDTO(String mId, String mPw, String mEmail, int mBirth, int mRole) {
		this.mId = mId;	this.mPw = mPw;	this.mEmail = mEmail; this.mBirth = mBirth;	this.mRole = mRole;}

	public String getmId() {return mId;}
	public void setmId(String mId) {this.mId = mId;}
	public String getmPw() {return mPw;}
	public void setmPw(String mPw) {this.mPw = mPw;}
	public String getmEmail() {return mEmail;}
	public void setmEmail(String mEmail) {this.mEmail = mEmail;}
	public int getmBirth() {return mBirth;}
	public void setmBirth(int mBirth) {this.mBirth = mBirth;}
	public int getmRole() {return mRole;}
	public void setmRole(int mRole) {this.mRole = mRole;}

	@Override
	public String toString() {
		return "\n회원 ID: " + mId + " 회원 PW: " + mPw + " 회원 이메일 주소: " + mEmail + " 회원 생일: " + mBirth + " 회원 권한: "	+ mRole;}
}

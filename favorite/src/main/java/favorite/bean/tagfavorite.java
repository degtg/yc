package favorite.bean;

public class tagfavorite implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer fid;
	private Integer tid;
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	@Override
	public String toString() {
		return "tagfavorite [fid=" + fid + ", tid=" + tid + "]";
	}
	
	
	
}

package donnees;

abstract public class Evenement implements Comparable<Evenement> {
	private long date;

	public Evenement(long newEventDate){
		this.date = newEventDate;
	}

	public long getDate(){
		return this.date;
	}

	public int compareTo(Evenement e){
		return (int) (this.date - e.getDate());	// ATTENTION : date est un long
// 		if (this.date > e.getDate()) {
// 			return 1;
// 		}
// 		else if (this.date == e.getDate()) {
// 			return 0;
// 		}
// 		else {
// 			return -1;
// 		}
	}

	abstract public void execute();
}
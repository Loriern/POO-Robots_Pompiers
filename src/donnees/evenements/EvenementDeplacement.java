package donnees.evenements;
import donnees.*;

public class EvenementDeplacement extends Evenement {
	private Robot robot;
	private Direction direction;

	public EvenementDeplacement(long date, Robot robotADeplacer, Direction dir){
		super(date);
		this.robot = robotADeplacer;
		this.direction = dir;
	}

	public void execute(){
		this.robot.deplacer(direction);
	}
}
public class ManagerChef extends Manager{
    Robot[] pompiers;
    private LinkedList<Incendie> listeIncendies;
    private PriorityQueue<Evenement> events;
    
    public ManagerChef(Simulateur simToConnect) {
        super(simToConnect);
        pompiers = simToConnect.getDonneesSimulation().getRobots();
        listeIncendies = simToConnect.getDonneesSimulation().getIncendies();
        
    }

    public void manageSimple() {

    }
}

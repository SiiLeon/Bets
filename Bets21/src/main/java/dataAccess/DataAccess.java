package dataAccess;

import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.*;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccess()  {	
		 this(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month+1,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month+1,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month+1,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month+1,28));
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			Pronostico p1=new Pronostico("0-1",q4);
			Pronostico p2=new Pronostico("0-2",q4);
			Pronostico p3=new Pronostico("0-3",q4);
			Pronostico p4=new Pronostico("0-4",q4);
			
			q4.addPronostico(p1);
			q4.addPronostico(p2);
			q4.addPronostico(p3);
			q4.addPronostico(p4);
			
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6); 
	
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);		
			
			Usuario admin= new Usuario("Alfredo","12345",null,true);
			Usuario user= new Usuario("User1","12345",null,false);
			Usuario admi1= new Usuario("Silvia","contrase�a",null,true);
			Usuario admi2= new Usuario("Yubo","12345",null,true);
			Usuario admi3= new Usuario("Carlos","12345",null,true);
			Usuario admi4= new Usuario("Jaime","12345",null,true);
			db.persist(admin);
			db.persist(user);
			db.persist(admi1);
			db.persist(admi2);
			db.persist(admi3);
			db.persist(admi4);
			db.persist(p1);
			db.persist(p2);
			db.persist(p3);
			db.persist(p4);
			db.getTransaction().commit();
			System.out.println("Db initialized");
			

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	
	
	public void open(boolean initializeMode){
			
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());
	
		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
			
		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			 db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());
			emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);	
		    db = emf.createEntityManager();
	    }
			
	}
	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);
		
	}
	public void close(){
			db.close();
			System.out.println("DataBase closed");
	}
		
	public boolean createUser(String userName, String pass,String cCode){
		Usuario u= db.find(Usuario.class,userName);
		if(u!=null) return false;
		u = new Usuario(userName,pass,cCode,false);
		db.getTransaction().begin();
		db.persist(u);
		db.getTransaction().commit();
		System.out.println(">> DataAccess: createUser=> User: "+ userName +" registered");
		return true;
	}
	public Usuario login(String uName, String pass) {
		Usuario u = db.find(Usuario.class,uName);
		if(u==null)return null;
		if(!u.getPassword().equals(pass))return null;
		return u;
	}	
	public Event createEvent(String inputDescription, Date firstDay) {
		
		System.out.println(">> DataAccess: createEvent=> description= "+inputDescription+" date="+firstDay.toString());
		TypedQuery<Event>  query = db.createQuery("SELECT e FROM Event e WHERE e.eventDate=?1",Event.class);
		query.setParameter(1, firstDay);
		List<Event> eventos = query.getResultList();
		if(eventos!=null) 
			for(Event e: eventos)if(e.getDescription().equals(inputDescription))return null;
		
		db.getTransaction().begin();
		Event ev=new Event(inputDescription,firstDay);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return ev;
		
	}

	public List<Pronostico> findPronosticos(Question q) {
		
		TypedQuery<Pronostico>  query = db.createQuery("SELECT FROM Pronostico q WHERE q.getQuestion()=?1",Pronostico.class);
		query.setParameter(1, q);
		List<Pronostico> pronosticos = query.getResultList();
		
		return pronosticos;
}


	public Question createPronostic(String description, Event event,int i) {
		Question q = event.getQuest(i);
		List<Pronostico> list=findPronosticos(q);
		for(Pronostico p1:list) {
			if(p1.getPronostico().equals(description))return q=null;
		}
		
			db.getTransaction().begin();
			Pronostico p = new Pronostico(description, q);
			q.addPronostico(p);
			db.persist(p); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
	
	
	}
	
	/**
	 * Calcula el procentaje de cada pronostico en una apuesta y la asigna a cada una
	 * @param La apuesta en cuestion
	 */
	public void calcularPorcentajePronostico(Question q) {
		Vector<Pronostico> pronos = q.getListPronosticos();
		ArrayList<Integer> participantes = this.calcularListaPersonasQuestion(q);
		int totalPar= this.calcularSumaLista(participantes);
		if(participantes.size()>0) {
			float div;
			for(int i=0; i<participantes.size(); i++){
				div = ((float)participantes.get(i)/totalPar)*100;
				db.getTransaction().begin();
				pronos.get(i).setCuota(div);
				db.getTransaction().commit();
			}
		}
		
		return;
		
	}
	
	/**
	 * Calcula la lista de cantidad de apostadores que hay en cada apuesta.
	 * @param La apuesta en cuestion.
	 * @return Una lista con sus numeros de participantes.
	 */
	public ArrayList<Integer> calcularListaPersonasQuestion(Question q) {
		Vector<Pronostico> pronos = q.getListPronosticos();
		ArrayList<Integer> participantes = new ArrayList<>();
		if(pronos.size()>0) {
			for(Pronostico p: pronos) {
				participantes.add(p.getNumbApostados());
			}
			return participantes;
		}else {
			return null;
		}
	}
	
	/**
	 * Calcula la suma de una lista de enteros.
	 * @param Lista 
	 * @return Sumatorio de todos los numero de las lista.
	 */
	public int calcularSumaLista(ArrayList<Integer> listaE) {
		int total = 0;
		if(listaE.size()>0) {
			for(Integer num: listaE) {
				total= total+num;
			}
		}else {
			return total;
		}
		return total;
	}




}

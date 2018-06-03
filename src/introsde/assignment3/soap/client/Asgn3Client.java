package introsde.assignment3.soap.client;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import introsde.assignment3.soap.ws.*;

public class Asgn3Client {
	
	static{
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        System.setProperty("current.date.time", dateFormat.format(new Date()));
    }
	
	
	public static void main(String[] args) {
		//BasicConfigurator.configure();
		Logger logger = LoggerFactory.getLogger(Asgn3Client.class);
		
		PeopleActivityImplService serviceImp = new PeopleActivityImplService();
		PeopleActivity serviceInt = serviceImp.getPeopleActivityImplPort();
		Long personId,activityId;
		
		logger.info(serviceImp.getWSDLDocumentLocation().toString());

	    //Method #1: readPersonList() => List<Person>
		logger.info("\n\n/////Method #1: readPersonList() => List<Person>");
		for(Person p: serviceInt.readPeopleList()) {
			logger.info(printPerson(p));
		}
		
		//Method #2: readPerson(1) => Person
		logger.info("\n\n/////Method #2: readPerson(Long id) => Person | id=1 as parameter");
		personId=(long) 1;
		logger.info(printPerson(serviceInt.readPerson(personId)));
		
		//Method #3: updatePerson(Person p) => Person 
		logger.info("\n\n/////Method #3: updatePerson(Person p) => Person ");
		logger.info("\nParameter passed ->	LastName=\"michal12\",	FirstName=\"george12\"");
		logger.info("\nUpdated Person ->");
		personId=(long)1;
		Holder<Person> person= new Holder<>();
		person.value = serviceInt.readPerson(personId);
		person.value.setLastname("michal12");
		person.value.setName("george12");
		
		serviceInt.updatePerson(person);
		logger.info(printPerson(serviceInt.readPerson((long)person.value.getPersonId())));
		
		//Method #4: createPerson(Person p) => Person
		logger.info("\n\n/////Method #4: createPerson(newPersonObject) => Person");
		logger.info("\n///Parameter passed for Person ->	LastName=\"michal6\",	FirstName=\"george6\"");
		logger.info("\n///Parameter passed for related Activity ->	Name=\"Meeting6\",	Place=\"Povo\"");
		logger.info("\n///newely generated Person with related Activity");
		person= new Holder<>();
		person.value = new ObjectFactory().createPerson();
		person.value.setLastname("michal6");
		person.value.setName("george6");
		person.value.setBirthdate("1980-06-20");
		Holder<Activity> activity = new Holder<>();
		activity.value= new ObjectFactory().createActivity();
		activity.value.setName("Meeting6");
		activity.value.setPlace("Povo");
		activity.value.setType(ActivityType.WORK_MEETING);
		person.value.getActivities().add(activity.value);
		
		serviceInt.addPerson(person);
		logger.info(printPerson(serviceInt.readPerson((long)person.value.getPersonId())));
		
		//Method #5: deletePerson(5) 
		int deletePersonID= 5;
		if(serviceInt.deletePerson((long)deletePersonID) == -1){
		logger.info("\n\n/////Method #5: deletePerson("+ deletePersonID +") | deleted person by ID unsuccessful, no person with same ID");
		}
		else {
			logger.info("\n\n/////Method #5: deletePerson("+ deletePersonID +") | deleted person by ID: "+deletePersonID);
		}
		
		//Method #6: readPersonPreferences(1, "Meeting") => List<Preference>
		logger.info("\n\n/////Method #6: readPersonPreferences(PersonId(1), activity_type(\"SOCIAL\")) => List<Preference>");
		personId=(long)1;
		for(Activity a: serviceInt.readPersonActivityByType(personId, ActivityType.WORK_MEETING)) {
			logger.info(printActivity(a));
		}
		
		//Method #7: readPreferences() => List<Preferences>
		logger.info("\n\n/////Method #7: readPreferences() => List<Preferences>");
		for(Activity a: serviceInt.readAllActivity()) {
			logger.info(printActivity(a));
		}
		
		//Method #8: readPersonPreferences(Long id, Long activity_id) => Preference
		logger.info("\n\n/////Method #8: readPersonPreferences(PersonId(1), activity_id(1)) => Preference");
		personId=(long)1;
		activityId=(long)1;
		logger.info(printActivity(serviceInt.readPersonActivityByID(personId, activityId)));
		
		
		//Method #9: savePersonPreferences(Long id, Activity activity)
		logger.info("\n\n/////Method #9: savePersonPreferences(PersonId(1), newActivityObject)");
		personId=(long)1;
		Holder<Activity> activity2 = new Holder<>();
		activity2.value= new ObjectFactory().createActivity();
		activity2.value.setName("Meeting6");
		activity2.value.setPlace("Povo");
		activity2.value.setType(ActivityType.WORK_MEETING);
		serviceInt.savePersonActivity(personId, activity2.value);
		logger.info(printPerson(serviceInt.readPerson(personId)));
		
		//Method #10: updatePersonPreferences(Long id, Activity activity) => Preference
		personId =(long)1;
		activityId= (long)1;
		logger.info("\n\n////Method #10: updatePersonPreferences(PersonId(1), modifiedActivityObject) => Preference");
		Holder<Activity> activity3 = new Holder<>();
		activity3.value=serviceInt.readPersonActivityByID(personId, activityId);
		activity3.value.setName("Meeting2");
		activity3.value.setPlace("Povo");
		activity3.value.setType(ActivityType.SOCIAL);
		serviceInt.updatePersonActivity(personId, activity3);
		logger.info(printActivity(serviceInt.readPersonActivityByID(personId, (long)activity3.value.getIdActivity())));
		
		//Method #11 (Extra): evaluatePersonPreferences(Long id, Activity activity, int value) => Preference
		logger.info("\n\n////Method #11 (Extra): evaluatePersonPreferences(PersonId(1), ActivityObject, evaluationValue(9)) => Preference");
		personId=(long)1;
		activityId =(long)1;
		int evValue= 9;
		Holder<Activity> activity4 = new Holder<>();
		activity4.value=serviceInt.readPersonActivityByID(personId, activityId);
		serviceInt.evaluatePersonActivity(personId, activity4, evValue);
		logger.info(printActivity(serviceInt.readPersonActivityByID(personId, activityId)));
		
		//Method #12 (Extra): getBestPersonPreference(PersonId(1)) => List<Preference>
		logger.info("\n\n////Method #12 (Extra): getBestPersonPreference(PersonId(1) => List<Preference>");
		personId=(long)1;
		for(Activity act:serviceInt.bestPersonActivity(personId)) {
			logger.info(printActivity(act));
		}
	}
	
	private static String printActivity(Activity act) {
		StringBuffer s= new StringBuffer();
		s.append("\n	Activity -> ID: "+ act.getIdActivity());
		s.append("\n		Name: "+ act.getName());
		s.append("\n		Description: "+ act.getDescription());
		s.append("\n		Type: "+ act.getType());
		s.append("\n		Place: "+ act.getPlace());
		s.append("\n		StartDate: "+ act.getStartdate());
		s.append("\n		Evaluatione: "+ act.getEvaluatione());
		return s.toString();
	}

	private static String printPerson(Person p) {
		StringBuffer s= new StringBuffer();
		
		s.append("\nPerson -> ID: "+p.getPersonId());
		s.append("\n	Name: "+ p.getLastname()+" "+p.getName());
		s.append("\n	DOB: "+ p.getBirthdate());			
		for(Activity act: p.getActivities()) {
			s.append(printActivity(act));
		}
		return s.toString();
	}

}

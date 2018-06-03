# introsde-2017-assignment-3-client

Name : Main muhammad faheem - Jan  
Email : main.jan@unitn.it  
Client Git riop: https://github.com/faheemjan5000/introsde-2017-assignment-3-client  

Server Code done by  
Name : Danish Asghar - Cheema  
Email : danishasghar.cheema@unitn.it 
Server heroku ULR: http://introsde-asgn3-server.herokuapp.com/ws/people?wsdl       
Server Git ripo: https://github.com/danishc/introsde-2017-assignment-3-server    

  

### About the Code:

I created one Client Class named as "Asgn3Client" which has  different methods and main method defined. like reading person, updating person,deleting person, printing preferences etc etc. there is one log file which has all the ouput saved.there is the Build file which has all the targets written and ivy.xml file which has different dependencies needed for my project.    

### Tasks the Code do :  

Method #0: Print server WSDL url (one generated for you by Heroku + e.g. /ws/people?wsdl).  
`Method #1: readPersonList() => List<Person>` //prints all the persons in the list with their activities.    

`Method #2: readPerson(Long id) => Person | id=1 as parameter` //takes id of a person as a parameter and prints the person  
 with all its activities  
`Method #3: updatePerson(Person p) => Person` //takes person object as parameter and update it.  
`Method #4: createPerson(newPersonObject) => Person`  //creates new person with specific information about the person.   
`Method #5: deletePerson(5) | deleted person by ID unsuccessful, no person with same ID` //deletes person with its id given in parameter. in our case the person with id=5 is not exists so the delete is unsuccessful.    
`Method #6: readPersonPreferences(PersonId(1), activity_type("SOCIAL")) => List<Preference>` //prints all the activities of the person with the id  and activity_type given.    
`Method #7: readPreferences() => List<Preferences>` //prints all the Activity preferences in the database.        
`Method #8: readPersonPreferences(PersonId(1), activity_id(1)) => Preference` //prints the person's preference given person id and activiy id.      
`Method #9: savePersonPreferences(PersonId(1), newActivityObject)` //saves the preference for the person with id given.      
`Method #10: updatePersonPreferences(PersonId(1), modifiedActivityObject) => Preference` //updates the preference of the person with the id sent in parameter.        
`Method #11: (Extra): evaluatePersonPreferences(PersonId(1), ActivityObject, evaluationValue(9)) => Preference` //it updates the activity identified with its id, related to the Person identified by {id} with the value that define a specific value of preferences    
`Method #12 (Extra): getBestPersonPreference(PersonId(1) => List<Preference>` //it returns the best preference (or preferences if there   are more) of the Person identified by {id}  from his/her list of preferences.    
                  

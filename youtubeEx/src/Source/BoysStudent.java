package Source;

public class BoysStudent extends CollegeStudent{
    String boyStudentName;
    String boyStudentLocation;

    BoysStudent(String collegeLocation, String collegeName, String name, String studentLocation,String boyStudentName
    ,String boyStudentLocation) {
        super (collegeLocation, collegeName, name, studentLocation);
        this.boyStudentLocation = boyStudentLocation;
    }
    BoysStudent(){

    }
}

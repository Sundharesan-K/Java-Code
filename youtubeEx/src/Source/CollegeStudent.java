package Source;

public class CollegeStudent extends College{
    String name;
    String studentLocation;

    CollegeStudent(String collegeLocation,String collegeName,String name, String studentLocation){
        super(collegeName, collegeLocation);
        this.name = name;
        this.studentLocation = studentLocation;
    }
    CollegeStudent(){

    }
}

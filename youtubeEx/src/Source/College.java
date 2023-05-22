package Source;

public class College {
    String collegeName;
    String collegeLocation;

    College(){
        this.collegeName = "sundhar";
        this.collegeLocation = "Coimbatore";
    }

    College(String collegeName,String collegeLocation){
        this.collegeName = collegeName;
        this.collegeLocation = collegeLocation;
    }

    College(College old){
        this.collegeName = old.collegeName;
        this.collegeLocation = old.collegeLocation;
    }
}

package Source;

public class main {
    public static void main(String[] args) {
        College college = new College ();
        System.out.println (college.collegeName+" "+college.collegeLocation);
        CollegeStudent college1 = new CollegeStudent ("ss","aaa","fff","eee");
        System.out.println (college1.name);

        BoysStudent boysStudent = new BoysStudent ();
    }
}

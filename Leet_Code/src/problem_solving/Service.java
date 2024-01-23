package problem_solving;

import java.util.HashMap;
import java.util.Map;

public class Service{
    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<>();
        map.put(0,"Sundharesan");
        map.put(1,"Sundhar");
        map.put(2,"Sun");
        map.put(3,"Surya");
        map.put(4,"Suguna");
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i) == "Sun"){
                System.out.println(i);
            }
        }
        System.out.println(map.size());

    }

}

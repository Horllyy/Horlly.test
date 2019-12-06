package JSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.processors.JsonVerifier;
import org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JsonToJavaObject {
//    class person{
//        private String name;
//        private int age;
//        private String gender;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public int getAge() {
//            return age;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }
//
//        public String getGender() {
//            return gender;
//        }
//
//        public void setGender(String gender) {
//            this.gender = gender;
//        }
//
//        @Override
//        public String toString() {
//            return "person{" +
//                    "name='" + name + '\'' +
//                    ", age=" + age +
//                    ", gender='" + gender + '\'' +
//                    '}';
//        }
//    }
//
//    public void method(){
//        person p=new person();
//        p.setName("Sara");
//        p.setAge(17);
//        p.setGender("女");
//
//        ObjectMapper mapper=new ObjectMapper();
//        String json=p.toString();
//        System.out.println(json);
//        try {
//            person per=mapper.readValue(json,person.class);
//            System.out.println(per);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void main(String[] args) {
//        JsonToJavaObject jtjo=new JsonToJavaObject();
//        jtjo.method();
//    }


//   class PersonList {
//    ArrayList<person> person;
//
//    public ArrayList<person> getPerson() {
//        return person;
//    }
//    public void setStudent(ArrayList<person> person) {
//        this.person = person;
//    }
//}

    ArrayList<person> person;

    public ArrayList<person> getPerson() {
        return person;
    }
    public void setStudent(ArrayList<person> person) {
        this.person = person;
    }

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try {
//            person per=mapper.readValue(new File("D:\\trial\\personFile.txt"), person.class);//person对象

            person perlist=mapper.readValue(new File("D:\\trial\\listPersonFile.txt"), person.class);//person数组
            System.out.println( perlist);//妈呀 json数组怎么转化成java数组啊 哭了 连文件输出string都不行啊 这次真的哭了

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("okay");
    }

}

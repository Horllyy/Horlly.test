package JSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class person{
    private String name;
    private int age;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }

}


public class javaObjectToJson {
    public void ObjectMethod(){
        person p=new person();
        p.setName("Tony Stark");
        p.setAge(43);
        p.setGender("男");

        ObjectMapper mapper=new ObjectMapper();
        try {
            String json=mapper.writeValueAsString(p);
            System.out.println(json);

            //关联到file
            mapper.writeValue(new File("D:\\trial\\personFile.txt"),p);

            //关联到filewriter
            mapper.writeValue(new FileWriter("D:\\trial\\personFileWriter.txt") ,p);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ListMethod(){
        person p1=new person();
        p1.setName("Scarllet");
        p1.setAge(34);
        p1.setGender("女");

        person p2=new person();
        p2.setName("Mary");
        p2.setAge(24);
        p2.setGender("女");

        person p3=new person();
        p3.setName("Betty Chen");
        p3.setAge(32);
        p3.setGender("女");

        List<person> personList=new ArrayList<>();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);

        ObjectMapper mapper=new ObjectMapper();
        try {
            String json=mapper.writeValueAsString(personList);
            System.out.println(json);
            mapper.writeValue(new File("D:\\trial\\listPersonFile.txt"),personList);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void MapMethod(){
        person p4=new person();
        p4.setName("Scarllet");
        p4.setAge(34);
        p4.setGender("女");

        person p5=new person();
        p5.setName("Mary");
        p5.setAge(24);
        p5.setGender("女");

        person p6=new person();
        p6.setName("Betty Chen");
        p6.setAge(32);
        p6.setGender("女");

        Map<String,person> map=new HashMap<>();
        map.put("No1",p4);
        map.put("No2",p5);
        map.put("No3",p6);

        ObjectMapper mapper=new ObjectMapper();
        try {
            String json=mapper.writeValueAsString(map);
            System.out.println(json);
            mapper.writeValue(new File("D:\\trial\\mapPersonFile.txt"),map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        javaObjectToJson jotj=new javaObjectToJson();
//        jotj.ObjectMethod();
//        jotj.ListMethod();
          jotj.MapMethod();
    }
}


package horlly.test.two;//package horlly.test.two;
//
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.Objects;
//
//public class Employee {
//
//    private String name;
//    private double salary;
//    private Date hireDay;
//
//    public Employee(String n,double s,int year,int month,int day){
//        name = n;
//        salary = s;
//        GregorianCalendar calendar = new GregorianCalendar(year,month-1,day);
//        hireDay = calendar.getTime();
//    }
//
//
//    public String getName(){
//        return name;
//    }
//    public double getSalary(){
//        return salary;
//    }
//    public Date getHireDay(){
//        return hireDay;
//    }
//
//    public void raiseSalary(double byPercent){
//        double raise = salary * byPercent / 100;
//        salary += raise;
//    }
//    public boolean equals(Object otherObject){
//        //这里获得一个对象参数，第一个if语句判断两个引用是否是同一个，如果是那么这两个对象肯定相等
//        if(this == otherObject)
//            return true;
////这里判断这个参数是否引用空值
//        if(otherObject == null)
//            return false;
////getClass()方法是得到对象的类，这里就是如果两个对象的类不一样，那么就不相等
//        if(getClass()!=otherObject.getClass())
//            return false;
////在以上判断完成，再将得到的参数对象强制转换为该对象，考虑到父类引用子类的对象的出现，然后再判断对象的属性是否相同
//        Employee other = (Employee)otherObject;
//
//        return Objects.equals(name, other.name)&&salary ==other.salary&&
//                Objects.equals(hireDay, other.hireDay);
//    }
//    //哈希散列，有自己的计算方法，根据字符串来得到一段数字
//    public int hashCode(){
//        return Objects.hash(name,salary,hireDay);
//    }
//
//    //toString()方法，可自动生成
//    @Override
//    public String toString() {
//        return getClass().getName()+ "[name=" + name + ", salary=" + salary + ", hireDay=" + hireDay + "]";
//    }
//
//
//}

class Employee {
    public String name;

    public Employee(String name) {
        this.name = name;
    }

    public String toString() {
        return "[name=" + name + "]";
    }
}
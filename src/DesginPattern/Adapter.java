package DesginPattern;



import java.util.*;
import java.util.Collection;
import java.util.Iterator;

public class Adapter {
    public static void main(String[] args) {
       // build pattern
       List<Object> list = new ArrayList<>();
       list.add("111");
       list.add(true);
       list.add("Alex");
       list.add("phd");
       AdaptorFactory factory = new AdaptorFactory();
       Icomponent c = factory.product(Type.Citizen, list);
        Citizen citizen = null;
        Collection<Citizen> citizens = new ArrayList<>();
        //比较安全的对implmentation类型转成 class
       if (c instanceof Citizen) {
           citizen = (Citizen)c;
           citizens.add(citizen);
       }


       List<Object> l = new ArrayList<>();
       l.add("222");
       l.add(false);
       l.add("Wang");
       l.add("null");
       //l.add(new String[]{"sql","c","aws"});
       //(linkedInMember)比较冒险，可能类型不一致,complier会报错
       LinkedInMember linkedInMember = (LinkedInMember) factory.product(Type.LinkedInMember, list);
       Collection<LinkedInMember> linkedInMembers = new ArrayList<>();
       linkedInMembers.add(linkedInMember);
       EmployeeAdapter employeeAdapter = EmployeeAdapter.getInstance();
       //要先在变化发生之前注册observer
        Iobserver ob = new Observerimpl();
        employeeAdapter.registerOb(ob);
       EmployeeManagementSystem e = new EmployeeManagementSystem(employeeAdapter);
       e.addCitizen(citizens);
       e.addLinkedinMembers(linkedInMembers);
        for (Iterator<Employee> it = e.someAPIAccessEmployees(); it.hasNext(); ) {
            Employee employee = it.next();
            System.out.println("employee:" + employee);
        }

    }
}


// TODO: Figure out factory pattern for citizen, linkedinmember and employee.  Java8 feature: variable length array.
interface  Icomponent{

}
class Citizen implements Icomponent{
    private final String ssn;
    private boolean gender;
    private String name;
    private String degree;

    public Citizen(CitizenBuilder citizenBuilder) {
        this.ssn = citizenBuilder.ssn;
        this.gender = citizenBuilder.gender;
        this.name = citizenBuilder.name;
        this.degree = citizenBuilder.degree;
    }
    public static class CitizenBuilder {
        String ssn;
        boolean gender;
        String name;
        String degree;

        public void setSsn(String ssn) {
            this.ssn = ssn;
        }

        public void setGender(boolean gender) {
            this.gender = gender;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }
    }

    public String getSsn() {
        return ssn;
    }

    public boolean isGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getDegree() {
        return degree;
    }
}

class LinkedInMember implements Icomponent {
    private final String lnkdId;
    private boolean isVIP;
    private final String name;
    private  String employer;
    private String[] skills = new String[1];

    public LinkedInMember(LinkedInMemberBuilder linkedInMemberBuilder) {
        this.lnkdId = linkedInMemberBuilder.lnkdId;
        this.isVIP = linkedInMemberBuilder.isVIP;
        this.name = linkedInMemberBuilder.name;
        this.employer = linkedInMemberBuilder.employer;
        this.skills = linkedInMemberBuilder.skills;
    }

    public static class LinkedInMemberBuilder{
        String lnkdId;
        boolean isVIP;
        String name;
        String employer;
        String[] skills;

        public void setLnkdId(String lnkdId) {
            this.lnkdId = lnkdId;
        }

        public void setVIP(boolean VIP) {
            isVIP = VIP;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmployer(String employer) {
            this.employer = employer;
        }

        public void setSkills(String[] skills) {
            this.skills = skills;
        }
    }

    public String getLnkdId() {
        return lnkdId;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public String getName() {
        return name;
    }

    public String getEmployer() {
        return employer;
    }

    public String[] getSkills() {
        return skills;
    }
}
interface Iproductable{
    Icomponent product(Type type, List<Object> list );
}
enum Type{
    Employee,
    Citizen,
    LinkedInMember;


}

class AdaptorFactory implements Iproductable{
   //private Icomponent citizen = new Citizen();
   //Icomponent.classType

    @Override
    public Icomponent product(Type type, List<Object> list) {
        if(type == Type.Employee) {
            Employee.EmployeeBuilder employeeBuilder = new Employee.EmployeeBuilder();
                employeeBuilder.setEmployeeId((int)(list.get(0)));
                employeeBuilder.setGender((boolean)(list.get(1)));
                employeeBuilder.setName((String)(list.get(2)));
                employeeBuilder.setTitle((String)(list.get(3)));
                employeeBuilder.setSalary((float)(list.get(4)));
                String[] skills = new String[]{(String)(list.get(5))};
                employeeBuilder.setSkills(skills);
                Icomponent employee = new Employee(employeeBuilder);
                return employee;
        }else if(type == Type.LinkedInMember){
            LinkedInMember.LinkedInMemberBuilder linkedInMemberBuilder = new LinkedInMember.LinkedInMemberBuilder();
            linkedInMemberBuilder.setLnkdId((String)(list.get(0)));
            linkedInMemberBuilder.setVIP((boolean)(list.get(1)));
            linkedInMemberBuilder.setName((String)(list.get(2)));
            linkedInMemberBuilder.setEmployer((String)(list.get(3)));
            //String[] skills = new String[]{(String)(list.get(4))};
            //linkedInMemberBuilder.setSkills(skills);
            Icomponent linkedInMember = new LinkedInMember(linkedInMemberBuilder);
            return linkedInMember;
        }
        Citizen.CitizenBuilder citizenBuilder = new Citizen.CitizenBuilder();
        citizenBuilder.setSsn((String)(list.get(0)));
        citizenBuilder.setGender((boolean)(list.get(1)));
        citizenBuilder.setName((String)(list.get(2)));
        citizenBuilder.setDegree((String)(list.get(3)));
        Icomponent citizen = new Citizen(citizenBuilder);
        return citizen;
    }



}

// TODO: Use builder pattern for employee.
class Employee implements Icomponent {
    private final int employeeId;
    private final boolean gender;
    private final String name;
    private  String title;
    private String[] skills;
    private float salary;


    public Employee(EmployeeBuilder employee) {
        this.employeeId = employee.employeeId;
        this.gender = employee.gender;
        this.name = employee.name;
        this.title = employee.title;
        this.skills = employee.skills;
        this.salary = employee.salary;
    }
    public static class EmployeeBuilder {
        public int setEmployeeId;
        //private final int employeeId;
        //why not final
        private int employeeId;
        private  boolean gender;
        private  String name;
        private  String title;
        private String[] skills;
        private float salary;

        public EmployeeBuilder setEmployeeId(int employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public EmployeeBuilder setGender(boolean gender) {
            this.gender = gender;
            return this;
        }

        public EmployeeBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public EmployeeBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public EmployeeBuilder setSkills(String[] skills) {
            this.skills = skills;
            return this;
        }

        public EmployeeBuilder setSalary(float salary) {
            this.salary = salary;
            return this;
        }

        public String[] getSkills() {
            return skills;
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", gender=" + gender +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", skills=" + Arrays.toString(skills) +
                ", salary=" + salary +
                '}';
    }
}

class EmployeeManagementSystem {
    private Iterator<Employee> employeeList;
    private IAdapter iAdapter;

     // List<Employee> can never be achieved from external!
    // anti-case, not applicable any more.
//    public EmployeeManagementSystem(List<Employee> list) {
//        employeeList = list;
//    }

    public EmployeeManagementSystem(IAdapter iAdapter) {
        this. iAdapter = iAdapter;
    }

    public void addLinkedinMembers(Collection<LinkedInMember> members) {
        iAdapter.consumeL(members);
    }

    public void addCitizen(Collection<Citizen> citizens) {
        iAdapter.consumeC(citizens);
    }

    public java.util.Iterator<Employee> someAPIAccessEmployees() {
        employeeList = iAdapter.getData();
        return employeeList;
    }


}

interface IAdapter {
    Iterator<Employee> getData();

    void consumeC(Collection<Citizen> c);

    void consumeL(Collection<LinkedInMember> c);
}
interface Iobserver {
    void onChange(Collection<Employee> employees);
}
class Observerimpl implements Iobserver{

    // private UI otherUIComponents.
    @Override
    public void onChange(Collection<Employee> employees) {
        // Invalidate existing UI.
        System.out.println("employees changes happend" + employees);
//        for (Employee e : employees) {
//            System.out.println("e:" + e);
//        }
    }
}
// TODO: Use singleton pattern.
// TODO: Use observer pattern so that whenever consumeC() or consumeL() is called, notify registered observer.
class EmployeeAdapter implements IAdapter {

    private Iobserver obInstance;
    private Collection<Employee> employees = new HashSet<>();
    private static final EmployeeAdapter employeeAdapter = new EmployeeAdapter();
    private int id = 0;
    public static synchronized EmployeeAdapter getInstance() {
        return employeeAdapter;
    }

    //注册observer的方法
    public void registerOb(Iobserver obInstance) {
        this.obInstance = obInstance;
    }

    //必须要把默认构造函数override 成private
    private EmployeeAdapter() {}

    // TODO: return iterator of the collection, rather than the collection itself.
    @Override
    public Iterator<Employee> getData() {
        java.util.Iterator<Employee> ite = employees.iterator();
        //Employee e = ite.next();
        return ite;
        //return employees;
    }

    @Override
    public void consumeC(Collection<Citizen> citizens) {
        for (Citizen c : citizens) {
            Employee.EmployeeBuilder e = new Employee.EmployeeBuilder();
            e.setEmployeeId(id++);
            e.setGender(c.isGender());
            e.setName(c.getName());
            if ("bachelor".equals(c.getDegree())) {
                e.setTitle("junior");
                e.setSalary(100000);
            } else if ("master".equals(c.getDegree()) || "phd".equals(c.getDegree())) {
                e.setTitle("senior");
                e.setSalary(130000);
            } else {
                e.setTitle("contractor");
                e.setSalary(60000);
            }
            Employee employee = new Employee(e);
            employees.add(employee);
            //如果在main函数里不call registerOb method
            //这里的obInstance == null
            //即使有发生变化，这里的onchange函数也不会发生
            if (obInstance != null) {
                obInstance.onChange(employees);
                // 这里onchange的逻辑靠backend
                //客户端实际上不会验证穿过来的数据是否有更新
                //一下例子就是backend逻辑错误，传了很多一样的数据
                //前端是不知道的
//                obInstance.onChange(employees);
//                obInstance.onChange(employees);
//                obInstance.onChange(employees);
//                obInstance.onChange(employees);
            }
        }
    }

    /**
     *  Adds given members to employee collections.
     */
    @Override
    public void consumeL(Collection<LinkedInMember> members) {

        for(LinkedInMember l: members) {
            Employee.EmployeeBuilder e = new Employee.EmployeeBuilder();
            e.setEmployeeId(id++);
            e.setName(l.getName());
            e.setSkills(l.getSkills());

            if(e.getSkills() == null || e.getSkills().length < 2) {
                e.setTitle("contractor");
                e.setSalary(60000);
            }else if(e.getSkills().length <= 4) {
                e.setTitle("junior");
                e.setSalary(100000);
            }else {
                e.setTitle("senior");
                e.setSalary(130000);
            }
            Employee employee = new Employee(e);
            employees.add(employee);
            if (obInstance != null) {
                obInstance.onChange(employees);
            }
        }
    }
}

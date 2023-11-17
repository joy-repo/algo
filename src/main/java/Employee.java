import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Employee implements Comparable<Employee> {

  public static void main(String[] args) {

    List<Employee> list = new ArrayList<>();

    //[new Employee(1.2500),new Employee(2,2400),new Employee(3,null), new Employee(4,2600)]

    list.add(new Employee(1, 2500));
    list.add(new Employee(2, 2400));
    list.add(new Employee(3, null));
    list.add(new Employee(4, 2600));

    List<Employee> res =list.stream().sorted().collect(Collectors.toList());

    System.out.println(res);

  }


  public Employee(Integer id, Integer salary){
    this.id=id;
    this.salary=salary;
  }
  private Integer id;
  private Integer salary;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getSalary() {
    return salary;
  }

  public void setSalary(Integer salary) {
    this.salary = salary;
  }

  @Override
  public int compareTo(Employee emp) {

    if(emp.getSalary()==null) return -1;
    if(this.getSalary()==null) return 1;

    return salary.compareTo(emp.getSalary());


  }

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", salary=" + salary +
        '}';
  }
}

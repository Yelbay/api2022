package pojos;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyApiDataPojo {

    private String employee_name;
    private Integer employee_salary;
    private Integer employee_age;
    private String profile_image;

    public DummyApiDataPojo() {
    }

    public DummyApiDataPojo(String employeeName, Integer employeeSalary, Integer employeeAge, String profileImage) {
        this.employee_name= employeeName;
        this.employee_salary= employeeSalary;
        this.employee_age= employeeAge;
        this.profile_image= profileImage;
    }


    public String getEmployeeName() {
        return employee_name;
    }

    public void setEmployeeName(String employeeName) {
        this.employee_name= employeeName;
    }

    public Integer getEmployeeSalary() {
        return employee_salary;
    }

    public void setEmployeeSalary(Integer employeeSalary) {
        this.employee_salary= employeeSalary;
    }

    public Integer getEmployeeAge() {
        return employee_age;
    }

    public void setEmployeeAge(Integer employeeAge) {
        this.employee_age= employeeAge;
    }

    public String getProfileImage() {
        return profile_image;
    }

    public void setProfileImage(String profileImage) {
        this.profile_image= profileImage;
    }


    @Override
    public String toString() {
        return "DummyApiDataPojo{" +
                "employeeName='" + employee_name + '\'' +
                ", employeeSalary=" + employee_salary +
                ", employeeAge=" + employee_age+
                ", profileImage='" + profile_image+ '\'' +
                '}';
    }
}

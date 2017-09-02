import entity.Address;
import entity.EmplProj;
import entity.Employee;
import entity.Project;
import service.AddressService;
import service.EmplProjService;
import service.EmployeeService;
import service.ProjectService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

public class Domain {
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        EmployeeService employeeService = new EmployeeService();
        ProjectService projectService = new ProjectService();
        EmplProjService emplProjService = new EmplProjService();

        Address address = new Address();
        address.setId(1L);
        address.setCountry("Russia");
        address.setCity("Moscow");
        address.setStreet("Red Square");
        address.setPostCode("123456");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Ivanov");
        employee.setLastName("Ivan");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1941, Calendar.JUNE, 22);

        employee.setBirthDay(new Date(calendar.getTime().getTime()));
        employee.setAddressId(address.getId());

        Project project = new Project();
        project.setId(1L);
        project.setTitle("BRUSCHATKA");

        EmplProj emplProj = new EmplProj();
        emplProj.setEmployeeId(employee.getId());
        emplProj.setProjectId(project.getId());

        try {
            addressService.add(address);
            employeeService.add(employee);
            projectService.add(project);
             emplProjService.add(emplProj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

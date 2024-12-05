package InheritanceMapping;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
//no table name

//@DiscriminatorValue("TEACHER")
public class Teacher extends Person
{
	@Column(name="fdepartment",length = 50)
	   private String department;
	   @Column(name="dqualification",length = 50)
	   private String qualification;
	   @Column(name="ddesignation",length = 50)
	   private String designation;
	   @Column(name="fsalary")
	   private double salary;
	   
	public String getDepartment() 
	{
		return department;
	}
	public void setDepartment(String department) 
	{
		this.department = department;
	}
	public String getQualification() 
	{
		return qualification;
	}
	public void setQualification(String qualification) 
	{
		this.qualification = qualification;
	}
	public String getDesignation() 
	{
		return designation;
	}
	public void setDesignation(String designation) 
	{
		this.designation = designation;
	}
	public double getSalary() 
	{
		return salary;
	}
	public void setSalary(double salary) 
	{
		this.salary = salary;
	}
}

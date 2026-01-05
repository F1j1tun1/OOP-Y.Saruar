public class Person {
    protected int id;
    protected String name;
    protected int age;
    protected String department;

    public Person(int id, String name, int age, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getDepartment() {
        return department;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Invalid name. Setting to 'Unknown'.");
            this.name = "Unknown";
        }
    }
    public void setAge(int age) {
        if (age >= 0 && age <= 121) {
            this.age = age;
        } else {
            System.out.println("Invalid age. Setting to 0");
            this.age = 0;
        }
    }
    public void setDepartment(String department) {
        if (department != null && !department.trim().isEmpty()) {
            this.department = department;
        } else {
            System.out.println("Invalid department. Setting to 'Unknown'.");
            this.department = "Unknown";
        }
    }

    public void work() {
        System.out.println(name + " is working.");
    }

    public String getRole() {
        return "Person";
    }

    @Override
    public String toString() {
        return "[" + getRole() + "] " + name +
                " (ID: " + id +
                ", Age: " + age +
                ", Dept: " + department + ")";
    }
}

package test.lab.common.client;


public class Organization implements Comparable<Organization> {
    private Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String fullName; //Строка не может быть пустой, Длина строки не должна быть больше 1435, Поле может быть null
    private OrganizationType type; //Поле не может быть null
    private Address postalAddress; //Поле не может быть null

    public Organization() {

    }

    public Organization(Integer id, String name, String fullName, OrganizationType type, Address postalAddress) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.type = type;
        this.postalAddress = postalAddress;
    }

    public Organization(Organization m) {
        this.id = m.id;
        this.name = m.name;
        this.fullName = m.fullName;
        this.type = m.type;
        this.postalAddress = new Address(m.postalAddress);

    }


    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public OrganizationType getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(OrganizationType type) {
        this.type = type;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    /**
     * @param postalAddress
     */
    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }

    /**
     * Возвращает 0 если объекты равны, иначе -1 - объект первый меньше второго, иначе 1
     *
     * @param o объект, который будем сравнивать
     * @return
     */
    public int compareTo(Organization o) {
        int result = name.compareTo(o.name);
        if (result != 0) {
            return result;
        }
        result = fullName.compareTo(o.fullName);
        if (result != 0) {
            return result;
        }
        return postalAddress.compareTo(o.postalAddress);


//        return Comparator.comparing(Organization::getName)
//                .thenComparing(Organization::getFullName)
//                .thenComparing(Organization::getPostalAddress)
//                .thenComparing(Organization::getId)
//                .compare(this, o);
    }
}

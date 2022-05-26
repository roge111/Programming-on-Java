package test.lab.common.client;


public class Organization implements Comparable<Organization> {
    private static Integer countId = 1;
    private Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String fullName; //Строка не может быть пустой, Длина строки не должна быть больше 1435, Поле может быть null
    private OrganizationType type; //Поле не может быть null
    private Address postalAddress; //Поле не может быть null
    private Organization organization;

    public Organization() {

    }

    public Organization(String name, String fullName, OrganizationType type, Address postalAddress) {
        this.id = countId++;
        this.name = name;
        this.fullName = fullName;
        this.type = type;
        this.postalAddress = postalAddress;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public static void setCountId(Integer countId) {
        Organization.countId = countId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }


    public int compareTo(Organization compared) {
        return (this.id - compared.getId());
    }

    public void setOrganiztion(String organiztion) {
    }

    public Organization getOrganization() {
        return organization;
    }
}

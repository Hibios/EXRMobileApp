package com.exrapp.exradd;

import java.util.ArrayList;
import java.util.Random;

/**
 * The class is a catalog object with displaying information
 * about each organization. This class provides a method for
 * creating a list of organizations with random values.
 *
 * @author  Ivan Minakov, Kravtsov Stefan, Belousov Viktor, Tolstykh Mikhail
 * @version 3.0
 * @since   2020-12-24
 */
public class Organization {
    private static final Random rand = new Random();
    private final String orgName;
    private final String orgPhone;
    private final String orgID;

    public Organization(String name, String phone, String ID) {
        orgName = name;
        orgPhone = phone;
        orgID = ID;
    }


    public String getOrgName() {
        return orgName;
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public String getOrgID() {
        return orgID;
    }

    private static int lastOrgId = 0;

    public static ArrayList<Organization> createOrgList(int numOrganizations) {
        ArrayList<Organization> organizations = new ArrayList<>();

        for (int i = 1; i <= numOrganizations; i++) {
            int rand_id = rand.nextInt((99999 - 10000) + 1) + 10000;
            organizations.add(new Organization("Organization " + ++lastOrgId, "+7(968)77" + rand_id, "ID " + rand_id * 9999));
        }
        lastOrgId = 0;
        return organizations;
    }
}

package com.exrapp.exradd;

import java.util.ArrayList;
import java.util.Random;

public class Organization {
    private static Random rand = new Random();
    private String orgName;
    private String orgPhone;
    private String orgID;

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
        ArrayList<Organization> organizations = new ArrayList<Organization>();

        for (int i = 1; i <= numOrganizations; i++) {
            int rand_id = rand.nextInt((99999 - 10000) + 1) + 10000;
            organizations.add(new Organization("Organization " + ++lastOrgId, "+7(968)77" + rand_id, "ID " + rand_id * 9999));
        }
        lastOrgId = 0;
        return organizations;
    }
}

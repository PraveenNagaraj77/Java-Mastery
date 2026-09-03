package com.javamastery.collections.hashset;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashSetUserDemo {
    public static class User{
        String name;
        int age;

        User(String name,int age){
            this.name=name;
            this.age=age;
        }

        @Override
        public boolean equals(Object obj) {
            if(this==obj){
                return  true;
            }
            if(!(obj instanceof User)){
                return false;
            }
            User other = (User)obj;
            return age==other.age && name.equals(other.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name,age);
        }
    }

    public static void main(String[] args) {

        User user1  = new User("Praveen",26);
        User user2 = new User("Praveen",26);
        User user3 = new User("Rahul",28);
        User user4 = new User("Anjali",25);
        User user5 = new User("Rahul",28);

        System.out.println(user1.equals(user2));

        Set<User> users = new HashSet<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);


        System.out.println(users.size());

    }

}

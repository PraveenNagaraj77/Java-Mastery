package com.javamastery.strings;

public class StringsDemo {
    public static void main(String[] args) {
        //String Creation
        String employeeName = "Praveen";
        String department = new String("Engineering");

        System.out.println("Employee Name: " + employeeName);
        System.out.println("Department : "+department);

        //String length

        String email = "praveen@gmail.com";
        System.out.println("\nEmail Length :" + email.length());

        //Character Accesss
        System.out.println("First Character" + employeeName.charAt(0));
        System.out.println("Last Character " + employeeName.charAt(employeeName.length()-1));


        //String Traversal
        System.out.println("\nEmployee name characters");

        for (int i = 0;i<employeeName.length();i++){
            System.out.println("INDEX" + i + " " + " : " + employeeName.charAt(i));
        }

        //String Comparision
        String role1 = "Developer";
        String role2 = "Developer";

        System.out.println("\nString Comparison");
        System.out.println("Using == : " + (role1==role2));
        System.out.println("Using Equals() " + role1.equals(role2));

        //String Pool Demonstration

        String language1 = "Java";
        String language2 = "Java";

        System.out.println("String Pool");

        System.out.println("language1==language2: "+(language1==language2));

        //New String Demonstration


        String technology1 = new String("Java");
        String technology2 = new String("Java");

        System.out.println("\nnew String() Comparison:");

        System.out.println(
                "technology1 == technology2 : "
                        + (technology1 == technology2)
        );

        System.out.println(
                "technology1.equals(technology2) : "
                        + technology1.equals(technology2)
        );

        //String Immutability

        String company = "Cognizant";
        company.concat("Technologies");
        System.out.println("\nString Immutability:");

        System.out.println(
                "After concat without assignment : " + company
        );

        company = company.concat(" Technologies");

        System.out.println(
                "After concat with assignment    : " + company
        );

        //String Methods
        String jobTitle = "Java Full Stack Developer";

        System.out.println("\nString Methods:");

        System.out.println(
                "Uppercase : " + jobTitle.toUpperCase()
        );

        System.out.println(
                "Lowercase : " + jobTitle.toLowerCase()
        );

        System.out.println(
                "Contains Java : " + jobTitle.contains("Java")
        );

        System.out.println(
                "Starts With Java : " + jobTitle.startsWith("Java")
        );

        System.out.println(
                "Ends With Developer : "
                        + jobTitle.endsWith("Developer")
        );


        //SubString

        String employeeId = "EMP-2026-001";

        System.out.println("\nSubstring:");

        System.out.println(
                "Employee ID : " + employeeId
        );

        System.out.println(
                "Year : " + employeeId.substring(4, 8)
        );


        // ============================================================
        // 11. indexOf()
        // ============================================================

        String username = "praveen.nagaraj";

        System.out.println("\nindexOf():");

        System.out.println(
                "Dot Position : " + username.indexOf('.')
        );


        // ============================================================
        // 12. Email Processing
        // ============================================================

        String userEmail = "developer@gmail.com";

        int atPosition = userEmail.indexOf('@');

        String user = userEmail.substring(0, atPosition);
        String domain = userEmail.substring(atPosition + 1);

        System.out.println("\nEmail Processing:");

        System.out.println("Username : " + user);
        System.out.println("Domain   : " + domain);


        // ============================================================
        // 13. Replace
        // ============================================================

        String statusMessage = "Employee is inactive";

        statusMessage = statusMessage.replace("inactive","active");

        System.out.println("\nUpdated Status:");

        System.out.println(statusMessage);

        // ============================================================
        // 14. trim()
        // ============================================================

        String customerName = "   John David   ";

        System.out.println("\nTrim:");

        System.out.println(
                "Before : [" + customerName + "]"
        );

        customerName = customerName.trim();

        System.out.println(
                "After  : [" + customerName + "]"
        );

        // ============================================================
        // 15. Convert String to char[]
        // ============================================================

        String programmingLanguage = "Java";
        char[] characters = programmingLanguage.toCharArray();

        System.out.println("\nChar Array");
        for (char character : characters){
            System.out.println(character);
        }

        // ============================================================
        // 16. Character Frequency
        // ============================================================

        String word = "banana";

        int[] frequency = new int[26];

        for (char character : word.toCharArray()) {

            frequency[character - 'a']++;
        }

        System.out.println("\nCharacter Frequency:");

        for (int i = 0; i < frequency.length; i++) {

            if (frequency[i] > 0) {

                System.out.println(
                        (char) ('a' + i)
                                + " : "
                                + frequency[i]
                );
            }
        }


// ============================================================
        // 17. Palindrome Check
        // ============================================================

        String input = "level";
        boolean palindrome = true;

        int left =0;
        int right = input.length()-1;

        while (left<right){
            if(input.charAt(left)!=input.charAt(right)){
                palindrome = false;
                break;
            }
            left++;
            right--;
        }
        System.out.println("\nPalindrome Check:");

        System.out.println(
                input + " : " + palindrome
        );

        // ============================================================
        // 18. Reverse String using StringBuilder
        // ============================================================

        String original = "JavaMastery";

        String reversed = new StringBuilder(original)
                .reverse()
                .toString();

        System.out.println("\nReverse String:");

        System.out.println("Original : " + original);
        System.out.println("Reversed : " + reversed);


        // ============================================================
        // 19. StringBuilder
        // ============================================================

        StringBuilder profile = new StringBuilder();

        profile.append("Name: Praveen");
        profile.append(", ");
        profile.append("Role: Java Developer");
        profile.append(", ");
        profile.append("Experience: 1.5 years");

        System.out.println("\nStringBuilder:");

        System.out.println(profile);

        // ============================================================
        // 20. StringBuilder in a Loop
        // ============================================================

        StringBuilder numbers = new StringBuilder();

        for (int i = 1; i <= 5; i++) {

            numbers.append(i);

            if (i < 5) {
                numbers.append(", ");
            }
        }

        System.out.println("\nGenerated Numbers:");

        System.out.println(numbers);

        // ============================================================
        // 21. StringBuilder Reverse
        // ============================================================

        StringBuilder builder = new StringBuilder("Interview");

        builder.reverse();

        System.out.println("\nStringBuilder Reverse:");

        System.out.println(builder);


        // ============================================================
        // 22. Null Handling
        // ============================================================

        String managerName = null;

        System.out.println("\nNull Handling:");

        System.out.println(
                "Manager Name : " + managerName
        );

        System.out.println(
                "Is Manager Name Null : "
                        + (managerName == null)
        );

        // ============================================================
        // 23. Safe String Comparison
        // ============================================================

        String employeeRole = null;

        boolean isDeveloper =
                "Developer".equals(employeeRole);

        System.out.println("\nSafe Comparison:");

        System.out.println(
                "Is Developer : " + isDeveloper
        );


    }
}

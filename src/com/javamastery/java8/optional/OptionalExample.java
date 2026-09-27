package com.javamastery.java8.optional;

import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {

        // =========================================================
        // 1. Optional.of()
        // =========================================================

        Optional<String> name = Optional.of("Praveen");

        System.out.println("of(): " + name);


        // =========================================================
        // 2. Optional.ofNullable()
        // =========================================================

        String value = null;

        Optional<String> nullableValue =
                Optional.ofNullable(value);

        System.out.println("ofNullable(): " + nullableValue);


        // =========================================================
        // 3. Optional.empty()
        // =========================================================

        Optional<String> emptyValue =
                Optional.empty();

        System.out.println("empty(): " + emptyValue);


        // =========================================================
        // 4. isPresent()
        // =========================================================

        if (name.isPresent()) {
            System.out.println("isPresent(): " + name.get());
        }


        // =========================================================
        // 5. isPresent() - empty
        // =========================================================

        if (!emptyValue.isPresent()) {
            System.out.println("isPresent(): Value is absent");
        }


        // =========================================================
        // 6. ifPresent()
        // =========================================================

        name.ifPresent(value1 ->
                System.out.println("ifPresent(): " + value1)
        );


        // =========================================================
        // 7. get()
        // =========================================================

        String actualName = name.get();

        System.out.println("get(): " + actualName);

        // WARNING:
        // emptyValue.get() → NoSuchElementException


        // =========================================================
        // 8. orElse()
        // =========================================================

        String result1 =
                emptyValue.orElse("Unknown");

        System.out.println("orElse(): " + result1);


        // =========================================================
        // 9. orElse() when value exists
        // =========================================================

        String result2 =
                name.orElse("Unknown");

        System.out.println("orElse() with value: " + result2);


        // =========================================================
        // 10. orElseGet()
        // =========================================================

        String result3 =
                emptyValue.orElseGet(() -> "Default Name");

        System.out.println("orElseGet(): " + result3);


        // =========================================================
        // 11. orElseThrow()
        // Java 10+
        // =========================================================

        String result4 =
                name.orElseThrow();

        System.out.println("orElseThrow(): " + result4);


        // =========================================================
        // 12. orElseThrow(Supplier)
        // Java 8+
        // =========================================================

        String result5 =
                name.orElseThrow(
                        () -> new RuntimeException("Name not found")
                );

        System.out.println("orElseThrow(Supplier): " + result5);


        // =========================================================
        // 13. map()
        // =========================================================

        Optional<String> upperName =
                name.map(String::toUpperCase);

        System.out.println("map(): " + upperName);


        // =========================================================
        // 14. map() with calculation
        // =========================================================

        Optional<Integer> nameLength =
                name.map(String::length);

        System.out.println("map() length: " + nameLength);


        // =========================================================
        // 15. filter()
        // =========================================================

        Optional<String> filteredName =
                name.filter(value1 ->
                        value1.length() > 5
                );

        System.out.println("filter(): " + filteredName);


        // =========================================================
        // 16. filter() - condition fails
        // =========================================================

        Optional<String> rejectedName =
                name.filter(value1 ->
                        value1.length() > 10
                );

        System.out.println("filter() failed: " + rejectedName);


        // =========================================================
        // 17. flatMap()
        // =========================================================

        Optional<String> city =
                Optional.of("Chennai");

        Optional<String> resultCity =
                city.flatMap(OptionalExample::getCity);

        System.out.println("flatMap(): " + resultCity);


        // =========================================================
        // 18. map() + filter() + orElse()
        // Real-world style
        // =========================================================

        String processedName =
                name
                        .map(String::toUpperCase)
                        .filter(value1 -> value1.length() > 5)
                                .orElse("INVALID");

        System.out.println(
                "map + filter + orElse: " + processedName
        );
    }


    // Used for flatMap() example
    private static Optional<String> getCity(String city) {

        if (city != null && !city.isEmpty()) {
            return Optional.of(city.toUpperCase());
        }

        return Optional.empty();
    }
}
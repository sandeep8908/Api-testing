package com.example.test.demo.api.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
class HamcrestTest {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class Person{
        private String name;
        private String city;
    }

    @Test
     void given2String_whenEquals_thenCorrect(){
        String a1 = "foo";
        String a2 = "FOO";
        assertThat(a1,equalToIgnoringCase(a2));
    }

    @Test
    void givenBean_whenToStringReturnsRequiredString_thenCorrect(){
        Person person = new Person("Rythum","Indore");
        String str = person.toString();
        assertThat(person,hasToString(str));
    }

    @Test
    void  givenBean_whenHasValue_thenCorrect(){
        Person person = new Person("Rythum","Indore");
        assertThat(person,hasProperty("name"));
    }
    @Test
     void  givenBean_whenHasCorrectValue_thenCorrect() {
        Person person = new Person("Rythum","Indore");
        assertThat(person,hasProperty("city",equalTo("Indore")));
    }
    @Test
     void given2Beans_whenHavingSameValues_thenCorrect(){
        Person person1 = new Person("Rythum","Indore");
        Person person2 = new Person("Rythum","Indore");
        assertThat(person1,samePropertyValuesAs(person2));
    }
    @Test
     void givenCollection_whenEmpty_thenCorrect(){
        ArrayList<String> emptyList = new ArrayList<>();
        assertThat(emptyList,empty());
    }
    @Test
     void givenAList_whenChecksSize_thenCorrect(){
        List<String> list = Arrays.asList("Rohit" ,"Ajay","Rythum","jayant");

        assertThat(list,hasSize(4));
    }
    @Test
     void givenAListAndValues_whenChecksListForGivenValuesWithOrder_thenCorrect(){
        List<String> list = Arrays.asList("Rohit" ,"Ajay","Rythum","jayant");
        assertThat(list,contains("Rohit","Ajay","Rythum","jayant"));
    }

    @Test
     void givenValueAndArray_whenValueIsOneOfArrayElements_thenCorrect(){
        String[] list = { "collections", "beans", "text", "number" };
        assertThat("beans",isOneOf(list));
    }
    @Test
     void givenMapAndKey_whenKeyFoundInMap_thenCorrect(){
        Map<String,String> map = new HashMap<>();
        map.put("blogname","w3schools");
        assertThat(map,hasKey("blogname"));
    }
    @Test
    void givenMapAndValue_whenValueFoundInMap_thenCorrect(){
        Map<String,String> map = new HashMap<>();
        map.put("blogname","w3schools");

        assertThat(map,hasValue("w3schools"));
    }
    @Test
     void givenADouble_whenCloseTo_thenCorrect(){
        assertThat(1.5, closeTo(1, 0.5));
    }
    @Test
     void given2Strings_whenIsEqualRegardlessWhiteSpace_thenCorrect(){
        String t1 = "test";
        String t2 = " test";

        assertThat(t1,equalToIgnoringWhiteSpace(t2));
    }
    @Test
     void given2Strings_whenIsEqual_thenCorrect(){
        String t1 = "test";
        String t2 = "test";

        assertThat(t1,is(t2));
    }
    @Test
     void givenAnObject_whenInstanceOfGivenClass_thenCorrect(){
        Person person = new Person();
        assertThat(person,instanceOf(Person.class));
    }
    @Test
     void givenString_whenMeetsAnyOfGivenConditions_thenCorrect(){
        String str = "calligraphy";
        String start = "call";
        String end = "foo";

        assertThat(str,anyOf(startsWith(start),containsString(end)));
    }
}

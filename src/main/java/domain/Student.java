package domain;

import json.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    protected List<Tuple<String, Integer>> examsArr;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.examsArr =  Arrays.asList(exams);
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject json = super.toJsonObject();
        JsonObject[] examsReal = new JsonObject[examsArr.size()];
        int counter = 0;
        for (Tuple<String, Integer> exam: examsArr) {
            examsReal[counter] = new JsonObject();
            examsReal[counter].add(new JsonPair("course", new JsonString(exam.key)));
            examsReal[counter].add(new JsonPair("mark", new JsonNumber(exam.value)));
            JsonBoolean valPassed = new JsonBoolean(exam.value >= 3);
            examsReal[counter].add(new JsonPair("passed", valPassed));
            counter += 1;
        }
        json.add(new JsonPair("exams", new JsonArray(examsReal)));


        return json;
    }
}
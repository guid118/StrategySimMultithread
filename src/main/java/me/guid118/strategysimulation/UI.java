package me.guid118.strategysimulation;

import me.guid118.strategysimulation.files.CSVOutput;
import me.guid118.strategysimulation.files.JSONConfig;
import me.guid118.strategysimulation.utils.Race;
import me.guid118.strategysimulation.utils.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface UI {

    Race race = null;
    JSONConfig config = null;

    CSVOutput csvOutput = null;
    int maxthreads = Runtime.getRuntime().availableProcessors() / 4 * 3;
    List<Result> results = new ArrayList<>();

    default void addResult(Result result) {
        if (results.getLast().getTime() > result.getTime()) {
            results.removeLast();
            results.add(result);
            results.sort(Comparator.comparingDouble(Result::getTime));
        }
    }
}

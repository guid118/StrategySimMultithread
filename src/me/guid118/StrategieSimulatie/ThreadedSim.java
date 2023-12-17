package me.guid118.StrategieSimulatie;

import me.guid118.StrategieSimulatie.utils.Race;
import me.guid118.StrategieSimulatie.utils.Result;
import me.guid118.StrategieSimulatie.utils.Strategy;

import static me.guid118.StrategieSimulatie.Main.*;
import static me.guid118.StrategieSimulatie.RiskFactor.Risk;


public class ThreadedSim implements Runnable {
    private Thread t;
    private final int threadnumber;
    private final Race race;
    private final Strategy strategy;

    ThreadedSim(int threadnumber, Strategy strategy, Race race) {
        this.threadnumber = threadnumber;
        this.strategy = strategy;
        this.race = race;
    }

    public void run() {
        //run every stint
        //return the result
        double time = 0;
        for (; strategy.getStintNr() <= strategy.stints; strategy.addStint()) {
            time = time + LapTime.calculate(strategy);
        }
        time += race.getPitstopTime() * strategy.boxlap.length;
        results.add(new Result(strategy, time, Risk(strategy)));
        remthread(threadnumber);
    }

    public void start () {
        if (t == null) {
            t = new Thread (this, String.valueOf(threadnumber));
            t.start ();
        }
    }

}

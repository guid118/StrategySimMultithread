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

    ThreadedSim(int threadnumber, Race race) {
        this.threadnumber = threadnumber;
        this.race = race;
    }

    public void run() {
        //run every stint
        //return the result
        Strategy strategy;
        while ((strategy = race.getNextStrategy()) != null) {
            double time = 0;
            time += strategy.run();
            time += (strategy.stints.length - 1) * race.PitstopTime;
            addResult(new Result(strategy, time, Risk(strategy)));
        }
        remthread(threadnumber);
    }

    public void start () {
        if (t == null) {
            t = new Thread (this, String.valueOf(threadnumber));
            t.start ();
        }
    }

}
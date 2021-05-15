package nix.alevel;

import nix.alevel.firsttask.controller.RunFirstTask;
import nix.alevel.secondtask.controller.RunSecondTask;
import nix.alevel.thirdtask.contoller.RunThirdTask;

public class RunAllTasks {
    public static void main(String[] args) {
        try {
            System.out.println("\n\n                FIRST TASK\n\n");
            RunFirstTask.runFirstTask();
            System.out.println("\n\n                SECOND TASK\n\n");
            RunSecondTask.run();
            System.out.println("\n\n                THIRD TASK\n\n");
            RunThirdTask.run();
        }catch (RuntimeException e){
            System.err.println("\n\nSomething went wrong");
            System.err.println("Please, check the correctness of files structure");
        }
    }
}

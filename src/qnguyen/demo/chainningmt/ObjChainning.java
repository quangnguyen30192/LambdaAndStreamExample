package qnguyen.demo.chainningmt;

import java.util.Arrays;
import java.util.List;

/**
 * Add dependence to the tasks following the order in list.
 * e.g
 * <code>
 *     ObjChainning objChainning = new ObjChainning(List.of(new Task("1"),
 *                                                          new Task("2"),
 *                                                          new Task("3"));
 *     then task1 has task2 as dependency
 *     task2 has task3 as dependency
 *     task3 has Finalized task as dependency
 *
 * </code>
 *
 */
class ObjChainning {

  private List<Task> tasks;

  ObjChainning(List<Task> tasks) {
    this.tasks = tasks;
  }

  void processChainning(int index) {
    if (index <= (tasks.size() - 2)) {
      tasks.get(index).depends(tasks.get(index + 1));
      processChainning(index + 1);
    }
  }

  List<Task> getTasks() {
    return tasks;
  }

  public static void main(String[] args) {
    ObjChainning objChainning = new ObjChainning(Arrays.asList(new Task("1"),
            new Task("2"),
            new Task("3"),
            new Task("4"),
            new Task("5"),
            new Task("6")));

    objChainning.processChainning(0);

    Task lastTask = objChainning.getTasks().get(objChainning.getTasks().size() - 1);
    lastTask.depends(new Task("Finalized"));

    Task task = objChainning.getTasks().get(0);
    System.out.println(task);
  }

}

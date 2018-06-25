package qnguyen.demo.chainningmt;

class Task {

  private String name;
  private Task dependOn;

  Task(String name) {
    this.name = name;
  }

  void depends(Task task) {
    this.dependOn = task;
  }

  @Override
  public String toString() {
    return "Task{" +
           "name='" + name + '\'' +
           ", dependOn=" + dependOn +
           '}';
  }
}


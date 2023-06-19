package D4C.todo.model.task;


/**
 * D4C.todo.model.task.Status represents the current status of a todo
 * ACTIVE - An incompleted TODO
 * COMPLETE - A completed TODO
 */
public enum Status {

    ACTIVE(0),
    COMPLETE(1);
    final int val;

    Status(int val) {
        this.val = val;
    }
}

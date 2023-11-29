package core;

import com.sun.source.tree.Tree;
import models.Task;

import java.util.*;
import java.util.stream.Collectors;

public class TaskManagerImpl implements TaskManager {
    private Deque<Task> executionQueue;
    private HashMap<String, Task> executedTasks;
    private HashMap<String, Task> allTasks;
    private HashMap<String, ArrayDeque<Task>> domainsToUnexecutedTasks;
    private TreeSet<Task> allTasksOrdered;

    public TaskManagerImpl() {
        this.executionQueue = new ArrayDeque<>();
        this.executedTasks = new HashMap<>();
        this.allTasks = new HashMap<>();
        this.domainsToUnexecutedTasks = new HashMap<>();
        Comparator<Task> comp = Comparator.comparing(Task::getEstimatedExecutionTime).reversed()
                .thenComparing(t -> t.getName().length());
        this.allTasksOrdered = new TreeSet<>(comp);
    }

    @Override
    public void addTask(Task task) {
        String id = task.getId();
        String domain = task.getDomain();

        this.executionQueue.offer(task);
        this.allTasks.put(id, task);
        this.allTasksOrdered.add(task);

        this.domainsToUnexecutedTasks.putIfAbsent(domain, new ArrayDeque<>());
        Deque<Task> executionQueue2 = this.domainsToUnexecutedTasks.get(domain);
        executionQueue2.offer(task);
    }

    @Override
    public boolean contains(Task task) {
        return this.allTasks.containsKey(task.getId());
    }

    @Override
    public int size() {
        return this.executionQueue.size();
    }

    @Override
    public Task getTask(String taskId) {
        Task task = this.allTasks.get(taskId);

        if (task == null) {
            throw new IllegalArgumentException();
        } else {
            return task;
        }
    }

    @Override
    public void deleteTask(String taskId) {
        Task task = this.allTasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException();
        }

        this.executionQueue.remove(task);
        this.allTasks.remove(taskId);
        this.executedTasks.remove(taskId);

        String domain = task.getDomain();
        Deque<Task> executionQueue2 = this.domainsToUnexecutedTasks.get(domain);
        executionQueue2.remove(task);
        if (executionQueue2.isEmpty()) {
            this.domainsToUnexecutedTasks.remove(domain);
        }

        this.allTasksOrdered.remove(task);
    }

    @Override
    public Task executeTask() {
        if (this.executionQueue.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Task task = this.executionQueue.poll();
        String id = task.getId();
        String domain = task.getDomain();
        this.executedTasks.put(id, task);

        Deque<Task> executionQueue2 = this.domainsToUnexecutedTasks.get(domain);
        executionQueue2.remove(task);
        if (executionQueue2.isEmpty()) {
            this.domainsToUnexecutedTasks.remove(domain);
        }

        return task;
    }

    @Override
    public void rescheduleTask(String taskId) {
        Task task = this.executedTasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException();
        }
        String domain = task.getDomain();

        this.executionQueue.offer(task);
        this.domainsToUnexecutedTasks.putIfAbsent(domain, new ArrayDeque<>());
        Deque<Task> executionQueue2 = this.domainsToUnexecutedTasks.get(domain);
        executionQueue2.offer(task);
    }

    @Override
    public Iterable<Task> getDomainTasks(String domain) {
        Deque<Task> found = this.domainsToUnexecutedTasks.get(domain);
        if (found.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return found;
    }

    @Override
    public Iterable<Task> getTasksInEETRange(int lowerBound, int upperBound) {
        return this.executionQueue.stream()
                .filter(t -> t.getEstimatedExecutionTime() >= lowerBound && t.getEstimatedExecutionTime() <= upperBound)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Task> getAllTasksOrderedByEETThenByName() {
        return this.allTasksOrdered;
    }
}

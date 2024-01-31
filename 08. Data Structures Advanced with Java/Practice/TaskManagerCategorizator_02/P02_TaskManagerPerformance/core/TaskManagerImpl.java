package core;

import models.Task;

import java.util.*;
import java.util.stream.Collectors;

public class TaskManagerImpl implements TaskManager {
    private LinkedHashMap<String, Task> idToTask;
    private Deque<String> pendingTasks;
    private HashSet<String> executedTasks;
    private HashMap<String, LinkedHashSet<String>> domainToPendingTasks;

    public TaskManagerImpl() {
        this.idToTask = new LinkedHashMap<>();
        this.pendingTasks = new ArrayDeque<>();
        this.executedTasks = new HashSet<>();
        this.domainToPendingTasks = new HashMap<>();
    }

    @Override
    public void addTask(Task task) {
        String id = task.getId();
        String domain = task.getDomain();

        this.idToTask.put(id, task);
        this.pendingTasks.offer(id);
        this.domainToPendingTasks.putIfAbsent(domain, new LinkedHashSet<>());
        Set<String> domainPendingTasks = this.domainToPendingTasks.get(domain);
        domainPendingTasks.add(id);
    }

    @Override
    public boolean contains(Task task) {
        return this.idToTask.containsKey(task.getId());
    }

    @Override
    public int size() {
        return this.pendingTasks.size();
    }

    @Override
    public Task getTask(String id) {
        Task result = this.idToTask.get(id);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public void deleteTask(String id) {
        Task task = this.idToTask.get(id);
        if (task == null) {
            throw new IllegalArgumentException();
        }

        this.idToTask.remove(id);
        this.pendingTasks.remove(id);
        this.executedTasks.remove(id);

        String domain = task.getDomain();
        Set<String> domainPendingTasks = this.domainToPendingTasks.get(domain);
        domainPendingTasks.remove(id);
        if (domainPendingTasks.isEmpty()) {
            this.domainToPendingTasks.remove(domain);
        }
    }

    @Override
    public Task executeTask() {
        if (this.pendingTasks.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String id = this.pendingTasks.poll();

        Task task = this.idToTask.get(id);
        String domain = task.getDomain();
        Set<String> domainPendingTasks = this.domainToPendingTasks.get(domain);
        domainPendingTasks.remove(id);
        if (domainPendingTasks.isEmpty()) {
            this.domainToPendingTasks.remove(domain);
        }

        this.executedTasks.add(id);
        return task;
    }

    @Override
    public void rescheduleTask(String id) {
        if (!this.executedTasks.contains(id)) {
            throw new IllegalArgumentException();
        }

        this.executedTasks.remove(id);
        this.pendingTasks.offer(id);

        Task task = this.idToTask.get(id);
        String domain = task.getDomain();
        this.domainToPendingTasks.putIfAbsent(domain, new LinkedHashSet<>());
        Set<String> domainPendingTasks = this.domainToPendingTasks.get(domain);
        domainPendingTasks.add(id);
    }

    @Override
    public Iterable<Task> getDomainTasks(String domain) {
        Set<String> ids = this.domainToPendingTasks.get(domain);
        if (ids.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return ids.stream().map((String id) -> this.idToTask.get(id)).collect(Collectors.toList());
    }

    @Override
    public Iterable<Task> getTasksInEETRange(int lowerBound, int upperBound) {
        List<Task> tasks = this.pendingTasks.stream().map((String id) -> this.idToTask.get(id)).collect(Collectors.toList());
        return tasks.stream()
                .filter((Task t) -> t.getEstimatedExecutionTime() >= lowerBound && t.getEstimatedExecutionTime() <= upperBound)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Task> getAllTasksOrderedByEETThenByName() {
        return this.idToTask.values().stream()
                .sorted(Comparator.comparing(Task::getEstimatedExecutionTime, Comparator.reverseOrder())
                        .thenComparing((Task t) -> t.getName().length()))
                .collect(Collectors.toList());
    }
}

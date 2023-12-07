package core;

import models.Category;

import java.util.*;
import java.util.stream.Collectors;

public class CategorizatorImpl implements Categorizator {
    private LinkedHashMap<String, Category> idToCategory;
    private HashMap<String, Category> childToParent;
    private HashMap<String, LinkedHashSet<Category>> parentToChildren;
    private HashMap<String, Integer> idToDepth;

    public CategorizatorImpl() {
        this.idToCategory = new LinkedHashMap<>();
        this.childToParent = new HashMap<>();
        this.parentToChildren = new HashMap<>();
        this.idToDepth = new HashMap<>();
    }

    @Override
    public void addCategory(Category category) {
        String id = category.getId();
        if (this.idToCategory.containsKey(id)) {
            throw new IllegalArgumentException();
        }

        this.idToCategory.put(id, category);
    }

    @Override
    public void assignParent(String childCategoryId, String parentCategoryId) {
        Category child = this.idToCategory.get(childCategoryId);
        Category parent = this.idToCategory.get(parentCategoryId);
        if (child == null || parent == null) {
            throw new IllegalArgumentException();
        }

        if (this.childToParent.containsKey(childCategoryId)) {
            if (this.childToParent.get(childCategoryId).equals(parent)) {
                throw new IllegalArgumentException();
            }
        }

        this.childToParent.put(childCategoryId, parent);
        this.parentToChildren.putIfAbsent(parentCategoryId, new LinkedHashSet<>());
        Set<Category> children = this.parentToChildren.get(parentCategoryId);
        children.add(child);
    }

    @Override
    public void removeCategory(String id) {
        Category category = this.idToCategory.get(id);
        if (category == null) {
            throw new IllegalArgumentException();
        }

        this.removeCategoryAndChildren(category);
    }

    private void removeCategoryAndChildren(Category category) {
        if (category == null) {
            return;
        }

        String id = category.getId();
        this.idToCategory.remove(id);
        this.childToParent.remove(id);

        Set<Category> children = this.parentToChildren.get(id);
        if (children != null) {
            for (Category child : children) {
                this.removeCategoryAndChildren(child);
            }
        }
    }

    @Override
    public boolean contains(Category category) {
        return this.idToCategory.containsKey(category.getId());
    }

    @Override
    public int size() {
        return this.idToCategory.size();
    }

    @Override
    public Iterable<Category> getChildren(String id) {
        Category category = this.idToCategory.get(id);
        if (category == null) {
            throw new IllegalArgumentException();
        }

        List<Category> result = new ArrayList<>();
        this.addAllChildren(category, result);
        return result;
    }

    private void addAllChildren(Category category, List<Category> list) {
        String id = category.getId();
        if (!this.parentToChildren.containsKey(id)) {
            return;
        }

        Set<Category> children = this.parentToChildren.get(id);
//        list.addAll(children);

        for (Category child : children) {
            list.add(child);
            this.addAllChildren(child, list);
        }
    }

    @Override
    public Iterable<Category> getHierarchy(String id) {
        Category category = this.idToCategory.get(id);
        if (category == null) {
            throw new IllegalArgumentException();
        }

        List<Category> result = new ArrayList<>();
        result.add(category);
        this.addParents(category, result);
        Collections.reverse(result);
        return result;
    }

    private void addParents(Category category, List<Category> list) {
        String id = category.getId();
        if (!this.childToParent.containsKey(id)) {
            return;
        }

        Category parent = this.childToParent.get(id);
        list.add(parent);
        this.addParents(parent, list);
    }

    @Override
    public Iterable<Category> getTop3CategoriesOrderedByDepthOfChildrenThenByName() {
        return this.idToCategory.values().stream()
                .sorted(Comparator.comparing((Category c) -> this.getMaxDepth(c))
                        .thenComparing(Category::getName)).limit(3)
                .collect(Collectors.toList());
    }

    private int getMaxDepth(Category category) {
        return 0;
    }
}

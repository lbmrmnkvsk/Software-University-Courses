package core;

import models.Category;

import java.util.*;
import java.util.stream.Collectors;

public class CategorizatorImpl implements Categorizator {
    private HashMap<String, Category> idToCategory;
    private HashMap<String, HashSet<String>> parentToChildren;
    private HashMap<String, String> childToParent;
    private TreeSet<Category> orderedCategories;

    public CategorizatorImpl() {
        this.idToCategory = new HashMap<>();
        this.parentToChildren = new HashMap<>();
        this.childToParent = new HashMap<>();

        Comparator<Object> comp = Comparator.comparing(c -> this.getDepthOfChildren((Category) c)).reversed()
                .thenComparing(c -> ( (Category) c).getName());
        this.orderedCategories = new TreeSet<>(comp);
    }

    @Override
    public void addCategory(Category category) {
        String id = category.getId();
        if (idToCategory.containsKey(id)) {
            throw new IllegalArgumentException();
        }

        this.idToCategory.put(id, category);
        this.orderedCategories.add(category);
    }

    @Override
    public void assignParent(String childCategoryId, String parentCategoryId) {
        if (!this.idToCategory.containsKey(childCategoryId) || !this.idToCategory.containsKey(parentCategoryId)) {
            throw new IllegalArgumentException();
        }

        this.parentToChildren.putIfAbsent(parentCategoryId, new HashSet<>());
        Set<String> children = this.parentToChildren.get(parentCategoryId);
        if (children.contains(childCategoryId)) {
            throw new IllegalArgumentException();
        }
        children.add(childCategoryId);

        this.childToParent.put(childCategoryId, parentCategoryId);
    }

    @Override
    public void removeCategory(String categoryId) {
        if (!idToCategory.containsKey(categoryId)) {
            throw new IllegalArgumentException();
        }

        this.removeCategoryAndChildren(categoryId);
    }

    private void removeCategoryAndChildren(String id) {
        this.orderedCategories.remove(this.idToCategory.get(id));
        this.idToCategory.remove(id);
        this.childToParent.remove(id);

        if (!this.parentToChildren.containsKey(id)) {
            return;
        }

        Set<String> children = this.parentToChildren.get(id);
        this.parentToChildren.remove(id);

        for (String childId : children) {
            this.removeCategoryAndChildren(childId);
        }
    }

    @Override
    public boolean contains(Category category) {
        String id = category.getId();
        return this.idToCategory.containsKey(id);
    }

    @Override
    public int size() {
        return this.idToCategory.size();
    }

    @Override
    public Iterable<Category> getChildren(String categoryId) {
        if (!this.idToCategory.containsKey(categoryId)) {
            throw new IllegalArgumentException();
        }

        List<Category> result = new ArrayList<>();
        this.getAllChildren(categoryId, result);
        return result;
    }

    private void getAllChildren(String parentId, List<Category> list) {
        if (!this.parentToChildren.containsKey(parentId)) {
            return;
        }

        Set<String> childrenIds = this.parentToChildren.get(parentId);

        for (String item : childrenIds) {
            list.add(this.idToCategory.get(item));
            getAllChildren(item, list);
        }
    }

    @Override
    public Iterable<Category> getHierarchy(String categoryId) {
       if (!this.idToCategory.containsKey(categoryId)) {
           throw new IllegalArgumentException();
       }

       List<Category> result = new ArrayList<>();
       result.add(this.idToCategory.get(categoryId));
       this.addParents(categoryId, result);

       Collections.reverse(result);
       return result;
    }

    private void addParents(String childId, List<Category> list) {
        if (!this.childToParent.containsKey(childId)) {
            return;
        }

        String parentId = this.childToParent.get(childId);
        list.add(this.idToCategory.get(parentId));

        addParents(parentId, list);
    }

    @Override
    public Iterable<Category> getTop3CategoriesOrderedByDepthOfChildrenThenByName() {
        return this.orderedCategories.stream().limit(3).collect(Collectors.toList());
    }

    private int getDepthOfChildren(Category category) {
        String categoryId = category.getId();
        return getDepthHelper(categoryId);
    }

    private int getDepthHelper(String categoryId) {
//        if (!parentToChildren.containsKey(categoryId)) {
//            return 1;  // No children, depth is 1
//        }

        Set<String> childrenIds = parentToChildren.get(categoryId);
        int maxChildDepth = 0;

        for (String childId : childrenIds) {
            int childDepth = getDepthHelper(childId);
            maxChildDepth = Math.max(maxChildDepth, childDepth);
        }

        return 1 + maxChildDepth;  // Add 1 to include the current category in the depth calculation
    }
}

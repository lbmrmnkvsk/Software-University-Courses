package core;

import models.Category;

import java.util.*;
import java.util.stream.Collectors;

public class CategorizatorSolution implements Categorizator {

    LinkedHashMap<String, Category> idToCategory = new LinkedHashMap<>();
    Map<String, Category> childToParent = new HashMap<>();
    Map<String, LinkedHashSet<Category>> parentToChildren = new HashMap<>();

    @Override
    public void addCategory(Category category) {
        if (contains(category)) {
            throw new IllegalArgumentException();
        }

        idToCategory.put(category.getId(), category);
        parentToChildren.put(category.getId(), new LinkedHashSet<>());
    }

    private Category tryGetCategory(String id) {
        return idToCategory.get(id);
    }

    @Override
    public void assignParent(String childCategoryId, String parentCategoryId) {
        Category child = tryGetCategory(childCategoryId);
        Category parent = tryGetCategory(parentCategoryId);

        if (child == null || parent == null) {
            throw new IllegalArgumentException();
        }

        Category previousParent = childToParent.put(child.getId(), parent);
        if (previousParent != null) {
            throw new IllegalArgumentException();
        }

        LinkedHashSet<Category> parentCategoryChildren = parentToChildren.get(parent.getId());
        parentCategoryChildren.add(child);
    }

    @Override
    public void removeCategory(String categoryId) {
        Category categoryToDelete = idToCategory.remove(categoryId);
        if (categoryToDelete == null) {
            throw new IllegalArgumentException();
        }

        LinkedHashSet<Category> childrenToDelete = new LinkedHashSet<>(parentToChildren.get(categoryToDelete.getId()));
        for (Category category : childrenToDelete) {
            removeCategory(category.getId());
        }

        Category parent = childToParent.remove(categoryToDelete.getId());
        if (parent != null) {
            LinkedHashSet<Category> parentCategoryChildren = parentToChildren.get(parent.getId());
            parentCategoryChildren.remove(categoryToDelete);
        }
    }

    @Override
    public boolean contains(Category category) {
        return contains(category.getId());
    }

    private boolean contains(String categoryId) {
        return tryGetCategory(categoryId) != null;
    }

    @Override
    public int size() {
        return idToCategory.size();
    }


    @Override
    public Iterable<Category> getChildren(String categoryId) {
        if (!contains(categoryId)) {
            throw new IllegalArgumentException();
        }

        List<Category> allChildren = new ArrayList<>();
        fillChildren(categoryId, allChildren);

        return allChildren;
    }

    private void fillChildren(String categoryId, List<Category> allChildren) {
        LinkedHashSet<Category> directChildren = parentToChildren.get(categoryId);
        for (Category directChild : directChildren) {
            allChildren.add(directChild);
            fillChildren(directChild.getId(), allChildren);
        }
    }

    @Override
    public Iterable<Category> getHierarchy(String categoryId) {
        Category category = tryGetCategory(categoryId);
        if (category == null) {
            throw new IllegalArgumentException();
        }

        List<Category> hierarchy = new ArrayList<>();
        while (category != null) {
            hierarchy.add(category);
            category = childToParent.get(category.getId());
        }

        Collections.reverse(hierarchy);
        return hierarchy;
    }

    Map<String, Long> depthByCategoryId = new HashMap<>();
    @Override
    public Iterable<Category> getTop3CategoriesOrderedByDepthOfChildrenThenByName() {
        depthByCategoryId = new HashMap<>();

        for (Category category : idToCategory.values()) {
            if (childToParent.get(category.getId()) == null) {
                calculateDepth(category);
            }
        }

        return idToCategory.values().stream()
                .sorted(
                        Comparator.comparing((Category c) -> depthByCategoryId.get(c.getId()), Comparator.reverseOrder())
                                .thenComparing((Category c) -> c.getName())
                )
                .limit(3)
                .collect(Collectors.toList());
    }

    private long calculateDepth(Category category) {
        long maxChildDepth = 0;

        for (Category childCategory : parentToChildren.get(category.getId())) {
            long childDepth = calculateDepth(childCategory);
            if (maxChildDepth < childDepth) {
                maxChildDepth = childDepth;
            }
        }

        long depth = 1 + maxChildDepth;
        depthByCategoryId.put(category.getId(), depth);

        return depth;
    }
}


package core;

import models.Expression;
import models.ExpressionType;

import java.util.HashMap;

public class ExpressionistImpl implements Expressionist {
    private Expression root;
    private HashMap<String, Expression> idToExpression;
    private HashMap<String, String> childToParent;

    public ExpressionistImpl() {
        this.root = null;
        this.idToExpression = new HashMap<>();
        this.childToParent = new HashMap<>();
    }

    @Override
    public void addExpression(Expression expression) {
        if (this.root != null || !this.idToExpression.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.root = expression;
        this.idToExpression.put(expression.getId(), expression);
    }

    @Override
    public void addExpression(Expression expression, String parentId) {
        Expression parent = this.idToExpression.get(parentId);
        if (parent == null) {
            throw new IllegalArgumentException();
        }
        if (parent.getLeftChild() != null && parent.getRightChild() != null) {
            throw new IllegalArgumentException();
        }

        String id = expression.getId();
        this.idToExpression.put(id, expression);
        this.childToParent.put(id, parentId);

        if (parent.getLeftChild() == null) {
            parent.setLeftChild(expression);
        } else if (parent.getRightChild() == null) {
            parent.setRightChild(expression);
        }

    }

    @Override
    public boolean contains(Expression expression) {
        return this.idToExpression.containsKey(expression.getId());
    }

    @Override
    public int size() {
        return this.idToExpression.size();
    }

    @Override
    public Expression getExpression(String expressionId) {
        Expression expression = this.idToExpression.get(expressionId);
        if (expression == null) {
            throw new IllegalArgumentException();
        }

        return expression;
    }

    @Override
    public void removeExpression(String id) {
        Expression expression = this.idToExpression.get(id);
        if (expression == null) {
            throw new IllegalArgumentException();
        }
        if (expression.equals(this.root)) {
            this.root = null;
            this.idToExpression.clear();
            this.childToParent.clear();
            return;
        }

        String parentId = this.childToParent.get(id);
        Expression parent = this.idToExpression.get(parentId);
        this.childToParent.remove(id);
        this.idToExpression.remove(id);

        if (parent.getRightChild().equals(expression)) {
            parent.setRightChild(null);
        } else if (parent.getLeftChild().equals(expression)) {
            parent.setLeftChild(parent.getRightChild());
            parent.setRightChild(null);
        }

        removeChildren(expression);
    }

    private void removeChildren(Expression expression) {
        if (expression == null) {
            return;
        }
        String id = expression.getId();

        this.childToParent.remove(id);
        this.idToExpression.remove(id);
        removeChildren(expression.getLeftChild());
        removeChildren(expression.getRightChild());
    }

    @Override
    public String evaluate() {
        StringBuilder sb = new StringBuilder();
        this.Traverse(this.root, sb);
        return sb.toString();
    }

    private void Traverse(Expression expression, StringBuilder sb) {
        if (expression == null) {
            return;
        }

        Traverse(expression.getLeftChild(), sb);
        sb.append(this.Stringify(expression));
        Traverse(expression.getRightChild(), sb);
    }

    private String Stringify(Expression expression) {
        String result = "";
        if (expression.getType().equals(ExpressionType.VALUE)) {
            result = expression.getValue();
        } else if (expression.getType().equals(ExpressionType.OPERATOR)) {
            result = "(" + expression.getLeftChild().getValue() + " " + expression.getValue() + " " +
                    expression.getRightChild().getValue() + ")";
        }

        return result;
    }

}

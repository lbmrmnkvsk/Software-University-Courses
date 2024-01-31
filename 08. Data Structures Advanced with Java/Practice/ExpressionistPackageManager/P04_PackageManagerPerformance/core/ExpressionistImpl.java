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
        if (this.root == null) {
            this.root = expression;
            String id = expression.getId();
            this.idToExpression.put(id, expression);
        } else {
            throw new IllegalArgumentException();
        }
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

        if (parent.getLeftChild() == null) {
            parent.setLeftChild(expression);
        } else if (parent.getRightChild() == null) {
            parent.setRightChild(expression);
        }

        String childId = expression.getId();
        this.idToExpression.put(childId, expression);
        this.childToParent.put(childId, parentId);
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
        Expression result = this.idToExpression.get(expressionId);
        if (result == null) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    @Override
    public void removeExpression(String expressionId) {
        if (!this.idToExpression.containsKey(expressionId)) {
            throw new IllegalArgumentException();
        }

        Expression expression = this.idToExpression.get(expressionId);
        if (expression.equals(this.root)) {
            this.root = null;
            this.idToExpression.clear();
            this.childToParent.clear();
            return;
        }

        Expression parent = this.idToExpression.get(this.childToParent.get(expressionId));
        boolean expressionIsLeftChild = parent.getLeftChild().equals(expression);

        this.removeChildren(expression);

        if (expressionIsLeftChild) {
            parent.setLeftChild(parent.getRightChild());
            parent.setRightChild(null);
        } else {
            parent.setRightChild(null);
        }
    }

    private void removeChildren(Expression expression) {
        if (expression == null) {
            return;
        }

        String id = expression.getId();
        this.idToExpression.remove(id);
        this.childToParent.remove(id);

        this.removeChildren(expression.getLeftChild());
        this.removeChildren(expression.getRightChild());
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

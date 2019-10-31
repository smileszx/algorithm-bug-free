package com.algorithm.leetcode.list;

import java.util.Stack;

/**
 * @Description
 * 二叉树镜像
 * @Author victor su
 * @Date 2019/10/31 21:29
 **/
public class TreeNodeMirror {

    public static void main(String[] args) {
        TreeNode[] node = new TreeNode[10];//以数组形式生成一棵完全二叉树

        for(int i = 0; i < 10; i++)
        {
            node[i] = new TreeNode(i);
        }

        for(int i = 0; i < 10; i++)
        {
            if(i*2+1 < 10)
                node[i].left = node[i*2+1];
            if(i*2+2 < 10)
                node[i].right = node[i*2+2];
        }
        preOrderRe(node[0]);
        System.out.println("");
        TreeNode mirror = getTreeNodeMirror(node[0]);
        System.out.println("");
        preOrderRe(mirror);
        System.out.println("");
        preOrder(mirror);
    }

    /**
     * 前序遍历递归实现: root -> left -> right
     * @param biTree
     */
    public static void preOrderRe(TreeNode biTree)
    {//递归实现
        System.out.print(biTree.val);
        TreeNode leftTree = biTree.left;
        if(leftTree != null)
        {
            preOrderRe(leftTree);
        }
        TreeNode rightTree = biTree.right;
        if(rightTree != null)
        {
            preOrderRe(rightTree);
        }
    }

    /**
     * 前序遍历非递归方式
     * @param biTree
     */
    public static void preOrder(TreeNode biTree)
    {//非递归实现
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(biTree != null || !stack.isEmpty())
        {
            while(biTree != null)
            {
                System.out.print(biTree.val);
                stack.push(biTree);
                biTree = biTree.left;
            }
            if(!stack.isEmpty())
            {
                biTree = stack.pop();
                biTree = biTree.right;
            }
        }
    }

    /**
     * 递归实现
     * 关键就在于把大问题转化为子问题即可
     * @param root
     */
    public static TreeNode getTreeNodeMirror(TreeNode root) {
        if(root != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            getTreeNodeMirror(root.left);
            getTreeNodeMirror(root.right);
        }
        return root;
    }
}

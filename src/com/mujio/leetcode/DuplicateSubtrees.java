package com.mujio.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description: DuplicateSubtrees
 * @Author: GZY
 * @Date: 2020/10/16
 */

public class DuplicateSubtrees {
    Map<String, Integer> temp = new HashMap<>();
    List<TreeNode> res = new LinkedList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {

        traverse(root);
        return res;
    }

    String traverse(TreeNode root){
        if (root == null) {
            return "#";
        }

        String left = traverse(root.left);
        String right = traverse(root.right);

        String subTree = left + "," + right + "," + root.val;

        int freq = temp.getOrDefault(subTree, 0);

        if (freq == 1) {//第一次满足时添加进结果集
            res.add(root);
        }

        temp.put(subTree, ++freq);

        return subTree;
    }

}

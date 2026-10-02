package leetcode.mals.tasks.trees;

import leetcode.mals.others.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTree(preorder,  0, preorder.length - 1,
                inorderMap, 0, inorder.length - 1
        );
    }

    private TreeNode buildTree(
            int[] preorder, int preStart, int preEnd,
            Map<Integer, Integer> inorderMap, int inStart, int inEnd
    ) {

        if (inStart > inEnd) {
            return null;
        }

        int nodeValue = preorder[preStart];
        int rootIndex = inorderMap.get(nodeValue);

        int leftSize = rootIndex - inStart;

        int inStartLeft = inStart;
        int inEndLeft = rootIndex - 1;

        int preStartLeft = preStart + 1;
        int preEndLeft = preStart + leftSize;

        int inStartRight = rootIndex + 1;
        int inEndRight = inEnd;

        int preStartRight = preEndLeft + 1;
        int preEndRight = preEnd;

        return new TreeNode(
                nodeValue,
                buildTree(
                        preorder, preStartLeft, preEndLeft,
                        inorderMap, inStartLeft, inEndLeft),
                buildTree(
                        preorder, preStartRight, preEndRight,
                        inorderMap, inStartRight, inEndRight
                )
        );
    }
}

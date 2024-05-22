package problem;

import utils.AlgoLevel3Study;

import java.util.*;

public class FindPathGame implements AlgoLevel3Study {
    static class Node {
        int order;
        int x;
        int y;
        Node left;
        Node right;

        public Node(int order, int x, int y, Node left, Node right) {
            this.order = order;
            this.x = x;
            this.y = y;
            this.left = left;
            this.right = right;
        }
    }

    public int preorder(Node node, int[] saved, int count) {
        // 일단 현재 node 투입
        saved[count] = node.order;
        count += 1;

        // node 부터 시작
        if (node.left != null) {
            count  = preorder(node.left, saved, count);
        }

        if (node.right != null) {
            count = preorder(node.right, saved, count);
        }

        return count;
    }
    public int postorder(Node node, int[] saved, int count) {
        if(node == null) return count;

        count = postorder(node.left, saved, count);
        count = postorder(node.right, saved, count);

        saved[count] = node.order;
        count += 1;

        return count;
    }


    public void makeTree(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                makeTree(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                makeTree(parent.right, child);
            }
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        // 이진 트리 만들기
        Node[] nodeList = new Node[nodeinfo.length];

        for (int i = 0; i < nodeinfo.length; i++) {
            nodeList[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1], null, null);
        }

        Arrays.sort(nodeList, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.y == o2.y) return o1.x - o2.x;
                //
                return o2.y - o1.y;
            }
        });


        // 트리 만들기
        int index = 0;
        for (Node node : nodeList) {
            if (index == 0) {
                index++;
                continue;
            }
            makeTree(nodeList[0], node);
        }

        int[] preorderList = new int[nodeinfo.length];
        int[] postorderList = new int[nodeinfo.length];
        preorder(nodeList[0], preorderList, 0);
        postorder(nodeList[0], postorderList, 0);

//        System.out.println("preorderList = " + Arrays.toString(preorderList));
//        System.out.println("postorderList = " + Arrays.toString(postorderList));

;

        return new int[][]{preorderList, postorderList};
    }

    @Override
    public void init() {

        int[][] data = {
                {5, 3},
                {11, 5},
                {13, 3},
                {3, 5},
                {6, 1},
                {1, 3},
                {8, 6},
                {7, 2},
                {2, 2}
        };

        int[][] solution = solution(data);
        System.out.println("solution = " + Arrays.deepToString(solution));

    }
}

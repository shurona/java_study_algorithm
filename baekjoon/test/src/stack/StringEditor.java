package stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/1406
 */
public class StringEditor implements BaekAlgoStudy {

    static Stack<Node> store = new Stack<>();

    static void printData(Node node, String prev) {
        if (node.next == null) {
            System.out.println(prev + node.data);
            return;
        }

        printData(node.next, prev + node.data);
    }

    static void addNode(String data) {
        Node newNode = new Node(data);
        newNode.next = store.peek().next;
        store.peek().next = newNode;
        store.add(newNode);
    }

    static void deleteNode() {
        Node target = store.pop();
        Node before = store.peek();
        before.next = target.next;
    }

    public void overTimeSolution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> input = Arrays.stream(reader.readLine().split(""))
            .collect(Collectors.toList());

        int ct = Integer.parseInt(reader.readLine());

        int loc = input.size();
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < ct; i++) {
                String[] command = reader.readLine().split(" ");

                switch (command[0]) {
                    case "L":
                        if (loc > 0) {
                            loc -= 1;
                        }
                        break;

                    case "B":
                        if (loc > 0) {
                            input.remove(loc - 1);
                            loc -= 1;
                        }
                        break;

                    case "D":
                        if (loc < input.size()) {
                            loc += 1;
                        }
                        break;

                    default:
                        input.add(loc, command[1]);
                        loc += 1;
                        break;

                }

//                sb.append(input).append("  loc : ").append(loc).append("\n");

            }
//            System.out.println(sb);
            System.out.println(String.join("", input));

        } catch (Exception e) {
            System.out.println(sb);
            e.printStackTrace();
        }
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split("");

        int ct = Integer.parseInt(reader.readLine());

        Node root = new Node("");
        Node before = root;
        store.add(root);
        int storeIndex = 1;
        // Linked List로 변경
        for (String s : input) {
            Node now = new Node(s);
            // 스택으로 저장한다.
            store.add(now);
            storeIndex += 1;
            before.next = now;
            before = now;

        }

        try {
            for (int i = 0; i < ct; i++) {
                String[] command = reader.readLine().split(" ");

                switch (command[0]) {
                    case "L":
                        if (store.size() > 1) {
                            store.pop();
                        }
                        break;

                    case "B":
                        if (store.size() > 1) {
                            deleteNode();
                        }
                        break;

                    case "D":
                        Node peek = store.peek();
                        if (peek.next != null) {
                            store.add(peek.next);
                        }
                        break;

                    default:
                        addNode(command[1]);
                        break;

                }

//                printData(root, "");
//                sb.append(input).append("  loc : ").append(loc).append("\n");

            }
//            System.out.println(sb);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

            Node tt = root;
            while (tt.next != null) {
                Node tp = tt.next;
                writer.write(tp.data);
                tt = tp;
            }
            writer.flush();
            writer.close();
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Node {

        String data;

        Node next;


        public Node(String data) {
            this.data = data;
        }
    }
}

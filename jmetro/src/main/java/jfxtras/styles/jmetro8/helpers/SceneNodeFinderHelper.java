package jfxtras.styles.jmetro8.helpers;

import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.ArrayList;

public class SceneNodeFinderHelper {
    public static ArrayList<Node> getAllNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<>();
        addAllDescendents(root, nodes);
        return nodes;
    }

    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            nodes.add(node);

            if (node instanceof Parent) {
                addAllDescendents((Parent)node, nodes);
            }
        }
    }
}

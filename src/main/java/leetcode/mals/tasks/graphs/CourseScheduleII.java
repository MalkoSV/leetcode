package leetcode.mals.tasks.graphs;

import java.util.ArrayList;
import java.util.List;

public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<List<Integer>> parents = new ArrayList<>(numCourses);
        List<List<Integer>> children = new ArrayList<>(numCourses);

        for (int i = 0; i < numCourses; i++) {
            parents.add(new ArrayList<>());
            children.add(new ArrayList<>());
        }

        for (int[] pr : prerequisites) {
            parents.get(pr[0]).add(pr[1]);
            children.get(pr[1]).add(pr[0]);
        }

        List<Integer> withoutParents = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {

            if (parents.get(i).isEmpty()) {
                withoutParents.add(i);
            }
        }

        List<Integer> visited = new ArrayList<>();
        for (int first : withoutParents) {

            if (traverse(first, children, parents, visited)) {
                return visited.stream()
                        .mapToInt(Integer::intValue)
                        .toArray();
            }

        }

        return new int[] {};
    }

    private boolean traverse(int course,
                             List<List<Integer>> children,
                             List<List<Integer>> parents,
                             List<Integer> visited)
    {

        if (visited.contains(course)) {
            return false;
        }

        visited.add(course);

        for (int child : children.get(course)) {

            if (isAllParentsVisited(parents.get(child), visited)) {
                traverse(child, children, parents, visited);
            }

        }

        return visited.size() == children.size();
    }

    private boolean isAllParentsVisited(List<Integer> parents, List<Integer> visited) {
        for (int p : parents) {
            if (!visited.contains(p)) {
                return false;
            }
        }

        return true;
    }
}

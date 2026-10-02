package leetcode.mals.tasks.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        if (prerequisites.length == 0) {
            return true;
        }

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

        Set<Integer> visited = new HashSet<>();
        for (int first : withoutParents) {

            if (traverse(first, children, parents, visited)) {
                return true;
            }

        }

        return false;
    }

    private boolean traverse(int course,
                             List<List<Integer>> children,
                             List<List<Integer>> parents,
                             Set<Integer> visited)
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

    private boolean isAllParentsVisited(List<Integer> parents, Set<Integer> visited) {
        for (int p : parents) {
            if (!visited.contains(p)) {
                return false;
            }
        }

        return true;
    }
}

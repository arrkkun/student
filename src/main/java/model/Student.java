package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private Map<String, String> scores;
    private String id;

    /*public Student(String name) {
        this.name = name;
        this.scores = new HashMap<>();
    }*/
    public Student(String id,String name) {
        this.id = id;
        this.name = name;
        this.scores = new HashMap<>();
    }

    public void addScore(String subject, String score) {
        scores.put(subject, score);
    }

    public double getTotalScore() {
        return scores.values().stream()
                .filter(Objects::nonNull)
                .mapToDouble(this::convertScoreToDouble)
                .sum();
    }
    /*public Map<String, String> getScores() {
        return scores;
    }*/

    private double convertScoreToDouble(String score) {
        switch (score) {
            case "优": return 90.0;
            case "良": return 80.0;
            case "中": return 70.0;
            case "差": return 60.0;
            default:
                try {
                    return Double.parseDouble(score);
                } catch (NumberFormatException e) {
                    return 0.0;
                }
        }
    }

    public double getAverageScore() {
        return scores.isEmpty() ? 0 : getTotalScore() / scores.size();
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getScores() {
        return scores;
    }

    public int getId() {
        return Integer.parseInt(id);
    }
}

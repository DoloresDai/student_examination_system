package main.java.dai.model;

public class Score {
    private float score;

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public boolean isEmpty() {
        return this.score == 0;
    }

    @Override
    public String toString() {
        return " ,成绩： " + this.score;
    }
}

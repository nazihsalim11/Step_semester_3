import java.util.ArrayList;

abstract class Question {

    protected String text;
    protected String correctAnswer;

    public Question(String text, String correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    public abstract boolean evaluate(String answer);

    public String getText() {
        return text;
    }
}

class MultipleChoiceQuestion extends Question {

    public MultipleChoiceQuestion(
            String text,
            String correctAnswer) {

        super(text, correctAnswer);
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {

    public TrueFalseQuestion(
            String text,
            String correctAnswer) {

        super(text, correctAnswer);
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Examination {

    private String title;
    private ArrayList<Question> questions;

    public Examination(String title) {
        this.title = title;
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public Attempt startAttempt(Student student) {

        System.out.println(
                "Examination '" + title
                + "' started by "
                + student.getName()
                + "."
        );

        return new Attempt(student, this);
    }
}

class Attempt {

    private Student student;
    private Examination examination;
    private ArrayList<String> answers;
    private boolean submitted;

    public Attempt(
            Student student,
            Examination examination) {

        this.student = student;
        this.examination = examination;
        this.answers = new ArrayList<>();
        this.submitted = false;
    }

    public void answerQuestion(
            int questionNumber,
            String answer) {

        if (submitted) {
            System.out.println(
                    "Cannot change answer after submission."
            );
            return;
        }

        while (answers.size() < questionNumber) {
            answers.add(null);
        }

        answers.set(questionNumber - 1, answer);

        System.out.println(
                "Question "
                + questionNumber
                + " answered with '"
                + answer
                + "'."
        );
    }

    public void submit() {

        if (submitted) {
            System.out.println(
                    "Attempt has already been submitted."
            );
            return;
        }

        submitted = true;

        System.out.println(
                "Examination '"
                + examination.getTitle()
                + "' submitted successfully."
        );

        evaluate();
    }

    private void evaluate() {

        int correct = 0;

        ArrayList<Question> questions =
                examination.getQuestions();

        for (int i = 0; i < questions.size(); i++) {

            if (i < answers.size() &&
                answers.get(i) != null) {

                if (questions.get(i).evaluate(
                        answers.get(i))) {

                    correct++;
                }
            }
        }

        System.out.println(
                "Result for '"
                + examination.getTitle()
                + "' attempt: "
                + correct
                + "/"
                + questions.size()
                + " correct"
        );
    }
}

public class OnlineExaminationSystem {

    public static void main(String[] args) {

        Student student =
                new Student("John Doe");

        Examination exam =
                new Examination("Math Quiz");

        exam.addQuestion(
                new MultipleChoiceQuestion(
                        "What is 2 + 2?",
                        "A"
                )
        );

        exam.addQuestion(
                new MultipleChoiceQuestion(
                        "What is 3 + 3?",
                        "B"
                )
        );

        Attempt attempt =
                exam.startAttempt(student);

        attempt.answerQuestion(1, "A");
        attempt.answerQuestion(2, "C");

        attempt.submit();

        // This will be rejected because
        // the attempt is already submitted.
        attempt.answerQuestion(1, "B");
    }
}

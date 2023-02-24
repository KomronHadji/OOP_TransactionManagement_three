package transactions;

public class EvaluatedTransaction {
    private String transactionId;
    private int score;

    public EvaluatedTransaction(String transactionId, int score) {
        this.transactionId = transactionId;
        this.score = score;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public int getScore() {
        return score;
    }
}

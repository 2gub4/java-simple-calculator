public class ValueHolder {
    private Double firstValue = null;
    private Double secondValue = null;
    private Double result = null;
    private double memorizedValue = 0;

    ValueHolder() {}

    public void setFirstValue(Double value) { this.firstValue = value; }
    public Double getFirstValue() { return this.firstValue; }

    public void setSecondValue(Double value) { this.secondValue = value; }
    public Double getSecondValue() { return this.secondValue; }

    public void setResult(Double value) { this.result = value; }
    public Double getResult() { return this.result; }

    public void setMemorizedValue(double value, char operator)
    {
        value = operator == '-' ? -value : value;
        this.memorizedValue += value;
    }

    public double getMemorizedValue() {
        return this.memorizedValue;
    }

    public void resetValues() {
        this.setFirstValue(null);
        this.setSecondValue(null);
        this.setResult(null);
        this.memorizedValue = 0;
    }
}

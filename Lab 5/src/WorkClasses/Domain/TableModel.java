package WorkClasses.Domain;

public class TableModel {
    private final int index;
    private final String info;
    private final int parentIndex;
    private final int leftIndex;

    public TableModel(int index, String info, int parentIndex, int leftIndex) {
        this.index = index;
        this.info = info;
        this.parentIndex = parentIndex;
        this.leftIndex = leftIndex;
    }

    @Override
    public String toString() {
        return "WorkClasses.Domain.TableModel{" +
                "index=" + (index+1) +
                ", info='" + info + '\'' +
                ", parentIndex=" + (parentIndex+1) +
                ", leftIndex=" + (leftIndex+1) +
                '}';
    }
}
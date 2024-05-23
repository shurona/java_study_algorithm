package test.domain;

public class User {
    private int id;
    private Long commonId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getCommonId() {
        return commonId;
    }

    public void setCommonId(Long commonId) {
        this.commonId = commonId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", commonId=" + commonId +
                '}';
    }
}

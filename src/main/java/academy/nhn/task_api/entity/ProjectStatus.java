package academy.nhn.task_api.entity;

public enum ProjectStatus {
    ACTIVE("활성"), INACTIVE("휴면"), TERMINATED("종료");
    private String statusName;

    ProjectStatus(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}

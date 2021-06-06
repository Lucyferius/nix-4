package com.alevel.hibernate.model.dto;

import java.util.ArrayList;
import java.util.List;

public class MarksOfGroup {
    private Long groupId;
    private List<Double> marks;

    public MarksOfGroup(Long groupId, List<Double> marks) {
        this.groupId = groupId;
        this.marks = marks;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<Double> getMarks() {
        return marks;
    }

    public void setMarks(List<Double> marks) {
        this.marks = marks;
    }
}

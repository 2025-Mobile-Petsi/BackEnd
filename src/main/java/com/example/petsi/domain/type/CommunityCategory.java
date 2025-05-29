package com.example.petsi.domain.type;

public enum CommunityCategory {
    ADOPTION_REVIEW("입양후기"),
    QUESTION("질문"),
    FREE("자유"),
    MISSING("실종제보"),
    SIGHTING("목격제보");

    private final String label;

    CommunityCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
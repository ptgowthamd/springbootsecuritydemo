package com.test.security.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "RequirementInfo")
public class RequirementInfo {
    @Id
    private String id;
    private String requirementTitle;
    private String requirementId;
    private String requirementVersion;
    private String requirementDescription;
}


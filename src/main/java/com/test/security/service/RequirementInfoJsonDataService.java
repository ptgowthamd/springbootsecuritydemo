package com.test.security.service;

import com.test.security.entity.RequirementInfo;

public interface RequirementInfoJsonDataService {

    public void updateJson(String id, String json);

    public RequirementInfo getRequirementInfoById(String id);

}


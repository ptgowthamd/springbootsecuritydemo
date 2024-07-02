package com.test.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.security.entity.RequirementInfo;
import com.test.security.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RequirementInfoJsonDataServiceImpl implements RequirementInfoJsonDataService{
    @Autowired
    @Qualifier("vehicleDBMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void updateJson(String id, String json) {
        try {
            // Parse JSON to RequirementInfo object
            RequirementInfo requirementInfo = objectMapper.readValue(json, RequirementInfo.class);

            // Create query to find existing document by requirementId
            Query query = new Query(Criteria.where("_id").is(id));

            // Create update object with all fields from requirementInfo
            Update update = new Update();
            update.set("requirementId", requirementInfo.getRequirementId());
            update.set("requirementTitle", requirementInfo.getRequirementTitle());
            update.set("requirementVersion", requirementInfo.getRequirementVersion());
            update.set("requirementDescription", requirementInfo.getRequirementDescription());

            // Upsert document
            mongoTemplate.upsert(query, update, RequirementInfo.class);

            log.debug("saving json: " + json);
        } catch (Exception e) {
            throw new CustomException("failed to save or update requirement: " + e.getMessage());
        }
    }

    @Override
    public RequirementInfo getRequirementInfoById(String id) {
        try {
            Query query = new Query(Criteria.where("_id").is(id));
            RequirementInfo requirementInfo = mongoTemplate.findOne(query, RequirementInfo.class);
            if (requirementInfo == null) {
                return null;
            }
            return requirementInfo;
        }catch (Exception e){
            throw new CustomException("failed to fetch requirement-data by requirementId: " + e.getMessage());
        }
    }
}



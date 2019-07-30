package com.solution.ntq.service.base;
import com.solution.ntq.controller.request.ContentRequest;
import com.solution.ntq.controller.response.ContentResponse;
import com.solution.ntq.repository.entities.Content;

import java.util.List;

/**
 * @author Duc Anh
 */
public interface ContentService {

    void addContent(ContentRequest content, String idToken);

    ContentResponse getContentById(int contentId);

    void updateContent(ContentRequest content);

    List<Content> getPendingItemByClassId(int classId);

    List<ContentResponse> findContentByClassId(int classId);

}
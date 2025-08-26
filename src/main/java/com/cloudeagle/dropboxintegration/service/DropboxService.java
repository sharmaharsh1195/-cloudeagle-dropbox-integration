package com.cloudeagle.dropboxintegration.service;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxTeamClientV2;
import com.dropbox.core.v2.team.TeamGetInfoResult;
import com.dropbox.core.v2.team.MembersListResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DropboxService {

    @Value("${dropbox.access.token}")
    private String accessToken;

    @Value("${app.name:CloudEagleApp/1.0}")
    private String appName;

    private DbxTeamClientV2 getTeamClient() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder(appName).build();
        return new DbxTeamClientV2(config, accessToken);
    }

    // API 1 & 2: Get team info (includes plan/license information)
    public TeamGetInfoResult getTeamInfo() throws Exception {
        DbxTeamClientV2 teamClient = getTeamClient();
        return teamClient.team().getInfo();
    }

    // API 3: List all users in organization
    public MembersListResult listTeamMembers() throws Exception {
        DbxTeamClientV2 teamClient = getTeamClient();
        return teamClient.team().membersList();
    }

}
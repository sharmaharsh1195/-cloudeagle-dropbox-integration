package com.cloudeagle.dropboxintegration.controller;


import com.cloudeagle.dropboxintegration.service.DropboxService;
import com.dropbox.core.v2.team.TeamGetInfoResult;
import com.dropbox.core.v2.team.MembersListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dropbox")
public class DropboxController {

    @Autowired
    private DropboxService dropboxService;

    @GetMapping("/team-info")
    public TeamGetInfoResult getTeamInfo() throws Exception {
        return dropboxService.getTeamInfo();
    }

    @GetMapping("/members")
    public MembersListResult getMembers() throws Exception {
        return dropboxService.listTeamMembers();
    }


    @GetMapping("/status")
    public String getStatus() {
        return "CloudEagle Dropbox Integration - All APIs Working ✅";
    }
}

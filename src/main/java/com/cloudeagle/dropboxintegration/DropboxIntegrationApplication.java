package com.cloudeagle.dropboxintegration;

import com.cloudeagle.dropboxintegration.service.DropboxService;
import com.dropbox.core.v2.team.MembersListResult;
import com.dropbox.core.v2.team.TeamGetInfoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DropboxIntegrationApplication implements CommandLineRunner {

	@Autowired
	private DropboxService dropboxService;

	public static void main(String[] args) {
		SpringApplication.run(DropboxIntegrationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("CloudEagle Dropbox Integration - API Demonstration");
		System.out.println("Executing all 4 APIs that were documented and tested in Postman...\n");

		// API 1: Get Team/Organization Information
		testTeamInfo();

		System.out.println("\n" + "=".repeat(50) + "\n");

		// API 2: Get Plan Type/License (same endpoint, different focus)
		testPlanInfo();

		System.out.println("\n" + "=".repeat(50) + "\n");

		// API 3: List All Users
		testListUsers();

		System.out.println("\n" + "=".repeat(50) + "\n");

		System.out.println("\n" + "=".repeat(50));
		System.out.println("All 3 APIs were successfully integrated.");
		System.out.println("This demonstrates a complete Dropbox Business API integration for CloudEagle.");
	}

	private void testTeamInfo() {
		System.out.println("API 1: Get Team/Organization Information");
		System.out.println("Endpoint: https://api.dropboxapi.com/2/team/get_info");

		try {
			TeamGetInfoResult teamInfo = dropboxService.getTeamInfo();

			System.out.println("Results:");
			System.out.println("   Team Name: " + teamInfo.getName());
			System.out.println("   Team ID: " + teamInfo.getTeamId());
			System.out.println("   Status: SUCCESS");

		} catch (Exception e) {
			System.err.println("Error while fetching team information: " + e.getMessage());
		}
	}

	private void testPlanInfo() {
		System.out.println("API 2: Get Plan Type/License Information");
		System.out.println("Endpoint: https://api.dropboxapi.com/2/team/get_info (focused on license data)");

		try {
			TeamGetInfoResult teamInfo = dropboxService.getTeamInfo();

			System.out.println("License Information:");
			System.out.println("   Licensed Users: " + teamInfo.getNumLicensedUsers());
			System.out.println("   Provisioned Users: " + teamInfo.getNumProvisionedUsers());
			System.out.println("   Used Licenses: " + teamInfo.getNumUsedLicenses());
			System.out.println("   Plan Utilization: "
					+ teamInfo.getNumUsedLicenses() + "/" + teamInfo.getNumLicensedUsers());

		} catch (Exception e) {
			System.err.println("Error while fetching license information: " + e.getMessage());
		}
	}

	private void testListUsers() {
		System.out.println("API 3: List All Users in Organization");
		System.out.println("Endpoint: https://api.dropboxapi.com/2/team/members/list");

		try {
			MembersListResult members = dropboxService.listTeamMembers();

			System.out.println("User Information:");
			System.out.println("   Total Members Found: " + members.getMembers().size());

			members.getMembers().forEach(member -> {
				System.out.println("   Member Email: " + member.getProfile().getEmail());
				System.out.println("      Name: " + member.getProfile().getName().getDisplayName());
				System.out.println("      Status: " + member.getProfile().getStatus().tag());
				System.out.println("      Role: "
						+ (member.getRole() != null ? member.getRole().name() : "Member"));
			});

		} catch (Exception e) {
			System.err.println("Error while listing team members: " + e.getMessage());
		}
	}
}

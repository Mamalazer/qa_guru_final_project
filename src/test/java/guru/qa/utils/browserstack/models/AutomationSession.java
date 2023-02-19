package guru.qa.utils.browserstack.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AutomationSession {

	@JsonProperty("reason")
	private String reason;

	@JsonProperty("os")
	private String os;

	@JsonProperty("app_details")
	private AppDetails appDetails;

	@JsonProperty("os_version")
	private String osVersion;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("browser_url")
	private String browserUrl;

	@JsonProperty("hashed_id")
	private String hashedId;

	@JsonProperty("project_name")
	private String projectName;

	@JsonProperty("build_name")
	private String buildName;

	@JsonProperty("duration")
	private Integer duration;

	@JsonProperty("public_url")
	private String publicUrl;

	@JsonProperty("session_terminal_logs_url")
	private String sessionTerminalLogsUrl;

	@JsonProperty("video_url")
	private String videoUrl;

	@JsonProperty("browserstack_status")
	private String browserstackStatus;

	@JsonProperty("browser")
	private Object browser;

	@JsonProperty("device_logs_url")
	private String deviceLogsUrl;

	@JsonProperty("name")
	private String name;

	@JsonProperty("build_terminal_logs_url")
	private String buildTerminalLogsUrl;

	@JsonProperty("browser_version")
	private String browserVersion;

	@JsonProperty("appium_logs_url")
	private String appiumLogsUrl;

	@JsonProperty("device")
	private String device;

	@JsonProperty("logs")
	private String logs;

	@JsonProperty("status")
	private String status;

	@JsonProperty("build_hashed_id")
	private String buildHashedId;
}
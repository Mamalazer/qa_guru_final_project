package guru.qa.utils.browserstack.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AppDetails {

	@JsonProperty("app_custom_id")
	private Object appCustomId;

	@JsonProperty("app_name")
	private String appName;

	@JsonProperty("app_url")
	private String appUrl;

	@JsonProperty("app_version")
	private String appVersion;

	@JsonProperty("uploaded_at")
	private String uploadedAt;
}
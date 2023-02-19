package guru.qa.utils.browserstack.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BrowserStackSessionInfo {

	@JsonProperty("automation_session")
	private AutomationSession automationSession;
}
package im.wity.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TermsOfCondType {
    MARKETING("marketing",false),
    INFORMATION_USAGE("informationUsage",true),
    SERVICE_USAGE("serviceUsage",true);

    private final String value;
    private final boolean isRequired;

}

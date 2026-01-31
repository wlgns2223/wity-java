package im.wity.constant;

public enum AuthProvider {
    LOCAL("local"),
    KAKAO("kakao")
    ;

    private String value;
    AuthProvider(String value) {this.value = value; }

    public boolean isOauth(){
        return this != LOCAL;
    }
}

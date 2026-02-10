package im.wity.fixture;

import im.wity.entity.NameCard;

import java.util.LinkedHashSet;

public class NameCardFixture {
    public static NameCard create(){
        return NameCard.builder().blocks(new LinkedHashSet<>()).build();
    }
}
